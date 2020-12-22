<template>
  <div class="dashboard-editor-container">

    <panel-group @handleSetLineChartData="handleSetLineChartData" />

    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <h4>前12个月工单量</h4>
      <div style="height: 10px"></div>
      <div class="chart-wrapper" v-if="!!monthData && monthData.length > 0">
        <bar-chart :month-data="monthData" :month-str-list="monthStrList" />
      </div>
    </el-row>

  </div>
</template>

<script>
import PanelGroup from './dashboard/PanelGroup'
import LineChart from './dashboard/LineChart'
import RaddarChart from './dashboard/RaddarChart'
import PieChart from './dashboard/PieChart'
import BarChart from './dashboard/BarChart'


const lineChartData = {
  '2020-08': 200,
  '2020-07': 20,
  '2020-06': 150,
  '2020-05': 20,
  '2020-04': 120,
  '2020-03': 20,
  '2020-02': 20,
  '2020-01': 20,
  '2019-12': 20,
  '2019-11': 90,
  '2019-10': 20,
  '2019-09': 209,
}

export default {
  name: 'Index',
  components: {
    PanelGroup,
    LineChart,
    RaddarChart,
    PieChart,
    BarChart
  },
  data() {
    return {
      lineChartData: lineChartData,
      monthStrList: this.getNear12Month(),
      //[30, 52, 200, 334, 390, 330, 220, 200, 334, 390, 330, 220]
      monthData: []
    }
  },
  created() {
    this.getMonthCountList(this.getNear12Month())
  },
  methods: {
    handleSetLineChartData(type) {
      this.lineChartData = lineChartData[type]
    },
    getMonthCountList(monthStrList) {

    },

    // 返回前12个月的yyyy-mm格式数据，倒序
    getNear12Month() {
      let dataArr = [];
      let data = new Date();
      let year = data.getFullYear();
      data.setMonth(data.getMonth()+1, 1)           //获取到当前月份,设置月份
      for (let i = 0; i < 12; i++) {
        data.setMonth(data.getMonth() - 1);     //每次循环一次 月份值减1
        let m = data.getMonth() + 1;
        m = m < 10 ? "0" + m : m;
        dataArr.push(data.getFullYear() + "-" + (m));
      }
      return dataArr.reverse();
    },
  }
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

@media (max-width:1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>
