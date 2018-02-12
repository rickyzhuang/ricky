package com.ricky.user.entity;

import com.ricky.user.entity.UserEntity;
import com.ricky.user.entity.UserEntityCondition;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserEntityMapper {
    int countByExample(UserEntityCondition example);

    int deleteByExample(UserEntityCondition example);

    int deleteByPrimaryKey(String id);

    int insert(UserEntity record);

    int insertSelective(UserEntity record);

    List<UserEntity> selectByExample(UserEntityCondition example);

    UserEntity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserEntity record, @Param("example") UserEntityCondition example);

    int updateByExample(@Param("record") UserEntity record, @Param("example") UserEntityCondition example);

    int updateByPrimaryKeySelective(UserEntity record);

    int updateByPrimaryKey(UserEntity record);
}