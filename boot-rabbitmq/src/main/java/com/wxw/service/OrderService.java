package com.wxw.service;

import com.wxw.common.api.CommonResult;
import com.wxw.domain.Order;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: wxw
 * @create: 2020-03-15-14:54
 * 前台订单管理Service
 */
public interface OrderService {

    /**
     * 根据提交信息生成订单
     */
    @Transactional
    CommonResult generateOrder(Order order);

    /**
     * 取消单个超时订单
     */
    @Transactional
    void cancelOrder(Long orderId);
}
