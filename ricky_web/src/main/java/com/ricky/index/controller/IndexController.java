package com.ricky.index.controller;

import com.ricky.base.BaseController;
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

    @RequestMapping("/index.htm")
    public  String index(Model model, HttpServletRequest request, HttpServletResponse response){
        logger.info("index.htm 进入首页！");
        model.addAttribute("userName","庄先生");
        model.addAttribute("say","hello world");
        return  "/index/index";
    }


}
