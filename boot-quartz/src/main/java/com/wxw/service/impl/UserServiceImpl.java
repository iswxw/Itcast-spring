package com.wxw.service.impl;

import com.wxw.domain.User;
import com.wxw.dao.UserMapper;
import com.wxw.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author WXW
 * @since 2020-11-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
