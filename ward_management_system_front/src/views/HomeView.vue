<template>
  <div class="common-layout">
    <el-container>
      <el-header>Header</el-header>
      <el-container>
        <el-aside>
          <el-menu active-text-color="#ffd04b" background-color="#545c64" class="el-menu-vertical-demo"
            default-active="2" text-color="#fff" @select="toPage">
            <el-menu-item index="/index">
              <el-icon>
                <House />
              </el-icon>
              首页
            </el-menu-item>
            <el-sub-menu index="2">
              <template #title>
                <el-icon>
                  <el-icon>
                    <HomeFilled />
                  </el-icon>
                </el-icon>
                <span>病房管理</span>
              </template>
              <el-menu-item index="/patient">患者管理</el-menu-item>
              <el-menu-item index="/dept">科室管理</el-menu-item>
              <el-menu-item index="/nurse">护士管理</el-menu-item>
              <el-menu-item index="/ward">病房管理</el-menu-item>
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

function toPage(indexPath) {
  //编程式导航
  router.push(indexPath);
}
</script>
