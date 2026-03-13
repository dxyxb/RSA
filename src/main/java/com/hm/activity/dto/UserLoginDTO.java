package com.hm.activity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户登录 DTO
 */
@Data
@Schema(name = "用户登录参数")
public class UserLoginDTO {
    @Schema(description = "账号（学号/工号）", required = true)
    private String account;

    @Schema(description = "密码", required = true)
    private String password;
}