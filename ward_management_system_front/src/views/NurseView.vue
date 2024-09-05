<template>
    <el-card style="max-width: 800px">
        <el-button type="success" style="margin-bottom: 10px;" @click="showAddDialog">添加</el-button>
        <el-table :data="pageInfo.list" border style="width: 100%">
            <el-table-column prop="nid" label="ID" width="50px" />
            <el-table-column prop="nno" label="编号" />
            <el-table-column prop="nname" label="姓名" />
            <el-table-column prop="did" label="所属科室" />
            <el-table-column label="操作">
                <template #default="scope">
                    <el-button type="primary" size="small" @click="" round>修改</el-button>
                    <el-popconfirm title="你确定要删除该科室吗？" confirm-button-text="确认" cancel-button-text="取消" width="200px"
                        @confirm="">
                        <template #reference>
                            <el-button size="small" type="danger" round>删除</el-button>
                        </template>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>
        <el-row class="row-bg" justify="center">
            <el-pagination background layout="prev, pager, next" :total="pageInfo.total" :page-size="pageInfo.pageSize"
                @change="selectByPage" />
        </el-row>
    </el-card>


    <!-- 添加对话框开始 -->
    <el-dialog v-model="addDialogShow" title="添加护士" width="500">
        <el-form>
            <el-form-item label="编号" label-width="20%">
                <el-input v-model="nurseAdd.nno" autocomplete="off" />
            </el-form-item>
            <el-form-item label="姓名" label-width="20%">
                <el-input v-model="nurseAdd.nname" autocomplete="off" />
            </el-form-item>
            <el-form-item label="所属科室" label-width="20%">
                <el-select v-model="nurseAdd.did" placeholder="请选择科室" style="width: 300px;">
                    <el-option v-for="(department, index) in departmentList" :key="index" :label="department.dname"
                        :value="department.did" />
                </el-select>
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

</template>
<script setup>
import departmentApi from '@/api/departmentApi';
import nurseApi from '@/api/nurseApi';
import { ref } from 'vue';
import { ElMessage } from 'element-plus';

let pageNow;
const nname = ref('');
//分页信息
const pageInfo = ref({
    total: 0,
    pageSize: 0
});

const nurseList = ref([]);

//所有科室
const departmentList = ref([]);

const nurseAdd = ref({
    nno: '',
    nname: '',
    did: ''
});
const addDialogShow = ref(false);

//分页显示
function selectByPage(pageNum) {
    nurseApi.selectByPage(pageNum, nname.value)
        .then(resp => {
            pageInfo.value = resp.data;
            pageNow = resp.data.pageNum;
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

function selectAll() {
    nurseApi.selectAll()
        .then(resp => {
            nurseList.value = resp.data;
        })
}

//定义方法完成护士添加
function insert() {
    nurseApi.insert(nurseAdd.value)
        .then(resp => {
            if (resp.code == 10000) {
                //弹出消息
                ElMessage.success(resp.msg);
                //隐藏对话框
                addDialogShow.value = false;
                //清空对话框中的数据
                nurseAdd.value = {
                    nno: '',
                    nname: '',
                    did: ''
                }

                //刷新表格数据
                selectByPage(pageNow);
            } else {
                //弹出消息
                ElMessage.error(resp.msg);
            }
        });
}

//默认查询首页
selectByPage(1);
</script>
<style scoped></style>