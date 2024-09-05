<template>
    <el-card style="max-width: 600px">
        <el-button type="success" style="margin-bottom: 10px;" @click="showAddDialog">添加</el-button>
        <el-table :data="wardList" border style="width: 100%">
            <el-table-column prop="wid" label="ID" width="50px" />
            <el-table-column prop="wnumber" label="病房号" />
            <el-table-column prop="did" label="所属科室" />
            <el-table-column label="操作">
                <template #default="scope">
                    <el-button type="primary" size="small" @click="" round>修改</el-button>
                    <el-popconfirm title="你确定要删除该病房吗？" confirm-button-text="确认" cancel-button-text="取消" width="200px"
                        @confirm="">
                        <template #reference>
                            <el-button size="small" type="danger" round>删除</el-button>
                        </template>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>
    </el-card>
</template>
<script setup>
import wardApi from '@/api/wardApi';
import departmentApi from '@/api/departmentApi';
import { ref } from 'vue';
import { ElMessage } from 'element-plus';

const wardList = ref([]);

function selectAll() {
    wardApi.selectAll()
        .then(resp => {
            wardList.value = resp.data; 
        });
    
}

selectAll();
</script>
<style scoped>

</style>