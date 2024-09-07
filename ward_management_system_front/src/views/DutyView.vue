<template>
    <el-col :span="24">
        <el-card>
            <el-button type="success" style="margin-bottom: 10px;">添加</el-button>
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
                                <el-button size="small" type="success" @click="showSetEmployeeDialog(scope.row.pid)">分配护士</el-button>
                            </template>
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
</template>
<script setup>
import { reactive, ref } from 'vue';
import { ElMessage } from 'element-plus';
import dutyApi from '@/api/dutyApi';


const dutyList = ref([]);
//是否展示分配护士对话框
const setNurseDialogShow = ref(false);
const allNurse = ref([]);
//被选中护士的nid


//查询所有值班安排的信息
function selectAll() {
    dutyApi.selectAll()
        .then(resp => {
            dutyList.value = resp.data;
        })
}

selectAll();
</script>
<style scoped>

</style>