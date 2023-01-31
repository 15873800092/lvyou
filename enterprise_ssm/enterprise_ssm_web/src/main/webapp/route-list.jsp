
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>旅游-搜索</title>
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" href="css/search.css">
    <script src="js/jquery-3.3.1.js"></script>

</head>
<body>
<!--引入头部-->
<div><jsp:include page="header.jsp"></jsp:include></div>
<div class="page_one">
    <div class="contant">
        <div class="crumbs">
            <img src="images/search.png" alt="">
            <p>旅行><span>搜索结果</span>
                <a href="${pageContext.request.contextPath}/route/findAllPage.do?page=1&size=8">刷新</a>
            </p>
        </div>
        <div class="xinxi clearfix">
            <div class="left">
                <div class="header">
                    <span>商品信息</span>
                    <span class="jg">价格</span>
                </div>
                <ul id="route">
                    <c:forEach var="route" items="${pageInfo.list}">
                        <li>
                            <div class="img"><img src="${route.rimage}" alt=""></div>
                            <div class="text1">
                                <p>${route.rname}</p>
                                <br/>
                                <p>${route.routeIntroduce}</p>
                            </div>
                            <div class="price">
                                <p class="price_num">
                                    <span>&yen;</span>
                                    <span>${route.price}</span>
                                    <span>起</span>
                                </p>
                                <p><a href="route_detail.html">查看详情</a></p>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
                <div class="page_num_inf">
                    <i></i> 共
                    <span>${pageInfo.pages}</span>页<span>${pageInfo.total}</span>条
                </div>
                <div class="pageNum">
                    <ul>
                        <li class="threeword">
                            <a href="${pageContext.request.contextPath}/route/findAllPage.do?page=1&size=${pageInfo.pageSize}" aria-label="Previous">首页</a>
                        </li>
                        <li class="threeword"><a href="${pageContext.request.contextPath}/route/findAllPage.do?page=${pageInfo.pageNum-1}&size=${pageInfo.pageSize}">上一页</a></li>
                        <c:forEach begin="1" end="${pageInfo.pages}" var="pageNum">
                            <li class="threeword"><a href="${pageContext.request.contextPath}/route/findAllPage.do?page=${pageNum}&size=${pageInfo.pageSize}">${pageNum}</a></li>
                        </c:forEach>
                        <li class="threeword"><a href="${pageContext.request.contextPath}/route/findAllPage.do?page=${pageInfo.pageNum+1}&size=${pageInfo.pageSize}">下一页</a></li>
                        <li class="threeword">
                            <a href="${pageContext.request.contextPath}/route/findAllPage.do?page=${pageInfo.pages}&size=${pageInfo.pageSize}" aria-label="Next">尾页</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="right">
                <div class="top">
                    <div class="hot">HOT</div>
                    <span>热门推荐</span>
                </div>
                <ul>
                    <c:forEach var="route" items="${pageInfo.list}" begin="1" end="5}">
                        <li>
                            <div class="left"><img src="${route.image}" alt=""></div>
                            <div class="right">
                                <p>${route.rname}</p>
                                <p>网付价<span>&yen;<span>${route.price}</span>起</span>
                                </p>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>

<!--引入头部-->
<div><jsp:include page="footer.jsp"></jsp:include></div>
<!--导入布局js，共享header和footer-->
<script type="text/javascript" src="js/include.js"></script>
</body>

</html>
