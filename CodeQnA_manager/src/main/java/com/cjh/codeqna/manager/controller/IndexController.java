package com.cjh.codeqna.manager.controller;

import com.cjh.codeqna.manager.service.SysMenuService;
import com.cjh.codeqna.manager.service.SysUserService;
import com.cjh.codeqna.manager.service.ValidateCodeService;
import com.cjh.codeqna.model.dto.system.LoginDto;
import com.cjh.codeqna.model.entity.system.SysUser;
import com.cjh.codeqna.model.vo.common.Result;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import com.cjh.codeqna.model.vo.system.LoginVo;
import com.cjh.codeqna.model.vo.system.SysMenuVo;
import com.cjh.codeqna.model.vo.system.ValidateCodeVo;
import com.cjh.codeqna.util.AuthContextUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 管理员用户登录、登出、验证码生成、可操作菜单以及获取个人信息接口
 * @Create: 2024-12-25 17:21
 */
@RestController
@RequestMapping(value = "/admin/system/index")
@Tag(name = "管理员用户接口")
public class IndexController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ValidateCodeService validateCodeService;
    @Autowired
    private SysMenuService sysMenuService;

    // 管理员用户登录
    @Operation(summary = "登录接口")
    @PostMapping("login")
    public Result login(@RequestBody LoginDto loginDto) {
        LoginVo loginVo = sysUserService.login(loginDto);
        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }

    // 生成图片验证码
    @Operation(summary = "验证码接口")
    @GetMapping(value = "generateValidateCode")
    public Result generateValidateCode() {
        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();
        return Result.build(validateCodeVo, ResultCodeEnum.SUCCESS);
    }

    // 获取当前管理员用户的登录信息
    @Operation(summary = "管理员用户信息接口")
    @GetMapping(value = "getSysUserInfo")
    public Result getSysUserInfo() {
        return Result.build(AuthContextUtil.get(), ResultCodeEnum.SUCCESS);
    }

    // 管理员用户退出
    @Operation(summary = "管理员用户退出接口")
    @GetMapping(value = "logout")
    public Result logout(@RequestHeader(name="token") String token) {
        sysUserService.logout(token);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 查询管理员可以操作的菜单
    @Operation(summary = "管理员可操作菜单接口")
    @GetMapping("/findMenusByUserId")
    public Result findMenusByUserId() {
        List<SysMenuVo> list = sysMenuService.findMenusByUserId();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

}
