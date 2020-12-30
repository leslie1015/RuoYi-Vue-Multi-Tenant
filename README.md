# RuoYi-Vue-Multi-Tenant

#### 介绍
基于RuoYi-Vue扩展的多租户框架（SpringBoot，Spring Security，JWT，Vue & Element 的前后端分离权限管理系统）

#### 环境准备

1.clone项目到本地

2.找到项目目录下脚本文件 /ruo-yi-vue-multi-tenant/ruoyi/multi_tenant.sql，导入到mysql

3.修改配置（未列出的配置请根据实际情况自行按需调整）

```properties
# datasource
spring.datasource.druid.master.url=jdbc:mysql://127.0.0.1:3306/mt?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8
spring.datasource.druid.master.username=root
spring.datasource.druid.master.password=123456
```

```properties
# redis
spring.redis.host=127.0.0.1
spring.redis.port=6379
#spring.redis.password=
```

如果使用阿里云OSS存储，可以修改以下配置，否则忽略或删除（删除时检查启动是否有影响）

```properties
# aliyun oss
aliyun.oss.endpoint = http://oss-cn-shanghai.aliyuncs.com
aliyun.oss.accessKeyId = xxxxxxxxxx
aliyun.oss.accessKeySecret = xxxxxxxxxx
aliyun.oss.defaultBucketName = xxxxxxxxxx
aliyun.oss.endpointContent = oss-cn-shanghai.aliyuncs.com
```

4.前端代码导入后，npm install / cnpm install 安装依赖，修改vue.config.js中devServer.proxy.target为后端地址

```javascript
devServer: {
  host: '0.0.0.0',
  port: port,
  proxy: {
    [process.env.VUE_APP_BASE_API]: {
      target: `http://127.0.0.1:8080`,
      changeOrigin: true,
      pathRewrite: {
        ['^' + process.env.VUE_APP_BASE_API]: ''
      }
    }
  }
```

5.npm run dev启动前端

构建测试环境： npm run build:stage

构建生产环境：npm run build:prod



#### 功能说明

```sql
-- 超级管理员，拥有系统最高权限
select * from sys_user where super_admin_flag = 1
-- 租户管理员
select * from sys_user where admin_flag = 1

-- 是否超级管理员菜单（0否1是），如创建菜单后，菜单栏不显示，注意检查此字段是否对应正确
select * from sys_menu where super_flag = 1
```

multi_tenant.sql脚本中初始化了超级管理员的账号密码

1.使用admin/admin123登录系统

2.打开 租户管理-权限模板-新增，添加一个权限模板，指定模板拥有的菜单权限

![超级管理员-新增权限模板](https://images.gitee.com/uploads/images/2020/1221/165014_a69e2bdd_2024853.png "image-20201221160550454.png")

3.打开 租户管理-租户列表-新增，新增一个租户，选择刚才的权限模板

![超级管理员-创建租户](https://images.gitee.com/uploads/images/2020/1221/165113_8a7a7217_2024853.png "image-20201221160752827.png")

4.登出系统，使用刚才创建的租户账号重新登录系统，默认账号是上图中填写的手机号 13866688888，默认密码是123456.

```properties
# 默认的租户密码
sys.default.pwd=123456.
```

登录进入租户首页
![租户管理员-首页](https://images.gitee.com/uploads/images/2020/1221/165142_6393d144_2024853.png "image-20201221161209345.png")
租户管理员可以创建角色，并且给角色分配该租户已有的权限，然后创建租户下的用户，并分配对应角色


#### 其它

1.业务代码生成改为默认生成到business目录，与系统相关功能区分开

2.暂未支持岗位与部门，仅通过角色来控制权限

3.代码中有一些业务名词没有完全改过来，比如超管首页、租户/公司、首页的工单信息、icon等，时间原因，后续会逐步调整，当前如有需要请自行修改

4.感谢[RuoYi](https://gitee.com/y_project) ，原项目地址 https://gitee.com/y_project/RuoYi-Vue

5.如有疑问，或者遇到bug，欢迎联系 wangyy866@163.com
