package com.wxw.common.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: wxw
 * @create: 2020-03-15-14:50\
 * 用于从取消订单的消息队列（mall.order.cancel）里接收消息。
 * 取消订单消息的处理者
 */
@Component
@RabbitListener(queues = "bs.order.cancel")
public class CancelOrderReceiver {
    private static Logger LOGGER = LoggerFactory.getLogger(CancelOrderReceiver.class);



}
