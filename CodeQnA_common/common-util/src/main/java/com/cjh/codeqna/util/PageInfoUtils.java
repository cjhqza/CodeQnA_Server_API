package com.cjh.codeqna.util;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 分页工具类
 * @Create: 2025-04-07 23:58
 */
public class PageInfoUtils {
    public static <S, T> PageInfo<T> copyListPage(PageInfo<S> source, List<T> target) {
        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setList(target);

        // 复制原pageInfo的除list属性外的其他属性的值
        pageInfo.setPageNum(source.getPageNum());
        pageInfo.setPageSize(source.getPageSize());
        pageInfo.setSize(source.getSize());
        pageInfo.setStartRow(source.getStartRow());
        pageInfo.setEndRow(source.getEndRow());
        pageInfo.setTotal(source.getTotal());
        pageInfo.setPages(source.getPages());
        pageInfo.setPrePage(source.getPrePage());
        pageInfo.setNextPage(source.getNextPage());
        pageInfo.setIsFirstPage(source.isIsFirstPage());
        pageInfo.setIsLastPage(source.isIsLastPage());
        pageInfo.setHasPreviousPage(source.isHasPreviousPage());
        pageInfo.setHasNextPage(source.isHasNextPage());
        pageInfo.setNavigatePages(source.getNavigatePages());
        pageInfo.setNavigateFirstPage(source.getNavigateFirstPage());
        pageInfo.setNavigateLastPage(source.getNavigateLastPage());
        pageInfo.setNavigatepageNums(source.getNavigatepageNums());

        return pageInfo;
    }
}
