package com.wxw.itcast.util;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

public class ConnectionUtil {
    /**
     * 建立与RabbitMQ的连接
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("47.101.141.168");
        //客户端连接端口
        factory.setPort(5672);
        //设置账号信息，用户名、密码、vhost
        factory.setVirtualHost("/wxw");
        factory.setUsername("wxw");
        factory.setPassword("wxw");

        //设置自动重连
        factory.setAutomaticRecoveryEnabled(true);
        factory.setNetworkRecoveryInterval(3000);//重连时间

        // 通过工程获取连接
        Connection connection = factory.newConnection();
        return connection;
    }

}
