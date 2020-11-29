package com.lzw.flowablespringboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * @auther LZW
 * @date 2020/5/12 20:34
 */
@Mapper
@Repository
public interface MyRepositoryMapper {

    @Select("SELECT * FROM `ACT_RE_MODEL` WHERE `KEY_` = #{id} AND `CATEGORY_` = #{category} AND `TENANT_ID_` = #{tenantId}")
    List<Map<String,String>> findByKeyAndTypeAndTenant(@Param("id") String id, @Param("category") String category, @Param("tenantId") String tenantId);
}
