
export default {
  install(Vue) {

    // 全局域名
    // Vue.prototype.domainSite = 'http://127.0.0.1/dev-api'
    Vue.prototype.domainSite = 'https://www.njntyl.com/prod-api'

    /**
     * 根据url参数名取参数值
     *
     * @param name
     * @returns {string}
     */
    Vue.prototype.getQueryString = function(name) {
      const reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
      const r = window.location.search.substr(1).match(reg);
      if (r != null) {
        return unescape(r[2]);
      }
      return "";
    };

  }
};
