package com.ischoolbar.programmer.service.admin.impl;

import com.ischoolbar.programmer.dao.admin.CommentDao;
import com.ischoolbar.programmer.entity.admin.Comment;
import com.ischoolbar.programmer.service.admin.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/*
新闻评论service实现类
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;


    @Override
    public int add(Comment comment) {
        return commentDao.add(comment);
    }

    @Override
    public int edit(Comment comment) {
        return commentDao.edit(comment);
    }

    @Override
    public int delete(String ids) {
        return commentDao.delete(ids);
    }

    @Override
    public List<Comment> findList(Map<String, Object> queryMap) {
        return commentDao.findList(queryMap);
    }

    @Override
    public List<Comment> findAll() {
        return commentDao.findAll();
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return commentDao.getTotal(queryMap);
    }
}
