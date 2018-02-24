package com.ricky.index.controller;

import com.ricky.base.BaseController;
import com.ricky.user.service.IUserService;
import com.ricky.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Describetion
 * Created  by  zhuangjiayin
 * Date : 2018/2/11
 */
@Controller
@RequestMapping("/ricky/index")
public class IndexController extends BaseController{

    @Autowired
    private IUserService userService;

    @RequestMapping("/index.htm")
    public  String index(Model model, HttpServletRequest request, HttpServletResponse response){
        logger.info("index.htm 进入首页！");
        UserVO userVO=userService.getUserVOBYId("2");
        model.addAttribute("userName","庄先生");
        model.addAttribute("say","hello world");
        model.addAttribute("result",userVO.getName());
        return  "/index/index";
    }


}
