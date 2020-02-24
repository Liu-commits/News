package com.ischoolbar.programmer.controller.admin;

import com.ischoolbar.programmer.entity.admin.NewsCategory;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.NewsCategoryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * 新闻分类控制器
 */
@RequestMapping("/admin/news_category")
@Controller
public class NewsCategoryController {

    @Autowired
    private NewsCategoryService newsCategoryService;
    /*
    新闻分类列表页面
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model){
       
        model.setViewName("news_category/list");
        return model;
    }

    /*
    添加
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> add(NewsCategory newsCategory){
        Map<String,String> ret = new HashMap<String, String>();
        if (newsCategory == null){
            ret.put("type","error");
            ret.put("msg","请填写正确的分类信息！");
            return ret;
        }
        if (StringUtils.isEmpty(newsCategory.getName())){
            ret.put("type","error");
            ret.put("msg","分类名称不能为空！");
            return ret;
        }
        if (newsCategoryService.add(newsCategory) <= 0){
            ret.put("type","error");
            ret.put("msg","分类添加失败，请联系管理员！");
            return ret;
        }
        ret.put("type","success");
        ret.put("msg","添加成功！");
        return ret;
    }

    /*
    分类编辑
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> edit(NewsCategory newsCategory){
        Map<String,String> ret = new HashMap<String, String>();
        if (newsCategory == null){
            ret.put("type","error");
            ret.put("msg","请填写正确的分类信息！");
            return ret;
        }
        if (StringUtils.isEmpty(newsCategory.getName())){
            ret.put("type","error");
            ret.put("msg","分类名称不能为空！");
            return ret;
        }
        if (newsCategoryService.edit(newsCategory) <= 0){
            ret.put("type","error");
            ret.put("msg","分类修改失败，请联系管理员！");
            return ret;
        }
        ret.put("type","success");
        ret.put("msg","修改成功！");
        return ret;
    }

    /*
   分类删除
    */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> delete(Long id){
        Map<String,String> ret = new HashMap<String, String>();
        if (id == null){
            ret.put("type","error");
            ret.put("msg","请选择要删除的分类信息！");
            return ret;
        }

        try {
            if (newsCategoryService.delete(id) <= 0){
                ret.put("type","error");
                ret.put("msg","分类删除失败，请联系管理员！");
                return ret;
            }
        }catch (Exception e){
            ret.put("type","error");
            ret.put("msg","该分类下存在新闻信息，不允许删除！");
            return ret;

        }
        ret.put("type","success");
        ret.put("msg","删除成功！");
        return ret;
    }

    /*
  分类查询
   */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getList(
            @RequestParam(name = "name",required = false,defaultValue = "")String name,
            Page page
    ){
        Map<String,Object> ret = new HashMap<String, Object>();
        Map<String,Object> queryMap = new HashMap<String, Object>();
        queryMap.put("name",name);
        queryMap.put("offset",page.getOffset());
        queryMap.put("pageSize",page.getRows());

        ret.put("rows",newsCategoryService.findList(queryMap));
        ret.put("total",newsCategoryService.getTotal(queryMap));
        return ret;
    }
}
