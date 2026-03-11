package com.hm.activity.service.impl;

import com.hm.activity.entity.Activity;
import com.hm.activity.mapper.ActivityMapper;
import com.hm.activity.service.IActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 活动主表 服务实现类
 * </p>
 *
 * @author 肖羽欣
 * @since 2026-03-12
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements IActivityService {

}
