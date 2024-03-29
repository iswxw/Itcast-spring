package com.wxw.common.mq;

import com.wxw.common.enums.QueueEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: wxw
 * @create: 2020-03-15-14:26
 * 用于向订单延迟消息队列（mall.order.cancel.ttl）里发送消息。
 * 取消订单消息的发出者
 */
@Component
public class CancelOrderSender {

    private static Logger LOGGER= LoggerFactory.getLogger(CancelOrderSender.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(Long orderId,final Long delayTimes){
        //给延迟队列发送消息
        amqpTemplate.convertAndSend(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange(),
                                    QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey(),orderId,
                                    new MessagePostProcessor(){
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //给消息设置延迟毫秒值
                message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
                return message;
            }
        });
        LOGGER.info("send delay message orderId:{}",orderId);
     }

}
