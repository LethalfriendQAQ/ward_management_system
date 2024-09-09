<template>
    <el-card style="max-width: 850px">
        <el-button type="success" style="margin-bottom: 10px;" @click="addDialogShow = true;">添加</el-button>
        <el-table :data="sortedBedList" border style="width: 100%">
            <!-- 序号列 -->
            <el-table-column label="序号" width="60px">
                <template #default="scope">
                    {{ scope.$index + 1 }}
                </template>
            </el-table-column>
            <!-- <el-table-column prop="bid" label="ID" width="50px" /> -->
            <el-table-column prop="bnumber" label="病床号" sortable="custom" />
            <el-table-column prop="pno" label="患者编号">
                <template #default="scope">
                    {{ scope.row.pno ? scope.row.pno : '空' }}
                </template>
            </el-table-column>
            <el-table-column prop="nno" label="负责护士">
                <template #default="scope">
                    {{ scope.row.nno ? scope.row.nno : '空' }}
                </template>
            </el-table-column>
            <el-table-column prop="wnumber" label="病房号" />
            <el-table-column label="操作">
                <template #default="scope">
                    <el-button type="primary" size="small" @click="selectByBid(scope.row.bid)" round>修改</el-button>
                    <el-popconfirm title="你确定要删除该科室吗？" confirm-button-text="确认" cancel-button-text="取消" width="200px"
                        @confirm="deleteByBid(scope.row.bid)">
                        <template #reference>
                            <el-button size="small" type="danger" round>删除</el-button>
                        </template>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>
    </el-card>

    <!-- 添加对话框开始 -->
    <el-dialog v-model="addDialogShow" title="添加病床" width="500">
        <el-form>
            <el-form-item label="病房号" label-width="20%">
                <el-input v-model="bedAdd.wnumber" autocomplete="off" />
            </el-form-item>
            <el-form-item label="病床号" label-width="20%">
                <el-input v-model="bedAdd.bnumber" autocomplete="off" />
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
    <el-dialog v-model="updateDialogShow" title="修改病床" width="500">
        <el-form>
            <el-form-item label="病床号" label-width="20%">
                <el-input v-model="bedUpdate.bnumber" autocomplete="off" />
            </el-form-item>
            <el-form-item label="患者编号" label-width="20%">
                <el-input v-model="bedUpdate.pno" disabled="true" autocomplete="off" />
            </el-form-item>
            <!-- <el-form-item label="负责护士" label-width="20%">
                <el-input v-model="bedUpdate.nno" autocomplete="off" />
            </el-form-item> -->
            <el-form-item label="护士编号" label-width="20%">
                <el-select v-model="bedUpdate.nno" placeholder="请选择护士" style="width: 300px;">
                    <el-option v-for="(nurse, index) in nurseList" :key="index" :label="nurse.nno" :value="nurse.nno" />
                </el-select>
            </el-form-item>
            <el-form-item label="病房号" label-width="20%">
                <el-input v-model="bedUpdate.wnumber" autocomplete="off" />
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
</template>
<script setup>
import bedApi from '@/api/bedApi';
import { ref, computed } from 'vue';
import { ElMessage } from 'element-plus';
import departmentApi from '@/api/departmentApi';
import wardApi from '@/api/wardApi';
import nurseApi from '@/api/nurseApi';


const nurseList = ref([]);
const bedList = ref([]);
const addDialogShow = ref(false);
const bedAdd = ref({
    bnumber: '',
    wnumber: ''
});
const updateDialogShow = ref(false);
const bedUpdate = ref({
    bid: '',
    bnumber: '',
    pno: '',
    nno: '',
    wnumber: ''
});

// sortedBedList 处理 bedList，按 bnumber 数字部分升序排序
const sortedBedList = computed(() => {
    return [...bedList.value].sort((a, b) => {
        // 提取病床号中数字部分进行排序
        const numA = a.bnumber.split('-').map(Number); // '101-1' => [101, 1]
        const numB = b.bnumber.split('-').map(Number); // '101-2' => [101, 2]
        if (numA[0] === numB[0]) {
            return numA[1] - numB[1]; // 比较病床号的后缀数字
        }
        return numA[0] - numB[0]; // 比较病房号
    });
});

//定义方法完成病床添加
function insert() {
    bedApi.insert(bedAdd.value)
        .then(resp => {
            if (resp.code == 10000) {
                ElMessage({
                    message: resp.msg,
                    type: 'success',
                    duration: 1200
                });
                //隐藏对话框
                addDialogShow.value = false;
                //清空对话框中的数据
                bedApi.value = {
                    bnumber: '',
                    wnumber: ''
                }
                //刷新表格数据
                selectAll();
            } else {
                ElMessage({
                    message: resp.msg,
                    type: 'error',
                    duration: 2000
                });
            }

        });
}

//定义方法完成病床修改
function update() {
    bedApi.update(bedUpdate.value)
        .then(resp => {
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
                selectAll();
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

// function selectByBid(bid) {
//     bedApi.selectByBid(bid)
//         .then(resp => {
//             bedUpdate.value = resp.data;
//             //显示修改对话框
//             updateDialogShow.value = true
//         })
// }

function selectByBid(bid) {
    bedApi.selectByBid(bid)
        .then(resp => {
            const bedData = resp.data;
            bedUpdate.value = bedData;
            //获取在病床所在科室的信息
            wardApi.selectByWnumber(bedData.wnumber)
                .then(wardResp => {
                    const did = wardResp.data.did;
                    //获取该科室的护士列表
                    nurseApi.selectByDid(did)
                        .then(nurseResp => {
                            nurseList.value = nurseResp.data;

                            //显示修改对话框
                            updateDialogShow.value = true;
                        });
                });
        });
}


function selectAll() {
    bedApi.selectAll()
        .then(resp => {
            bedList.value = resp.data;
        })
}

//定义方法根据部门id删除病房
function deleteByBid(bid) {
    bedApi.delete(bid)
        .then(resp => {
            //判断-弹出消息-刷新表格
            if (resp.code == 10000) {
                //弹出消息
                ElMessage({
                    message: resp.msg,
                    type: 'success',
                    duration: 1200
                });
                //刷新表格
                selectAll();
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

selectAll();
</script>
<style scoped></style>