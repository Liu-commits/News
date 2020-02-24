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
 * �����б������
 */
@RequestMapping("/admin/news")
@Controller
public class NewsController {

    @Autowired
    private NewsCategoryService newsCategoryService;
    @Autowired
    private NewsService newsService;
    /*
    �����б�ҳ��
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model){
        model.addObject("newsCategoryList",newsCategoryService.findAll());
        model.setViewName("news/list");
        return model;
    }

    /*
   ����addҳ��
    */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public ModelAndView add(ModelAndView model){
        model.addObject("newsCategoryList",newsCategoryService.findAll());
        model.setViewName("news/add");
        return model;
    }

    /*
    ���
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> add(NewsCategory newsCategory){
        Map<String,String> ret = new HashMap<String, String>();
        if (newsCategory == null){
            ret.put("type","error");
            ret.put("msg","����д��ȷ���б���Ϣ��");
            return ret;
        }
        if (StringUtils.isEmpty(newsCategory.getName())){
            ret.put("type","error");
            ret.put("msg","�б����Ʋ���Ϊ�գ�");
            return ret;
        }
        if (newsCategoryService.add(newsCategory) <= 0){
            ret.put("type","error");
            ret.put("msg","�б����ʧ�ܣ�����ϵ����Ա��");
            return ret;
        }
        ret.put("type","success");
        ret.put("msg","��ӳɹ���");
        return ret;
    }

    /*
    �б�༭
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> edit(NewsCategory newsCategory){
        Map<String,String> ret = new HashMap<String, String>();
        if (newsCategory == null){
            ret.put("type","error");
            ret.put("msg","����д��ȷ���б���Ϣ��");
            return ret;
        }
        if (StringUtils.isEmpty(newsCategory.getName())){
            ret.put("type","error");
            ret.put("msg","�б����Ʋ���Ϊ�գ�");
            return ret;
        }
        if (newsCategoryService.edit(newsCategory) <= 0){
            ret.put("type","error");
            ret.put("msg","�б��޸�ʧ�ܣ�����ϵ����Ա��");
            return ret;
        }
        ret.put("type","success");
        ret.put("msg","�޸ĳɹ���");
        return ret;
    }

    /*
   �б�ɾ��
    */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> delete(Long id){
        Map<String,String> ret = new HashMap<String, String>();
        if (id == null){
            ret.put("type","error");
            ret.put("msg","��ѡ��Ҫɾ�����б���Ϣ��");
            return ret;
        }

        try {
            if (newsCategoryService.delete(id) <= 0){
                ret.put("type","error");
                ret.put("msg","�б�ɾ��ʧ�ܣ�����ϵ����Ա��");
                return ret;
            }
        }catch (Exception e){
            ret.put("type","error");
            ret.put("msg","���б��´���������Ϣ��������ɾ����");
            return ret;

        }
        ret.put("type","success");
        ret.put("msg","ɾ���ɹ���");
        return ret;
    }

    /*
  �б��ѯ
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
     * �ϴ�ͼƬ
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
            ret.put("msg", "ѡ��Ҫ�ϴ����ļ���");
            return ret;
        }
        if(photo.getSize() > 1024*1024*1024){
            ret.put("type", "error");
            ret.put("msg", "�ļ���С���ܳ���10M��");
            return ret;
        }
        //��ȡ�ļ���׺
        String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".")+1,photo.getOriginalFilename().length());
        if(!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())){
            ret.put("type", "error");
            ret.put("msg", "��ѡ��jpg,jpeg,gif,png��ʽ��ͼƬ��");
            return ret;
        }
        String savePath = request.getServletContext().getRealPath("/") + "/resources/upload/";
        File savePathFile = new File(savePath);
        if(!savePathFile.exists()){
            //�������ڸ�Ŀ¼���򴴽�Ŀ¼
            savePathFile.mkdir();
        }
        String filename = new Date().getTime()+"."+suffix;
        try {
            //���ļ�������ָ��Ŀ¼
            photo.transferTo(new File(savePath+filename));
        }catch (Exception e) {
            // TODO Auto-generated catch block
            ret.put("type", "error");
            ret.put("msg", "�����ļ��쳣��");
            e.printStackTrace();
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "�û��ϴ�ͼƬ�ɹ���");
        ret.put("filepath",request.getServletContext().getContextPath() + "/resources/upload/" + filename );
        return ret;
    }
}
