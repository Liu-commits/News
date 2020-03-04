<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>刘有权--新闻博客网站${title}</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/home/css/bootstrap.min.css"></c:url> ">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/home/css/nprogress.css"></c:url>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/home/css/style.css"></c:url>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/home/css/font-awesome.min.css"></c:url>">
    <link rel="apple-touch-icon-precomposed" href="<c:url value="/resources/home/images/icon.png"></c:url>">
    <link rel="shortcut icon" href="<c:url value="/resources/home/images/favicon.ico"></c:url>">
    <script src="<c:url value="/resources/home/js/jquery-2.1.4.min.js"></c:url>"></script>
    <script src="<c:url value="/resources/home/js/nprogress.js"></c:url>"></script>
    <script src="<c:url value="/resources/home/js/jquery.lazyload.min.js"></c:url>"></script>
    <!--[if gte IE 9]>
    <script src="<c:url value="/resources/home/js/jquery-1.11.1.min.js"></c:url>" type="text/javascript"></script>
    <script src="<c:url value="/resources/home/js/html5shiv.min.js"></c:url>" type="text/javascript"></script>
    <script src="<c:url value="/resources/home/js/respond.min.js"></c:url>" type="text/javascript"></script>
    <script src="<c:url value="/resources/home/js/selectivizr-min.js"></c:url>" type="text/javascript"></script>
    <![endif]-->

<script type="application/javascript">
    function addFavorite(url, title) {
        try {
            window.external.addFavorite(url, title);
        } catch (e) {
            try {
                if(document.all){
                    window.external.AddFavorite(url,title);
                }
                else if(window.sidebar && window.sidebar.addPanel){
                    window.sidebar.addPanel(title,url,"");
                }
                else if(window.external){
                    window.external.AddFavorite(url,title);
                }
                else if(window.opera && window.print){
                    return true;
                }
               /* window.sidebar.addPanel(title, url, '');*/
            } catch (e) {
                alert("请按 Ctrl+D 键添加到收藏夹", 'notice');
            }
        }
    }

</script>
</head>
<body class="user-select">
<header class="header">
    <nav class="navbar navbar-default" id="navbar">
        <div class="container">
            <div class="header-topbar hidden-xs link-border">
                <ul class="site-nav topmenu">
                    <li><a href="https://github.com/Liu-commits/News" >站长GitHub</a></li>
                    <%--<li><a href="#" rel="nofollow" >读者墙</a></li>--%>
                    <li><a href="#" onclick="addFavorite('http://localhost:8080/News_war_exploded/index/index', '超凡科技-新闻网')" title="RSS订阅" >
                        <i class="fa fa-rss">
                        </i> RSS订阅
                    </a></li>
                </ul>
                【超凡科技】想你所想,真正懂您的新闻博客!</div>
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#header-navbar" aria-expanded="false"> <span class="sr-only"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
                <h1 class="logo hvr-bounce-in"><a href="/News_war_exploded/index/index" title="超凡新闻博客"><img src="<c:url value="/resources/home/images/xwbk.png"></c:url> " alt="超凡新闻博客"></a></h1>
            </div>
            <div class="collapse navbar-collapse" id="header-navbar">
                <form class="navbar-form visible-xs" action="/Search" method="post">
                    <div class="input-group">
                        <input type="text" name="keyword" class="form-control" placeholder="请输入关键字" maxlength="20" autocomplete="off">
                        <span class="input-group-btn">
		<button class="btn btn-default btn-search" name="search" type="submit">搜索</button>
		</span> </div>
                </form>
                <ul class="nav navbar-nav navbar-right">
                    <li><a data-cont="新闻网站" title="超凡新闻" href="/News_war_exploded/index/index">首页</a></li>
                    <c:forEach items="${newscategoryList}" var="newsCategory">
                    <li><a data-cont="${newsCategory.name}" title="${newsCategory.name}" href="../news/category_list?cid=${newsCategory.id} ">${newsCategory.name}</a></li>
                    </c:forEach>

                </ul>
            </div>
        </div>
    </nav>
</header>