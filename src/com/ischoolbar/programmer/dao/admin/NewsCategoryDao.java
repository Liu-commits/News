package com.ischoolbar.programmer.dao.admin;

import com.ischoolbar.programmer.entity.admin.NewsCategory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/*
新闻分类
 */
@Repository
public interface NewsCategoryDao {
    public int add(NewsCategory newsCategory);
    public int edit(NewsCategory newsCategory);
    public int delete(long id);
    public List<NewsCategory> findList(Map<String,Object> queryMap);
    public List<NewsCategory> findAll();
    public int getTotal(Map<String,Object> queryMap);

}
