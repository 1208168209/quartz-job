package com.hongguaninfo.quartz.controller;

import com.hongguaninfo.hgdf.core.base.BaseController;
import com.hongguaninfo.hgdf.core.base.BasePage;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.quartz.entity.JobAndTrigger;
import com.hongguaninfo.quartz.entity.QrtzJobDetails;
import com.hongguaninfo.quartz.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenqinglong on 2019/2/1 0001.
 */

@Controller
@RequestMapping(value = "/")
public class HomeController extends BaseController{

    @Autowired
    private JobService jobService;

    @RequestMapping(value="/home")
    public String home(){
        return "index";
    }

    @RequestMapping(value="/home/page")
    @ResponseBody
    public ModelAndView goHome(){
        System.out.println("go to the home page!");
        ModelAndView mode = new ModelAndView();
        mode.addObject("name", "zhangsan");
        mode.setViewName("index");
        return mode;
    }
}
