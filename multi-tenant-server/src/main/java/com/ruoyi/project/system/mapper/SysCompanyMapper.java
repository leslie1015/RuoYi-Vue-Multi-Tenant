package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.SysCompany;
import org.apache.ibatis.annotations.Param;

/**
 * 公司信息Mapper接口
 * 
 * @author ruoyi
 * @date 2020-07-11
 */
public interface SysCompanyMapper 
{
    /**
     * 查询公司信息
     * 
     * @param id 公司信息ID
     * @return 公司信息
     */
    public SysCompany selectSysCompanyById(String id);

    SysCompany getByCode(@Param("comCode") String comCode);

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
     * 删除公司信息
     * 
     * @param id 公司信息ID
     * @return 结果
     */
    public int deleteSysCompanyById(String id);

    /**
     * 批量删除公司信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysCompanyByIds(String[] ids);

    Integer countByComCode(@Param("comCode") Integer comCode);

    Integer countByTempId(@Param("tempId") String tempId);

}
