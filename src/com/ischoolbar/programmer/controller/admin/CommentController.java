package com.ischoolbar.programmer.controller.admin;

import com.ischoolbar.programmer.entity.admin.Comment;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.CommentService;
import com.ischoolbar.programmer.service.admin.NewsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 新闻评论控制器
 */
@RequestMapping("/admin/comment")
@Controller
public class CommentController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private CommentService commentService;
    /*
    新闻评论列表页面
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model){
        Map<String,Object> queryMap = new HashMap<String, Object>();
        queryMap.put("offset",0);
        queryMap.put("pageSize",999);
        model.addObject("newsList",newsService.findList(queryMap));
        model.setViewName("comment/list");
        return model;
    }

    /*
    添加
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> add(Comment comment){
        Map<String,String> ret = new HashMap<String, String>();
        if (comment == null){
            ret.put("type","error");
            ret.put("msg","请填写正确的评论信息！");
            return ret;
        }
        if (comment.getNewsId() == null){
            ret.put("type","error");
            ret.put("msg","请选择要评论的新闻！");
            return ret;
        }
        if (StringUtils.isEmpty(comment.getNickname())){
            ret.put("type","error");
            ret.put("msg","评论昵称不能为空！");
            return ret;
        }
        if (StringUtils.isEmpty(comment.getContent())){
            ret.put("type","error");
            ret.put("msg","评论内容不能为空！");
            return ret;
        }
        comment.setCreateTime(new Date());
        if (commentService.add(comment) <= 0){
            ret.put("type","error");
            ret.put("msg","评论添加失败，请联系管理员！");
            return ret;
        }
        
        ret.put("type","success");
        ret.put("msg","评论成功！");
        //评论增加
        newsService.updateCommentNumber(comment.getNewsId());
        
        return ret;
    }

    /*
    评论编辑
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> edit(Comment comment){
        Map<String,String> ret = new HashMap<String, String>();
        if (comment == null){
            ret.put("type","error");
            ret.put("msg","请填写正确的评论信息！");
            return ret;
        }
        if (comment.getNewsId() == null){
            ret.put("type","error");
            ret.put("msg","请选择要评论的新闻！");
            return ret;
        }
        if (StringUtils.isEmpty(comment.getNickname())){
            ret.put("type","error");
            ret.put("msg","评论昵称不能为空！");
            return ret;
        }
        if (StringUtils.isEmpty(comment.getContent())){
            ret.put("type","error");
            ret.put("msg","评论内容不能为空！");
            return ret;
        }
        if (commentService.edit(comment) <= 0){
            ret.put("type","error");
            ret.put("msg","评论修改失败，请联系管理员！");
            return ret;
        }
        ret.put("type","success");
        ret.put("msg","修改评论成功！");
        return ret;
    }

    /*
   评论删除
    */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> delete(String ids){
        Map<String,String> ret = new HashMap<String, String>();
        if (ids == null){
            ret.put("type","error");
            ret.put("msg","请选择要删除的评论信息！");
            return ret;
        }
        if(ids.contains(",")){
            ids = ids.substring(0,ids.length()-1);
        }
        if (commentService.delete(ids) <= 0){
            ret.put("type","error");
            ret.put("msg","评论删除失败，请联系管理员！");
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
            @RequestParam(name = "nickname",required = false,defaultValue = "")String nickname,
            @RequestParam(name = "content",required = false,defaultValue = "")String content,
            Page page
    ){
        Map<String,Object> ret = new HashMap<String, Object>();
        Map<String,Object> queryMap = new HashMap<String, Object>();
        queryMap.put("nickname",nickname);
        queryMap.put("content",content);
        queryMap.put("offset",page.getOffset());
        queryMap.put("pageSize",page.getRows());

        ret.put("rows",commentService.findList(queryMap));
        ret.put("total",commentService.getTotal(queryMap));
        return ret;
    }
}
