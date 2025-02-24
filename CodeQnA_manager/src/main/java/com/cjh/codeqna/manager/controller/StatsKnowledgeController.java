package com.cjh.codeqna.manager.controller;
import com.cjh.codeqna.common.log.annotation.Log;
import com.cjh.codeqna.common.log.enums.OperatorType;
import com.cjh.codeqna.manager.service.StatsKnowledgeService;
import com.cjh.codeqna.model.dto.statistcs.StatsKnowledgeDto;
import com.cjh.codeqna.model.vo.common.Result;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import com.cjh.codeqna.model.vo.statistcs.StatsKnowledgeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: cjh
 * @Description: 知识统计控制器
 * @Create: 2025-02-24 9:20
 */
@RestController
@RequestMapping(value = "/admin/statistcs/statsKnowledge")
public class StatsKnowledgeController {
    @Autowired
    private StatsKnowledgeService statsKnowledgeService;

    // 获取知识统计数据
    @Log(title = "知识统计:查找", businessType = 0, operatorType = OperatorType.MANAGE)
    @GetMapping(value = "/getStatsKnowledgeData")
    public Result getStatsKnowledgeData(StatsKnowledgeDto statsKnowledgeDto) {
        StatsKnowledgeVo statsKnowledgeVo = statsKnowledgeService.getStatsKnowledgeData(statsKnowledgeDto);
        return Result.build(statsKnowledgeVo, ResultCodeEnum.SUCCESS);
    }
}
