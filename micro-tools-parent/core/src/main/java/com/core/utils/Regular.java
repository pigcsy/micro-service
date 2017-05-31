package com.core.utils;

import com.google.common.base.Joiner;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * Created by maven on 2017/3/29.
 * 多条件拼接sql
 */

public class Regular {
    public static String SplicedSql(String sql, List<String> whereClause) {
        StringBuffer sbSql = new StringBuffer();
        if (CollectionUtils.isNotEmpty(whereClause)) {
            sbSql.append(sql).append(" ").append("where");
            String whereClauseString = Joiner.on(' ').skipNulls().join(whereClause);
            whereClauseString = whereClauseString.replaceFirst("^(\\s*(and|AND|OR|or)?\\s+)", " ");
            return sbSql.append(whereClauseString).toString();
        } else {
            return sql;
        }

    }


}
