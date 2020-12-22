package com.ruoyi.project.system.service.impl;

import com.ruoyi.project.system.domain.SysTempMenu;
import com.ruoyi.project.system.mapper.SysTempMenuMapper;
import com.ruoyi.project.system.service.SysTempMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统模板菜单表(SysTempMenu)表服务实现类
 *
 * @author makejava
 * @since 2020-07-09 13:38:38
 */
@Service("sysTempMenuService")
public class SysTempMenuServiceImpl implements SysTempMenuService {
    @Resource
    private SysTempMenuMapper sysTempMenuMapper;


    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysTempMenu> queryAllByLimit(int offset, int limit) {
        return this.sysTempMenuMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysTempMenu 实例对象
     * @return 实例对象
     */
    @Override
    public SysTempMenu insert(SysTempMenu sysTempMenu) {
        this.sysTempMenuMapper.insert(sysTempMenu);
        return sysTempMenu;
    }

    /**
     * 修改数据
     *
     * @param sysTempMenu 实例对象
     * @return 实例对象
     */
    @Override
    public SysTempMenu update(SysTempMenu sysTempMenu) {
        this.sysTempMenuMapper.update(sysTempMenu);
        return null;
    }
}