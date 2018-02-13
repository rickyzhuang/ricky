package com.ricky.user.service;

import com.ricky.exception.BusinessException;
import com.ricky.user.entity.UserEntity;
import com.ricky.user.entity.UserEntityMapper;
import com.ricky.user.vo.UserVO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Describetion
 * Created  by  zhuangjiayin
 * Date : 2018/2/12
 */
public class UserService implements  IUserService {

    public UserVO getUserVOBYId(String id) throws BusinessException {
        return null;
    }


}
