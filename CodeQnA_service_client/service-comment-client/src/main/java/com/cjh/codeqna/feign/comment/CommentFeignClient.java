package com.cjh.codeqna.feign.comment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 答复评论远程调用服务接口
 * @Create: 2025-04-08 20:07
 */
@FeignClient(value = "service-comment")
public interface CommentFeignClient {
    @GetMapping(value = "/api/comment/commentInfo/getKnowledgeIdOfComment")
    public List<Long> getKnowledgeIdOfComment();

    @GetMapping (value = "/api/comment/commentInfo/getResponseCountByKnowledgeId/{id}")
    public Long getResponseCountByKnowledgeId(@PathVariable(value = "id") Long id);
}
