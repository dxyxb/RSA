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
 * 活动物资表
 * </p>
 *
 * @author 肖羽欣
 * @since 2026-03-12
 */
@Getter
@Setter
@TableName("activity_material")
@ApiModel(value = "ActivityMaterial对象", description = "活动物资表")
public class ActivityMaterial implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("物资ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("活动ID")
    @TableField("activity_id")
    private Long activityId;

    @ApiModelProperty("物资名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("物资数量")
    @TableField("quantity")
    private Integer quantity;

    @ApiModelProperty("状态：0-未准备 1-已准备 2-已使用 3-已归还")
    @TableField("status")
    private Byte status;

    @ApiModelProperty("供应商")
    @TableField("supplier")
    private String supplier;

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
