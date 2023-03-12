package com.example.homeaccommodationtravel.mapper;

import com.example.homeaccommodationtravel.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    /**
     * 根据手机号查询
     * @param userPhone
     * @return
     */
    List<UserEntity> getUserByPhone(@Param("userPhone") String userPhone);

    /**
     * 用户注册
     * @param userEntity
     * @return
     */
    Integer register(@Param("userEntity") UserEntity userEntity);

    /**
     * 修改信息
     * @param userEntity
     * @return
     */
    Integer update(@Param("userEntity") UserEntity userEntity);

    /**
     * 修改密码
     * @param userId
     * @param salt
     * @param userPassword
     * @return
     */
    Integer updateUserPwd(@Param("userId") Integer userId,@Param("salt") String salt,@Param("userPassword") String userPassword);

    /**
     *
     * @param userId
     * @return
     */
    List<UserEntity> selUserById(@Param("userId") Integer userId);
}
