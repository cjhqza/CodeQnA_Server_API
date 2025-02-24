package com.cjh.codeqna.manager.service.impl;

import cn.hutool.core.date.DateUtil;
import com.cjh.codeqna.manager.mapper.DtKnowledgeMapper;
import com.cjh.codeqna.manager.mapper.StatsKnowledgeMapper;
import com.cjh.codeqna.manager.service.StatsKnowledgeService;
import com.cjh.codeqna.model.dto.statistcs.StatsKnowledgeDto;
import com.cjh.codeqna.model.entity.statistcs.StatsKnowledge;
import com.cjh.codeqna.model.vo.statistcs.StatsKnowledgeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: cjh
 * @Description: 知识统计服务实现类
 * @Create: 2025-02-24 9:22
 */
@Service
public class StatsKnowledgeServiceImpl implements StatsKnowledgeService {
    @Autowired
    private StatsKnowledgeMapper statsKnowledgeMapper;

    // 获取知识统计数据
    @Override
    public StatsKnowledgeVo getStatsKnowledgeData(StatsKnowledgeDto statsKnowledgeDto) {
        // 查询知识统计集合
        List<StatsKnowledge> statsKnowledgeList = statsKnowledgeMapper.selectList(statsKnowledgeDto);
        // 遍历list集合，得到所有日期，把所有日期封装list新集合里面
        List<String> dateList = statsKnowledgeList.stream().map(statsKnowledge -> {
            return DateUtil.format(statsKnowledge.getKnowledgeDate(), "yyyy-MM-dd");
        }).collect(Collectors.toList());
        // 遍历list集合，得到所有日期对应总产量，把总产量封装到list集合里面
        List<Long> numList = statsKnowledgeList.stream().map(StatsKnowledge::getTotalNum).collect(Collectors.toList());
        // 把两个list集合封装StatsKnowledgeVo，返回StatsKnowledgeVo
        StatsKnowledgeVo statsKnowledgeVo = new StatsKnowledgeVo();
        statsKnowledgeVo.setDateList(dateList);
        statsKnowledgeVo.setNumList(numList);
        return statsKnowledgeVo;
    }
}
