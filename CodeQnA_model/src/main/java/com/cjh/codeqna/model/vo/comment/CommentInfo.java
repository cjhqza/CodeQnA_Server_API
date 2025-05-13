package com.cjh.codeqna.model.vo.comment;

import com.cjh.codeqna.model.entity.data.DtComment;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 答复评论信息响应数据对象
 * @Create: 2025-04-11 22:34
 */
@Data
@Schema(description = "答复评论信息响应数据对象")
public class CommentInfo {
    @Schema(description = "答复评论id")
    private Long id;

    @Schema(description = "父评论id")
    private Long parentId;

    @Schema(description = "祖先评论id")
    private Long ancestorId;

    @Schema(description = "答复人id")
    private Long userId;

    @Schema(description = "答复人头像")
    private String userHeadImgUrl;

    @Schema(description = "答复人姓名")
    private String userName;

    @Schema(description = "被答复人id")
    private Long toUserId;

    @Schema(description = "被答复人头像")
    private String toUserHeadImgUrl;

    @Schema(description = "被答复人姓名")
    private String toUserName;

    @Schema(description = "内容")
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "答复评论时间")
    private String createTime;

    @Schema(description = "是否赞赏该评论")
    private Boolean isAppreciate;

    @Schema(description = "赞赏数")
    private Long appreciateCount;

    @Schema(description = "子答复评论")
    private List<CommentInfo> children;
}
