<template>
    <el-card style="max-width: 600px">
        <el-button type="success" style="margin-bottom: 10px;" @click="addDialogShow = true;">添加</el-button>
        <el-table :data="departmentlist" border style="width: 100%">
            <el-table-column prop="did" label="ID" width="50px" />
            <el-table-column prop="dname" label="科室名称" />
            <el-table-column prop="dlocation" label="科室位置" />
            <el-table-column label="操作">
                <template #default="scope">
                    <el-button type="primary" size="small" @click="selectBydid(scope.row.did)" round>修改</el-button>
                    <el-popconfirm title="你确定要删除该科室吗？" confirm-button-text="确认" cancel-button-text="取消"
                            width="200px" @confirm="deleteByDid(scope.row.did)">
                            <template #reference>
                                <el-button size="small" type="danger" round>删除</el-button>
                            </template>
                        </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>
    </el-card>

    <!-- 添加对话框开始 -->
    <el-dialog v-model="addDialogShow" title="添加科室" width="500">
        <el-form>
            <el-form-item label="科室名称" lable-width="20%">
                <el-input v-model="departmentAdd.dname" autocomplete="off" />
            </el-form-item>
            <el-form-item label="科室位置" lable-width="20%">
                <el-input v-model="departmentAdd.dlocation" autocomplete="off" />
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
    <el-dialog v-model="updateDialogShow" title="添加部门" width="500">
        <el-form>
            <el-form-item label="部门名称" lable-width="20%">
                <el-input v-model="departmentUpdate.dname" autocomplete="off" />
            </el-form-item>
            <el-form-item label="部门位置" lable-width="20%">
                <el-input v-model="departmentUpdate.dlocation" autocomplete="off" />
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
import departmentApi from '@/api/departmentApi';
import { reactive, ref } from 'vue';
import { ElMessage } from 'element-plus';

const departmentlist = ref([]);
//添加对话框是否显示
const addDialogShow = ref(false);
//修改对话框是否显示
const updateDialogShow = ref(false);

//被添加科室的信息
const departmentAdd = ref({
    dname: '',
    dlocation: ''
});

//被修改部门的信息
const departmentUpdate = ref({
    did: 0,
    dname: '',
    dlocation: ''
});

//修改
function selectBydid(did) {
    departmentApi.selectById(did)
        .then(resp => {
            departmentUpdate.value = resp.data;
            //显示修改对话框
            updateDialogShow.value = true
        })
}



//定义方法完成部门修改
function update() {
    departmentApi.update(departmentUpdate.value)
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

//定义方法完成部门添加
function insert() {
    departmentApi.insert(departmentAdd.value)
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
                departmentAdd.value = {
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


function selectAll() {
    departmentApi.selectAll()
        .then(resp => {
            departmentlist.value = resp.data;

        })
}

//定义方法根据部门id删除部门
function deleteByDid(did) {
    departmentApi.delete(did)
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