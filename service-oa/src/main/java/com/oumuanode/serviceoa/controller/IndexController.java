package com.oumuanode.serviceoa.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.oumuanode.commonutil.jwt.JwtHelper;
import com.oumuanode.commonutil.result.Result;
import com.oumuanode.commonutil.utils.MD5;
import com.oumuanode.model.model.system.SysUser;
import com.oumuanode.model.vo.system.LoginVo;
import com.oumuanode.model.vo.system.RouterVo;
import com.oumuanode.serviceoa.service.SysMenuService;
import com.oumuanode.serviceoa.service.SysUserService;
import com.oumuanode.serviceutil.exception.OpenException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "后台登录管理")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo) {

        // 1、获取用户名和密码
        // 2、根据用户名查询数据库
        String username = loginVo.getUsername();
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getName, username);
        SysUser sysUser = sysUserService.getOne(queryWrapper);
        // 3、用户信息是否存在
        if (StringUtils.isEmpty(sysUser)) {
            throw new OpenException(503, "用户不存在");
        }
        // 4、判断密码
        // 取出数据库中的密文密码（MD5）
        String password = sysUser.getPassword();
        String encrypt = MD5.encrypt(loginVo.getPassword());

        if (!encrypt.equals(password)) {
            throw new OpenException(502, "密码错误,请重新输入");
        }
        // 5、判断用户是否被禁用  1  可用    0   禁用
        if (sysUser.getStatus().intValue() == 0) {
            throw new OpenException(501, "用户被禁用,您无权登录");
        }
        // 6、使用jwt根据用户id和用户名称生成token的字符串
        String token = JwtHelper.createToken(sysUser.getId(), sysUser.getPassword());
        // 7、返回
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", token);
        return Result.ok(map);
    }

    @GetMapping("/info")
    public Result  getInfo(HttpServletRequest request){
        //1 从请求头获取用户信息（获取请求头token字符串）
        String token = request.getHeader("token");

        //2 从token字符串获取用户id 或者 用户名称
        Long userId = JwtHelper.getUserId(token);

        //3 根据用户id查询数据库，把用户信息获取出来
        SysUser sysUser = sysUserService.getById(userId);

        //4 根据用户id获取用户可以操作菜单列表
        //查询数据库动态构建路由结构，进行显示
        List<RouterVo> routerList = sysMenuService.findUserMenuListByUserId(userId);

        //5 根据用户id获取用户可以操作按钮列表
        List<String> permsList = sysMenuService.findUserPermsByUserId(userId);

        //6 返回相应的数据
        Map<String, Object> map = new HashMap<>();
        map.put("roles","[admin]");
        map.put("name",sysUser.getName());
        map.put("avatar","https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
        //返回用户可以操作菜单
        map.put("routers",routerList);
        //返回用户可以操作按钮
        map.put("buttons",permsList);
        return Result.ok(map);
    }

    @PostMapping("logout")
    public Result logout(){
        return Result.ok();
    }
}