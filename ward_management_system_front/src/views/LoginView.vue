<template>
    <div class="container">
        <h1>用户登录</h1>
        <el-form rel="form" label-width="60px">
            <el-form-item label="用户名">
                <el-input type="text" v-model="admin.username"></el-input>
            </el-form-item>
            <el-form-item label="密码">
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

const admin = ref({
    username: '',
    password: ''
});

//登录
function login() {
    adminApi.login(admin.value)
    .then(resp => {
        if(resp.code == 10000) {
            //弹出提示信息
            ElMessage({
                    message: resp.msg,
                    type: 'success',
                    duration: 1200,
                    onClose: () => {
                        //保存jwt到sessionStorage
                        sessionStorage.setItem('token', resp.data);
                        //跳转到后台首页
                        router.push('/index');
                    }
                });
            
        } else {
            ElMessage.error(resp.msg);
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
    background-color: lightskyblue;
  }</style>