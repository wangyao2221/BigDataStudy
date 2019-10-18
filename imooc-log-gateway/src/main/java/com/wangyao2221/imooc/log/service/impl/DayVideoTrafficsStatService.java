package com.wangyao2221.imooc.log.service.impl;

import com.wangyao2221.imooc.log.entity.DayVideoTrafficsStat;
import com.wangyao2221.imooc.log.mapper.DayVideoTrafficsStatMapper;
import com.wangyao2221.imooc.log.service.IDayVideoTrafficsStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DayVideoTrafficsStatService implements IDayVideoTrafficsStatService {
    @Autowired
    DayVideoTrafficsStatMapper dayVideoTrafficsStatMapper;

    @Override
    public List<DayVideoTrafficsStat> findAll() {
        return dayVideoTrafficsStatMapper.findAll();
    }
}
