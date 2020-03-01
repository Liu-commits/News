package com.ischoolbar.programmer.controller.admin;

import com.ischoolbar.programmer.entity.admin.News;
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
    public Map<String,String> add(News news){
        Map<String,String> ret = new HashMap<String, String>();
        if (news == null){
            ret.put("type","error");
            ret.put("msg","请填写正确的列表信息！");
            return ret;
        }
        if (StringUtils.isEmpty(news.getTitle())){
            ret.put("type","error");
            ret.put("msg","列表标题不能为空！");
            return ret;
        }
        if (news.getCategoryId() == null){
            ret.put("type","error");
            ret.put("msg","请选择新闻分类！");
            return ret;
        }
        if (StringUtils.isEmpty(news.getAbstrs())){
            ret.put("type","error");
            ret.put("msg","新闻摘要不能为空！");
            return ret;
        }
        if (StringUtils.isEmpty(news.getTags())){
            ret.put("type","error");
            ret.put("msg","新闻标签不能为空！");
            return ret;
        }
        if (StringUtils.isEmpty(news.getPhoto())){
            ret.put("type","error");
            ret.put("msg","新闻图片必须上传！");
            return ret;
        }
        if (StringUtils.isEmpty(news.getAuthor())){
            ret.put("type","error");
            ret.put("msg","新闻作者不能为空！");
            return ret;
        }
        if (StringUtils.isEmpty(news.getContent())){
            ret.put("type","error");
            ret.put("msg","新闻内容不能为空！");
            return ret;
        }
        news.setCreateTime(new Date());
        if (newsService.add(news) <= 0){
            ret.put("type","error");
            ret.put("msg","列表添加失败，请联系管理员！");
            return ret;
        }
        
        ret.put("type","success");
        ret.put("msg","添加成功！");
        return ret;
    }

    /*
   新闻编辑页面
    */
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public ModelAndView edit(ModelAndView model,Long id){
        model.addObject("newsCategoryList",newsCategoryService.findAll());
        model.addObject("news",newsService.find(id));
        model.setViewName("news/edit");
        return model;
    }
    
    /*
    列表编辑
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> edit(News news){
        Map<String,String> ret = new HashMap<String, String>();
        if (news == null){
            ret.put("type","error");
            ret.put("msg","请填写正确的列表信息！");
            return ret;
        }
        if (StringUtils.isEmpty(news.getTitle())){
            ret.put("type","error");
            ret.put("msg","列表标题不能为空！");
            return ret;
        }
        if (news.getCategoryId() == null){
            ret.put("type","error");
            ret.put("msg","请选择新闻分类！");
            return ret;
        }
        if (StringUtils.isEmpty(news.getAbstrs())){
            ret.put("type","error");
            ret.put("msg","新闻摘要不能为空！");
            return ret;
        }
        if (StringUtils.isEmpty(news.getTags())){
            ret.put("type","error");
            ret.put("msg","新闻标签不能为空！");
            return ret;
        }
        if (StringUtils.isEmpty(news.getPhoto())){
            ret.put("type","error");
            ret.put("msg","新闻图片必须上传！");
            return ret;
        }
        if (StringUtils.isEmpty(news.getAuthor())){
            ret.put("type","error");
            ret.put("msg","新闻作者不能为空！");
            return ret;
        }
        if (StringUtils.isEmpty(news.getContent())){
            ret.put("type","error");
            ret.put("msg","新闻内容不能为空！");
            return ret;
        }
        
        if (newsService.edit(news) <= 0){
            ret.put("type","error");
            ret.put("msg","新闻修改失败，请联系管理员！");
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
            if (newsService.delete(id) <= 0){
                ret.put("type","error");
                ret.put("msg","新闻删除失败，请联系管理员！");
                return ret;
            }
        }catch (Exception e){
            ret.put("type","error");
            ret.put("msg","未知错误（可能存在评论信息），不允许删除！");
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
            @RequestParam(name = "title",required = false,defaultValue = "")String title,
            @RequestParam(name = "author",required = false,defaultValue = "")String author,
            @RequestParam(name = "categoryId",required = false)Long categoryId,
            Page page
    ){
        Map<String,Object> ret = new HashMap<String, Object>();
        Map<String,Object> queryMap = new HashMap<String, Object>();
        queryMap.put("title",title);
        queryMap.put("author",author);
        if (categoryId != null && categoryId.longValue() != -1){
            queryMap.put("categoryId",categoryId);
        }
        queryMap.put("offset",page.getOffset());
        queryMap.put("pageSize",page.getRows());

        ret.put("rows",newsService.findList(queryMap));
        ret.put("total",newsService.getTotal(queryMap));
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
