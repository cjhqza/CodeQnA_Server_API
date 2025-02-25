package com.cjh.codeqna.manager.service.impl;

import cn.hutool.core.util.StrUtil;
import com.cjh.codeqna.common.exception.CodeQnAException;
import com.cjh.codeqna.manager.mapper.DtTagMapper;
import com.cjh.codeqna.manager.service.DtTagService;
import com.cjh.codeqna.manager.utils.TreeHelper;
import com.cjh.codeqna.model.dto.data.DtTagDto;
import com.cjh.codeqna.model.entity.data.DtTag;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 标签管理接口服务实现类
 * @Create: 2025-01-16 22:16
 */
@Service
public class DtTagServiceImpl implements DtTagService {
    @Autowired
    private DtTagMapper dtTagMapper;

    // 标签列表
    @Override
    public List<DtTag> findAll() {
        List<DtTag> list = dtTagMapper.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return TreeHelper.buildTagTree(list);
    }

    // 查询修改后的标签名称是否存在
    private DtTag findTagName(String tagName) {
        return dtTagMapper.findTagName(tagName);
    }

    // 标签添加
    @Override
    public void addDtTag(DtTag dtTag) {
        // 查询当前标签名称是否存在
        DtTag dbDtTag = findTagName(dtTag.getTagName());
        if (dbDtTag != null) {
            // 如果存在，抛出异常
            throw new CodeQnAException(ResultCodeEnum.TAG_NAME_IS_EXISTS);
        }
        // 检查是否有头像地址，没有则设置为空
        if (StrUtil.isEmpty(dtTag.getImg())) {
            dtTag.setImg("");
        }
        // 将标签信息就行存储
        dtTagMapper.add(dtTag);
    }

    // 标签修改
    @Transactional
    @Override
    public void editDtTag(DtTag dtTag) {
        // 查询修改后的标签名称是否存在
        DtTag dbDtTag = findTagName(dtTag.getTagName());
        if (dbDtTag != null && dbDtTag.getId().longValue() != dtTag.getId().longValue() && StringUtils.equals(dbDtTag.getTagName(), dtTag.getTagName())) {
            // 如果存在，抛出异常
            throw new CodeQnAException(ResultCodeEnum.TAG_NAME_IS_EXISTS);
        }
        dtTagMapper.edit(dtTag);
    }

    // 标签删除
    @Override
    public void deleteDtTag(Long dtTagId) {
        // 根据当前标签id，查询当前包含子标签的数量
        int count = dtTagMapper.findChildrenById(dtTagId);
        // count大于0，说明有子标签，那么不建议删除
        if (count > 0) {
            throw new CodeQnAException(ResultCodeEnum.NODE_ERROR);
        }
        dtTagMapper.delete(dtTagId);
    }
}
