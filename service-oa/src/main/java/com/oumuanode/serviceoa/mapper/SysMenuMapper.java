package com.oumuanode.serviceoa.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oumuanode.model.model.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {


    //多表关联查询：用户角色关系表 、 角色菜单关系表、 菜单表
    List<SysMenu> findMenuListByUserId(@Param("userId") Long userId);

}
