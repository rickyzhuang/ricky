package com.ricky.game.service;

import com.ricky.base.IBaseService;
import com.ricky.exception.BusinessException;
import com.ricky.game.vo.GameVO;

public interface IGameService  extends IBaseService {

    GameVO  getVOById(String id) throws BusinessException;


}
