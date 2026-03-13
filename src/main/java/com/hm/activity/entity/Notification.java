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
 * 消息通知表
 * </p>
 *
 * @author 肖羽欣
 * @since 2026-03-12
 */
@Getter
@Setter
@TableName("notification")
@ApiModel(value = "Notification对象", description = "消息通知表")
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("通知ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("接收用户ID")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("通知类型：报名审核/活动提醒/系统通知等")
    @TableField("type")
    private String type;

    @ApiModelProperty("通知内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("关联业务ID（活动ID/报名ID）")
    @TableField("business_id")
    private Long businessId;

    @ApiModelProperty("已读状态：0-未读 1-已读")
    @TableField("is_read")
    private Byte isRead;

    @ApiModelProperty("已读时间")
    @TableField("read_time")
    private LocalDateTime readTime;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty("软删除：0-未删 1-已删")
    @TableField("is_deleted")
    private Byte isDeleted;
}
