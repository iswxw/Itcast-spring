package com.wxw.itcast.direct;

import com.wxw.itcast.util.ConnectionUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
/**
 * 生产者，模拟为商品服务
 */
public class Send {
    private final static String EXCHANGE_NAME = "direct_exchange_test";

    public static void main(String[] argv) throws Exception {
        // 获取到连接
        Connection connection = ConnectionUtil.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();
        /**
         * 交换机属性
         * exchangeDeclare(String exchange, BuiltinExchangeType type, boolean durable, boolean autoDelete, boolean internal, Map<String, Object> arguments)
         * （1） exchange 交换机名称
         * （2） type     交换机类型  direct/fanout/topic/header
         *  (3)  durable   是否需要持久化
         * （4） autoDelete 当最后一个绑定到Exchanee 被删除后队列会被自动删除
         * （5） internal   当前Exchange 是否用于RabbitMQ内部使用
         * （6） arguments  扩展参数，用与扩展AMQP协议自 定制化使用
         */
        // 声明exchange，指定类型为direct
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        // 消息内容
        String message = "商品删除了， id = 1001";
        // 发送消息，并且指定routing key 为：insert ,代表新增商品
        channel.basicPublish(EXCHANGE_NAME, "delete", null, message.getBytes());
        System.out.println(" [商品服务：] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}