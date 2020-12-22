package com.ruoyi.project.system.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.system.domain.SysTemplate;
import com.ruoyi.project.system.service.ISysTemplateService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 权限模板Controller
 *
 * @author leslie1015
 * @date 2020-07-09
 */
@RestController
@RequestMapping("/system/template")
public class SysTemplateController extends BaseController {

  @Autowired
  private ISysTemplateService sysTemplateService;

  /**
   * 查询权限模板列表
   */
  @PreAuthorize("@ss.hasPermi('system:template:list')")
  @GetMapping("/list")
  public TableDataInfo list(SysTemplate sysTemplate) {
    startPage();
    List<SysTemplate> list = sysTemplateService.selectSysTemplateList(sysTemplate);
    return getDataTable(list);
  }

  /**
   * 导出权限模板列表
   */
  @PreAuthorize("@ss.hasPermi('system:template:export')")
  @Log(title = "权限模板", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public AjaxResult export(SysTemplate sysTemplate) {
    List<SysTemplate> list = sysTemplateService.selectSysTemplateList(sysTemplate);
    ExcelUtil<SysTemplate> util = new ExcelUtil<SysTemplate>(SysTemplate.class);
    return util.exportExcel(list, "template");
  }

  /**
   * 获取权限模板详细信息
   */
  @PreAuthorize("@ss.hasPermi('system:template:query')")
  @GetMapping(value = "/{id}")
  public AjaxResult getInfo(@PathVariable("id") String id) {
    return AjaxResult.success(sysTemplateService.selectSysTemplateById(id));
  }

  /**
   * 新增权限模板
   */
  @PreAuthorize("@ss.hasPermi('system:template:add')")
  @Log(title = "权限模板", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody SysTemplate sysTemplate) {
    return toAjax(sysTemplateService.insertSysTemplate(sysTemplate));
  }

  /**
   * 修改权限模板
   */
  @PreAuthorize("@ss.hasPermi('system:template:edit')")
  @Log(title = "权限模板", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(@RequestBody SysTemplate sysTemplate) {
    return toAjax(sysTemplateService.updateSysTemplate(sysTemplate));
  }

  /**
   * 删除权限模板
   */
  @PreAuthorize("@ss.hasPermi('system:template:remove')")
  @Log(title = "权限模板", businessType = BusinessType.DELETE)
  @DeleteMapping("/{ids}")
  public AjaxResult remove(@PathVariable String[] ids) {
    return toAjax(sysTemplateService.deleteSysTemplateByIds(ids));
  }
}
