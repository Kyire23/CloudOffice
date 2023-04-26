package com.oumuanode.serviceoa.utils;

import com.oumuanode.model.model.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

public class MenuHelper{
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        ArrayList<SysMenu> trees = new ArrayList<>();

        for (SysMenu sysMenu : sysMenuList) {
            if (sysMenu.getParentId().longValue() == 0) {
                trees.add(getChildren(sysMenu,sysMenuList));
            }
        }
        return trees;
    }


    public static SysMenu getChildren(SysMenu sysMenu,
                                      List<SysMenu> sysMenuList) {
        sysMenu.setChildren(new ArrayList<SysMenu>());
        //遍历所有菜单数据，判断 id 和 parentId对应关系
        for(SysMenu it: sysMenuList) {
            if(sysMenu.getId().longValue() == it.getParentId().longValue()) {
                if (sysMenu.getChildren() == null) {
                    sysMenu.setChildren(new ArrayList<>());
                }
                sysMenu.getChildren().add(getChildren(it,sysMenuList));
            }
        }
        return sysMenu;
    }
}

