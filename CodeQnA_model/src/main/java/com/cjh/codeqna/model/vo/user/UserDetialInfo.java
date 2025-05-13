package com.cjh.codeqna.model.vo.user;

import com.cjh.codeqna.model.vo.tag.TagBaseInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 用户详细信息数据对象
 * @Create: 2025-04-12 19:18
 */
@Data
@Schema(description = "用户详细信息数据对象")
public class UserDetialInfo {
    @Schema(description = "用户搜索数据")
    private UserSearchInfo userSearchInfo;

    @Schema(description = "用户其他信息")
    private UserotherInfo userotherInfo;

    @Schema(description = "相关标签集合")
    List<TagBaseInfo> tagBaseInfoList;
}
