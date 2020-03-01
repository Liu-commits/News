package com.ischoolbar.programmer.dao.admin;

import com.ischoolbar.programmer.entity.admin.News;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/*
пбне
 */
@Repository
public interface NewsDao {
    public int add(News news);
    public int edit(News news);
    public int delete(long id);
    public List<News> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
    public News find(Long id);
    public int updateCommentNumber(Long id);
    public int updateViewNumber(Long id);

}
