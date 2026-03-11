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
 * 活动日程表
 * </p>
 *
 * @author 肖羽欣
 * @since 2026-03-12
 */
@Getter
@Setter
@TableName("activity_schedule")
@ApiModel(value = "ActivitySchedule对象", description = "活动日程表")
public class ActivitySchedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("日程ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("活动ID")
    @TableField("activity_id")
    private Long activityId;

    @ApiModelProperty("日程标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("日程开始时间")
    @TableField("start_time")
    private LocalDateTime startTime;

    @ApiModelProperty("日程结束时间")
    @TableField("end_time")
    private LocalDateTime endTime;

    @ApiModelProperty("日程地点")
    @TableField("location")
    private String location;

    @ApiModelProperty("日程详情")
    @TableField("description")
    private String description;

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
