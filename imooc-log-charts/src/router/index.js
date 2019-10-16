import Vue from 'vue'
import Router from 'vue-router'
import IMoocLog from '../page/IMoocLog'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'IMoocLog',
      component: IMoocLog
    }
  ]
})
