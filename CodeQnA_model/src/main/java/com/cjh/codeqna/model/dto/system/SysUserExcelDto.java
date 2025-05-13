package com.cjh.codeqna.model.dto.system;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 管理员数据导入数据对象
 * @Create: 2025-04-25 0:50
 */

@Data
public class SysUserExcelDto {
    @ExcelProperty(value = "用户名" ,index = 0)
    private String userName;
    @ExcelProperty(value = "密码" ,index = 1)
    private String password;
    @ExcelProperty(value = "别名" ,index = 2)
    private String name;
    @ExcelProperty(value = "手机号码" ,index = 3)
    private String phone;
    @ExcelProperty(value = "头像" ,index = 4)
    private String avatar;
    @ExcelProperty(value = "描述" ,index = 5)
    private String description;
    @ExcelProperty(value = "状态（1：正常 0：停用）" ,index = 6)
    private Integer status;
}
