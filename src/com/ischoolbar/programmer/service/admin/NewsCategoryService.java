package com.ischoolbar.programmer.service.admin;

import com.ischoolbar.programmer.entity.admin.NewsCategory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/*
新闻分类接口
 */
@Service
public interface NewsCategoryService {
    public int add(NewsCategory newsCategory);
    public int edit(NewsCategory newsCategory);
    public int delete(long id);
    public List<NewsCategory> findList(Map<String,Object> queryMap);
    public List<NewsCategory> findAll();
    public int getTotal(Map<String,Object> queryMap);
    public NewsCategory find(Long id);

}
