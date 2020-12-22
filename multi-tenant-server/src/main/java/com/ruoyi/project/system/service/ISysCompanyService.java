package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.SysCompany;

/**
 * 公司信息Service接口
 * 
 * @author leslie1015
 * @date 2020-07-11
 */
public interface ISysCompanyService 
{
    /**
     * 查询公司信息
     * 
     * @param id 公司信息ID
     * @return 公司信息
     */
    public SysCompany selectSysCompanyById(String id);

    /**
     * 查询公司信息列表
     * 
     * @param sysCompany 公司信息
     * @return 公司信息集合
     */
    public List<SysCompany> selectSysCompanyList(SysCompany sysCompany);

    /**
     * 新增公司信息
     * 
     * @param sysCompany 公司信息
     * @return 结果
     */
    public int insertSysCompany(SysCompany sysCompany);

    /**
     * 修改公司信息
     * 
     * @param sysCompany 公司信息
     * @return 结果
     */
    public int updateSysCompany(SysCompany sysCompany);

    /**
     * 批量删除公司信息
     * 
     * @param ids 需要删除的公司信息ID
     * @return 结果
     */
    public int deleteSysCompanyByIds(String[] ids);

    /**
     * 删除公司信息信息
     * 
     * @param id 公司信息ID
     * @return 结果
     */
    public int deleteSysCompanyById(String id);
}
