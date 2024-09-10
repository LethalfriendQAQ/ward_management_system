<template>
    <el-col :span="24">
        <el-card>
            <el-button type="success" style="margin-bottom: 10px;" @click="addDialogShow = true;">添加</el-button>
            <el-table :data="pageInfo.list" border style="width: 100%">
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
                                <el-table-column property="dname" label="护士部门" />
                            </el-table>
                        </el-popover>
                        <el-button size="small" type="primary" @click="selectByDutyId(scope.row.dutyId)">修改</el-button>
                        <el-popconfirm title="你确定要删除该值班安排吗？" confirm-button-text="确认" cancel-button-text="取消"
                            width="200px" @confirm="deleteByDutyId(scope.row.dutyId)">
                            <template #reference>
                                <el-button size="small" type="danger">删除</el-button>
                            </template>
                        </el-popconfirm>
                    </template>
                </el-table-column>
            </el-table>
          <el-row class="row-bg" justify="center">
            <el-pagination background layout="prev, pager, next" :total="pageInfo.total"
                           :page-size="pageInfo.pageSize" @change="selectByPage" />
          </el-row>
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
                    <el-time-select v-model="dutyAdd.dutyWorkTime" style="width: 240px" start="08:30" step="00:30" end="22:30"
                        placeholder="上班打卡时间" />
                </template>
            </el-form-item>
            <el-form-item label="下班时间" label-width="20%">
                <template #default="scope">
                    <el-time-select v-model="dutyAdd.dutyClosingTime" style="width: 240px" start="08:30" step="00:30" end="22:30"
                        placeholder="下班打卡时间" />
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

    <!-- 修改对话框开始 -->
    <el-dialog v-model="updateDialogShow" title="修改值班" width="500">
        <el-form>
            <el-form-item label="值班时间" label-width="20%">
                <el-input v-model="dutyUpdate.dutyDate" autocomplete="off" />
            </el-form-item>
            <el-form-item label="上班时间" label-width="20%">
                <template #default="scope">
                    <el-time-select v-model="dutyUpdate.dutyWorkTime" style="width: 240px" start="08:30" step="00:30" end="22:30"
                        placeholder="上班打卡时间" />
                </template>
            </el-form-item>
            <el-form-item label="下班时间" label-width="20%">
                <template #default="scope">
                    <el-time-select v-model="dutyUpdate.dutyClosingTime" style="width: 240px" start="08:30" step="00:30" end="22:30"
                        placeholder="下班打卡时间" />
                </template>
            </el-form-item>
            <el-form-item label="任务进度" label-width="20%">
                <el-input v-model="dutyUpdate.dutyTaskProgress" autocomplete="off" />
            </el-form-item>
            <el-form-item label="值班详情" label-width="20%">
                <el-input v-model="dutyUpdate.dutyDetails" autocomplete="off" />
            </el-form-item>
        </el-form>
        <template #footer>
            <div class="dialog-footer">
                <el-button @click="updateDialogShow = false">取消</el-button>
                <el-button type="primary" @click="update">确认</el-button>
            </div>
        </template>
    </el-dialog>
    <!-- 修改对话框结束 -->


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
import patientApi from "@/api/patientApi.js";


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

const updateDialogShow = ref(false);

const dutyAdd = ref({
    dutyDate: '',
    dutyWorkTime: '',
    dutyClosingTime: '',
    dutyTaskProgress: '',
    dutyDetails: '',
    nurses: ''
})
const dutyUpdate = ref({
    dutyId: '',
    dutyDate: '',
    dutyWorkTime: '',
    dutyClosingTime: '',
    dutyTaskProgress: '',
    dutyDetails: '',
    nurses: ''
})

const pageInfo = ref({
  total: 0,
  pageSize: 0
});
let pageNow;
function selectByPage(pageNum) {
  dutyApi.selectByPage(pageNum)
      .then(resp => {
        pageNow = pageNum;
        console.log(pageNow);
        pageInfo.value = resp.data;
        console.log(resp);
      });
}

//查询修改的id并显示修改对话框
function selectByDutyId(dutyId) {
    dutyApi.selectByDutyId(dutyId)
        .then(resp => {
            dutyUpdate.value = resp.data;
            //显示修改对话框
            updateDialogShow.value = true
        })
}

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

//定义方法完成值班修改
function update() {
    dutyApi.update(dutyUpdate.value)
        .then(resp => {
            console.log(resp);
            if (resp.code == 10000) {
                //弹出消息
                ElMessage({
                    message: resp.msg,
                    type: 'success',
                    duration: 1200
                });
                //隐藏对话框
                updateDialogShow.value = false;
                //刷新表格数据
                selectByPage(pageNow);
            } else {
                //弹出消息
                ElMessage({
                    message: resp.msg,
                    type: 'error',
                    duration: 2000
                });
            }
        });
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
            //弹出消息
            ElMessage.success(resp.msg);
            //隐藏分配员工的对话框
            setDutyDialogShow.value = false;
            selectAll();
        });
}

//定义方法完成值班安排删除
function deleteByDutyId(dutyId) {
    dutyApi.delete(dutyId) 
        .then(resp => {
            //判断-弹出消息-刷新表格
            if (resp.code == 10000) {
                //弹出消息
                ElMessage.success(resp.msg);
                selectAll();
            } else {
                //弹出消息
                ElMessage.error(resp.msg);
            }
        });
}

//默认查询首页
selectByPage(1);
</script>
<style scoped>
.demo-range .el-date-editor {
    margin: 8px;
}

.demo-range .el-range-separator {
    box-sizing: content-box;
}
</style>