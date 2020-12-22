import request from '@/utils/request'

// 查询权限模板列表
export function listTemplate(query) {
  return request({
    url: '/system/template/list',
    method: 'get',
    params: query
  })
}

// 查询权限模板详细
export function getTemplate(id) {
  return request({
    url: '/system/template/' + id,
    method: 'get'
  })
}

// 新增权限模板
export function addTemplate(data) {
  return request({
    url: '/system/template',
    method: 'post',
    data: data
  })
}

// 修改权限模板
export function updateTemplate(data) {
  return request({
    url: '/system/template',
    method: 'put',
    data: data
  })
}

// 删除权限模板
export function delTemplate(id) {
  return request({
    url: '/system/template/' + id,
    method: 'delete'
  })
}

// 导出权限模板
export function exportTemplate(query) {
  return request({
    url: '/system/template/export',
    method: 'get',
    params: query
  })
}

// 查询所有菜单
export function listAllMenus(tempId) {
  return request({
    url: '/system/menu/allMenus/' + tempId,
    method: 'get',
    // params: query
  })
}
