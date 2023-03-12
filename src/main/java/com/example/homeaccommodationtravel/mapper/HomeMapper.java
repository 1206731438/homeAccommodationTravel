package com.example.homeaccommodationtravel.mapper;

import com.example.homeaccommodationtravel.po.HomeInsertPo;
import com.example.homeaccommodationtravel.vo.HomeListVo;
import com.example.homeaccommodationtravel.vo.TypeNameList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HomeMapper {
    /**
     * 分页查询
     * @param search
     * @return
     */
    List<HomeListVo> selectPage(@Param("search") String search,@Param("typeId") String typeId);

    /**
     * 添加
     * @param homeInsertPo
     * @param adminId
     * @return
     */
    Integer insert(@Param("homeInsertPo") HomeInsertPo homeInsertPo,@Param("adminId") Integer adminId);

    /**
     * 修改
     * @param homeInsertPo
     * @param adminId
     * @return
     */
    Integer update(@Param("homeInsertPo") HomeInsertPo homeInsertPo,@Param("adminId") Integer adminId);

    /**
     * 删除/批量删除
     * @param homeIds
     * @return
     */
    Integer del(@Param("homeIds") List<Integer> homeIds);

    /**
     * 查询类型下拉框
     * @return
     */
    List<TypeNameList> getTypeNameList();

    List<HomeListVo> selUserPage(@Param("search") String search,@Param("typeId") String typeId);
}
