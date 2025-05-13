package com.cjh.codeqna.tag.mapper;

import com.cjh.codeqna.model.entity.data.DtTag;
import com.cjh.codeqna.model.vo.tag.TagBaseInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 标签服务映射文件
 * @Create: 2025-04-07 15:37
 */
@Mapper
public interface TagMapper {

    // 远程调用：根据标签id集合获取标签基本信息对象集合
    List<TagBaseInfo> getTagBaseInfoListByIds(List<Long> tagIds);

    // 根据热门搜索（最多人关注排序）
    List<DtTag> getHotTagBaseInfoList(String input);

    // 根据篇数多搜索（问答贴数量+文章数量）
    List<DtTag> getMostTagBaseInfoList(String input);

    // 获取所有标签数据
    List<DtTag> getTagList();
}
