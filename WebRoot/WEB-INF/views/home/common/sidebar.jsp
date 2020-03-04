<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<aside class="sidebar">
    <div class="fixed">
        <div class="widget widget-tabs">
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#notice" aria-controls="notice" role="tab" data-toggle="tab" >统计信息</a></li>
                <li role="presentation"><a href="#contact" aria-controls="contact" role="tab" data-toggle="tab" >联系站长</a></li>
            </ul>
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane contact active" id="notice">
                    <h2>文章总数:
                        888篇
                    </h2>
                    <h2>网站运行:
                        <span id="sitetime">88天 </span></h2>
                </div>
                <div role="tabpanel" class="tab-pane contact" id="contact">
                    <h2>QQ:
                        <a href="#" target="_blank" rel="nofollow" data-toggle="tooltip" data-placement="bottom" title=""  data-original-title="QQ:1656389800">1656389800</a>
                    </h2>
                    <h2>Email:
                        <a href="#" target="_blank" data-toggle="tooltip" rel="nofollow" data-placement="bottom" title=""  data-original-title="liuyouquan6688@163.com">liuyouquan6688@163.com</a></h2>
                </div>
            </div>
        </div>
        <div class="widget widget_search">
            <form class="navbar-form" action="/Search" method="post">
                <div class="input-group">
                    <input type="text" name="keyword" class="form-control" size="35" placeholder="请输入关键字" maxlength="15" autocomplete="off">
                    <span class="input-group-btn">
		<button class="btn btn-default btn-search" name="search" type="submit">搜索</button>
		</span> </div>
            </form>
        </div>
    </div>
    <div class="widget widget_hot">
        <h3>最新评论文章</h3>
        <ul id="last-comment-list">
				
			
				
            

        </ul>
    </div>
    <div class="widget widget_sentence">
        <a href="https://github.com/Liu-commits/News" target="_blank" rel="nofollow" title="广告位招租:QQ：1656389800" >
            <img style="width: 100%" src="<c:url value="/resources/home/images/cebianguanggao.png"></c:url>" alt="广告位招租：QQ：1656389800" ></a>
    </div>
    <div class="widget widget_sentence">
        <a href="https://github.com/Liu-commits/News" target="_blank" rel="nofollow" title="GitHub" >
            <img style="width: 100%" src="<c:url value="/resources/home/images/youxiajiao.png"></c:url>" alt="MZ-NetBlog主题" ></a>
    </div>
    <div class="widget widget_sentence">
        <h3>友情链接</h3>
        <div class="widget-sentence-link">
            <a href="https://github.com/Liu-commits/News" title="新闻博客源码（GitHub）" target="_blank" >GitHub</a>&nbsp;&nbsp;&nbsp;
            <a href="http://baidu.com" title="百度" target="_blank" >百度</a>&nbsp;&nbsp;&nbsp;
        </div>
    </div>
</aside>
<script type="application/javascript">
    $(document).ready(function () {
        $.ajax({
            url:'../news/last_comment_list',
            type:'post',
            dataType:'json',
            success:function (data) {
                if (data.type == 'success'){
                    var newsList = data.newsList;
                    var html = '';
                    for (var i=0;i<newsList.length;i++){
                        var li = '<li><a title="'+newsList[i].title+'" href="../news/detail?id=' + newsList[i].id +'" ><span class="thumbnail">';
                        li += '<img class="thumb" data-original="'+newsList[i].photo+'" src="'+newsList[i].photo+'" alt="'+newsList[i].title+'"  style="display: block;">';
                        li += '</span><span class="text">'+newsList[i].title+'</span><span class="muted"><i class="glyphicon glyphicon-time"></i>';
                        li += ''+newsList[i].createTime+'' +'</span><span class="muted"><i class="glyphicon glyphicon-eye-open"></i>'+newsList[i].viewNumber+'</span></a></li>';
                        html += li;
                        
                    }
                    $("#last-comment-list").append(html);
                }
            }
        });
    });
</script>