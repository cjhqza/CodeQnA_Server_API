package com.cjh.codeqna.manager.service;

import com.cjh.codeqna.model.dto.data.DtTagDto;
import com.cjh.codeqna.model.entity.data.DtTag;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 标签管理接口服务
 * @Create: 2025-01-16 22:15
 */
public interface DtTagService {
    // 标签列表
    List<DtTag> findAll();

    // 标签添加
    void addDtTag(DtTag dtTag);

    // 标签修改
    void editDtTag(DtTag dtTag);

    // 标签删除
    void deleteDtTag(Long dtTagId);

}
