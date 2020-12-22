package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.SysTemplate;

/**
 * 权限模板Service接口
 * 
 * @author leslie1015
 * @date 2020-07-09
 */
public interface ISysTemplateService 
{
    /**
     * 查询权限模板
     * 
     * @param id 权限模板ID
     * @return 权限模板
     */
    public SysTemplate selectSysTemplateById(String id);

    /**
     * 查询权限模板列表
     * 
     * @param sysTemplate 权限模板
     * @return 权限模板集合
     */
    public List<SysTemplate> selectSysTemplateList(SysTemplate sysTemplate);

    /**
     * 新增权限模板
     * 
     * @param sysTemplate 权限模板
     * @return 结果
     */
    public int insertSysTemplate(SysTemplate sysTemplate);

    /**
     * 修改权限模板
     * 
     * @param sysTemplate 权限模板
     * @return 结果
     */
    public int updateSysTemplate(SysTemplate sysTemplate);

    /**
     * 批量删除权限模板
     * 
     * @param ids 需要删除的权限模板ID
     * @return 结果
     */
    public int deleteSysTemplateByIds(String[] ids);

    /**
     * 删除权限模板信息
     * 
     * @param id 权限模板ID
     * @return 结果
     */
    public int deleteSysTemplateById(String id);
}
