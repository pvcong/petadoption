package com.ck.utils;

import org.springframework.util.StringUtils;

import javax.persistence.Query;

public class JpaUtils {
    public static void buildLimit2Offset(Query query, Integer limit, Integer offset){
        if(offset != null && offset > 0){
            query.setFirstResult(offset);
        }
        if(limit != null && limit > 0){
            query.setMaxResults(limit);
        }
    }
    public static void buildOrderBy(StringBuilder stringBuilder, String sortProperty, String sortValue){
        if(sortProperty != null && !StringUtils.isEmpty(sortProperty) && sortValue != null && !StringUtils.isEmpty(sortValue)){
            if(sortValue.toUpperCase().equals("ASC")){
                stringBuilder.append(" ORDER BY  ").append(sortProperty).append(" ASC");
            }
            else if(sortValue.toUpperCase().equals("DESC")){
                stringBuilder.append(" ORDER BY  ").append(sortProperty).append(" DESC");

            }
        }
    }
}
