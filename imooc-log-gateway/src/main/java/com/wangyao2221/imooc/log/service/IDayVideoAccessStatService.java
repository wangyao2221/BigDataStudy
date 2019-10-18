package com.wangyao2221.imooc.log.service;

import com.wangyao2221.imooc.log.entity.DayVideoAccessStat;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IDayVideoAccessStatService {
    List<DayVideoAccessStat> findAll();
}
