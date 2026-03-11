package com.hm.activity.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 活动主表
 * </p>
 *
 * @author 肖羽欣
 * @since 2026-03-12
 */
@Getter
@Setter
@TableName("activity")
@ApiModel(value = "Activity对象", description = "活动主表")
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("活动ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("活动标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("活动类型：学术讲座/志愿活动/文体活动等")
    @TableField("type")
    private String type;

    @ApiModelProperty("状态：0-草稿 1-发布 2-报名中 3-报满 4-结束")
    @TableField("status")
    private Byte status;

    @ApiModelProperty("创建人ID（关联sys_user.id）")
    @TableField("creator_id")
    private Long creatorId;

    @ApiModelProperty("活动开始时间")
    @TableField("start_time")
    private LocalDateTime startTime;

    @ApiModelProperty("活动结束时间")
    @TableField("end_time")
    private LocalDateTime endTime;

    @ApiModelProperty("报名开始时间")
    @TableField("sign_start_time")
    private LocalDateTime signStartTime;

    @ApiModelProperty("报名结束时间")
    @TableField("sign_end_time")
    private LocalDateTime signEndTime;

    @ApiModelProperty("最大报名人数")
    @TableField("max_people")
    private Integer maxPeople;

    @ApiModelProperty("当前报名人数")
    @TableField("current_people")
    private Integer currentPeople;

    @ApiModelProperty("活动地点")
    @TableField("location")
    private String location;

    @ApiModelProperty("活动详情")
    @TableField("description")
    private String description;

    @ApiModelProperty("学分/学时类型")
    @TableField("credit_type")
    private String creditType;

    @ApiModelProperty("学分/学时数量")
    @TableField("credit_num")
    private BigDecimal creditNum;

    @ApiModelProperty("可见范围：{\"college\":\"软件学院\",\"grade\":\"2022级\"}")
    @TableField("visible_scope")
    private String visibleScope;

    @ApiModelProperty("封面图片")
    @TableField("cover_img")
    private String coverImg;

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
