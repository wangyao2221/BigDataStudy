<template>
  <el-menu :default-active="'1'">
    <el-submenu index="1">
      <template slot="title">
        <i class="el-icon-pie-chart"></i>
        <span>AsideMenu1</span>
      </template>
      <el-menu-item index="1-1">
        <i class="el-icon-date"></i>
        <span>AsideSubMenu1-1</span>
      </el-menu-item>
      <el-menu-item index="1-2">
        <i class="el-icon-location"></i>
        <span>AsideSubMenu1-2</span>
      </el-menu-item>
      <el-menu-item index="1-3">
        <i class="el-icon-data-line"></i>
        <span>AsideSubMenu1-3</span>
      </el-menu-item>
    </el-submenu>
    <el-submenu index="2">
      <template slot="title">
        <i class="el-icon-pie-chart"></i>
        <span>AsideMenu2</span>
      </template>
      <el-menu-item index="2-2">
        <i class="el-icon-date"></i>
        <span>AsideSubMenu2-1</span>
      </el-menu-item>
      <el-menu-item index="2-2">
        <i class="el-icon-location"></i>
        <span>AsideSubMenu2-2</span>
      </el-menu-item>
      <el-menu-item index="2-3">
        <i class="el-icon-data-line"></i>
        <span>AsideSubMenu2-3</span>
      </el-menu-item>
    </el-submenu>
  </el-menu>
</template>

<script>
import Router from 'vue-router'
export default {
  name: 'AsideMenu',
  components: {
    Router
  }
}
</script>

<style scoped>

</style>
