import Vue from 'vue'

import Cookies from 'js-cookie'

import 'normalize.css/normalize.css' // a modern alternative to CSS resets

import Element from 'element-ui'
import './assets/styles/element-variables.scss'

import '@/assets/styles/index.scss' // global css
import '@/assets/styles/ruoyi.scss' // ruoyi css
import App from './App'
import store from './store'
import router from './router'
import permission from './directive/permission'

import './assets/icons' // icon
import './permission' // permission control
import { getDicts } from "@/api/system/dict/data";
import { getConfigKey } from "@/api/system/config";
import { parseTime, resetForm, addDateRange, selectDictLabel, download, handleTree, getObjFromArray } from "@/utils/ruoyi";
import Pagination from "@/components/Pagination";

// 全局方法挂载
Vue.prototype.getDicts = getDicts
Vue.prototype.getConfigKey = getConfigKey
Vue.prototype.parseTime = parseTime
Vue.prototype.resetForm = resetForm
Vue.prototype.addDateRange = addDateRange
Vue.prototype.selectDictLabel = selectDictLabel
Vue.prototype.download = download
Vue.prototype.handleTree = handleTree
Vue.prototype.getObjFromArray = getObjFromArray

Vue.prototype.msgSuccess = function (msg) {
  this.$message({ showClose: true, message: msg, type: "success" });
}

Vue.prototype.msgError = function (msg) {
  this.$message({ showClose: true, message: msg, type: "error" });
}

Vue.prototype.msgInfo = function (msg) {
  this.$message.info(msg);
}

import base from "./utils/base.js"
Vue.use(base);

// 全局组件挂载
Vue.component('Pagination', Pagination)

Vue.use(permission)

/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online! ! !
 */

Vue.use(Element, {
  size: Cookies.get('size') || 'medium' // set element-ui default size
})

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})

import moment from "moment";
Vue.filter("dateFormat", function(dataStr, pattern) {
  if (!!!dataStr) {
    return '';
  }
  if (!!!pattern) {
    pattern = "YYYY-MM-DD HH:mm:ss";
  }
  return moment(dataStr).format(pattern);
});

/*
Vue.filter('formatTime',function(time){
  if(time){
    var date=new Date(time);
    var difftime = Math.abs(new Date() - date)
    // 获取当前时间的年月
    var nowyear = date.getFullYear();
    var nowmonth = date.getMonth+1;
    var yearAllday = 0;
    var monthAllday = 0;
    // 判断是否为闰年
    if((nowyear%4===0&& nowyear%100!==0) || nowyear%400===0){
      yearAllday = 366
    }else{
      yearAllday = 365
    }
    // 每个月的天数
    if(yearAllday === 366 && nowmonth === 2){
      monthAllday = 29
    }else if(yearAllday === 365 && nowmonth === 2){
      monthAllday = 28
    }
    if(nowmonth === 4 || nowmonth === 6 || nowmonth === 9 || nowmonth === 11){
      monthAllday = 30
    }else {
      monthAllday = 31
    }
    if(difftime > yearAllday*24*3600*1000){
      var yearNum = Math.floor(difftime/ (yearAllday*24*3600*1000))
      return yearNum + "年前"
    }else if(difftime >  monthAllday * 24 * 3600 * 1000 && difftime< yearAllday*24*3600*1000){
      var monthNum = Math.floor(difftime/(monthAllday*24*60*60*1000))
      // 拼接
      return monthNum + "月前";
    }else if(difftime>7*24*60*60*1000 && difftime && difftime < monthAllday*24*60*60*1000){
      var weekNum = Math.floor(difftime/(7*24*3600*1000))
      return weekNum+"周前";
    }else if(difftime < 7 * 24 * 3600 * 1000 && difftime > 24 * 3600 * 1000){
      // //注释("一周之内");
      // var time = newData - diffTime;
      var dayNum = Math.floor(difftime / (24 * 60 * 60 * 1000));
      return dayNum + "天前";
    } else if (difftime < 24 * 3600 * 1000 && difftime > 3600 * 1000) {
      // //注释("一天之内");
      // var time = newData - diffTime;
      var dayNum = Math.floor(difftime / (60 * 60 * 1000));
      return dayNum + "小时前";

    } else if (difftime < 3600 * 1000 && difftime > 0) {
      // //注释("一小时之内");
      // var time = newData - diffTime;
      var dayNum = Math.floor(difftime / (60 * 1000));
      return dayNum + "分钟前";
    }
  }
})*/


