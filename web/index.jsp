<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/9/7
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>首页</title>
  </head>
  <body>
  <a href="${pageContext.request.contextPath}/views/login.jsp">登录</a>
  <br><br>
  <a href="${pageContext.request.contextPath}/views/register.jsp">注册</a>
  <br><br>
  <a href="/views/addLetter.jsp">添加信件</a>
  <br><br>
  <a href="/petitionLetter/page">查看信件</a>
  <br><br>
  <a href="/petitionLetter/test">TEST</a>
  </body>
</html>
