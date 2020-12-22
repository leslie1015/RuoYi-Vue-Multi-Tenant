package com.ruoyi.project.system.service.impl;

import com.ruoyi.project.system.mapper.SysCompanyMapper;
import java.util.List;

import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.KeyUtils;
import com.ruoyi.project.system.domain.SysTempMenu;
import com.ruoyi.project.system.mapper.SysTempMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.SysTemplateMapper;
import com.ruoyi.project.system.domain.SysTemplate;
import com.ruoyi.project.system.service.ISysTemplateService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * 权限模板Service业务层处理
 *
 * @author ruoyi
 * @date 2020-07-09
 */
@Service
public class SysTemplateServiceImpl implements ISysTemplateService {

  @Autowired
  private SysTemplateMapper sysTemplateMapper;

  @Autowired
  private SysTempMenuMapper sysTempMenuMapper;


  @Autowired
  private SysCompanyMapper sysCompanyMapper;

  /**
   * 查询权限模板
   *
   * @param id 权限模板ID
   * @return 权限模板
   */
  @Override
  public SysTemplate selectSysTemplateById(String id) {
    return sysTemplateMapper.selectSysTemplateById(id);
  }

  /**
   * 查询权限模板列表
   *
   * @param sysTemplate 权限模板
   * @return 权限模板
   */
  @Override
  public List<SysTemplate> selectSysTemplateList(SysTemplate sysTemplate) {
    return sysTemplateMapper.selectSysTemplateList(sysTemplate);
  }

  /**
   * 新增权限模板
   *
   * @param sysTemplate 权限模板
   * @return 结果
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public int insertSysTemplate(SysTemplate sysTemplate) {
    sysTemplate.setId(KeyUtils.genUniqueKey());
    List<Integer> menuIds = sysTemplate.getMenuIds();
    if (CollectionUtils.isEmpty(menuIds)) {
      throw new CustomException("权限不能为空");
    }
    resetSysTempMenus(sysTemplate, menuIds);
    return sysTemplateMapper.insertSysTemplate(sysTemplate);

  }

  /**
   * 修改权限模板
   *
   * @param sysTemplate 权限模板
   * @return 结果
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public int updateSysTemplate(SysTemplate sysTemplate) {
    List<Integer> menuIds = sysTemplate.getMenuIds();
    if (CollectionUtils.isEmpty(menuIds)) {
      throw new CustomException("权限不能为空");
    }
    resetSysTempMenus(sysTemplate, menuIds);
    return sysTemplateMapper.updateSysTemplate(sysTemplate);
  }

  /**
   * 重置模板对应的菜单
   */
  private void resetSysTempMenus(SysTemplate sysTemplate, List<Integer> menuIds) {
    sysTempMenuMapper.deleteByTempId(sysTemplate.getId());
    SysTempMenu sysTempMenu = null;
    for (Integer menuId : menuIds) {
      sysTempMenu = new SysTempMenu();
      sysTempMenu.setTempId(sysTemplate.getId());
      sysTempMenu.setMenuId(menuId);
      sysTempMenuMapper.insert(sysTempMenu);
    }
  }


  /**
   * 批量删除权限模板
   *
   * @param ids 需要删除的权限模板ID
   * @return 结果
   */
  @Override
  public int deleteSysTemplateByIds(String[] ids) {
    for (String tempId : ids) {
      checkDeleteTemp(tempId);
    }
    return sysTemplateMapper.deleteSysTemplateByIds(ids);
  }

  private void checkDeleteTemp(String tempId) {
    Integer count = sysCompanyMapper.countByTempId(tempId);
    if (null != count && count > 0) {
      SysTemplate sysTemplate = sysTemplateMapper.selectSysTemplateById(tempId);
      throw new CustomException(sysTemplate.getName() + "下存在公司，请重新分配模板后再尝试删除");
    }
  }

  /**
   * 删除权限模板信息
   *
   * @param id 权限模板ID
   * @return 结果
   */
  @Override
  public int deleteSysTemplateById(String id) {
    checkDeleteTemp(id);
    return sysTemplateMapper.deleteSysTemplateById(id);
  }
}
