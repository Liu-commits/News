<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../common/header.jsp"%>
<section class="container">
    <div class="content-wrap">
        <div class="content">
            <div id="focusslide" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#focusslide" data-slide-to="0" class="active"></li>
                    <li data-target="#focusslide" data-slide-to="1"></li>
                </ol>
                <div class="carousel-inner" role="listbox">
                    <div class="item active">
                        <a href="#" target="_blank" title="超凡新闻博客源码" >
                            <img src="<c:url value="/resources/home/images/side1.png"></c:url>" alt="超凡新闻博客源码" class="img-responsive"></a>
                    </div>
                    <div class="item">
                        <a href="#" target="_blank" title="专业网站建设" >
                            <img src="<c:url value="/resources/home/images/side2.png"/>" alt="专业网站建设" class="img-responsive"></a>
                    </div>
                </div>
                <a class="left carousel-control" href="#focusslide" role="button" data-slide="prev" rel="nofollow"> 
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">上一个</span> 
                </a> 
                <a class="right carousel-control" href="#focusslide" role="button" data-slide="next" rel="nofollow">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">下一个</span> 
                </a>
                </div>
<%--            <article class="excerpt-minic excerpt-minic-index">--%>
<%--                <h2><span class="red">【推荐】</span><a target="_blank" href="#" title="用DTcms做一个独立博客网站（响应式模板）" >用DTcms做一个独立博客网站（响应式模板）</a>--%>
<%--                </h2>--%>
<%--                <p class="note">用DTcms做一个独立博客网站（响应式模板），采用DTcms V4.0正式版（MSSQL）。开发环境：SQL2008R2+VS2010。DTcms V4.0正式版功能修复和优化：1、favicon.ico图标后台上传。（解决要换图标时要连FTP或者开服务器的麻烦）</p>--%>
<%--            </article>--%>
            <div class="title">
                <h3>最新发布</h3>
                <%--<div class="more">
                    <a href="#" title="MZ-NetBlog主题" >MZ-NetBlog主题</a>
                    <a href="#" title="IT技术笔记" >IT技术笔记</a>
                    <a href="#" title="源码分享" >源码分享</a>
                    <a href="#" title="靠谱网赚" >靠谱网赚</a>
                    <a href="#" title="资讯分享" >资讯分享</a>
                </div>--%>
            </div>
            <c:forEach items="${newsList}" var="news">
            <article class="excerpt excerpt-1" style="">
                <a class="focus" href="../news/datail?id=${news.id}" title="${news.title}" target="_blank" ><img class="thumb" data-original="${news.id}" src="${news.photo}" alt="${news.title}"  style="display: inline;"></a>
                <header><a class="cat" href="../news/category_list?id=${news.categoryId}" title="${news.newsCategory.name}" >${news.newsCategory.name}<i></i></a>
                    <%--标签--%>
                    <a class="cat" href="*" title="${news.tags}" >${news.tags}<i></i></a>
                    <h2><a href="#" title="${news.title}" target="_blank" >${news.title}</a>
                    </h2>
                </header>
                <p class="meta">
                    <time class="time"><i class="glyphicon glyphicon-time"></i> <fmt:formatDate value="${news.createTime}" pattern="yyyy-MM-dd hh:mm:ss"></fmt:formatDate> </time>
                    <span class="views"><i class="glyphicon glyphicon-eye-open"></i> ${news.viewNumber}</span>
                    <a class="comment" href="../news/datail?id=${news.id}#comment" title="评论" target="_blank" ><i class="glyphicon glyphicon-comment"></i> ${news.commentNumber}</a>
                </p>
                <p class="note">${news.abstrs}</p>
            </article>
            </c:forEach>
            <div class="ias_trigger" style="display: none"><a href="javascript:;">查看更多</a></div>
            <%--<nav class="pagination" style="display: none;">
                <ul>
                    <li class="prev-page"></li>
                    <li class="active"><span>1</span></li>
                    <li><a href="?page=2">2</a></li>
                    <li class="next-page"><a href="?page=2">下一页</a></li>
                    <li><span>共 2 页</span></li>
                </ul>
            </nav>--%>
        </div>
    </div>
    <div class="tlinks">Collect from <a href="http://www.cssmoban.com/" >企业网站模板</a></div>
    <%@include file="../common/sidebar.jsp"%>
</section>

<%@include file="../common/footer.jsp"%>

