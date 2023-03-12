package com.example.homeaccommodationtravel.mapper;

import com.example.homeaccommodationtravel.entity.AdminEntity;
import com.example.homeaccommodationtravel.vo.AdminListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminMapper {
    /**
     * 管理员/员工登录
     * @param adminPhone
     * @return
     */
    List<AdminEntity> getUserByPhone(@Param("adminPhone") String adminPhone);

    /**
     * 分页查询
     * @param search
     * @param adminType
     * @return
     */
    List<AdminListVo> selectPage(@Param("search") String search, @Param("adminType") Integer adminType);

    /**
     * 添加员工/管理员
     * @param adminEntity
     * @param adminId
     * @return
     */
    Integer insert(@Param("adminEntity") AdminEntity adminEntity,@Param("adminId") Integer adminId);

    /**
     * 修改员工/管理员信息
     * @param adminEntity
     * @param adminId
     * @return
     */
    Integer update(@Param("adminEntity") AdminEntity adminEntity,@Param("adminId") Integer adminId);

    /**
     * 批量删除/删除
     * @param adminIdList
     * @return
     */
    Integer del(@Param("adminIdList") List<Integer> adminIdList);

    /**
     * 根据id查询
     * @param adminId
     * @return
     */
    List<AdminEntity> selUserById(@Param("adminId") Integer adminId);

    /**
     * 修改密码
     * @param adminId
     * @param salt
     * @param adminPassword
     * @return
     */
    Integer updatePwd(@Param("adminId") Integer adminId,@Param("salt") String salt,@Param("adminPassword") String adminPassword);
}
