package com.cjh.codeqna.feign.tag;

import com.cjh.codeqna.model.vo.tag.TagBaseInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 标签远程调用服务接口+
 * @Create: 2025-04-07 12:23
 */
@FeignClient(value = "service-tag")
public interface TagFeignClient {
    @PostMapping(value = "/api/tag/tagInfo/getTagBaseInfoListByIds")
    public List<TagBaseInfo> getTagBaseInfoListByIds(@RequestBody List<Long> tagIds);

    @GetMapping(value = "/api/tag/tagInfo/getTagIdsByUserId/{userId}")
    public List<Long> getTagIdsByUserId(@PathVariable(value = "userId") Long userId);
}
