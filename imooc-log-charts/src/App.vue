<template>
  <div id="app">
    <el-container>
<!--      <el-aside width="200px">-->
<!--        <aside-menu></aside-menu>-->
<!--      </el-aside>-->
      <el-container>
        <el-header><Menu></Menu></el-header>
        <el-main><router-view/></el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import Menu from './components/Menu'
import AsideMenu from './components/AsideMenu'

export default {
  name: 'App',
  components: {
    Menu,
    AsideMenu
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  /*margin-top: 60px;*/
}

.el-main {
  text-align: center;
}
</style>
