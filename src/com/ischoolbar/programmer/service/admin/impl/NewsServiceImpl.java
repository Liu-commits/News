package com.ischoolbar.programmer.service.admin.impl;

import com.ischoolbar.programmer.dao.admin.NewsDao;
import com.ischoolbar.programmer.entity.admin.News;
import com.ischoolbar.programmer.service.admin.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/*
����serviceʵ����
 */
@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDao newsDao;

    @Override
    public int add(News news) {
        return newsDao.add(news);
    }

    @Override
    public int edit(News news) {
        return newsDao.edit(news);
    }

    @Override
    public int delete(long id) {
        return newsDao.delete(id);
    }

    @Override
    public List<News> findList(Map<String, Object> queryMap) {
        return newsDao.findList(queryMap);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return newsDao.getTotal(queryMap);
    }
}