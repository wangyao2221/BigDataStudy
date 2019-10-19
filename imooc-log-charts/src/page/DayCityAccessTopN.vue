<template>
    <div id="page-content">
      <div id="myChart" v-loading="loading" style="height: 500px"></div>
    </div>
</template>

<script>
import Axios from 'axios'

export default {
  name: 'DayCityAccessTopN',
  data () {
    return {
      dayCityAccessTopNDataRank: [[], [], []],
      labels: [],
      loading: true,
      n: 5,
      options: {
        title: {
          text: 'Video地区访问量统计',
          xAxis: 'center'
        },
        legend: {
          data: ['当前地区第1名', '当前地区第2名', '当前地区第3名']
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          },
          formatter: function (params, ticket, callback) {
            let result = ''

            for (let i in params) {
              let item = params[i]['data']
              let text = '视频' + item['name']
              let value = item['value']
              result += text + ' - ' + value + '<br>'
            }

            return result
          }
        },
        xAxis: {
          title: '省份',
          type: 'value'
        },
        yAxis: {
          title: '访问量',
          type: 'category',
          data: []
        },
        series: [
          {
            name: '当前地区第1名',
            type: 'bar',
            stack: 'total',
            label: {
              normal: {
                show: true,
                position: 'insideRight'
                // formatter: '视频{b} - {c}'
              }
            },
            data: []
          },
          {
            name: '当前地区第2名',
            type: 'bar',
            stack: 'total',
            label: {
              normal: {
                show: true,
                position: 'insideRight'
                // formatter: '视频{b} - {c}'
              }
            },
            data: []
          },
          {
            name: '当前地区第3名',
            type: 'bar',
            stack: 'total',
            label: {
              normal: {
                show: true,
                position: 'insideRight'
                // formatter: '视频{b} - {c}'
              }
            },
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
      let url = '/api/imooc/log/topN/dayVideoCityAccessStatTopN?n=' + this.n

      let myChart = this.echarts.init(document.getElementById('myChart'))
      myChart.setOption(this.options)

      Axios.get(url).then(response => {
        let result = response.data.result
        let dataMap = {}

        for (let i in result) {
          let item = result[i]

          if (!dataMap.hasOwnProperty(item['city'])) {
            dataMap[item['city']] = []
          }
          dataMap[item['city']].push({name: item['cmsId'], value: item['times'], rank: item['timesRank']})
        }

        for (let key in dataMap) {
          this.labels.push(key)
          let cityItem = dataMap[key]
          for (let i in cityItem) {
            let item = cityItem[i]
            let rank = item['rank']
            // console.log(item)
            if (rank <= 3) {
              this.dayCityAccessTopNDataRank[rank - 1].push({name: item['name'], value: item['value']})
            }
          }
        }

        myChart.setOption({
          yAxis: {
            data: this.labels
          },
          series: [
            {
              name: '当前地区第1名',
              data: this.dayCityAccessTopNDataRank[0]
            },
            {
              name: '当前地区第2名',
              data: this.dayCityAccessTopNDataRank[1]
            },
            {
              name: '当前地区第3名',
              data: this.dayCityAccessTopNDataRank[2]
            }
          ]
        })

        this.loading = false
        // console.log(this.labels)
        // console.log(this.dayCityAccessTopNDataRank)
      })
    }
  }
}
</script>

<style scoped>
</style>
