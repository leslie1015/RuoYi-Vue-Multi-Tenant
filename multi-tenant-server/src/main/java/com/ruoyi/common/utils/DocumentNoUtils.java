package com.ruoyi.common.utils;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.enums.BillType;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.project.system.domain.SysCompany;
import com.ruoyi.project.system.mapper.SysCompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DocumentNoUtils {

    @Autowired
    private SysCompanyMapper sysCompanyMapper;

    @Autowired
    private RedisCache redisCache;

    public String genDocumentNo(BillType billType, String comId) {
        StringBuilder ducomentNo = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        SysCompany sysCompany = sysCompanyMapper.selectSysCompanyById(comId);
        if (null == sysCompany || null == sysCompany.getComCode()) {
            throw new CustomException("未查询到公司单据前缀");
        }
        ducomentNo.append(billType.getCode()).append(sysCompany.getComCode()).append(sdf.format(new Date()));

        String documentSuffixRedisKey = Constants.DOCUMENT_REDIS_KEY_COMMON_PRE +":" +  billType.getCode() + ":" + sysCompany.getComCode();

        //从redis中取出后缀, 用单据前缀字母+公司唯一个单据编号，确保取出的每个公司每种单据后缀都从0自增
        // redisTemplate的incr默认自增第一个值是0，所以加1，让初始值为1
        Long documentSuffix = redisCache.incrByKey(documentSuffixRedisKey) + 1;

        return ducomentNo.append(String.format("%03d", documentSuffix)).toString();
    }
}
