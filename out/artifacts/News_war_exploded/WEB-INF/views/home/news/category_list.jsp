<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../common/header.jsp"%>
<section class="container">
    <div class="content-wrap">
        
        <div class="content" id="news-list">
<%--            <article class="excerpt-minic excerpt-minic-index">--%>
<%--                <h2><span class="red">【推荐】</span><a target="_blank" href="#" title="用DTcms做一个独立博客网站（响应式模板）" >用DTcms做一个独立博客网站（响应式模板）</a>--%>
<%--                </h2>--%>
<%--                <p class="note">用DTcms做一个独立博客网站（响应式模板），采用DTcms V4.0正式版（MSSQL）。开发环境：SQL2008R2+VS2010。DTcms V4.0正式版功能修复和优化：1、favicon.ico图标后台上传。（解决要换图标时要连FTP或者开服务器的麻烦）</p>--%>
<%--            </article>--%>
        <div class="title">
            <h3>${newsCategory.name}</h3>
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
                <a class="focus" href="../news/datail?id=${news.id}" title="${news.title}" target="_blank" >
                    <img class="thumb" data-original="${news.id}" src="${news.photo}" alt="${news.title}"  style="display: inline;">
                </a>
                <header>
                    <a class="cat" href="../news/category_list?id=${news.categoryId}" title="${news.newsCategory.name}" >${news.newsCategory.name}<i></i></a>
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
            <div class="ias_trigger" ><a href="javascript:;" id="Load-more-btn">查看更多</a></div>
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
<script type="application/javascript">
    
    var page = 1;
    $(document).ready(function () {
        if ($('#Load-more-btn').attr('data-key') == 'all'){
            return;
        }
        $('#Load-more-btn').text("查看更多");
        $('#Load-more-btn').click(function () {
            $.ajax({
                url:'../news/get_category_list',
                type:'post',
                dataType:'json',
                data:{rows:2,page:page++,cid:'${newsCategory.id}'},
                success:function (data) {
                    if (data.type == 'success'){
                        $('#Load-more-btn').text("查看更多");
                        var newsList = data.newsList;
                        if (newsList.length == 0){
                            $('#Load-more-btn').text("没有更多了！");
                            $('#Load-more-btn').attr('data-key','all');
                        }
                        var html = '';
                        for (var i=0;i<newsList.length;i++){
                            var atical = '<article class="excerpt excerpt-1" style="">';
                            atical += '<a class="focus" href="../news/datail?id='+newsList[i].id+'" title="'+newsList[i].title+'" target="_blank" >';
                            atical += ' <img class="thumb" data-original="'+newsList[i].id+'" src="'+newsList[i].photo+'" alt="'+newsList[i].title+'"  style="display: inline;"></a>';
                            atical += '<header><a class="cat" href="../news/category_list?id='+newsList[i].categoryId+'" title="'+newsList[i].newsCategory.name+'" >'+newsList[i].newsCategory.name+'<i></i></a>';
                            /*atical += '<a class="cat" href="*" title="'+newsList[i].title+'" >'+newsList[i].tags+'<i></i></a>';*/
                            atical += '<h2><a href="#" title="'+newsList[i].title+'" target="_blank" >'+newsList[i].title+'</a></h2></header>';
                            atical += '<p class="meta">';
                            atical += '<time class="time"><i class="glyphicon glyphicon-time"></i> '+newsList[i].createTime+'</time>';
                            atical += '<span class="views"><i class="glyphicon glyphicon-eye-open"></i>'+newsList[i].viewNumber+'</span>';
                            atical += '<a class="comment" href="../news/datail?id='+newsList[i].id+'#comment" title="评论" target="_blank" ><i class="glyphicon glyphicon-comment"></i>'+newsList[i].commentNumber+'</a></p>';
                            atical += '<p class="note">'+newsList[i].abstrs+'</p>';
                            atical += '</article>';
                            html += atical;
                        }
                        $("#Load-more-btn").parent("div").before(html);
                    }
                }
            });
        });
        
    });
</script>
