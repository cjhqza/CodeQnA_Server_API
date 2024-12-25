package com.cjh.codeqna.manager.controller;

import com.cjh.codeqna.manager.service.SysUserService;
import com.cjh.codeqna.manager.service.ValidateCodeService;
import com.cjh.codeqna.model.dto.system.LoginDto;
import com.cjh.codeqna.model.entity.system.SysUser;
import com.cjh.codeqna.model.vo.common.Result;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import com.cjh.codeqna.model.vo.system.LoginVo;
import com.cjh.codeqna.model.vo.system.ValidateCodeVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result getSysUserInfo(@RequestHeader(name="token") String token) {
        // 根据token查询redis获取管理员用户信息
        SysUser sysUser = sysUserService.getSysUserInfo(token);
        // 返回管理员用户信息
        return Result.build(sysUser, ResultCodeEnum.SUCCESS);
    }

}
