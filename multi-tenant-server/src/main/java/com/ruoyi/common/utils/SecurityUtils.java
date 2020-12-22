package com.ruoyi.common.utils;

import com.ruoyi.project.system.domain.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.framework.security.LoginUser;

/**
 * 安全服务工具类
 *
 * @author ruoyi
 */
public class SecurityUtils {
    /**
     * 获取用户账户
     **/
    public static String getUsername() {
        try {
            return getLoginUser().getUsername();
        } catch (Exception e) {
            throw new CustomException("获取用户账户异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取用户对应公司ID
     **/
    public static String getCurrComId() {
        try {
            return getLoginUser().getUser().getComId();
        } catch (Exception e) {
            throw new CustomException("获取用户公司信息异常", HttpStatus.UNAUTHORIZED);
        }
    }


    /**
     * 获取用户ID
     **/
    public static Long getCurrUserId() {
        try {
            return getLoginUser().getUser().getUserId();
        } catch (Exception e) {
            throw new CustomException("获取用户ID信息异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser() {
        try {
            return (LoginUser) getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new CustomException("获取用户信息异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取用户
     **/
    public static SysUser getSysUser() {
        return getLoginUser().getUser();
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword     真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }


    private static final Integer ADMIN_FLAG_YES = 1;

    /**
     * 是否为管理员
     *
     * @param user 用户
     * @return 结果
     */
    public static boolean isComAdmin(SysUser user) {
        return ADMIN_FLAG_YES.equals(user.getAdminFlag());
    }

    /**
     * 判断当前登录者是否为所在公司的管理员
     * @return
     */
    public static boolean isComAdmin() {
        return ADMIN_FLAG_YES.equals(getSysUser().getAdminFlag());
    }

    /**
     * 是否为平台超级管理员
     * @return
     */
    public static boolean isSuperAdmin(SysUser user) {
        return ADMIN_FLAG_YES.equals(user.getSuperAdminFlag());
    }

    /**
     * 登陆者是否为平台超级管理员
     * @return
     */
    public static boolean isSuperAdmin() {
        return ADMIN_FLAG_YES.equals(getSysUser().getSuperAdminFlag());
    }

    /**
     * 获取用户昵称
     **/
    public static String getNickName() {
        try {
            return getLoginUser().getUser().getNickName();
        } catch (Exception e) {
            throw new CustomException("获取用户昵称异常", HttpStatus.UNAUTHORIZED);
        }
    }

}
