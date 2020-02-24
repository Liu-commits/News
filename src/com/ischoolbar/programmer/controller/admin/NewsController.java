package com.ischoolbar.programmer.controller.admin;

import com.ischoolbar.programmer.entity.admin.NewsCategory;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.NewsCategoryService;
import com.ischoolbar.programmer.service.admin.NewsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 新闻列表控制器
 */
@RequestMapping("/admin/news")
@Controller
public class NewsController {

    @Autowired
    private NewsCategoryService newsCategoryService;
    @Autowired
    private NewsService newsService;
    /*
    新闻列表页面
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model){
        model.addObject("newsCategoryList",newsCategoryService.findAll());
        model.setViewName("news/list");
        return model;
    }

    /*
   新闻add页面
    */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public ModelAndView add(ModelAndView model){
        model.addObject("newsCategoryList",newsCategoryService.findAll());
        model.setViewName("news/add");
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
            ret.put("msg","请填写正确的列表信息！");
            return ret;
        }
        if (StringUtils.isEmpty(newsCategory.getName())){
            ret.put("type","error");
            ret.put("msg","列表名称不能为空！");
            return ret;
        }
        if (newsCategoryService.add(newsCategory) <= 0){
            ret.put("type","error");
            ret.put("msg","列表添加失败，请联系管理员！");
            return ret;
        }
        ret.put("type","success");
        ret.put("msg","添加成功！");
        return ret;
    }

    /*
    列表编辑
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> edit(NewsCategory newsCategory){
        Map<String,String> ret = new HashMap<String, String>();
        if (newsCategory == null){
            ret.put("type","error");
            ret.put("msg","请填写正确的列表信息！");
            return ret;
        }
        if (StringUtils.isEmpty(newsCategory.getName())){
            ret.put("type","error");
            ret.put("msg","列表名称不能为空！");
            return ret;
        }
        if (newsCategoryService.edit(newsCategory) <= 0){
            ret.put("type","error");
            ret.put("msg","列表修改失败，请联系管理员！");
            return ret;
        }
        ret.put("type","success");
        ret.put("msg","修改成功！");
        return ret;
    }

    /*
   列表删除
    */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> delete(Long id){
        Map<String,String> ret = new HashMap<String, String>();
        if (id == null){
            ret.put("type","error");
            ret.put("msg","请选择要删除的列表信息！");
            return ret;
        }

        try {
            if (newsCategoryService.delete(id) <= 0){
                ret.put("type","error");
                ret.put("msg","列表删除失败，请联系管理员！");
                return ret;
            }
        }catch (Exception e){
            ret.put("type","error");
            ret.put("msg","该列表下存在新闻信息，不允许删除！");
            return ret;

        }
        ret.put("type","success");
        ret.put("msg","删除成功！");
        return ret;
    }

    /*
  列表查询
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

    /**
     * 上传图片
     * @param photo
     * @param request
     * @return
     */
    @RequestMapping(value="/upload_photo",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> uploadPhoto(MultipartFile photo, HttpServletRequest request){
        Map<String, String> ret = new HashMap<String, String>();
        if(photo == null){
            ret.put("type", "error");
            ret.put("msg", "选择要上传的文件！");
            return ret;
        }
        if(photo.getSize() > 1024*1024*1024){
            ret.put("type", "error");
            ret.put("msg", "文件大小不能超过10M！");
            return ret;
        }
        //获取文件后缀
        String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".")+1,photo.getOriginalFilename().length());
        if(!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())){
            ret.put("type", "error");
            ret.put("msg", "请选择jpg,jpeg,gif,png格式的图片！");
            return ret;
        }
        String savePath = request.getServletContext().getRealPath("/") + "/resources/upload/";
        File savePathFile = new File(savePath);
        if(!savePathFile.exists()){
            //若不存在改目录，则创建目录
            savePathFile.mkdir();
        }
        String filename = new Date().getTime()+"."+suffix;
        try {
            //将文件保存至指定目录
            photo.transferTo(new File(savePath+filename));
        }catch (Exception e) {
            // TODO Auto-generated catch block
            ret.put("type", "error");
            ret.put("msg", "保存文件异常！");
            e.printStackTrace();
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "用户上传图片成功！");
        ret.put("filepath",request.getServletContext().getContextPath() + "/resources/upload/" + filename );
        return ret;
    }
}
