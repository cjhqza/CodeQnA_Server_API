package com.cjh.codeqna.user.mapper;

import cn.hutool.system.UserInfo;
import com.cjh.codeqna.model.dto.user.UserDto;
import com.cjh.codeqna.model.entity.data.DtUser;
import com.cjh.codeqna.model.vo.user.UserBaseInfo;
import com.cjh.codeqna.model.vo.user.UserCbInfo;
import com.cjh.codeqna.model.vo.user.UserSearchInfo;
import com.cjh.codeqna.model.vo.user.UserotherInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 用户信息服务映射文件
 * @Create: 2025-04-10 15:41
 */
@Mapper
public interface UserInfoMapper {
    // 查找当前电话号码对应的用户对象
    DtUser findByPhone(String phone);

    // 插入用户新数据
    void insert(DtUser dtUser);

    // 远程调用：根据用户ID获取用户名
    DtUser getUserById(Long userId);

    // 用户检索集合
    List<UserCbInfo> findByUserName(String userInput);

    // 用户搜索结果集合
    List<UserSearchInfo> getUserSearchInfoList(String userName);

    // 远程调用：根据用户ID获取用户名
    String getUserNameById(Long userId);

    // 按关注数(关注高的)获取用户信息
    List<UserInfo> getUserInfoByFollow(Long userId);

    // 根据用户感兴趣标签集合获取用户信息集合
    List<UserInfo> getRelatedUserInfo(List<Long> tagIds, Long userId);

    // 根据userId找到用户搜索数据信息
    UserSearchInfo getUserSearchInfo(Long userId);

    // 根据userId找用户其他数据信息
    UserotherInfo getUserotherInfo(Long userId, Long myId);

    // 判断当前用户是否已经关注过该用户
    int getUserFollow(Long id, Long userId);

    // 执行取消关注操作
    void cancelFollow(Long id, Long userId);

    // 执行关注操作
    void follow(Long id, Long userId);

    // 修改用户信息
    void updateUserInfo(UserDto userDto);
}
