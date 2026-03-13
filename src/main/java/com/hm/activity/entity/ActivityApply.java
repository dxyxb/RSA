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
 * 活动报名表
 * </p>
 *
 * @author 肖羽欣
 * @since 2026-03-12
 */
@Getter
@Setter
@TableName("activity_apply")
@ApiModel(value = "ActivityApply对象", description = "活动报名表")
public class ActivityApply implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("报名ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("活动ID")
    @TableField("activity_id")
    private Long activityId;

    @ApiModelProperty("报名用户ID")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("报名时间")
    @TableField("apply_time")
    private LocalDateTime applyTime;

    @ApiModelProperty("审核状态：0-待审核 1-通过 2-驳回")
    @TableField("review_status")
    private Byte reviewStatus;

    @ApiModelProperty("审核人ID")
    @TableField("reviewer_id")
    private Long reviewerId;

    @ApiModelProperty("审核时间")
    @TableField("review_time")
    private LocalDateTime reviewTime;

    @ApiModelProperty("签到状态：0-未签到 1-已签到")
    @TableField("sign_in_status")
    private Byte signInStatus;

    @ApiModelProperty("签到时间")
    @TableField("sign_in_time")
    private LocalDateTime signInTime;

    @ApiModelProperty("经验等级：BEGINNER/INTERMEDIATE/ADVANCED/OTHER")
    @TableField("experience_level")
    private String experienceLevel;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

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
