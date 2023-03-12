package com.example.homeaccommodationtravel.service;

import com.example.homeaccommodationtravel.entity.AdminEntity;
import com.example.homeaccommodationtravel.po.UpdatePwdPo;
import com.example.homeaccommodationtravel.publicClass.Page;
import com.example.homeaccommodationtravel.utils.ResultUtil;
import com.example.homeaccommodationtravel.vo.AdminListVo;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    /**
     * 管理员/员工登录
     * @param adminEntity
     * @return
     */
    ResultUtil login(AdminEntity adminEntity);

    /**
     * 分页查询
     * @param page
     * @param adminType
     * @return
     */
    PageInfo<AdminListVo> selectPage(Page page, Integer adminType);

    /**
     * 添加员工/管理员
     * @param adminId
     * @param adminEntity
     * @return
     */
    ResultUtil insert(Integer adminId, AdminEntity adminEntity);

    /**
     * 修改员工信息
     * @param adminEntity
     * @param adminId
     * @return
     */
    Integer update(AdminEntity adminEntity, Integer adminId);

    /**
     * 批量删除/删除
     * @param adminIdList
     * @return
     */
    Integer del(List<Integer> adminIdList);

    /**
     * 修改密码
     * @param updatePwdPo
     * @param adminId
     * @return
     */
    Integer updatePwd(UpdatePwdPo updatePwdPo, Integer adminId);
}
