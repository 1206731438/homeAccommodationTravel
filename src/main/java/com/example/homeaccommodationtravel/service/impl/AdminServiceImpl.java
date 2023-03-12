package com.example.homeaccommodationtravel.service.impl;

import com.example.homeaccommodationtravel.entity.AdminEntity;
import com.example.homeaccommodationtravel.mapper.AdminMapper;
import com.example.homeaccommodationtravel.po.UpdatePwdPo;
import com.example.homeaccommodationtravel.publicClass.Page;
import com.example.homeaccommodationtravel.service.AdminService;
import com.example.homeaccommodationtravel.utils.JWTUtil;
import com.example.homeaccommodationtravel.utils.ResultUtil;
import com.example.homeaccommodationtravel.vo.AdminListVo;
import com.example.homeaccommodationtravel.vo.AdminVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public ResultUtil login(AdminEntity adminEntity) {
        //根据用户名查询
        List<AdminEntity> users = adminMapper.getUserByPhone(adminEntity.getAdminPhone());
        //不存在提示
        if (users == null || users.size() == 0) {
            return ResultUtil.respinseFail("用户不存在！");
        }
        //如果存在，判断密码是否正确
        AdminEntity admin = users.get(0);
        //判断加密后的密码是否和查出用户密码一致
        if (DigestUtils.md5DigestAsHex((adminEntity.getAdminPassword()+admin.getAdminSalt()).getBytes(StandardCharsets.UTF_8)).equals(admin.getAdminPassword())) {
            //如果一致，生成token，返回数据
            String token = JWTUtil.createToken(admin.getAdminId(),admin.getAdminType());
            AdminVo adminVo = new AdminVo();
            adminVo.setAdminId(admin.getAdminId());
            adminVo.setAdminToken(token);
            adminVo.setAdminType(admin.getAdminType());
            adminVo.setAdminImg(admin.getAdminImg());
            adminVo.setAdminAge(admin.getAdminAge());
            adminVo.setAdminName(admin.getAdminName());
            adminVo.setAdminSex(admin.getAdminSex());
            adminVo.setAdminPhone(admin.getAdminPhone());
            return ResultUtil.respinseSuccess("登陆成功",adminVo);
        }
        return ResultUtil.respinseFail("密码错误");
    }

    @Override
    public PageInfo<AdminListVo> selectPage(Page page, Integer adminType) {
        if (page.getCurrentPage() != null && page.getPageSize() != null) {
            PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        }
        List<AdminListVo> adminEntities = adminMapper.selectPage(page.getSearch(),adminType);
        return new PageInfo<>(adminEntities);
    }

    @Override
    public ResultUtil insert(Integer adminId, AdminEntity adminEntity) {
        //根据手机号判断员工是否存在
        List<AdminEntity> adminEntities = adminMapper.getUserByPhone(adminEntity.getAdminPhone());
        if (adminEntities != null && adminEntities.size() != 0) {
            return ResultUtil.respinseFail("手机号已存在");
        }
        //为员工设置默认密码
        //生成密钥
        String salt = UUID.randomUUID().toString();
        adminEntity.setAdminSalt(salt);
        //为员工生成密码
        String adminPassword = DigestUtils.md5DigestAsHex(("123456" + salt).getBytes(StandardCharsets.UTF_8));
        adminEntity.setAdminPassword(adminPassword);
        //添加
        Integer i = adminMapper.insert(adminEntity, adminId);
        if (i == 0) {
            return ResultUtil.respinseFail("添加失败");
        }
        return ResultUtil.respinseSuccess("添加成功");
    }

    @Override
    @Transactional
    public Integer update(AdminEntity adminEntity, Integer adminId) {
        Integer i = 0;
        //判断手机号是否存在
        List<AdminEntity> admins = adminMapper.getUserByPhone(adminEntity.getAdminPhone());
        if (admins != null && admins.size() > 0) {
            AdminEntity admin = admins.get(0);
            if (admin.getAdminId() == adminEntity.getAdminId()) {
                //代表手机号未修改
                i = adminMapper.update(adminEntity,adminId);
            }else {
                //代表手机号已被其他人使用
                i = 2;
            }
        }else {
            i = adminMapper.update(adminEntity,adminId);
        }
        return i;
    }

    @Override
    public Integer del(List<Integer> adminIdList) {
        return adminMapper.del(adminIdList);
    }

    @Override
    public Integer updatePwd(UpdatePwdPo updatePwdPo, Integer adminId) {
        Integer i = 0;
        //先根据id查询原密码
        List<AdminEntity> adminEntities = adminMapper.selUserById(adminId);
        if (adminEntities != null && adminEntities.size() != 0) {
            AdminEntity admin = adminEntities.get(0);
            //判断密码加密后是否一致
            if (DigestUtils.md5DigestAsHex((updatePwdPo.getOldPassword()+admin.getAdminSalt()).getBytes(StandardCharsets.UTF_8)).equals(admin.getAdminPassword())) {
                //可以修改密码
                //生成密钥
                String salt = UUID.randomUUID().toString();
                //为员工生成新密码
                String adminPassword = DigestUtils.md5DigestAsHex((updatePwdPo.getNewPassword() + salt).getBytes(StandardCharsets.UTF_8));
                //修改
                i = adminMapper.updatePwd(adminId,salt,adminPassword);
            }else {
                i = 2;
            }
        }

        return i;
    }
}
