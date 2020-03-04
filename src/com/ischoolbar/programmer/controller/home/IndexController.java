package com.ischoolbar.programmer.controller.home;

import com.ischoolbar.programmer.service.admin.NewsCategoryService;
import com.ischoolbar.programmer.service.admin.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/*
前台页面首页控制器
 */
@RequestMapping("/index")
@Controller
public class IndexController {
    @Autowired
    private NewsCategoryService newsCategoryService;

    @Autowired
    private NewsService newsService;
    
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView index(ModelAndView model){
        Map<String,Object> queryMap = new HashMap<String, Object>();
        queryMap.put("offset",0);
        queryMap.put("pageSize",10);
        model.addObject("newscategoryList",newsCategoryService.findAll());
        model.addObject("newsList",newsService.findList(queryMap));
        model.setViewName("home/index/index");
        return  model;
    }
    
    
    
}
