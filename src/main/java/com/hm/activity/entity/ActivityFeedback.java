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
 * 活动反馈表
 * </p>
 *
 * @author 肖羽欣
 * @since 2026-03-12
 */
@Getter
@Setter
@TableName("activity_feedback")
@ApiModel(value = "ActivityFeedback对象", description = "活动反馈表")
public class ActivityFeedback implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("反馈ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("活动ID")
    @TableField("activity_id")
    private Long activityId;

    @ApiModelProperty("反馈用户ID")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("报名ID（关联activity_apply.id）")
    @TableField("apply_id")
    private Long applyId;

    @ApiModelProperty("评分（1-5分）")
    @TableField("score")
    private Byte score;

    @ApiModelProperty("反馈内容")
    @TableField("content")
    private String content;

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
