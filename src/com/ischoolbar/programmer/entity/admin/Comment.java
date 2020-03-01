package com.ischoolbar.programmer.entity.admin;

import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class Comment {

  private Long id;
  private Long newsId;
  private News news;
  private String nickname;
  private String content;
  private Date createTime;

  public News getNews() {
    return news;
  }

  public void setNews(News news) {
    this.news = news;
  }
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public Long getNewsId() {
    return newsId;
  }

  public void setNewsId(Long newsId) {
    this.newsId = newsId;
  }


  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

}
