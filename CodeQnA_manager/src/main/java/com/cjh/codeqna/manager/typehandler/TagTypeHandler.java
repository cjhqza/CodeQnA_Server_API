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
public class TagTypeHandler extends BaseTypeHandler<Set<String>> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Set<String> longs, JdbcType jdbcType) throws SQLException {
        // 写入时需要
    }

    @Override
    public Set<String> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String tagNames = resultSet.getString(s);
        return convertToSet(tagNames);
    }

    @Override
    public Set<String> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String tagNames = resultSet.getString(i);
        return convertToSet(tagNames);
    }

    @Override
    public Set<String> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String tagNames = callableStatement.getString(i);
        return convertToSet(tagNames);
    }

    private Set<String> convertToSet(String tagNames) {
        if (tagNames == null || tagNames.isEmpty()) {
            return new HashSet<>();
        }
        Set<String> tagNamesSet = new HashSet<>();
        String[] tags = tagNames.split(",");
        for (String tag : tags) {
            tagNamesSet.add(tag);
        }
        return tagNamesSet;
    }
}
