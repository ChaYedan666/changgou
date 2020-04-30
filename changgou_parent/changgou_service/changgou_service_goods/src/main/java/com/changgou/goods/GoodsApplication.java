package com.changgou.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author chayedan666
 * @version 1.0
 * @className: GoodsApplication
 * @description:
 * @date: 2020/4/29
 */
@SpringBootApplication
@EnableEurekaClient
// 注意MapperScan引入的是tk.mybatis.spring.annotation
@MapperScan(basePackages = {"com.changgou.goods.dao"})
public class GoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class);
    }
}
