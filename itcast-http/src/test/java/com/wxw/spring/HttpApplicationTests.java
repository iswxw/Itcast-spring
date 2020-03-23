package com.wxw.spring;
import com.wxw.spring.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;


import javax.annotation.Resource;

@SpringBootTest
public class HttpApplicationTests {

    @Resource
    private RestTemplate restTemplate;

    @Test
    public void httpGet() {
        User user = this.restTemplate.getForObject("http://localhost:8080/user", User.class);
        System.out.println(user);
    }

}
