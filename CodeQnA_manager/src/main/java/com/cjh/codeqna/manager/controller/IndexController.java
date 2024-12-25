package com.cjh.codeqna.manager.controller;

import com.cjh.codeqna.manager.service.SysUserService;
import com.cjh.codeqna.model.dto.system.LoginDto;
import com.cjh.codeqna.model.vo.common.Result;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import com.cjh.codeqna.model.vo.system.LoginVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // 管理员用户登录
    @Operation(summary = "登录接口")
    @PostMapping("login")
    public Result login(@RequestBody LoginDto loginDto) {
        LoginVo loginVo = sysUserService.login(loginDto);
        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }
}
