package com.example.homeaccommodationtravel.service;

import com.example.homeaccommodationtravel.entity.UserEntity;
import com.example.homeaccommodationtravel.po.UpdatePwdPo;
import com.example.homeaccommodationtravel.utils.ResultUtil;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    /**
     * 用户登录
     * @param userEntity
     * @return
     */
    ResultUtil login(UserEntity userEntity);

    /**
     * 用户注册
     * @param userEntity
     * @return
     */
    ResultUtil register(UserEntity userEntity);

    /**
     * 修改信息
     * @param userEntity
     * @return
     */
    Integer update(UserEntity userEntity);

    /**
     * 修改密码
     * @param updatePwdPo
     * @return
     */
    Integer updateUserPwd(UpdatePwdPo updatePwdPo, Integer userId);
}
