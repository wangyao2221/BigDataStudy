<template>
    <div id="page-content">
      <div id="myCharts" style="height: 500px"></div>
    </div>
</template>

<script>
import Axios from 'axios'

export default {
  name: 'DayAccessTopN',
  data () {
    return {
      dayVideoAccessStats: [],
      options: {
        title: {
          text: 'Video每日访问统计',
          x: 'center'
        },
        series: [
          {
            name: '姓名',
            type: 'pie',
            data: this.dayVideoAccessStats,
            radius: '55%',
            center: ['40%', '50%']
          }
        ]
      }
    }
  },
  mounted () {
    this.drawChart()
  },
  methods: {
    drawChart () {
      let url = '/api/imooc/log/topN/dayVideoAccessStats'
      Axios.get(url).then(response => {
        this.dayVideoAccessStats = response.data.result
      })
      let myChart = this.echarts.init(document.getElementById('myCharts'))

      // let options = {
      //   title: {
      //     text: '未来一周气温变化',
      //     subtext: '纯属虚构'
      //   },
      //   tooltip: {
      //     trigger: 'axis'
      //   },
      //   legend: {
      //     data: ['最高气温', '最低气温']
      //   },
      //   xAxis: [{
      //     type: 'category',
      //     boundaryGap: false,
      //     data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
      //   }],
      //   yAxis: [{
      //     type: 'value',
      //     axisLabel: {
      //       formatter: '{value} °C'
      //     }
      //   }],
      //   series: [
      //     {
      //       name: '最高气温',
      //       type: 'line', // pie->饼状图  line->折线图  bar->柱状图
      //       data: [11, 11, 15, 13, 12, 13, 10]
      //     },
      //     {
      //       name: '最低气温',
      //       type: 'line', // pie->饼状图  line->折线图  bar->柱状图
      //       data: [1, -2, 2, 5, 3, 2, 0]
      //     }
      //   ]
      // }
      myChart.setOption(this.options)
    }
  }
}
</script>

<style scoped>

</style>
