package com.example.homeaccommodationtravel.mapper;

import com.example.homeaccommodationtravel.entity.CollectEntity;
import com.example.homeaccommodationtravel.vo.CollectVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CollectMapper {
    /**
     * 分页查询
     * @param search
     * @param userId
     * @return
     */
    List<CollectVo> selectPage(@Param("search") String search,@Param("userId") Integer userId);

    /**
     * 根据homeId和userId查询用户是否已经收藏
     * @param homeId
     * @param userId
     * @return
     */
    List<Integer> selByUserIdAndHomeId(@Param("homeId") Integer homeId,@Param("userId") Integer userId);

    /**
     * 添加收藏
     * @param collectEntity
     * @param userId
     * @return
     */
    Integer insert(@Param("collectEntity") CollectEntity collectEntity,@Param("userId") Integer userId);

    /**
     * 删除收藏
     * @param collectId
     * @return
     */
    Integer del(@Param("collectId") Integer collectId);
}
