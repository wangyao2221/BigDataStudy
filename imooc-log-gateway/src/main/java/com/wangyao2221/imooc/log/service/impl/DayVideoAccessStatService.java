package com.wangyao2221.imooc.log.service.impl;

import com.wangyao2221.imooc.log.entity.DayVideoAccessStat;
import com.wangyao2221.imooc.log.mapper.DayVideoAccessStatMapper;
import com.wangyao2221.imooc.log.service.IDayVideoAccessStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DayVideoAccessStatService implements IDayVideoAccessStatService {
    @Autowired
    DayVideoAccessStatMapper dayVideoAccessStatMapper;

    @Override
    public List<DayVideoAccessStat> findAll() {
        return dayVideoAccessStatMapper.findAll();
    }

    @Override
    public List<DayVideoAccessStat> findTimesTopN(int n) {
        return dayVideoAccessStatMapper.findTimesTopN(n);
    }
}
