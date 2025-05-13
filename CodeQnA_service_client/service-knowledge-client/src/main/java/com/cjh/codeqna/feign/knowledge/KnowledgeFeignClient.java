package com.cjh.codeqna.feign.knowledge;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: cjh
 * @Description: 知识远程调用服务接口
 * @Create: 2025-05-10 10:06
 */
@FeignClient(value = "service-knowledge")
public interface KnowledgeFeignClient {
    @GetMapping(value = "/api/knowledge/knowledgeInfo/getPostNumByTagId/{tagId}")
    public Long getPostNumByTagId(@PathVariable(value = "tagId") Long tagId);

    @GetMapping(value = "/api/knowledge/knowledgeInfo/getArticleNumByTagId/{tagId}")
    Long getArticleNumByTagId(@PathVariable(value = "tagId") Long tagId);

    @GetMapping(value = "/api/knowledge/knowledgeInfo/getAppreciateCountByUserId/{userId}")
    public Long getAppreciateCountByUserId(@PathVariable(value = "userId") Long userId);
}
