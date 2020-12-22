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
import com.ruoyi.project.system.domain.SysCompany;
import com.ruoyi.project.system.service.ISysCompanyService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 公司信息Controller
 *
 * @author leslie1015
 * @date 2020-07-11
 */
@RestController
@RequestMapping("/system/company")
public class SysCompanyController extends BaseController {

  @Autowired
  private ISysCompanyService sysCompanyService;

  /**
   * 查询公司信息列表
   */
  @PreAuthorize("@ss.hasPermi('system:company:list')")
  @GetMapping("/list")
  public TableDataInfo list(SysCompany sysCompany) {
    checkSuperAdmin();
    startPage();
    List<SysCompany> list = sysCompanyService.selectSysCompanyList(sysCompany);
    return getDataTable(list);
  }

  /**
   * 导出公司信息列表
   */
  @PreAuthorize("@ss.hasPermi('system:company:export')")
  @Log(title = "公司信息", businessType = BusinessType.EXPORT)
  @GetMapping("/export")
  public AjaxResult export(SysCompany sysCompany) {
    checkSuperAdmin();
    List<SysCompany> list = sysCompanyService.selectSysCompanyList(sysCompany);
    ExcelUtil<SysCompany> util = new ExcelUtil<SysCompany>(SysCompany.class);
    return util.exportExcel(list, "company");
  }

  /**
   * 获取公司信息详细信息
   */
  @PreAuthorize("@ss.hasPermi('system:company:query')")
  @GetMapping(value = "/{id}")
  public AjaxResult getInfo(@PathVariable("id") String id) {
    return AjaxResult.success(sysCompanyService.selectSysCompanyById(id));
  }

  /**
   * 新增公司信息
   */
  @PreAuthorize("@ss.hasPermi('system:company:add')")
  @Log(title = "公司信息", businessType = BusinessType.INSERT)
  @PostMapping
  public AjaxResult add(@RequestBody SysCompany sysCompany) {
    checkSuperAdmin();
    return toAjax(sysCompanyService.insertSysCompany(sysCompany));
  }

  /**
   * 修改公司信息
   */
  @PreAuthorize("@ss.hasPermi('system:company:edit')")
  @Log(title = "公司信息", businessType = BusinessType.UPDATE)
  @PutMapping
  public AjaxResult edit(@RequestBody SysCompany sysCompany) {

    return toAjax(sysCompanyService.updateSysCompany(sysCompany));
  }

  /**
   * 删除公司信息
   */
  @PreAuthorize("@ss.hasPermi('system:company:remove')")
  @Log(title = "公司信息", businessType = BusinessType.DELETE)
  @DeleteMapping("/{ids}")
  public AjaxResult remove(@PathVariable String[] ids) {
    checkSuperAdmin();
    return toAjax(sysCompanyService.deleteSysCompanyByIds(ids));
  }
}
