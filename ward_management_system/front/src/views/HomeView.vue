<template>
  <div class="common-layout">
    <el-container>
      <el-header style="padding: 0; margin: 0;">
        <el-header style="background-color: #1e1c23; color: white; padding: 0; margin: 0;width: 100%;">
          <img src="/images/logo.png" alt="Logo"
            style="height: 50px; margin-left: 20px; padding: 0; margin: 0; width: 100%; width: 200px;">
          <el-button type="danger" @click="logout" style="float: right; margin: 15px" round>
            <a href="#" style="color: inherit; text-decoration: none;">退出登录</a>
          </el-button>
          <el-tag type="success" style="float: right; margin: 20px;"><span>欢迎 {{ username }} </span></el-tag>
        </el-header>
      </el-header>
      <el-container>
        <el-aside>
          <el-menu active-text-color="#ffd04b" background-color="#1e1c23" class="el-menu-vertical-demo"
            default-active="2" text-color="#fff" @select="toPage">
            <el-menu-item index="/index"><el-icon>
                <House />
              </el-icon>首页</el-menu-item>
            <el-sub-menu index="2">
              <template #title>
                <el-icon><el-icon>
                    <User />
                  </el-icon></el-icon><span>患者管理</span>
              </template>
              <el-menu-item index="/patient" style="background-color: #3a3646;">患者管理</el-menu-item>
            </el-sub-menu>
            <el-sub-menu index="3">
              <template #title>
                <el-icon><el-icon>
                    <Avatar />
                  </el-icon></el-icon><span>员工管理</span>
              </template>
              <el-menu-item index="/dept" style="background-color: #3a3646;">科室管理</el-menu-item>
              <el-menu-item index="/nurse" style="background-color: #3a3646;">护士管理</el-menu-item>
              <el-menu-item index="/duty" style="background-color: #3a3646;">值班管理</el-menu-item>
            </el-sub-menu>
            <el-sub-menu index="4">
              <template #title>
                <el-icon><el-icon>
                    <HomeFilled />
                  </el-icon></el-icon><span>病房管理</span>
              </template>
              <el-menu-item index="/ward" style="background-color: #3a3646;">病房管理</el-menu-item>
              <el-menu-item index="/bed" style="background-color: #3a3646;">病床管理</el-menu-item>
            </el-sub-menu>
          </el-menu>
        </el-aside>
        <el-main>
          <RouterView />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>


<script setup>
import router from '@/router'
import { RouterView, RouterLink } from 'vue-router';
import adminApi from "@/api/adminApi.js";
import {ref} from "vue";

const username = ref('');

function toPage(indexPath) {
  //编程式导航
  router.push(indexPath);
}
function logout() {
  //删除sessionStorage中的token
  sessionStorage.removeItem('token');
  //跳转到登录页
  router.push('/login');
}

//获取已登录用户的信息
function getLoginInfo() {
  adminApi.getLoginInfo()
      .then(resp => {
        console.log(resp);
          username.value = resp.data.username;
      });
}

getLoginInfo();
</script>
