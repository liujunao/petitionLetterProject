<%@ page import="com.springmvc.service.PetitionLetterService" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/9/13
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>首页</title>
</head>
<body>

<div>

    <ul>
        <%
            PetitionLetterService petitionLetterService = new PetitionLetterService();
            List<Map<String, Object>> list = null;
            Map<String, Object> map = null;
            int pageSize = 2;
            int pageNumber = Integer.parseInt(request.getAttribute("pageNumber") != null ? String.valueOf(request.getAttribute("pageNumber")) : "0");
            System.out.println(pageNumber);
            Object totalPage1 = request.getAttribute("totalPage");
            System.out.println(totalPage1);
            list = petitionLetterService.queryPage((pageNumber - 1)* pageSize,pageSize);
            if (list != null && list.size() > 0){
                for (int i = 0;i < list.size();i++){
                    map = list.get(i);
                    %>
        <li>
            <a href="${pageContext.request.contextPath}/petitionLetter/reviseLetter?id=<%=map.get("letterId")%>&name=view"><%=map.get("letterId")%>--<%=map.get("title")%></a>
            <a href="${pageContext.request.contextPath}/petitionLetter/reviseLetter?id=<%=map.get("letterId")%>&name=delete">删除</a>
            <a href="${pageContext.request.contextPath}/petitionLetter/reviseLetter?id=<%=map.get("letterId")%>&name=revise">修改</a>
        </li>
        <%
                }
            }
        %>

    </ul>


    <span>${pageNumber}/${totalPage} &nbsp;&nbsp;&nbsp;第${pageNumber}页</span>
    <a href="${pageContext.request.contextPath}/petitionLetter/page?pageNumber=1">首页</a>
    <c:if test="${pageNumber > 1}">
        <a href="/petitionLetter/page?pageNumber=${pageNumber - 1}">上一页</a>
    </c:if>
    <c:if test="${pageNumber < totalPage}">
        <a href="${pageContext.request.contextPath}/petitionLetter/page?pageNumber=${pageNumber + 1}">下一页</a>
    </c:if>
    <a href="${pageContext.request.contextPath}/petitionLetter/page?pageNumber=${totalPage}">尾页</a>
</div>

</body>
</html>
