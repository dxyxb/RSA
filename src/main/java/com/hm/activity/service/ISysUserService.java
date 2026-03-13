package com.hm.activity.service;

import com.hm.activity.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hm.activity.dto.UserRegisterDTO;
/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author 肖羽欣
 * @since 2026-03-12
 */
public interface ISysUserService extends IService<SysUser> {
    /**
     * 用户登录
     * @param account 账号
     * @param password 密码
     * @return 用户信息
     */
    SysUser login(String account, String password);

    /**
     * 用户注册
     * @param dto 注册参数
     * @return 注册后的用户
     */
    SysUser register(UserRegisterDTO dto);
}
