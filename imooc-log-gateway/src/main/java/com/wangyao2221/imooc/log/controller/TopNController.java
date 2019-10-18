package com.wangyao2221.imooc.log.controller;

import com.wangyao2221.imooc.log.entity.DayCityAccessStat;
import com.wangyao2221.imooc.log.entity.DayVideoAccessStat;
import com.wangyao2221.imooc.log.entity.DayVideoTrafficsStat;
import com.wangyao2221.imooc.log.entity.Response;
import com.wangyao2221.imooc.log.service.impl.DayCityAccessStatService;
import com.wangyao2221.imooc.log.service.impl.DayVideoAccessStatService;
import com.wangyao2221.imooc.log.service.impl.DayVideoTrafficsStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/log/topN")
public class TopNController {
    @Autowired
    DayCityAccessStatService dayCityAccessStatService;

    @Autowired
    DayVideoAccessStatService dayVideoAccessStatService;

    @Autowired
    DayVideoTrafficsStatService dayVideoTrafficsStatService;

    @GetMapping("/dayCityAccessStats")
    @ResponseBody
    public Response<List<DayCityAccessStat>> dayCityAccessStats() {
        Response<List<DayCityAccessStat>> response = Response.Error();

        try {
            List<DayCityAccessStat> result = dayCityAccessStatService.findAll();
            response = Response.Result(Response.DEFAULT, result);
        } catch (Exception e) {
            response = Response.Error(e.getMessage());
        }

        return response;
    }

    @GetMapping("/dayVideoAccessStats")
    @ResponseBody
    public Response<List<DayVideoAccessStat>> dayVideoAccessStats() {
        Response<List<DayVideoAccessStat>> response = Response.Error();

        try {
            List<DayVideoAccessStat> result = dayVideoAccessStatService.findAll();
            response = Response.Result(Response.DEFAULT, result);
        } catch (Exception e) {
            response = Response.Error(e.getMessage());
        }

        return response;
    }

    @GetMapping("/dayVideoTrafficsStats")
    @ResponseBody
    public Response<List<DayVideoTrafficsStat>> dayVideoTrafficsStats() {
        Response<List<DayVideoTrafficsStat>> response = Response.Error();

        try {
            List<DayVideoTrafficsStat> result = dayVideoTrafficsStatService.findAll();
            response = Response.Result(Response.DEFAULT, result);
        } catch (Exception e) {
            response = Response.Error(e.getMessage());
        }

        return response;
    }
}
