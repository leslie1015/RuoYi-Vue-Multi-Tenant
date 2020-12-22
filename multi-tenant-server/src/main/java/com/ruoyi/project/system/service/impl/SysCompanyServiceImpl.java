package com.ruoyi.project.system.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.KeyUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.mapper.SysUserMapper;
import com.ruoyi.project.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.SysCompanyMapper;
import com.ruoyi.project.system.domain.SysCompany;
import com.ruoyi.project.system.service.ISysCompanyService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 公司信息Service业务层处理
 * 
 * @author leslie1015
 * @date 2020-07-11
 */
@Service
public class SysCompanyServiceImpl implements ISysCompanyService 
{
    @Autowired
    private SysCompanyMapper sysCompanyMapper;


    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private ISysUserService userService;

    @Value("${sys.default.pwd}")
    private String defaultPwd;

    /**
     *  0否1是
     */
    private static final Integer FLAG_NO = 0, FLAG_YES = 1;

    /**
     * 查询公司信息
     * 
     * @param id 公司信息ID
     * @return 公司信息
     */
    @Override
    public SysCompany selectSysCompanyById(String id)
    {
        return sysCompanyMapper.selectSysCompanyById(id);
    }

    /**
     * 查询公司信息列表
     * 
     * @param sysCompany 公司信息
     * @return 公司信息
     */
    @Override
    public List<SysCompany> selectSysCompanyList(SysCompany sysCompany)
    {
        return sysCompanyMapper.selectSysCompanyList(sysCompany);
    }

    /**
     * 新增公司信息
     * 
     * @param sysCompany 公司信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSysCompany(SysCompany sysCompany)
    {
        sysCompany.setId(KeyUtils.genUniqueKey());
        sysCompany.setComCode(getComCode());
        sysCompany.setCreateTime(DateUtils.getNowDate());
        if (null == sysCompany.getActiveFlag()) {
            sysCompany.setActiveFlag(FLAG_YES);
        }
        if (StringUtils.isBlank(sysCompany.getPhone())) {
            throw new CustomException("电话不能为空");
        }
        if (null == sysCompany.getTempId()) {
            throw new CustomException("请选择权限模板");
        }
        if (sysCompanyMapper.insertSysCompany(sysCompany) <= 0) {
            throw new CustomException("添加公司失败");
        }
        return addDefaultUser(sysCompany);
    }

    /**
     * 添加默认用户
     * @param sysCompany
     */
    private Integer addDefaultUser(SysCompany sysCompany) {
        SysUser user = new SysUser();
        user.setUserName(sysCompany.getPhone());
        user.setPhonenumber(sysCompany.getPhone());
        user.setNickName("管理员" + sysCompany.getComCode());
        user.setEmail(sysCompany.getEmail());
        user.setStatus("0");//0正常1停用
        user.setCreateBy("superAdmin");
        user.setCreateTime(new Date());
        user.setRemark("公司管理员账号");
        user.setComId(sysCompany.getId());
        user.setAdminFlag(FLAG_YES);//是否管理员0否1是
        user.setSuperAdminFlag(FLAG_NO);//是否平台超级管理员
        user.setPassword(SecurityUtils.encryptPassword(defaultPwd));
        checkUser(user);
        return userMapper.insertUser(user);
    }

    /**
     * 校验用户信息
     * @param user
     */
    private void checkUser(SysUser user) {
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName())))
        {
            throw new CustomException("新增用户'" + user.getUserName() + "'失败，该登录账号已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            throw new CustomException("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
        {
            throw new CustomException("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
    }

    private synchronized Integer getComCode() {
        Integer comCode = KeyUtils.genComCode();
        Integer count = sysCompanyMapper.countByComCode(comCode);
        if ( null != count && count > 0) {
            return getComCode();
        }
        return comCode;
    }

    /**
     * 修改公司信息
     * 
     * @param sysCompany 公司信息
     * @return 结果
     */
    @Override
    public int updateSysCompany(SysCompany sysCompany)
    {
        return sysCompanyMapper.updateSysCompany(sysCompany);
    }

    /**
     * 批量删除公司信息
     * 
     * @param ids 需要删除的公司信息ID
     * @return 结果
     */
    @Override
    public int deleteSysCompanyByIds(String[] ids)
    {
        return sysCompanyMapper.deleteSysCompanyByIds(ids);
    }

    /**
     * 删除公司信息信息
     * 
     * @param id 公司信息ID
     * @return 结果
     */
    @Override
    public int deleteSysCompanyById(String id)
    {
        return sysCompanyMapper.deleteSysCompanyById(id);
    }
}
