package com.ricky.game.service;

import com.ricky.base.BaseService;
import com.ricky.exception.BusinessException;
import com.ricky.game.entity.GameEntity;
import com.ricky.game.entity.GameEntityMapper;
import com.ricky.game.vo.GameVO;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;

/**
 * Describetion
 * Created  by  zhuangjiayin
 * Date : 2018/2/26
 */
public class GameService extends BaseService  implements  IGameService {

    @Resource
    private GameEntityMapper gameEntityMapper;

    public GameVO getVOById(String id) throws BusinessException {
        GameVO gameVO=new GameVO();
        GameEntity  entity=   gameEntityMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(entity,gameVO);
        logger.info("gameName是{}",gameVO.getName());
        return  gameVO;
    }
}
