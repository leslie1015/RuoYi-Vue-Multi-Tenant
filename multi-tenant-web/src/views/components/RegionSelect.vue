<template>
  <div id="regionSelect">
    <el-cascader  placeholder="请搜索选择" clearable filterable size="large" style="width: 100%" :options="options"
                 v-model="selectedOptions" ></el-cascader>
  </div>
</template>

<script>
  import {CodeToText, regionData} from 'element-china-area-data'

  export default {
    name: "RegionSelect",
    props: {
      value: String
    },
    computed: {
      selectedOptions: {
        get() {
          if (!!!this.value) {
            return undefined;
          }
          const v = this.value
          return v.split(",")
        },
        set(val) {
          let result = '';
          // debugger
          if (!!val) {
            for (let i = 0; i < val.length; i++) {
              if (i !== val.length - 1) {
                result = result + val[i] + ",";
              } else {
                result = result + val[i];
              }
            }
          }
          this.$emit('input', result)
        }
      }
    },
    data() {
      return {
        options: regionData,
      }

    },
    methods: {
      handleChange (value) {
        // value值为区域码
        console.log(value)
        // 用CodeToText转换成中文
        console.log(CodeToText[value[0]])
        console.log(CodeToText[value[1]])
        console.log(CodeToText[value[2]])
      },
    }
  }
</script>

<style scoped>

</style>
