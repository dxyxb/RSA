package com.hm.activity.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor // 🔥 新增：生成无参构造函数
@AllArgsConstructor // 🔥 新增：生成全参构造函数
@Schema(name = "Result", description = "统一返回结果")
public class Result<T> {
    @Schema(description = "状态码")
    private int code;
    @Schema(description = "消息")
    private String msg;
    @Schema(description = "数据")
    private T data;

    public static <T> Result<T> success(T data) {
        return new Result<>(200, "success", data);
    }

    public static <T> Result<T> error(String msg) {
        return new Result<>(500, msg, null);
    }
}