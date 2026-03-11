package com.hm.activity.service.impl;

import com.hm.activity.entity.UserTag;
import com.hm.activity.mapper.UserTagMapper;
import com.hm.activity.service.IUserTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户兴趣标签表 服务实现类
 * </p>
 *
 * @author 肖羽欣
 * @since 2026-03-12
 */
@Service
public class UserTagServiceImpl extends ServiceImpl<UserTagMapper, UserTag> implements IUserTagService {

}
