<template>
    <div id="page-content">
      <div id="myChart" style="height: 500px" v-loading="loading"></div>
    </div>
</template>

<script>
import Axios from 'axios'

export default {
  name: 'DayAccessTopN',
  data () {
    return {
      dayAccessTopNData: [],
      dayAccessTopNDataLabel: [],
      loading: true,
      n: 100,
      options: {
        title: {
          text: 'Video每日访问统计',
          x: 'center'
        },
        tooltip: {},
        xAxis: {
          data: []
        },
        yAxis: {},
        dataZoom: [
          {
            type: 'slider',
            xAxisIndex: 0,
            start: 0,
            end: 100
          },
          {
            type: 'inside',
            xAxisIndex: 0,
            start: 10,
            end: 100
          },
          {
            type: 'slider',
            yAxisIndex: 0,
            start: 0,
            end: 100
          },
          {
            type: 'inside',
            yAxisIndex: 0,
            start: 0,
            end: 100
          }
        ],
        series: [
          {
            name: '访问量',
            type: 'bar',
            data: []
          }
        ]
      }
    }
  },
  created () {
    let query = this.$route.query
    if (query['n']) {
      this.n = query['n']
    }
  },
  mounted () {
    this.drawChart()
  },
  methods: {
    drawChart () {
      let url = '/api/imooc/log/topN/dayVideoAccessStatTopN?n=' + this.n

      let myChart = this.echarts.init(document.getElementById('myChart'))
      myChart.setOption(this.options)

      this.loading = true
      Axios.get(url).then(response => {
        let result = response.data.result

        for (let i in result) {
          let item = result[i]
          this.dayAccessTopNData.push(item.time)
          this.dayAccessTopNDataLabel.push('视频' + item.cmsId)
        }

        myChart.setOption({
          xAxis: {
            data: this.options.xAxis.data = this.dayAccessTopNDataLabel
          },
          series: [{
            name: '访问量',
            data: this.dayAccessTopNData
          }]
        })

        this.loading = false
      })
    }
  }
}
</script>

<style scoped>

</style>
