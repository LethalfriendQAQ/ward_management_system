<template>
    <el-col :span="24">
        <el-card>
            <el-button type="success" style="margin-bottom: 10px;" @click="addDialogShow = true;">添加</el-button>
            <el-table :data="dutyList" border style="width: 100%">
                <el-table-column prop="dutyId" label="ID" width="50px" />
                <el-table-column prop="dutyDate" label="值班日期" />
                <el-table-column prop="dutyWorkTime" label="上班时间" />
                <el-table-column prop="dutyClosingTime" label="下班时间" />
                <el-table-column label="任务进度">
                    <template #default="scope">
                        <el-progress :percentage="scope.row.dutyTaskProgress" />
                    </template>
                </el-table-column>
                <el-table-column prop="dutyDetails" label="值班详情" />
                <el-table-column label="操作">
                    <template #default="scope">
                        <el-popover placement="right" :width="400" trigger="hover">
                            <template #reference>
                                <el-button size="small" type="success"
                                    @click="showSetDutyDialog(scope.row.dutyId)">分配护士</el-button>
                            </template>
                            <el-table :data="scope.row.nurses">
                                <el-table-column property="nno" label="护士工号" />
                                <el-table-column property="nname" label="护士姓名" />
                                <el-table-column property="department.dname" label="护士部门" />
                            </el-table>
                        </el-popover>
                        <el-button size="small" type="primary">修改</el-button>
                        <el-popconfirm title="你确定要删除该值班安排吗？" confirm-button-text="确认" cancel-button-text="取消"
                            width="200px">
                            <template #reference>
                                <el-button size="small" type="danger">删除</el-button>
                            </template>
                        </el-popconfirm>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>
    </el-col>

    <!-- 添加对话框开始 -->
    <el-dialog v-model="addDialogShow" title="添加值班" width="500">
        <el-form>
            <el-form-item label="值班时间" label-width="20%">
                <el-input v-model="dutyAdd.dutyDate" autocomplete="off" />
            </el-form-item>
            <el-form-item label="上班时间" label-width="20%">
                <template #default="scope">
                    <div class="example-basic">
                        <el-time-picker v-model="dutyAdd.dutyWorkTime" arrow-control placeholder="上班打卡时间" />
                    </div>
                </template>
            </el-form-item>
            <el-form-item label="下班时间" label-width="20%">
                <template #default="scope">
                    <div class="example-basic">
                        <el-time-picker v-model="dutyAdd.dutyClosingTime" arrow-control placeholder="下班打卡时间" />
                    </div>
                </template>
            </el-form-item>
            <el-form-item label="任务进度" label-width="20%">
                <el-input v-model="dutyAdd.dutyTaskProgress" autocomplete="off" />
            </el-form-item>
            <el-form-item label="值班详情" label-width="20%">
                <el-input v-model="dutyAdd.dutyDetails" autocomplete="off" />
            </el-form-item>
        </el-form>
        <template #footer>
            <div class="dialog-footer">
                <el-button @click="addDialogShow = false">取消</el-button>
                <el-button type="primary" @click="insert">确认</el-button>
            </div>
        </template>
    </el-dialog>
    <!-- 添加对话框结束 -->


    <!-- 分配护士对话框开始 -->
    <el-dialog v-model="setDutyDialogShow" title="添加护士">
        <el-transfer v-model="selectNids" :data="allNurse" />
        <template #footer>
            <div class="dialog-footer">
                <el-button @click="setDutyDialogShow = false">取消</el-button>
                <el-button type="primary" @click="insertDutyIdAndNid">确认</el-button>
            </div>
        </template>
    </el-dialog>
    <!-- 分配护士对话框结束 -->

</template>
<script setup>
import { reactive, ref } from 'vue';
import { ElMessage } from 'element-plus';
import dutyApi from '@/api/dutyApi';

const value1 = ref < [Date, Date] > ([
    new Date(2016, 9, 10, 8, 40),
    new Date(2016, 9, 10, 9, 40),
])
const value2 = ref < [Date, Date] > ([
    new Date(2016, 9, 10, 8, 40),
    new Date(2016, 9, 10, 9, 40),
])

const dutyList = ref([]);
//是否展示分配护士对话框
const setNurseDialogShow = ref(false);
const allNurse = ref([]);
//被选中护士的nid
const selectNids = ref([]);
//需要分配护士值班安排的ID
const selectDutyId = ref([]);
//是否展示分配护士对话框
const setDutyDialogShow = ref(false);

const addDialogShow = ref(false);

const dutyAdd = ref({
    dutyDate: '',
    dutyWorkTime: '',
    dutyClosingTime: '',
    dutyTaskProgress: '',
    dutyDetails: '',
    nurses: ''
})

//定义方法完成值班安排添加
function insert() {
    dutyApi.insert()
        .then(resp => {
            if (resp.code == 10000) {
                ElMessage.success(resp.msg);
                //隐藏对话框
                addDialogShow.value = false;
                //清空对话框中的数据
                dutyAdd.value = {
                    dutyDate: '',
                    dutyWorkTime: '',
                    dutyClosingTime: '',
                    dutyTaskProgress: '',
                    dutyDetails: '',
                    nurses: ''
                }
                //刷新表格数据
                selectAll();
            } else {
                ElMessage.error(resp.msg);
            }
        })
}

//查询所有值班安排的信息
function selectAll() {
    dutyApi.selectAll()
        .then(resp => {
            dutyList.value = resp.data;
        })
}

function showSetDutyDialog(dutyId) {
    dutyApi.allNurse(dutyId)
        .then(resp => {
            allNurse.value = resp.data.allNurse;
            selectNids.value = resp.data.selectNids;
            selectDutyId.value = dutyId;
            setDutyDialogShow.value = true;
        })
        .catch(error => {
            console.error('请求失败', error);
        });
}

function insertDutyIdAndNid() {
    dutyApi.insertDutyIdAndNid(selectDutyId.value, selectNids.value)
        .then(resp => {
            console.log(resp);
            //弹出消息
            ElMessage.success(resp.msg);
            //隐藏分配员工的对话框
            setDutyDialogShow.value = false;
            selectAll();
        });
}

selectAll();
</script>
<style scoped>
.demo-range .el-date-editor {
    margin: 8px;
}

.demo-range .el-range-separator {
    box-sizing: content-box;
}
</style>