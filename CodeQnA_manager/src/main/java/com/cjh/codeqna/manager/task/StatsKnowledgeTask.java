package com.cjh.codeqna.manager.task;

import cn.hutool.core.date.DateUtil;
import com.cjh.codeqna.manager.mapper.DtKnowledgeMapper;
import com.cjh.codeqna.manager.mapper.StatsKnowledgeMapper;
import com.cjh.codeqna.model.entity.statistcs.StatsKnowledge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Author: cjh
 * @Description: 知识统计定时任务
 * @Create: 2025-02-24 9:26
 */
@Component
public class StatsKnowledgeTask {
    @Autowired
    private DtKnowledgeMapper dtKnowledgeMapper;
    @Autowired
    private StatsKnowledgeMapper statsKnowledgeMapper;

    @Scheduled(cron = "0 0 1 * * ?")    // 每日凌晨1点统计
    public void statsKnowledgeForTotalNum() {
        // 获取前一天日期
        String createDate = DateUtil.offsetDay(new Date(), -1).toString("yyyy-MM-dd");
        // 获取前一天知识产出总数
        StatsKnowledge statsKnowledge = dtKnowledgeMapper.selectStatistcsByDate(createDate);
        // 把统计结果加入到知识统计表中
        if (statsKnowledge != null) {
            statsKnowledgeMapper.insert(statsKnowledge);    // 插入后发现列knowledge_date为2025-02-22
        }
    }
}
