<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2022/4/19
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" href="css/register.css">
    <!--导入jquery-->
    <script src="js/jquery-3.3.1.js"></script>




</head>
<body>
<!--引入头部-->
<div id="header"></div>
<!-- 头部 end -->
<div class="rg_layout">
    <div class="rg_form clearfix">
        <div class="rg_form_left">
            <p>新用户注册</p>
            <p>USER REGISTER</p>
        </div>
        <div class="rg_form_center">
            <div id="errorMsg" style="color:red;text-align: center"></div>
            <!--注册表单-->
            <form id="registerForm" action="${pageContext.request.contextPath}/register_ok.jsp" method="post">
                <!--提交处理请求的标识符-->
                <input type="hidden" name="action" value="register">
                <table style="margin-top: 25px;">
                    <tr>
                        <td class="td_left">
                            <label for="username">姓名</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="username" name="name" placeholder="请输入账号">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="password">密码</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="password" name="password" placeholder="请输入密码">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="email">Email</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="email" name="email" placeholder="请输入Email">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="name">身份证号码</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="name" name="credentialsNum" placeholder="请输入身份证号码">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="telephone">手机号</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="telephone" name="phoneNum" placeholder="请输入您的手机号">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="sex">性别</label>
                        </td>
                        <td class="td_right gender">
                            <input type="radio" id="sex" name="sex" value="男" checked> 男
                            <input type="radio" name="sex" value="女"> 女
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="sex">身份证类型</label>
                        </td>
                        <td class="td_right gender">
                            <input type="radio" name="credentialsTypeStr" value="身份证" checked> 身份证
                            <input type="radio" name="credentialsTypeStr" value="护照"> 护照
                            <input type="radio" name="credentialsTypeStr" value="军官证"> 军官证
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="sex">人群类型</label>
                        </td>
                        <td class="td_right gender">
                            <input type="radio" name="travellerTypeStr" value="儿童" checked> 儿童
                            <input type="radio" name="travellerTypeStr" value="成人"> 成人
                        </td>
                    </tr>


                    <tr>
                        <td class="td_left">
                        </td>
                        <td class="td_right check">
                            <input type="submit" class="submit" value="注册">
                            <span id="msg" style="color: red;"></span>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div class="rg_form_right">
            <p>
                已有账号？
                <a href="${pageContext.request.contextPath}/login1.jsp">立即登录</a>
            </p>
        </div>
    </div>
</div>
<!--引入尾部-->
<div id="footer"></div>
<!--导入布局js，共享header和footer-->
<script type="text/javascript" src="js/include.js"></script>

</body>
</html>
