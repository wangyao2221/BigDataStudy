<template>
  <div id="searchComponent" style="height: 50px">
    <div v-if="config && data">
      <el-button :size="config.size" type="primary" class="pull-right search-button" @click="handleSearch">查询</el-button>
      <div v-for="(item,index) in configItems" :key="index">
        <el-input
          :size="config.size"
          v-if="item.type === 'input' && (item.method === 'equals' || item.method === 'contains')"
          class="input-size pull-right search-item"
          :style="'width:' + item.width + 'px'"
          v-model="item.value"
          :label="item.label"
          :placeholder="item.placeholder == null || item.placeholder === '' ? label : item.placeholder">
        </el-input>
        <div class="el-input el-input--mini pull-right input-range search-item" style="width: 145px" v-if="item.type === 'input' && (item.method === 'range')">
          <div class="range-label">{{ item.label + ':' }}</div>
          <div style="width: 50px;" class="pull-left"><el-input v-model="item.min" class="no-border" placeholder="min"></el-input></div>
          <div style="width: 50px" class="pull-left"><el-input v-model="item.max" placeholder="max"></el-input></div>
        </div>
        <el-date-picker
          :size="config.size"
          class="input-size pull-right search-item"
          v-if="item.type === 'date' && (item.method === 'range')"
          v-model="item.value"
          type="daterange"
          range-separator="至"
          :start-placeholder="item.startPlaceholder == null || item.startPlaceholder === '' ? '请选择开始时间' : item.startPlaceholder"
          :end-placeholder="item.endPlaceholder == null || item.endPlaceholder === '' ? '请选择结束时间' : item.endPlaceholder">
        </el-date-picker>
        <el-date-picker
          :size="config.size"
          class="input-size pull-right search-item"
          v-if="item.type === 'date' && (item.method === 'equals')"
          v-model="item.value"
          range-separator="至"
          :placeholder="item.placeholder == null || item.placeholder === '' ? '请选择时间' : item.placeholder">
        </el-date-picker>
        <el-select
          :size="config.size"
          class="input-size pull-right search-item"
          v-model="item.value"
          :placeholder="item.placeholder == null || item.placeholder === '' ? label : item.placeholder"
          v-if="item.type === 'select'">
          <el-option
            v-for="option in item.options"
            :key="option"
            :value="option"
            :label="option">
          </el-option>
        </el-select>
      </div>
      <!--   没有这一行渲染会出问题？   -->
      <div hidden="true">{{ this.searchData.length }}</div>
    </div>
    <div v-if="!config">搜索组件配置信息未给定</div>
    <div v-if="!data">搜索组件数据源未给定</div>
  </div>
</template>
<script>
export default {
  name: 'SearchComponent',
  props: ['config', 'data'],
  data () {
    return {
      searchData: [],
      configItems: []
    }
  },
  created () {
    window.vue = this
  },
  mounted () {
    Object.assign(this.$data, this.$options.data())
    this.searchData = this.data
    this.configItems = this.config.items.reverse()
  },
  methods: {
    search () {
      this.searchData = []

      for (let i in this.data) {
        let dataItem = this.data[i]
        let isSearchItem = true

        for (let j in this.config.items) {
          let searchItem = this.config.items[j]
          let searchMethod = searchItem.type + '-' + searchItem.method
          let searchColData = dataItem[searchItem.key]

          if (searchMethod === 'input-range') {
            searchItem.value = [searchItem.min, searchItem.max]
          } else if (searchMethod.indexOf('date') > -1) {
            searchColData = searchColData == null ? null : new Date(searchColData + ' ')
          }

          let searchContent = searchItem.value

          if (searchContent != null) {
            if (searchMethod.indexOf('equals') > -1) {
              isSearchItem = this.equals(searchColData, searchContent)
            } else if (searchMethod.indexOf('contains') > -1) {
              isSearchItem = this.contains(searchColData, searchContent)
            } else if (searchMethod.indexOf('range') > -1) {
              isSearchItem = this.range(searchColData, searchContent)
            } else {
              isSearchItem = this.equals(searchColData, searchContent)
            }
          }

          if (isSearchItem === false) {
            break
          }
        }

        if (isSearchItem) {
          this.searchData.push(dataItem)
        }
      }
    },
    handleSearch () {
      this.search()
      this.$emit('searchResult', this.searchData)
    },
    equals (data, searchContent) {
      return searchContent === '' || searchContent == null || data + '' === searchContent + ''
    },
    contains (data, searchContent) {
      return searchContent === '' || searchContent == null || (data + '').indexOf(searchContent + '') > -1
    },
    range (data, searchContent) {
      // console.log(data)
      // console.log(searchContent)
      return searchContent == null ||
        (searchContent[0] === '' || searchContent[0] == null) ||
        (searchContent[1] === '' || searchContent[1] == null) ||
        (searchContent[0] < data && data < searchContent[1])
    }
  }
}
</script>

<style>
.search-item {
  margin: 10px 5px;
}

.search-button {
  margin: 10px 5px;
}

.input-size {
  width: 145px;
}

.pull-right{
  float: right;
}

.pull-left {
  float: left;
}

.range-label {
  width: 32px;
  float: left;
  margin-top: 4px;
  margin-left: 5px;
  color: #909399;
}

.input-range {
  border-radius: 4px;
  border: 1px solid #dcdfe6;
}

.input-range > div > .el-input > input {
  border: 0 !important;
  height: 26px !important;
  padding-left: 3px !important;
  padding-right: 3px !important;
}
</style>
