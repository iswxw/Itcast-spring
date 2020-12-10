package com.wxw.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * localhost:8080 点击登录github
 * @author: wxw
 * @date: 2020-10-24-1:28
 */
@RestController
@Slf4j
public class LoginController {

    @PostMapping("logout")
    public Boolean logout(String name){
        log.info("退出信息：{}",name);
       return true;
    }

    @GetMapping("/user")
    public Map<String, Object> getUser(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("name", principal.getAttribute("name"));
    }

    /**
     * 登录码云
     * @return
     */
    @GetMapping("/index")
    public String returnIndex(){
        return "主页";
    }

    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code){
        return code;
    }




}
