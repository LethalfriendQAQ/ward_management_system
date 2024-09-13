<template>
    <el-col :span="24">
        <el-card>
            <el-form :inline="true" :model="formInline" class="demo-form-inline">
                <el-form-item>
                    <el-button type="primary" @click="showAddDialog">添加</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="success" plain @click="exportToExcel">导出 Excel</el-button>
                    <!--<el-button type="success" plain @click="exportPatient">导出Excel</el-button>-->
                </el-form-item>
                <el-form-item>
                  <el-upload :on-success="uploadPatient" action="http://localhost:8080/user/patient/upload"
                             :headers="headers" method="post" name="file" list-type="none" :show-file-list="false">
                    <el-button type="success" plain>导入Excel</el-button>
                  </el-upload>
                </el-form-item>
                <el-form-item style="float: right;">
                    <el-radio-group v-model="pstatus" @change="selectByPage(1);">
                        <el-radio-button label="" value="">所有</el-radio-button>
                        <el-radio-button label="1" value="1">住院中</el-radio-button>
                        <el-radio-button label="2" value="2">已出院</el-radio-button>
                    </el-radio-group>
                </el-form-item>
                <el-form-item style="float: right;">
                    <el-input v-model="did" placeholder="搜索对应科室下的患者" @input="selectByPage(1);" />
                </el-form-item>
                <el-form-item style="float: right;">
                    <el-input v-model="pname" placeholder="请输入要搜索的姓名" @input="selectByPage(1);" />
                </el-form-item>
            </el-form>
            <el-table :data="pageInfo.list" border style="width: 100%">
                <el-table-column prop="pid" label="ID" width="50px" />
                <el-table-column prop="pno" label="编号" width="70px" />
                <el-table-column prop="eavatar" label="头像" width="70px">
                    <template #default="scope">
                        <el-avatar :size="size" :src="'http://localhost:8080/upload/' + scope.row.pavatar" />
                    </template>
                </el-table-column>
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
            <el-form-item label="编号" prop="patientAdd.pno" :rules="pnoRules" label-width="20%">
                <el-input v-model="patientAdd.pno" autocomplete="off" :rules="pnoRules" />
            </el-form-item>
            <el-form-item label="姓名" prop="patientAdd.pname" :rules="pnameRules" label-width="20%">
                <el-input v-model="patientAdd.pname" autocomplete="off" :rules="pnameRules" />
            </el-form-item>
            <el-form-item label="年龄" prop="patientAdd.page" :rules="pageRules" label-width="20%">
                <el-input v-model="patientAdd.page" autocomplete="off" :rules="pageRules" />
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
            <el-form-item label="头像" label-width="20%">
                <el-upload class="avatar-uploader" action="http://localhost:8080/user/upload" name="pic" :headers="headers"
                    :show-file-list="false" :before-upload="beforeAvatarUpload" :on-success="handleAvatarSuccessAdd">
                    <img v-if="imageUrlAdd" :src="imageUrlAdd" class="avatar" />
                    <el-icon v-else class="avatar-uploader-icon">
                        <Plus />
                    </el-icon>
                </el-upload>
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
    <el-dialog v-model="updateDialogShow" title="修改患者" width="500" @close="closeDialog">
        <el-form>
            <el-form-item label="编号" prop="patientUpdate.pno" :rules="pnoRules" label-width="20%">
                <el-input v-model="patientUpdate.pno" autocomplete="off" />
            </el-form-item>
            <el-form-item label="姓名" prop="patientUpdate.pname" :rules="pnameRules" label-width="20%">
                <el-input v-model="patientUpdate.pname" autocomplete="off" />
            </el-form-item>
            <el-form-item label="年龄"  prop="patientUpdate.page" :rules="pageRules" label-width="20%">
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
            <el-form-item label="头像" label-width="20%">
                <el-upload class="avatar-uploader" action="http://localhost:8080/user/upload" name="pic" :headers="headers"
                    :show-file-list="false" :before-upload="beforeAvatarUpload" :on-success="handleAvatarSuccessUpdate">
                    <img v-if="imageUrlUpdate" :src="imageUrlUpdate" class="avatar" />
                    <el-icon v-else class="avatar-uploader-icon">
                        <Plus />
                    </el-icon>
                </el-upload>
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
import { computed, ref, watch } from 'vue';
import { ElMessage } from 'element-plus';
import wardApi from '@/api/wardApi';
import { Download, UploadFilled } from '@element-plus/icons-vue';
import bedApi from '@/api/bedApi';
import * as XLSX from 'xlsx';
import { saveAs } from 'file-saver';
import { useTokenStore} from "@/stores/token.js";

const tokenStore = useTokenStore();

const headers = computed(() => {
  let token = tokenStore.tokenStr;
    return {
        token
    }
});

const did = ref('');
const pstatus = ref('');
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
//上传图片的地址
const imageUrl = ref("");

//添加上传图片的地址
const imageUrlAdd = ref("");
//修改上传图片的地址
const imageUrlUpdate = ref("");

//被添加患者的信息
const patientAdd = ref({
    pno: '',
    pavatar: '',
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
    pavatar: '',
    pname: '',
    page: '',
    pgender: '',
    padmissiondate: '',
    pleavedate: '',
    pstatus: '',
    ptelephone: '',
    did: ''
});
const pnoRules = [
  { required: true, message: '编号不能为空', trigger: 'blur' },
  { pattern: /^[0-9]+$/, message: '编号只能为数字', trigger: 'blur' }
]
const pnameRules = [
  { required: true, message: '姓名不能为空', trigger: 'blur' },
]
const pageRules = [
  { required: true, message: '年龄不能为空', trigger: 'blur' },
  { pattern: /^[0-9]+$/, message: '年龄只能为数字', trigger: 'blur' }
]

const submitFrom = () => {
  const formRef = $refs.formRef;
  formRef.validate((valid) => {
    if (valid) {
      // 提交表单
      console.log('提交数据:', patientAdd.value);
    } else {
      console.log('表单校验失败');
      return false;
    }
  });
};
// 导出 Excel 方法
function exportToExcel() {
    // 获取表格数据
    const data = pageInfo.value.list.map(item => ({
        ID: item.pid,
        编号: item.pno,
        姓名: item.pname,
        年龄: item.page,
        性别: item.pgender,
        入院时间: item.padmissiondate,
        出院时间: item.pleavedate,
        //住院状态: item.pstatus === 1 ? '住院中' : '已出院',
        住院状态: item.pstatus,
        电话: item.ptelephone,
        科室: getDepartmentName(item.did),
        护士编号: item.nno,
        病房号: item.wnumber,
        病床号: item.bnumber,
    }));

    // 创建工作簿和工作表
    const ws = XLSX.utils.json_to_sheet(data);
    const wb = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(wb, ws, '患者数据');

    // 生成 Excel 文件并下载
    const wbout = XLSX.write(wb, { bookType: 'xlsx', type: 'array' });
    saveAs(new Blob([wbout], { type: 'application/octet-stream' }), '患者数据.xlsx');
}


function uploadPatient(response) {
  if (response.code == 10000) {
    selectByPage(pageNow);
    ElMessage.success("上传成功");
  } else {
    ElMessage.error(response.msg);
  }

}


//添加成功上传之前的回调
function beforeAvatarUpload(rawFile) {
    if (rawFile.type !== 'image/jpeg') {
        ElMessage.error('图片仅支持jpg格式');
        return false
    } else if (rawFile.size / 1024 / 1024 > 2) {
        ElMessage.error('图片不能超过2M');
        return false
    }
    return true
}


//添加成功上传之后的回调
function handleAvatarSuccessAdd(resp, uploadFile) {
    if (resp.code == 10000) {
        ElMessage.success(resp.msg);
        imageUrlAdd.value = "http://localhost:8080/upload/" + resp.data;
        patientAdd.value.pavatar = resp.data;
    }
}

//修改成功上传之后的回调
function handleAvatarSuccessUpdate(resp, uploadFile) {
    if (resp.code == 10000) {
        ElMessage.success(resp.msg);
        imageUrlUpdate.value = "http://localhost:8080/upload/" + resp.data;
        patientUpdate.value.pavatar = resp.data;
    }
}


//添加：监听科室选择变化，获取对应科室的护士列表
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

//添加：监听科室选择变化，获取对应科室的病房列表
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

let unwatchDepartmentChange;  // 保存监听器

function watchDepartmentChange() {
    if (unwatchDepartmentChange) {
        unwatchDepartmentChange();  // 取消之前的监听
    }

    unwatchDepartmentChange = watch(() => patientUpdate.value.did, (newDid) => {
        if (newDid && updateDialogShow.value) {
            patientUpdate.value.nno = '';
            patientUpdate.value.wnumber = '';

            nurseApi.selectByDid(newDid)
                .then(resp => {
                    nurseList.value = resp.data;
                });

            wardApi.selectByDid(newDid)
                .then(resp => {
                    wardList.value = resp.data;
                });
        } else {
            nurseList.value = [];
            wardList.value = [];
        }
    });
}


departmentApi.selectAll()
    .then(resp => {
        departmentList.value = resp.data;
    });

wardApi.selectAll()
    .then(resp => {
        wardList.value = resp.data;
    });


//根据科室ID返回科室名称
function getDepartmentName(did) {
    const department = departmentList.value.find(dep => dep.did === did);
    return department ? department.dname : '未知科室';
}

// watch(pstatus, (newValue) => {
//     console.log(newValue);
//     selectByPage(1); // 重新加载第一页数据
// });
//分页显示
function selectByPage(pageNum) {
    patientApi.selectByPage(pageNum, pname.value, did.value, pstatus.value)
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
                    pavatar: '',
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
                console.log(patientAdd.value);
                
                //imgUrl清空
                imageUrl.value = '';

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
                    const patientData = resp.data;
                    // 加载护士列表
                    nurseApi.selectByDid(patientData.did)
                        .then(nurseResp => {
                            nurseList.value = nurseResp.data;
                            // 加载病房列表
                            wardApi.selectByDid(patientData.did)
                                .then(wardResp => {
                                    wardList.value = wardResp.data;
                                    // 数据全部加载完成后再绑定并打开对话框
                                    patientUpdate.value = patientData;
                                    //设置头像
                                    imageUrlUpdate.value = `http://localhost:8080/upload/${patientUpdate.value.pavatar}`;
                                    updateDialogShow.value = true;
                                    watchDepartmentChange();

                                });
                        });
                });
        })
}

function closeDialog() {
    updateDialogShow.value = false;
    nurseList.value = [];  // 清空护士列表
    wardList.value = [];   // 清空病房列表
}


//默认查询首页
selectByPage(1);
</script>
<style scoped>
.avatar-uploader,
.avatar {
    width: 178px;
    height: 178px;
    display: block;
    border: 1px dotted #dcdfe6;
    border-radius: 5px;
}

.el-icon.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    text-align: center;
}
</style>