package com.ricky.user.service;

import com.ricky.base.BaseService;
import com.ricky.exception.BusinessException;
import com.ricky.user.entity.UserEntity;
import com.ricky.user.entity.UserEntityMapper;
import com.ricky.user.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * Describetion
 * Created  by  zhuangjiayin
 * Date : 2018/2/12
 */
@Service
public class UserService extends BaseService implements  IUserService {


    @Resource
    private UserEntityMapper userEntityMapper;

    public UserVO getUserVOBYId(String id) throws BusinessException {
        UserVO userVO=new UserVO();
        UserEntity userEntity=   userEntityMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(userEntity,userVO);
//        userVO.setName("李大仁");
        logger.info("UserName是{}",userVO.getName());
        return  userVO;
    }


}
