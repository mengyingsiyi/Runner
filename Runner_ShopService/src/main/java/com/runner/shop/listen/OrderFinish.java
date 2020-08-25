package com.runner.shop.listen;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.stereotype.Component;

/**
 * @Description: 监听完成队列
 * @author: ZackJun
 * @date: 2020/8/25  22:37
 */
@Component
@RibbonClient(name = "open:putong")
public class OrderFinish {
    //  注入gooddao 然后查询
}
