package com.cjh.codeqna.manager.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.cjh.codeqna.manager.mapper.SysUserMapper;
import com.cjh.codeqna.model.dto.system.SysUserExcelDto;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * @Author: cjh
 * @Description: excel文件导入监听器
 * @Create: 2025-04-25 0:30
 */
public class SysUserExcelListener implements ReadListener<SysUserExcelDto> {
    // 每次存储数据的个数
    private static final int BATCH_COUNT = 100;

    // 缓存数据
    private List<SysUserExcelDto> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    // mapper对象，操作数据库
    private SysUserMapper sysUserMapper;

    // 构造函数
    public SysUserExcelListener(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    @Override
    public void invoke(SysUserExcelDto sysUserExcelDto, AnalysisContext analysisContext) {
        sysUserExcelDto.setPassword(DigestUtils.md5DigestAsHex(sysUserExcelDto.getPassword().getBytes()));
        cachedDataList.add(sysUserExcelDto);
        if (cachedDataList.size() >= BATCH_COUNT) {
            // 将数据存入数据库中
            save();
            // 清空list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        save();
    }

    private void save() {
        sysUserMapper.batchInsert(cachedDataList);
    }
}
