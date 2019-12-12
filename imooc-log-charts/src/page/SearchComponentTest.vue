<template>
  <div>
    <search-component :config="config" :data="data" @searchResult="searchResult"></search-component>
<!--    <table>-->
<!--      <tr v-for="(row,index) in searchData" :key="index">-->
<!--        <td>{{ row.id }}</td>-->
<!--        <td>{{ row.cpu }}</td>-->
<!--        <td>{{ row.disk }}</td>-->
<!--        <td>{{ row.date }}</td>-->
<!--        <td>{{ row.description }}</td>-->
<!--        <td>{{ row.cluster }}</td>-->
<!--      </tr>-->
<!--    </table>-->
    <el-table :data="searchData">
      <el-table-column prop="id" label="ID"></el-table-column>
      <el-table-column prop="cpu" label="CPU"></el-table-column>
      <el-table-column prop="disk" label="磁盘"></el-table-column>
      <el-table-column prop="date" label="日期"></el-table-column>
      <el-table-column prop="description" label="描述"></el-table-column>
      <el-table-column prop="cluster" label="集群"></el-table-column>
    </el-table>
  </div>
</template>

<script>
import SearchComponent from '../components/SearchComponent'

export default {
  name: 'SearchComponentTest',
  components: {
    SearchComponent
  },
  data () {
    return {
      config: {
        size: 'mini', // mini/small/medium
        items: [
          {
            key: 'id',
            type: 'input',
            label: 'ID',
            placeholder: '请输入ID',
            method: 'equals' // equals/contains/range(number)
            // value: ''
          },
          {
            key: 'cpu',
            type: 'input',
            label: 'CPU',
            placeholder: '请输入CPU容量',
            method: 'equals' // range/equals
          },
          {
            key: 'disk',
            type: 'input',
            label: '磁盘',
            placeholder: '请输入磁盘容量',
            method: 'range' // range/equals
            // min: null,
            // max: null
          },
          {
            key: 'date',
            type: 'date',
            label: '时间范围',
            method: 'range' // range/equals
          },
          {
            key: 'date',
            type: 'date',
            label: '时间',
            method: 'equals' // range/equals
          },
          {
            key: 'description',
            type: 'input',
            label: '描述',
            placeholder: '请输入描述',
            method: 'contains' // equals/contains/range(number)
          }
        ]
      },
      data: [
        {
          id: '1001',
          cpu: 16,
          disk: 1000,
          date: '2019-12-10',
          description: '描述1',
          cluster: 'bdwg'
        },
        {
          id: '1002',
          cpu: 32,
          disk: 2000,
          date: '2019-12-1',
          description: '描述2',
          cluster: 'bdwg-g1'
        }
      ],
      searchData: []
    }
  },
  methods: {
    searchResult (result) {
      this.searchData = result
    }
  },
  created () {
    this.searchData = this.data
  }
}
</script>

<style scoped>

</style>
