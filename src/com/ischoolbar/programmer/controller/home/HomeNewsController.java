package com.ischoolbar.programmer.controller.home;

import com.ischoolbar.programmer.entity.admin.NewsCategory;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.NewsCategoryService;
import com.ischoolbar.programmer.service.admin.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/*
ǰ̨���ſ�����
 */
@RequestMapping("/news")
@Controller
public class HomeNewsController {
    @Autowired
    private NewsCategoryService newsCategoryService;

    @Autowired
    private NewsService newsService;
    /*
    �鿴��������
     */
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public ModelAndView index(ModelAndView model,Long id){
        model.addObject("newsCategoryList",newsCategoryService.findAll());
        model.addObject("news",newsService.find(id));
        model.setViewName("home/news/detail");
        return  model;
    }
    /*
    ���շ�����ʾ����
     */
    @RequestMapping(value = "/category_list",method = RequestMethod.GET)
    public ModelAndView categoryList(ModelAndView model,
                                     @RequestParam(name = "cid",required = true)Long cid,
                                     Page page
                                     ){
        Map<String,Object> queryMap = new HashMap<String, Object>();
        queryMap.put("offset",0);
        queryMap.put("pageSize",3);
        queryMap.put("categoryId",cid);
        model.addObject("newscategoryList",newsCategoryService.findAll());
        NewsCategory newsCategory = newsCategoryService.find(cid);
        
        model.addObject("newsCategory",newsCategory);
        model.addObject("title",newsCategory.getName()+"�����µ�������Ϣ��");
        model.addObject("newsList",newsService.findList(queryMap));
        model.setViewName("home/news/category_list");
        return  model;
    }

    /*
    ������������
     */
    @RequestMapping(value = "/last_comment_list",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> lastCommonList(){
        Map<String,Object> ret = new HashMap<String, Object>();
        ret.put("type","success");
        ret.put("newsList",newsService.findLastCommentList(7));
        return  ret;
    }

    /*
    ��ȡ���������µ�n����Ϣ
     */
    @RequestMapping(value = "/get_category_list",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getCategoryList(
            @RequestParam(name = "cid",required = true)Long cid,
            Page page
    ){
        Map<String,Object> ret = new HashMap<String, Object>();
        Map<String,Object> queryMap = new HashMap<String, Object>();
        queryMap.put("offset",page.getOffset());
        queryMap.put("pageSize",page.getRows());
        queryMap.put("categoryId",cid);
        ret.put("type","success");
        ret.put("newsList",newsService.findList(queryMap));
        return  ret;
    }
    
}
