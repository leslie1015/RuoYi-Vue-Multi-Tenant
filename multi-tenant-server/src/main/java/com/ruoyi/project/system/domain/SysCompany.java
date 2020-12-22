package com.ruoyi.project.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 公司信息对象 sys_company
 * 
 * @author ruoyi
 * @date 2020-07-11
 */
@Data
public class SysCompany extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 公司名 */
    @Excel(name = "公司名")
    private String companyName;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 有效截止时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效截止时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date activeTime;

    /** 是否激活 */
    @Excel(name = "是否激活")
    private Integer activeFlag;

    /** 模板ID */
    @Excel(name = "模板ID")
    private String tempId;

    /**
     * 公司代码
     */
    private Integer comCode;

    /**
     * 模板名
     */
    private String tempName;

    /**
     * 行政区域编码
     */
    private String regionCodes;

}
