package com.example.homeaccommodationtravel.service;

import com.example.homeaccommodationtravel.entity.CommentEntity;
import com.example.homeaccommodationtravel.vo.CommentVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    /**
     *
     * @param homeId
     * @return
     */
    List<CommentVo> selComments(Integer homeId);

    /**
     * 添加评论
     * @param commentEntity
     * @return
     */
    Integer insert(CommentEntity commentEntity);
}
