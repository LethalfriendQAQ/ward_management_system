<template>
    <el-card style="max-width: 800px">
        <el-button type="success" style="margin-bottom: 10px;" @click="addDialogShow = true;">添加</el-button>
        <el-table :data="bedList" border style="width: 100%">
            <el-table-column prop="bid" label="ID" width="50px" />
            <el-table-column prop="bnumber" label="病床号" />
            <el-table-column prop="pno" label="患者编号" />
            <el-table-column prop="nno" label="负责护士" />
            <el-table-column prop="wnumber" label="病房号" />
            <el-table-column label="操作">
                <template #default="scope">
                    <el-button type="primary" size="small" @click="selectByBid(scope.row.bid)" round>修改</el-button>
                    <el-popconfirm title="你确定要删除该科室吗？" confirm-button-text="确认" cancel-button-text="取消"
                            width="200px" @confirm="deleteByWid(scope.row.did)">
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
            <el-form-item label="病床号" label-width="20%">
                <el-input v-model="bedAdd.bnumber" autocomplete="off" />
            </el-form-item>
            <el-form-item label="病房号" label-width="20%">
                <el-input v-model="bedAdd.wnumber" autocomplete="off" />
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
                <el-input v-model="bedUpdate.pno" autocomplete="off" />
            </el-form-item>
            <el-form-item label="负责护士" label-width="20%">
                <el-input v-model="bedUpdate.nno" autocomplete="off" />
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
import { ref } from 'vue';
import { ElMessage } from 'element-plus';

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

function selectByBid(bid) {
    bedApi.selectByBid(bid)
        .then(resp => {
            bedUpdate.value = resp.data;
            //显示修改对话框
            updateDialogShow.value = true
        })
}

function selectAll() {
    bedApi.selectAll()
        .then(resp => {
            bedList.value = resp.data;
        })
}

selectAll();
</script>
<style scoped>

</style>