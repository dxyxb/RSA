package com.hm.activity.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * AI助手对话历史表
 * </p>
 *
 * @author 肖羽欣
 * @since 2026-03-12
 */
@Getter
@Setter
@TableName("ai_chat_history")
@ApiModel(value = "AiChatHistory对象", description = "AI助手对话历史表")
public class AiChatHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("对话ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户ID")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("用户消息")
    @TableField("user_msg")
    private String userMsg;

    @ApiModelProperty("AI回复")
    @TableField("ai_msg")
    private String aiMsg;

    @ApiModelProperty("对话时间")
    @TableField("chat_time")
    private LocalDateTime chatTime;

    @ApiModelProperty("过期时间（自动清理）")
    @TableField("expire_time")
    private LocalDateTime expireTime;

    @ApiModelProperty("软删除：0-未删 1-已删")
    @TableField("is_deleted")
    private Byte isDeleted;
}
