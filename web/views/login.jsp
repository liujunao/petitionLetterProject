<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/9/9
  Time: 8:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上海交通委员会</title>
    <link href="/statics/css/ETUI.min.css" rel="stylesheet" type="text/css"/>
    <link href="/statics/css/jhelper_tool_style.css" rel="stylesheet" type="text/css"/>
    <link href="/statics/css/sh-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="/statics/css/style.css" rel="stylesheet" type="text/css"/>
    <script href="/statics/js/jquery-3.2.1.js"></script>
</head>
<body>

<div id="main">
    <div class="container">
        <div class="row-fluid">
            <div class="span12">
                <ul class="breadcrumb" id="breadCrumbNav">
                    <li>当前位置：</li>
                    <li><a title="首页" href="">首页</a></li>
                    <span class="divider">&gt;</span>
                    <li class="active"><a title="互动平台" href="">互动平台</a></li>
                </ul>
            </div>
        </div>
        <div class="row-fluid complaints">
            <div class="span12 no-margin-left">
                <div class="content border">

                    <form action="${pageContext.request.contextPath}/adminUserInfo/login" method="post">

                        <div class="page-header page-header-2 news-title no-margin">
                            <h2 class="margin-top10"><span>用户登录</span></h2>
                        </div>
                        <div class="row-fluid margin-bottom15">
                            <div class="span3 text-right">
                                <label><span class="text-danger">*</span>用户名</label>
                            </div>
                            <div class="span6"><input name="userName"type="text" placeholder="请输入用户名，必填项" maxlength="18"/></div>
                        </div>
                        <div class="row-fluid margin-bottom15">
                            <div class="span3 text-right">
                                <label><span class="text-danger">*</span>密码 </label>
                            </div>
                            <div class="span6"><input name="password"placeholder="请输入密码，必填项" type="password"/></div>
                        </div>
                        <div class="row-fluid margin-bottom15">
                            <div class="span3 text-right">
                                <label><span class="text-danger">*</span>验证码</label>
                            </div>
                            <div class="span6"><input name="validation" type="text" id="validation" placeholder="请输入验证码" maxlength="40"/></div>
                            <div class="span6"><img src="/adminUserInfo/validation" id="validate" onclick="this.src=this.src+'?'"></div>
                        </div>
                        <div class="row-fluid">
                            <div class="span6">
                                <input type="submit" value="登录"/>
                                <input type="submit" value="注册" src="/views/register.jsp"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
