<template>
    <el-card style="max-width: 800px">
        <el-button type="success" style="margin-bottom: 10px;" @click="">添加</el-button>
        <el-table :data="pageInfo.list" border style="width: 100%">
            <el-table-column prop="nid" label="ID" width="50px" />
            <el-table-column prop="nno" label="编号" />
            <el-table-column prop="nname" label="姓名" />
            <el-table-column prop="did" label="所属科室" />
            <el-table-column label="操作">
                <template #default="scope">
                    <el-button type="primary" size="small" @click="" round>修改</el-button>
                    <el-popconfirm title="你确定要删除该科室吗？" confirm-button-text="确认" cancel-button-text="取消"
                            width="200px" @confirm="">
                            <template #reference>
                                <el-button size="small" type="danger" round>删除</el-button>
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
</template>
<script setup>
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

//分页显示
function selectByPage(pageNum) {
    nurseApi.selectByPage(pageNum, nname.value)
        .then(resp => {
            pageInfo.value = resp.data;
            pageNow = resp.data.pageNum;
        });
}

function selectAll() {
    nurseApi.selectAll()
        .then(resp => {
            console.log(resp);
            
            nurseList.value = resp.data;
        })
}


//默认查询首页
selectByPage(1);
</script>
<style scoped>

</style>