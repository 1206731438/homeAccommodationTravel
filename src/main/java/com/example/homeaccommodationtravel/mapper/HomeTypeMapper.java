package com.example.homeaccommodationtravel.mapper;

import com.example.homeaccommodationtravel.entity.HomeTypeEntity;
import com.example.homeaccommodationtravel.vo.HomeTypeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HomeTypeMapper {
    /**
     * 分夜查询
     * @param search
     * @return
     */
    public List<HomeTypeVo> selectPage(@Param("search") String search);

    /**
     * 添加类型
     * @param homeTypeEntity
     * @param adminId
     * @return
     */
    Integer insert(@Param("homeTypeEntity") HomeTypeEntity homeTypeEntity,@Param("adminId") Integer adminId);

    /**
     * 根据类型名查询
     * @param typeName
     * @return
     */
    List<HomeTypeEntity> selByTypeName(@Param("typeName") String typeName);

    /**
     * 修改类型
     * @param homeTypeEntity
     * @param adminId
     * @return
     */
    Integer update(@Param("homeTypeEntity") HomeTypeEntity homeTypeEntity,@Param("adminId") Integer adminId);

    /**
     * 删除/批量删除
     * @param typeIds
     * @return
     */
    Integer del(@Param("typeIds") List<Integer> typeIds);
}
