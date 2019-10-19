package com.wangyao2221.imooc.log.controller;

import com.wangyao2221.imooc.log.entity.DayCityAccessStat;
import com.wangyao2221.imooc.log.entity.DayVideoAccessStat;
import com.wangyao2221.imooc.log.entity.DayVideoTrafficsStat;
import com.wangyao2221.imooc.log.entity.Response;
import com.wangyao2221.imooc.log.service.IDayCityAccessStatService;
import com.wangyao2221.imooc.log.service.IDayVideoAccessStatService;
import com.wangyao2221.imooc.log.service.IDayVideoTrafficsStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/log/topN")
public class TopNController {
    @Autowired
    IDayCityAccessStatService dayCityAccessStatService;

    @Autowired
    IDayVideoAccessStatService dayVideoAccessStatService;

    @Autowired
    IDayVideoTrafficsStatService dayVideoTrafficsStatService;

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

    @RequestMapping("/dayVideoAccessStatTopN")
    public Response<List<DayVideoAccessStat>> dayVideoAccessStatTopN(@RequestParam(value = "n",defaultValue = "100") Integer n) {
        Response<List<DayVideoAccessStat>> response = Response.Error();

        try {
            List<DayVideoAccessStat> result = dayVideoAccessStatService.findTimesTopN(n);
            response = Response.Result(Response.DEFAULT, result);
        } catch (Exception e) {
            response = Response.Error(e.getMessage());
        }

        return response;
    }

    @RequestMapping("/dayVideoCityAccessStatTopN")
    public Response<List<DayCityAccessStat>> dayCityVideoAccessStatTopN(@RequestParam(value = "n", defaultValue = "5") Integer n) {
        Response<List<DayCityAccessStat>> response = Response.Error();

        try {
            List<DayCityAccessStat> result = dayCityAccessStatService.findCityAccessTopN(n);
            response = Response.Result(Response.DEFAULT, result);
        } catch (Exception e) {
            response = Response.Error(e.getMessage());
        }

        return response;
    }
}
