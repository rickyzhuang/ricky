package com.ricky.user.service;

import com.ricky.exception.BusinessException;
import com.ricky.user.vo.UserVO;

/**
 * Describetion
 * Created  by  zhuangjiayin
 * Date : 2018/2/12
 */
public interface IUserService {
    UserVO getUserVOBYId(String id) throws BusinessException;

}
