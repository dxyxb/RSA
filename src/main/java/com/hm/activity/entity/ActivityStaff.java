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
 * 活动人员分工表
 * </p>
 *
 * @author 肖羽欣
 * @since 2026-03-12
 */
@Getter
@Setter
@TableName("activity_staff")
@ApiModel(value = "ActivityStaff对象", description = "活动人员分工表")
public class ActivityStaff implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("分工ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("活动ID")
    @TableField("activity_id")
    private Long activityId;

    @ApiModelProperty("参与分工的用户ID")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("分工角色：主持人/志愿者/技术支持等")
    @TableField("role")
    private String role;

    @ApiModelProperty("职责描述")
    @TableField("responsibility")
    private String responsibility;

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
