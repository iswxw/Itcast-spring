package com.wxw.controller;

import com.wxw.domain.Order;
import com.wxw.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: wxw
 * @create: 2020-03-15-15:10
 * 订单管理
 */
@Controller
@Api(tags = "OmsPortalOrderController", description = "订单管理")
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation("根据购物车信息生成订单")
    @PostMapping("/generateOrder")
    @ResponseBody
    public Object generateOrder(@RequestBody Order order) {
        return orderService.generateOrder(order);
    }

}
