package com.example.homeaccommodationtravel.controller;

import com.example.homeaccommodationtravel.entity.CommentEntity;
import com.example.homeaccommodationtravel.entity.HomeEntity;
import com.example.homeaccommodationtravel.service.CommentService;
import com.example.homeaccommodationtravel.utils.JWTUtil;
import com.example.homeaccommodationtravel.utils.ResultUtil;
import com.example.homeaccommodationtravel.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/selComments")
    public ResultUtil selComments(@RequestBody HomeEntity homeEntity) {
        List<CommentVo> commentVoList = commentService.selComments(homeEntity.getHomeId());
        return ResultUtil.respinseSuccess("查询成功",commentVoList);
    }

    @PostMapping("/insert")
    public ResultUtil insert(HttpServletRequest request, @RequestBody CommentEntity commentEntity) {
        //判断是否登录
        String userToken = request.getHeader("userToken");
        if (userToken == null || userToken.equals("")) {
            return ResultUtil.respinseFail(ResultUtil.NO_LOGIN,"请先登录");
        }
        //判断登录是否过期
        if (!JWTUtil.overTime(userToken)) {
            return ResultUtil.respinseFail(ResultUtil.HAVE_COLLECTION,"登录已过期");
        }
        Integer userId = JWTUtil.verifyUserToken(userToken);
        commentEntity.setUserId(userId);
        Integer i = commentService.insert(commentEntity);
        if (i == 0) {
            return ResultUtil.respinseFail("评论失败");
        }
        return ResultUtil.respinseSuccess("评论成功");
    }
}
