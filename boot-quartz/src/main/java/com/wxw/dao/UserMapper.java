package com.wxw.dao;

import com.wxw.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author WXW
 * @since 2020-05-26
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
