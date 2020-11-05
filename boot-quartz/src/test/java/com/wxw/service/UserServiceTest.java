package com.wxw.service;

import com.wxw.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: wxw
 * @create: 2020-05-26-0:21
 */
@SpringBootTest
public class UserServiceTest {

    @Resource
    private IUserService userService;

    @Test
    public void testFindAll(){
        List<User> list = userService.list();
        System.out.println(list.get(0));
    }
}
