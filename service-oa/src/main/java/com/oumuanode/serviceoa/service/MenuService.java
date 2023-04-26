package com.oumuanode.serviceoa.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.oumuanode.model.model.wechat.Menu;
import com.oumuanode.model.vo.system.wechat.MenuVo;

import java.util.List;

/**
 * <p>
 * 菜单 服务类
 * </p>
 */
public interface MenuService extends IService<Menu> {

    //获取全部菜单
    List<MenuVo> findMenuInfo();

    //同步菜单
    void syncMenu();

    //删除菜单
    void removeMenu();
}
