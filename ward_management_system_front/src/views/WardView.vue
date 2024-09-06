<template>
    <el-card style="max-width: 600px">
        <el-button type="success" style="margin-bottom: 10px;" @click="showAddDialog">添加</el-button>
        <el-table :data="sortedWardList" border style="width: 100%">
            <!-- 序号列 -->
            <el-table-column label="序号" width="60px">
                <template #default="scope">
                    {{ scope.$index + 1 }}
                </template>
            </el-table-column>
            <!-- <el-table-column prop="wid" label="ID" width="50px" /> -->
            <el-table-column prop="wnumber" label="病房号" />
            <el-table-column prop="did" label="所属科室" />
            <el-table-column label="操作">
                <template #default="scope">
                    <el-button type="primary" size="small" @click="selectBywid(scope.row.wid)" round>修改</el-button>
                    <el-popconfirm title="你确定要删除该病房吗？" confirm-button-text="确认" cancel-button-text="取消" width="200px"
                        @confirm="deleteByWid(scope.row.wid)">
                        <template #reference>
                            <el-button size="small" type="danger" round>删除</el-button>
                        </template>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>
    </el-card>

    <!-- 添加对话框开始 -->
    <el-dialog v-model="addDialogShow" title="添加病房" width="500">
        <el-form>
            <el-form-item label="所属科室" label-width="20%">
                <el-select v-model="wardAdd.did" placeholder="请选择科室" style="width: 300px;">
                    <el-option v-for="department in departmentList" :key="department.did"
                        :label="`${department.dname} (${department.did})`" :value="department.did" />
                </el-select>
            </el-form-item>
            <el-form-item label="病房号" label-width="20%">
                <el-input v-model="wardAdd.wnumber" autocomplete="off" />
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
    <el-dialog v-model="updateDialogShow" title="修改病房" width="500">
        <el-form> 
            <el-form-item label="病房号" label-width="20%">
                <el-input v-model="wardUpdate.wnumber" autocomplete="off" />
            </el-form-item>
            <el-form-item label="所属科室" label-width="20%">
                <el-input v-model="wardUpdate.did" autocomplete="off" />
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
import wardApi from '@/api/wardApi';
import departmentApi from '@/api/departmentApi';
import { ref, computed } from 'vue';
import { ElMessage } from 'element-plus';

//所有科室
const departmentList = ref([]);
const wardList = ref([]);

const wardAdd = ref({
    wnumber: '',
    did: ''
});

const wardUpdate = ref({
    wid: '',
    wnumber: '',
    did: ''
});

const addDialogShow = ref(false);
const updateDialogShow = ref(false);
function selectAll() {
    wardApi.selectAll()
        .then(resp => {
            wardList.value = resp.data;
        });

}

//查询所有科室并显示添加对话框
function showAddDialog() {
    departmentApi.selectAll()
        .then(resp => {
            departmentList.value = resp.data;
            addDialogShow.value = true;
        });
}

// sortedBedList 处理 bedList，按 bnumber 数字部分升序排序
const sortedWardList = computed(() => {
    return [...wardList.value].sort((a, b) => {
        // 提取病床号中数字部分进行排序
        const numA = a.wnumber.split('-').map(Number); // '101-1' => [101, 1]
        const numB = b.wnumber.split('-').map(Number); // '101-2' => [101, 2]
        if (numA[0] === numB[0]) {
            return numA[1] - numB[1]; // 比较病床号的后缀数字
        }
        return numA[0] - numB[0]; // 比较病房号
    });
});

//定义方法完成病房添加
function insert() {
    wardApi.insert(wardAdd.value)
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
                wardAdd.value = {
                    dname: '',
                    dlocation: ''
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

//定义方法完成病房修改
function update() {
    wardApi.update(wardUpdate.value)
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

function selectBywid(wid) {
    wardApi.selectByWid(wid)
        .then(resp => {
            wardUpdate.value = resp.data;
            // 显示修改对话框
            updateDialogShow.value = true;
        });
}


//定义方法根据部门id删除病房
function deleteByWid(wid) {
    wardApi.delete(wid)
        .then(resp => {
            console.log(resp);

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