<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="公司名" prop="companyName">
        <el-input
          v-model="queryParams.companyName"
          placeholder="请输入公司名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="公司代码" prop="companyName">
        <el-input
          v-model="queryParams.comCode"
          placeholder="请输入公司代码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="有效截止时间" prop="activeTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.activeTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择有效截止时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="是否激活" prop="activeFlag">
        <el-select v-model="queryParams.activeFlag" placeholder="请选择是否激活" clearable size="small">
          <el-option :key="0" label="否" :value="0"/>
          <el-option :key="1" label="是" :value="1"/>
        </el-select>
      </el-form-item>
      <el-form-item label="模板" prop="tempName">
        <el-input
          v-model="queryParams.tempName"
          placeholder="请输入模板名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:company:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:company:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:company:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:company:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table border v-loading="loading" :data="companyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="公司名" align="center" prop="companyName" />
      <el-table-column label="公司代码" align="center" prop="comCode" />
<!--      <el-table-column label="地址" align="center" prop="address" />-->
      <el-table-column label="电话" align="center" prop="phone" />
      <el-table-column label="邮箱" align="center" prop="email" />
      <el-table-column label="有效截止时间" align="center" prop="activeTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.activeTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否激活" align="center" prop="activeFlag" :formatter="activeFlagFormat" />
      <el-table-column label="模板" align="center" prop="tempName" />
      <el-table-column fixed="left" label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:company:edit']"
          >修改</el-button>
<!--          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:company:remove']"
          >删除</el-button>-->
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改公司信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="公司名" prop="companyName">
          <el-input v-model="form.companyName" placeholder="请输入公司名" />
        </el-form-item>
        <el-form-item label="地址" prop="regionCodes">
          <RegionSelect v-model="form.regionCodes"></RegionSelect>
        </el-form-item>
        <el-form-item label="" prop="address">
          <el-input type="textarea" v-model="form.address" placeholder="请输入详细地址" />
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="form.phone" placeholder="默认为管理员账号的电话" />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="form.email" placeholder="请输入邮箱" />
        </el-form-item>
        <el-form-item label="有效截止时间" prop="activeTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.activeTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择有效截止时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="是否激活">
          <el-radio-group v-model="form.activeFlag">
            <el-radio :key="1" :label="1">激活</el-radio>
            <el-radio :key="0" :label="0">禁用</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="权限模板" prop="tempId">
          <el-select v-model="form.tempId" filterable placeholder="请选择模板">
            <el-option
              v-for="item in this.templateList"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCompany, getCompany, delCompany, addCompany, updateCompany, exportCompany, listAllTemps } from "@/api/system/company";
import RegionSelect from "../../components/RegionSelect";

export default {
  name: "Company",
  components: {RegionSelect},
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 公司信息表格数据
      companyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //所有权限模板
      templateList:[],
      // 是否激活字典
      activeFlagOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        companyName: undefined,
        comCode: undefined,
        activeTime: undefined,
        activeFlag: undefined,
        tempName: undefined
      },
      // 表单参数
      form: {
        activeFlag: 1
      },
      // 表单校验
      rules: {
        phone: [
          { required: true, message: "手机号不能为空", trigger: "blur" },
          {
            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
            message: "请输入正确的手机号码",
            trigger: "blur"
          }
        ],
        companyName: [
          { required: true, message: "公司名不能为空", trigger: "blur" }
        ],
        email: [
          { required: true, message: "邮箱不能为空", trigger: "blur" },

        ],
        tempId: [
          { required: true, message: "权限模板不能为空", trigger: "blur" }
        ],
        activeTime: [
          { required: true, message: "有效截止时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("sys_flag").then(response => {
      this.activeFlagOptions = response.data;
    });
  },
  methods: {
    /** 查询公司信息列表 */
    getList() {
      this.loading = true;
      listCompany(this.queryParams).then(response => {
        this.companyList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 是否激活字典翻译
    activeFlagFormat(row, column) {
      let show = "";
      if (row.activeFlag === 0) {
        show = "否";
      } else if (row.activeFlag === 1) {
        show = "是";
      }
      return show;
      // return this.selectDictLabel(this.activeFlagOptions, row.activeFlag);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },

    // 表单重置
    reset() {
      this.form = {
        id: undefined,
        companyName: undefined,
        address: undefined,
        phone: undefined,
        email: undefined,
        createTime: undefined,
        activeTime: undefined,
        activeFlag: "0",
        tempId: undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      // 新增时默认公司为激活状态
      this.form.activeFlag = 1;
      this.open = true;
      this.title = "添加公司信息";
      this.initTemplates();
    },

    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCompany(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改公司信息";
        this.initTemplates();
      });
    },

    initTemplates() {
      // 查询所有可选模板
      listAllTemps().then(response => {
        this.templateList = response.rows;
      });
    },

    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateCompany(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addCompany(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除公司信息编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delCompany(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有公司信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportCompany(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
