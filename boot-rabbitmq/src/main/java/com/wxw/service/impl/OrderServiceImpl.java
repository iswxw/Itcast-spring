package com.wxw.service.impl;

import com.wxw.common.api.CommonResult;
import com.wxw.common.mq.CancelOrderSender;
import com.wxw.domain.Order;
import com.wxw.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wxw
 * @create: 2020-03-15-14:57
 * 前台订单管理
 */
@Service
public class OrderServiceImpl implements OrderService {

    public static Logger LOGGER= LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private CancelOrderSender cancelOrderSender;

    @Override
    public CommonResult generateOrder(Order order) {
        // todo 执行一些列下单操作
        LOGGER.info("生成订单的过程" );
        // 下单完成后开启一个延迟消息，用于当用户没有付款时取消订单（orderId在下单后生成）
        sendDelayMessageCancelOrder(11L);
        return CommonResult.success(null, "下单成功");
    }

    private void sendDelayMessageCancelOrder(Long orderId) {
        //获取订单超时时间 假设为60分钟
        long delayTimes=30*1000;
        //发送延迟消息
        cancelOrderSender.sendMessage(orderId, delayTimes);
    }

    @Override
    public void cancelOrder(Long orderId) {
        // todo 执行一系列取消订单操作
        LOGGER.info("订单取消中... 订单号：{}", orderId);
    }
}
