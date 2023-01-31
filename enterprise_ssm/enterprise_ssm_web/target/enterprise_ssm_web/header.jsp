
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 头部 start -->
<header id="header">
    <div class="top_banner">
        <img src="images/top_banner.jpg" alt="">
    </div>
    <div class="shortcut">
        <!-- 未登录状态  -->
        <div class="login_out">
            <a href="${pageContext.request.contextPath}/login1.jsp">登录</a>
            <a href="${pageContext.request.contextPath}/register.jsp">注册</a>
            <a href="${pageContext.request.contextPath}/login.jsp">后台登录</a>
        </div>
        <!-- 登录状态  -->
        <div class="login">
            <a href="myfavorite.html" class="collection">我的收藏</a>
            <a href="${pageContext.request.contextPath}/login1.jsp">退出</a>
        </div>
    </div>
    <div class="header_wrap">
        <div class="topbar">
            <div class="logo">
                <a href="/"><img src="images/logo.jpg" alt=""></a>
            </div>
            <div class="search">
                <input name="rname" id="search_input"  type="text" placeholder="请输入路线名称" class="search_input" autocomplete="off">
                <a href="${pageContext.request.contextPath}/route/findLikeName.do" id="search-button" class="search-button">搜索</a>
            </div>
            <div class="hottel">
                <div class="hot_pic">
                    <img src="images/hot_tel.jpg" alt="">
                </div>
                <div class="hot_tel">
                    <p class="hot_time">客服热线(9:00-6:00)</p>
                    <p class="hot_num">400-618-9090</p>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- 头部 end -->
<!-- 首页导航 -->
<div class="navitem">
    <ul id="category" class="nav">
         <li class="nav-active"><a href="${pageContext.request.contextPath}/index.jsp">首页</a></li>
         <c:forEach items="${categoryList}" var="category">
             <li><a href="${pageContext.request.contextPath}/route/saveId.do?cid=${category.cid}">${category.cname}</a></li>
         </c:forEach>
        <li><a href="favoriterank.html">收藏排行榜</a></li>
        <li><a href="${pageContext.request.contextPath}/category/findAll.do">刷新</a></li>
    </ul>
</div>

