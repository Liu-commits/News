package com.ischoolbar.programmer.dao.admin;

import com.ischoolbar.programmer.entity.admin.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/*
ÐÂÎÅpinlun
 */
@Repository
public interface CommentDao {
    public int add(Comment comment);
    public int edit(Comment comment);
    public int delete(String ids);
    public List<Comment> findList(Map<String, Object> queryMap);
    public List<Comment> findAll();
    public int getTotal(Map<String, Object> queryMap);

}
