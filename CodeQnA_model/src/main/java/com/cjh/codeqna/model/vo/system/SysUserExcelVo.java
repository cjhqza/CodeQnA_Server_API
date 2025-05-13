package com.cjh.codeqna.model.vo.system;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 管理员数据导出数据对象
 * @Create: 2025-04-24 23:24
 */
@Data
public class SysUserExcelVo {
    @ExcelProperty(value = "id" ,index = 0)
    private Long id;
    @ExcelProperty(value = "用户名" ,index = 1)
    private String userName;
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
