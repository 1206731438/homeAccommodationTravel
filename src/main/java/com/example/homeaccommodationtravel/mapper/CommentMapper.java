package com.example.homeaccommodationtravel.mapper;

import com.example.homeaccommodationtravel.entity.CommentEntity;
import com.example.homeaccommodationtravel.vo.CommentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    /**
     * 查询评论
     * @param homeId
     * @return
     */
    List<CommentVo> selComments(@Param("homeId") Integer homeId);

    /**
     * 添加评论
     * @param commentEntity
     * @return
     */
    Integer insert(@Param("commentEntity") CommentEntity commentEntity);
}
