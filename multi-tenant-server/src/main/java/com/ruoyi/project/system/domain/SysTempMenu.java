package com.ruoyi.project.system.domain;

import java.io.Serializable;

/**
 * 系统模板菜单表(SysTempMenu)实体类
 *
 * @author makejava
 * @since 2020-07-09 13:38:36
 */
public class SysTempMenu implements Serializable {
    private static final long serialVersionUID = 904598953972609708L;
    /**
    * 模板ID
    */
    private String tempId;
    /**
    * 菜单ID
    */
    private Integer menuId;


    public String getTempId() {
        return tempId;
    }

    public void setTempId(String tempId) {
        this.tempId = tempId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

}