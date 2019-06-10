package com.ricky.sb.service;

import com.ricky.sb.api.IWebServiceTestService;
import com.ricky.user.vo.UserVO;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.util.ArrayList;

/**
 * Describetion
 * Created  by  zhuangjiayin
 * Date : 2019/5/27
 */
@Component
@WebService(targetNamespace="http://com.ricky.sb.config/",endpointInterface = "com.ricky.sb.api.IWebServiceTestService")
public class WebServiceTestService  implements IWebServiceTestService {
    public String helloWorld(String name) {
        return "hello"+name;
    }

    public String getName(String userId) {
        return null;
    }

    public UserVO getUser(String userI) {
        return null;
    }

    public ArrayList<UserVO> getAlLUser() {
        return null;
    }
}
