package com.ruoyi.project.system.service;

import com.ruoyi.project.system.domain.SysTempMenu;
import java.util.List;

/**
 * 系统模板菜单表(SysTempMenu)表服务接口
 *
 * @author makejava
 * @since 2020-07-09 13:38:38
 */
public interface SysTempMenuService {



    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysTempMenu> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysTempMenu 实例对象
     * @return 实例对象
     */
    SysTempMenu insert(SysTempMenu sysTempMenu);

    /**
     * 修改数据
     *
     * @param sysTempMenu 实例对象
     * @return 实例对象
     */
    SysTempMenu update(SysTempMenu sysTempMenu);

}