package com.hm.activity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户注册 DTO
 */
@Data
@Schema(name = "用户注册参数")
public class UserRegisterDTO {
    @Schema(description = "账号（学号/工号）", required = true)
    private String account;

    @Schema(description = "密码", required = true)
    private String password;

    @Schema(description = "姓名", required = true)
    private String username;

    @Schema(description = "角色（1-学生 2-教师）", required = true)
    private Integer role; // 1学生 2教师

    @Schema(description = "学院")
    private String college;

    @Schema(description = "班级")
    private String clazz;

    @Schema(description = "年级")
    private String grade;
}