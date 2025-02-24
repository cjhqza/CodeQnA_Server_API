package com.cjh.codeqna.manager.mapper;

import com.cjh.codeqna.model.dto.data.DtTagDto;
import com.cjh.codeqna.model.entity.data.DtTag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 标签管理服务映射文件
 * @Create: 2025-01-16 22:16
 */
@Mapper
public interface DtTagMapper {
    // 标签列表
    List<DtTag> findAll();

    // 查询当前标签名称是否存在
    DtTag findTagName(String tagName);

    // 标签添加
    void add(DtTag dtTag);

    // 标签修改
    void edit(DtTag dtTag);

    // 标签删除
    void delete(Long dtTagId);

    // 查询当前包含子标签的数量
    int findChildrenById(Long dtTagId);
}
