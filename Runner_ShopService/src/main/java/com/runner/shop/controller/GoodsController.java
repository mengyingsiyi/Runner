package com.runner.shop.controller;

import com.runner.commons.vo.R;
import com.runner.shop.service.GoodsAllService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/21  20:49
 */
@Api(value = "商品")
@RestController
@RequestMapping("/goods/service/")
public class GoodsController {
    @Autowired
    private GoodsAllService service;

    @ApiOperation("通过分类查询")
    @GetMapping("/selectById.do")
    public R  selectById(Integer id){
        return service.selectByF(id);
    }

    @ApiOperation("通过id查询某件商品")
    @GetMapping("/selectGoodsById.do")
    public R  selectGoodsById(Integer id){

        return service.selectGoodsById(id);
    }

}
