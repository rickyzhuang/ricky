package com.ricky.sb.api;

import com.ricky.user.vo.UserVO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;

@WebService
public interface IWebServiceTestService {

    @WebMethod
    String helloWorld(@WebParam(name = "name") String name);

    @WebMethod
    String getName(@WebParam(name = "userId") String userId);

    @WebMethod
    UserVO getUser(String userI);

    @WebMethod
    ArrayList<UserVO> getAlLUser();

}
