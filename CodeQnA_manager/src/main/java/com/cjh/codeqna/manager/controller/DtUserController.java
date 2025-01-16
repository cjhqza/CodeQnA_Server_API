package com.cjh.codeqna.manager.controller;

import com.cjh.codeqna.manager.service.DtUserService;
import com.cjh.codeqna.model.dto.data.DtUserDto;
import com.cjh.codeqna.model.entity.data.DtUser;
import com.cjh.codeqna.model.entity.system.SysRole;
import com.cjh.codeqna.model.vo.common.Result;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: cjh
 * @Description: 用户管理控制器
 * @Create: 2025-01-15 14:54
 */
@RestController
@RequestMapping(value = "/admin/data/dtUser")
public class DtUserController {
    @Autowired
    private DtUserService dtUserService;

    // 用户列表
    @PostMapping(value = "/findByPage/{pageNum}/{pageSize}")
    public Result<PageInfo<DtUser>> findByPage(@PathVariable(value = "pageNum") Integer pageNum, @PathVariable(value = "pageSize") Integer pageSize, DtUserDto dtUserDto) {
        // pageHelper插件实现分页
        PageInfo<DtUser> pageInfo = dtUserService.findByPage(pageNum , pageSize, dtUserDto) ;
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS) ;
    }

    // 修改用户状态
    @PutMapping(value = "/editDtUser/{id}")
    public Result editDtUser(@PathVariable("id") Long id) {
        System.out.println(id);
        dtUserService.editDtUser(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 用户删除
    @DeleteMapping(value = "/deleteDtUser/{dtUserId}")
    public Result deleteDtUser(@PathVariable("dtUserId") Long dtUserId) {
        dtUserService.deleteDtUser(dtUserId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
