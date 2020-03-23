package com.wxw.spring;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class HttpTests {

    CloseableHttpClient httpClient;

    /**
     * https://blog.csdn.net/iexploration/article/details/82023893
     */

    @BeforeEach
    public void init() {
        // 相当于打开了一个浏览器
        httpClient = HttpClients.createDefault();
    }


    //模拟get请求
    @Test
    public void testGet() throws IOException {
        HttpGet request = new HttpGet("http://www.baidu.com/s?wd=java");
        //HttpGet request = new HttpGet("http://www.baidu.com");
        String response = this.httpClient.execute(request, new BasicResponseHandler());
        System.out.println(response);
    }

    //post请求
    @Test
    public void testPost() throws IOException {
        HttpPost request = new HttpPost("https://www.oschina.net/");
        request.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        String response = this.httpClient.execute(request, new BasicResponseHandler());
        System.out.println(response);
    }

    @Test
    public void testGetPojo() throws IOException {
        HttpGet request = new HttpGet("http://localhost:8080/login");
        String response = this.httpClient.execute(request, new BasicResponseHandler());
        System.out.println(response);
    }
}
