package com.ricky.game.entity;

import com.ricky.game.entity.GameEntity;
import com.ricky.game.entity.GameEntityCondition;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GameEntityMapper {
    int countByExample(GameEntityCondition example);

    int deleteByExample(GameEntityCondition example);

    int deleteByPrimaryKey(String id);

    int insert(GameEntity record);

    int insertSelective(GameEntity record);

    List<GameEntity> selectByExample(GameEntityCondition example);

    GameEntity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GameEntity record, @Param("example") GameEntityCondition example);

    int updateByExample(@Param("record") GameEntity record, @Param("example") GameEntityCondition example);

    int updateByPrimaryKeySelective(GameEntity record);

    int updateByPrimaryKey(GameEntity record);
}