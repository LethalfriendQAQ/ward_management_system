<template>
    <el-col :span="24">
        <el-card>
            <!-- <el-button type="primary" @click="showAddDialog">添加</el-button> -->
            <el-form :inline="true" :model="formInline" class="demo-form-inline">
                <el-form-item>
                    <el-button type="primary" @click="showAddDialog">添加</el-button>
                </el-form-item>
                <el-form-item style="float: right;">
                    <el-radio-group v-model="radio1" @change="">
                        <el-radio-button label="所有" value="" />
                        <el-radio-button label="住院中" value="" />
                        <el-radio-button label="已出院" value="" />
                    </el-radio-group>
                </el-form-item>
                <el-form-item style="float: right;">
                    <el-input v-model="pname" placeholder="搜索对应科室下的患者" @input="" />
                </el-form-item>
                <el-form-item style="float: right;">
                    <el-input v-model="pname" placeholder="请输入要搜索的姓名" @input="selectByPage(1);" />
                </el-form-item>

            </el-form>
            <el-table :data="pageInfo.list" border style="width: 100%">
                <el-table-column prop="pid" label="ID" width="50px" />
                <el-table-column prop="pno" label="编号" />
                <el-table-column prop="pname" label="姓名" />
                <el-table-column prop="page" label="年龄" width="70px" />
                <el-table-column prop="pgender" label="性别" width="70px" />
                <el-table-column prop="padmissiondate" label="入院时间" />
                <el-table-column prop="pleavedate" label="出院时间" />
                <el-table-column prop="pstatus" label="住院状态" width="90px">
                    <template #default="scope">
                        <el-tag type="primary" v-if="scope.row.pstatus == 1">住院中</el-tag>
                        <el-tag type="info" v-else>已出院</el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="ptelephone" label="电话" />
                <el-table-column prop="did" label="所属科室">
                    <template #default="scope">
                        <!-- 查找当前行科室ID对应的科室名称 -->
                        {{ getDepartmentName(scope.row.did) }} ({{ scope.row.did }})
                    </template>
                </el-table-column>
                <el-table-column prop="nno" label="护士编号" width="90px" />
                <el-table-column prop="wnumber" label="病房号" width="90px" />
                <el-table-column prop="bnumber" label="病床号" width="90px" />
                <el-table-column label="操作">
                    <template #default="scope">
                        <el-button type="primary" size="small" @click="selectByPid(scope.row.pid)" round>修改</el-button>
                        <el-popconfirm title="你确定要将该患者设置为出院吗？" confirm-button-text="确认" cancel-button-text="取消"
                            width="200px" @confirm="deleteByPid(scope.row.pid)">
                            <template #reference>
                                <el-button size="small" type="danger" round>出院</el-button>
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
    <el-dialog v-model="addDialogShow" title="添加患者" width="500">
        <el-form>
            <el-form-item label="编号" label-width="20%">
                <el-input v-model="patientAdd.pno" autocomplete="off" />
            </el-form-item>
            <el-form-item label="姓名" label-width="20%">
                <el-input v-model="patientAdd.pname" autocomplete="off" />
            </el-form-item>
            <el-form-item label="年龄" label-width="20%">
                <el-input v-model="patientAdd.page" autocomplete="off" />
            </el-form-item>
            <el-form-item label="性别" label-width="20%">
                <el-radio-group v-model="patientAdd.pgender">
                    <el-radio value="男" size="large">男</el-radio>
                    <el-radio value="女" size="large">女</el-radio>
                </el-radio-group>
            </el-form-item>

            <el-form-item label="入院日期" label-width="20%">
                <el-date-picker v-model="patientAdd.padmissiondate" type="date" placeholder="请选择入院日期"
                    format="YYYY-MM-DD" value-format="YYYY-MM-DD" style="width: 300px;" />
            </el-form-item>
            <el-form-item label="出院日期" label-width="20%">
                <el-date-picker v-model="patientAdd.pleavedate" type="date" placeholder="请选择出院日期" format="YYYY-MM-DD"
                    value-format="YYYY-MM-DD" style="width: 300px;" />
            </el-form-item>
            <el-form-item label="住院状态" label-width="20%">
                <el-radio-group v-model="patientAdd.pstatus">
                    <el-radio value="1" size="large">住院中</el-radio>
                    <el-radio value="2" size="large">已出院</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="联系电话" label-width="20%">
                <el-input v-model="patientAdd.ptelephone" autocomplete="off" />
            </el-form-item>
            <el-form-item label="科室名称" label-width="20%">
                <el-select v-model="patientAdd.did" placeholder="请选择科室" style="width: 300px;">
                    <el-option v-for="department in departmentList" :key="department.did"
                        :label="`${department.dname} (${department.did})`" :value="department.did" />
                </el-select>
            </el-form-item>
            <el-form-item label="护士编号" label-width="20%">
                <el-select v-model="patientAdd.nno" placeholder="请选择护士" style="width: 300px;">
                    <el-option v-for="(nurse, index) in nurseList" :key="index" :label="nurse.nno" :value="nurse.nno" />
                </el-select>
            </el-form-item>
            <el-form-item label="病房号" label-width="20%">
                <el-select v-model="patientAdd.wnumber" placeholder="请选择病房号" style="width: 300px;">
                    <el-option v-for="(ward, index) in wardList" :key="index" :label="ward.wnumber"
                        :value="ward.wnumber" />
                </el-select>
            </el-form-item>
            <el-form-item label="病床号" label-width="20%">
                <el-input v-model="patientAdd.bnumber" placeholder="请输入病床号，例如101-1" autocomplete="off" />
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
    <el-dialog v-model="updateDialogShow" title="修改患者" width="500">
        <el-form>
            <el-form-item label="编号" label-width="20%">
                <el-input v-model="patientUpdate.pno" autocomplete="off" />
            </el-form-item>
            <el-form-item label="姓名" label-width="20%">
                <el-input v-model="patientUpdate.pname" autocomplete="off" />
            </el-form-item>
            <el-form-item label="年龄" label-width="20%">
                <el-input v-model="patientUpdate.page" autocomplete="off" />
            </el-form-item>
            <el-form-item label="性别" label-width="20%">
                <el-radio-group v-model="patientUpdate.pgender">
                    <el-radio value="男" size="large">男</el-radio>
                    <el-radio value="女" size="large">女</el-radio>
                </el-radio-group>
            </el-form-item>

            <el-form-item label="入院日期" label-width="20%">
                <el-date-picker v-model="patientUpdate.padmissiondate" type="date" placeholder="请选择入院日期"
                    format="YYYY-MM-DD" value-format="YYYY-MM-DD" style="width: 300px;" />
            </el-form-item>
            <el-form-item label="出院日期" label-width="20%">
                <el-date-picker v-model="patientUpdate.pleavedate" type="date" placeholder="请选择出院日期" format="YYYY-MM-DD"
                    value-format="YYYY-MM-DD" style="width: 300px;" />
            </el-form-item>
            <el-form-item label="住院状态" label-width="20%">
                <el-radio-group v-model="patientUpdate.pstatus">
                    <el-radio :value="1" size="large">住院中</el-radio>
                    <el-radio :value="2" size="large">已出院</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="联系电话" label-width="20%">
                <el-input v-model="patientUpdate.ptelephone" autocomplete="off" />
            </el-form-item>
            <el-form-item label="科室名称" label-width="20%">
                <el-select v-model="patientUpdate.did" placeholder="请选择科室" style="width: 300px;">
                    <el-option v-for="department in departmentList" :key="department.did"
                        :label="`${department.dname} (${department.did})`" :value="department.did" />
                </el-select>
            </el-form-item>
            <el-form-item label="护士编号" label-width="20%">
                <el-select v-model="patientUpdate.nno" placeholder="请选择护士" style="width: 300px;">
                    <el-option v-for="(nurse, index) in nurseList" :key="index" :label="nurse.nno" :value="nurse.nno" />
                </el-select>
            </el-form-item>
            <el-form-item label="病房号" label-width="20%">
                <el-select v-model="patientUpdate.wnumber" placeholder="请选择病房号" style="width: 300px;">
                    <el-option v-for="(ward, index) in wardList" :key="index" :label="ward.wnumber"
                        :value="ward.wnumber" />
                </el-select>
            </el-form-item>
            <el-form-item label="病床号" label-width="20%">
                <el-input v-model="patientUpdate.bnumber" placeholder="请输入病床号，例如101-1" autocomplete="off" />
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
import patientApi from '@/api/patientApi';
import nurseApi from '@/api/nurseApi';
import { ref, watch } from 'vue';
import { ElMessage } from 'element-plus';
import wardApi from '@/api/wardApi';
import bedApi from '@/api/bedApi';

let pageNow;
const pname = ref('');
//分页信息
const pageInfo = ref({
    total: 0,
    pageSize: 0
});

//所有科室
const departmentList = ref([]);

//该科室下的护士
const nurseList = ref([])
//该科室下的病房
const wardList = ref([]);
//该病房下的病床
const bedList = ref([]);

//添加对话框是否显示
const addDialogShow = ref(false);
//修改对话框是否显示
const updateDialogShow = ref(false);
//被添加患者的信息
const patientAdd = ref({
    pno: '',
    pname: '',
    page: '',
    pgender: '',
    padmissiondate: '',
    pleavedate: '',
    pstatus: '',
    ptelephone: '',
    did: '',
    nno: '',
    wnumber: '',
    bnumber: ''
})

//被修改的患者的信息
const patientUpdate = ref({
    pid: '',
    pno: '',
    pname: '',
    page: '',
    pgender: '',
    padmissiondate: '',
    pleavedate: '',
    pstatus: '',
    ptelephone: '',
    did: ''
});

//监听科室选择变化，获取对应科室的护士列表
watch(() => patientAdd.value.did, (newDid) => {
    if (newDid) {
        //如果重新选择了科室，重置护士编号
        patientAdd.value.nno = '';
        //调用API获取该科室的护士列表
        nurseApi.selectByDid(newDid)
            .then(resp => {
                nurseList.value = resp.data;
            });
    } else {
        nurseList.value = []; //如果没有选择科室，则清空护士列表
    }
});

//监听科室选择变化，获取对应科室的病房列表
watch(() => patientAdd.value.did, (newDid) => {
    if (newDid) {
        //如果重新选择了科室，重置护士编号
        patientAdd.value.wnumber = '';
        //调用API获取该科室的病房列表
        wardApi.selectByDid(newDid)
            .then(resp => {
                wardList.value = resp.data;
            });
    } else {
        wardList.value = []; //如果没有选择科室，则清空护士列表
    }
});

//监听科室选择变化，获取对应科室的护士列表
watch(() => patientUpdate.value.did, (newDid) => {
    if (newDid) {
        //如果重新选择了科室，重置护士编号
        patientUpdate.value.nno = '';
        //调用API获取该科室的护士列表
        nurseApi.selectByDid(newDid)
            .then(resp => {
                nurseList.value = resp.data;
            });
    } else {
        nurseList.value = []; //如果没有选择科室，则清空护士列表
    }
});

//监听科室选择变化，获取对应科室的病房列表
watch(() => patientUpdate.value.did, (newDid) => {
    if (newDid) {
        //如果重新选择了科室，重置护士编号
        patientUpdate.value.wnumber = '';
        //调用API获取该科室的病房列表
        wardApi.selectByDid(newDid)
            .then(resp => {
                wardList.value = resp.data;
            });
    } else {
        wardList.value = []; //如果没有选择科室，则清空护士列表
    }
});

// 监听病房号变化，获取对应病房的空闲病床列表
// watch(() => patientAdd.value.wnumber, (newWnumber) => {
//     if (newWnumber) {
//         // 如果重新选择了病房，重置病床编号
//         patientAdd.value.bnumber = '';
//         // 调用API获取该病房下未被分配的病床
//         bedApi.selectFreeBedsByWnumber(newWnumber)
//             .then(resp => {
//                 console.log(resp);

//                 bedList.value = resp.data; // 更新病床列表
//             });
//     } else {
//         bedList.value = []; // 如果没有选择病房，清空病床列表
//     }
// });



departmentApi.selectAll()
    .then(resp => {
        departmentList.value = resp.data;
        console.log(departmentList.value); // 打印科室列表，确保数据正确
    });

wardApi.selectAll()
    .then(resp => {
        wardList.value = resp.data;
        console.log(wardList.value); // 打印病房列表
    });


//根据科室ID返回科室名称
function getDepartmentName(did) {
    const department = departmentList.value.find(dep => dep.did === did);
    return department ? department.dname : '未知科室';
}
//分页显示
function selectByPage(pageNum) {
    patientApi.selectByPage(pageNum, pname.value)
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

//定义方法完成患者添加
function insert() {
    patientApi.insert(patientAdd.value)
        .then(resp => {
            if (resp.code == 10000) {
                //弹出消息
                ElMessage.success(resp.msg);
                //隐藏对话框
                addDialogShow.value = false;
                //清空对话框中的数据
                patientAdd.value = {
                    pno: '',
                    pname: '',
                    page: '',
                    pgender: '',
                    padmissiondate: '',
                    pleavedate: '',
                    pstatus: '',
                    ptelephone: '',
                    did: '',
                    nno: '',
                    wnumber: '',
                    bnumber: ''
                }

                //刷新表格数据
                selectByPage(pageNow);
            } else {
                //弹出消息
                ElMessage.error(resp.msg);
            }
        });
}

//定义方法完成患者修改
function update() {
    patientApi.update(patientUpdate.value)
        .then(resp => {
            if (resp.code == 10000) {
                //弹出消息
                ElMessage.success(resp.msg);
                //隐藏对话框
                updateDialogShow.value = false;
                //刷新表格数据
                selectByPage(pageNow);
            } else {
                //弹出消息
                ElMessage.error(resp.msg);
            }
        });
}

//定义方法根据id删除员工
function deleteByPid(pid) {
    patientApi.delete(pid)
        .then(resp => {
            //判断-弹出消息-刷新表格
            if (resp.code == 10000) {
                //弹出消息
                ElMessage.success(resp.msg);
                selectByPage(pageNow);
            } else {
                //弹出消息
                ElMessage.error(resp.msg);
            }
        })
}

//查询所有科室及患者信息并显示修改对话框
function selectByPid(pid) {
    departmentApi.selectAll()
        .then(resp => {
            departmentList.value = resp.data;
            //根据pid查询患者信息
            patientApi.selectByPid(pid)
                .then(resp => {
                    patientUpdate.value = resp.data;
                    //显示修改对话框
                    updateDialogShow.value = true;
                });
        })

}



//默认查询首页
selectByPage(1);
</script>
<style scoped></style>