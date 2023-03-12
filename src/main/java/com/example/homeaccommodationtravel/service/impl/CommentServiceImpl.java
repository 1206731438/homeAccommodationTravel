package com.example.homeaccommodationtravel.service.impl;

import com.example.homeaccommodationtravel.entity.CommentEntity;
import com.example.homeaccommodationtravel.mapper.CommentMapper;
import com.example.homeaccommodationtravel.service.CommentService;
import com.example.homeaccommodationtravel.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<CommentVo> selComments(Integer homeId) {

        return commentMapper.selComments(homeId);
    }

    @Override
    public Integer insert(CommentEntity commentEntity) {
        return commentMapper.insert(commentEntity);
    }
}
