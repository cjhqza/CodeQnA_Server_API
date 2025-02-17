package com.cjh.codeqna.manager.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: cjh
 * @Description: 将获取得到的标签id集合字符串形式转换为集合形式
 * @Create: 2025-02-16 15:17
 */

@MappedTypes(Set.class) // 声明处理的java类型
@MappedJdbcTypes(JdbcType.VARCHAR)  // 声明处理的JDBC类型
public class TagIdsTypeHandler extends BaseTypeHandler<Set<Long>> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Set<Long> longs, JdbcType jdbcType) throws SQLException {
        // 写入时需要
    }

    @Override
    public Set<Long> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String tagIds = resultSet.getString(s);
        return convertToSet(tagIds);
    }

    @Override
    public Set<Long> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String tagIds = resultSet.getString(i);
        return convertToSet(tagIds);
    }

    @Override
    public Set<Long> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String tagIds = callableStatement.getString(i);
        return convertToSet(tagIds);
    }

    private Set<Long> convertToSet(String tagIds) {
        if (tagIds == null || tagIds.isEmpty()) {
            return new HashSet<>();
        }
        Set<Long> tagIdsSet = new HashSet<>();
        String[] tags = tagIds.split(",");
        for (String tag : tags) {
            tagIdsSet.add(Long.parseLong(tag));
        }
        return tagIdsSet;
    }
}
