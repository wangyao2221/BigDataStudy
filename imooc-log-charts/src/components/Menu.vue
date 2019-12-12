<template>
  <el-menu mode="horizontal" :default-active="$route.path" router>
    <el-submenu index="1">
      <template slot="title">
        <i class="el-icon-pie-chart"></i>
        <span>统计图表</span>
      </template>
      <el-menu-item index="/DayAccessTopN">
        <i class="el-icon-date"></i>
        <span>按访问量统计</span>
      </el-menu-item>
      <el-menu-item index="/DayCityAccessTopN">
        <i class="el-icon-location"></i>
        <span>按城市统计</span>
      </el-menu-item>
      <el-menu-item index="/DayTrafficTopN">
        <i class="el-icon-data-line"></i>
        <span>按流量统计</span>
      </el-menu-item>
      <el-menu-item index="/SearchComponentTest">
        <i class="el-icon-data-line"></i>
        <span>可配置化组件测试</span>
      </el-menu-item>
    </el-submenu>
  </el-menu>
</template>

<script>
import Router from 'vue-router'
export default {
  name: 'Menu',
  components: {
    Router
  }
}
</script>

<style scoped>

</style>
