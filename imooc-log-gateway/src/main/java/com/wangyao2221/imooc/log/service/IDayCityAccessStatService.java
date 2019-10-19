package com.wangyao2221.imooc.log.service;

import com.wangyao2221.imooc.log.entity.DayCityAccessStat;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IDayCityAccessStatService {
    List<DayCityAccessStat> findAll();
    List<DayCityAccessStat> findCityAccessTopN(int n);
}
