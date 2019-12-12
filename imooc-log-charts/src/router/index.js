import Vue from 'vue'
import Router from 'vue-router'
import LogStatCharts from '../page/LogStatCharts'
import DayAccessTopN from '../page/DayAccessTopN'
import DayCityAccessTopN from '../page/DayCityAccessTopN'
import DayTrafficTopN from '../page/DayTrafficTopN'
import SearchComponentTest from '../page/SearchComponentTest'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'LogStatCharts',
      component: LogStatCharts
    },
    {
      path: '/DayAccessTopN',
      name: 'DayAccessTopN',
      component: DayAccessTopN
    },
    {
      path: '/DayCityAccessTopN',
      name: 'DayCityAccessTopN',
      component: DayCityAccessTopN
    },
    {
      path: '/DayTrafficTopN',
      name: 'DayTrafficTopN',
      component: DayTrafficTopN
    },
    {
      path: '/SearchComponentTest',
      name: 'SearchComponentTest',
      component: SearchComponentTest
    }
  ]
})
