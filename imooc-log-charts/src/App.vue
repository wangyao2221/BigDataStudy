<template>
  <div id="app">
      <el-menu mode="vertical"
               default-active="2"
               class="el-menu-vertical-demo"
               @open="handleOpen"
               @close="handleClose">
        <el-submenu index="1">
          <template slot="title">统计图表</template>
          <el-menu-item index="1-1">按访问量统计</el-menu-item>
          <el-menu-item index="1-2">按城市统计</el-menu-item>
          <el-menu-item index="1-3">按流量统计</el-menu-item>
        </el-submenu>
      </el-menu>
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
