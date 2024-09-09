<template>
    <div class="container">
        <h1>用户登录</h1>
        <el-form :model="admin" :rules="rules" ref="form" label-width="60px">
            <el-form-item label="用户名" prop="username" label-width="65px">
                <el-input type="text" v-model="admin.username"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password" label-width="65px">
                <el-input type="password" v-model="admin.password"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="login">登录</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script setup>
import adminApi from '@/api/adminApi';
import router from '@/router';
import { ElMessage } from 'element-plus';
import { ref } from 'vue';

// 表单数据
const admin = ref({
    username: '',
    password: ''
});

// 表单验证规则
const rules = {
    username: [
        { required: true, message: '用户名不能为空', trigger: 'blur' }
    ],
    password: [
        { required: true, message: '密码不能为空', trigger: 'blur' }
    ]
};

// 表单引用
const form = ref(null);

// 登录逻辑
function login() {
    form.value.validate((valid) => {
        if (valid) {
            // 如果表单验证通过，调用登录 API
            adminApi.login(admin.value).then(resp => {
                if (resp.code === 10000) {
                    ElMessage({
                        message: resp.msg,
                        type: 'success',
                        duration: 1200,
                        onClose: () => {
                            // 保存 JWT 到 sessionStorage
                            sessionStorage.setItem('token', resp.data);
                            // 跳转到后台首页
                            router.push('/index');
                        }
                    });
                } else {
                    ElMessage.error(resp.msg);
                }
            });
        } else {
            ElMessage.error('不能为空');
        }
    });
}

</script>

<style scoped>
.container {
    width: 500px;
    border: 1px solid black;
    border-radius: 5px;
    padding: 20px;
    padding-top: 0px;
    margin: auto;
    margin-top: 150px;
    /* background-color: rgba(0, 0, 0, 0.1); */
}


</style>