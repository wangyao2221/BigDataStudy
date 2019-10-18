package com.wangyao2221.imooc.log.service;

import com.wangyao2221.imooc.log.entity.DayVideoTrafficsStat;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IDayVideoTrafficsStatService {
    List<DayVideoTrafficsStat> findAll();
}
