package com.cjh.codeqna.feign.user;

import com.cjh.codeqna.model.dto.message.MessageDto;
import com.cjh.codeqna.model.entity.data.DtUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: cjh
 * @Description: 用户远程调用服务接口
 * @Create: 2025-04-07 12:07
 */
@FeignClient(value = "service-user")
public interface UserFeignClient {
    @GetMapping(value = "/api/user/userInfo/getUserById/{userId}")
    public DtUser getUserById(@PathVariable(value = "userId") Long userId);

    @GetMapping(value = "/api/user/userInfo/getUserNameById/{userId}")
    public String getUserNameById(@PathVariable(value = "userId") Long userId);

    @PostMapping(value = "/api/user/message/createChat")
    public Long createChat(@RequestBody MessageDto messageDto);

    @PostMapping(value = "/api/user/message/storeMessage")
    public void storeMessage(@RequestBody MessageDto messageDto);

    @GetMapping(value = "/api/user/userInfo/validateToken/{token}")
    public Long validateToken(@PathVariable(value = "token") String token);
}
