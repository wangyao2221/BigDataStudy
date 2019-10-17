package com.wangyao2221.imooc.log.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

@RestController
@RequestMapping("/log/topN")
public class TopNController {
    @GetMapping("futureTest")
    @ResponseBody
    public Callable<String> futureTest() {
        System.out.println("进入futureTest方法");
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("进入call方法");
                Thread.sleep(10000);
                System.out.println("耗时操作结束");
                return "耗时操作结束";
            }
        };
        System.out.println("从futureTest方法返回");
        return callable;
    }

    @GetMapping
    @ResponseBody
    public String test() {
        return "test";
    }
}
