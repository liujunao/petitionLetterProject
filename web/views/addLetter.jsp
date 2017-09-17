<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/9/10
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加信件</title>
    <script src="${pageContext.request.contextPath}/statics/js/jquery-3.2.1.js"></script>
    <script src="${pageContext.request.contextPath}/statics/utf8-jsp/ueditor.config.js"></script>
    <script src="${pageContext.request.contextPath}/statics/utf8-jsp/ueditor.all.js"></script>

</head>
<body>

<div>
    <form action="/petitionLetter/addLetter" method="post">
        <table width="100%" border="0" cellpadding="8" cellspacing="0">
            <tr>
                <td width="90" align="right">信访目的</td>
                <td>
                    <input type="text" name="purpose" value="${purpose }"/>
                </td>
            </tr>
            <tr>
                <td width="90" align="right">文章标题</td>
                <td>
                    <input type="text" name="title" value="${title }"/>
                </td>
            </tr>
            <tr>
                <c:if test="${submitTime != null}">
                    <c:if test="${isWillPublic == 0}">
                        <td><input type="radio" name="isWillPublic" value="0" id="INoWillPublic0" checked/>
                            <label for="INoWillPublic0">不愿意</label>
                        </td>
                        <td><input type="radio" name="isWillPublic" value="1" id="IYesWillPublic0"/>
                            <label for="IYesWillPublic0">愿意</label>
                        </td>
                    </c:if>
                    <c:if test="${isWillPublic == 1}">
                        <td><input type="radio" name="isWillPublic" value="0" id="INoWillPublic1"/>
                            <label for="INoWillPublic1">不愿意</label>
                        </td>
                        <td><input type="radio" name="isWillPublic" value="1" id="IYesWillPublic1" checked/>
                            <label for="IYesWillPublic1">愿意</label>
                        </td>
                    </c:if>
                </c:if>
                <c:if test="${submitTime == null}">
                    <td><input type="radio" name="isWillPublic" value="0" id="noWillPublic"/>
                        <label for="noWillPublic">不愿意</label>
                    </td>
                    <td><input type="radio" name="isWillPublic" value="1" id="yesWillPublic"/>
                        <label for="yesWillPublic">愿意</label>
                    </td>
                </c:if>
            </tr>
            <tr>
                <td width="90" align="right">信件类型</td>
                <td>
                    <select name="letterType">
                        <c:if test="${submitTime != null}">
                            <option selected="selected">${letterType }</option>
                        </c:if>
                        <option value="0">信件类型</option>
                        <option value="1">市长之窗</option>
                        <option value="2">投诉受理信箱</option>
                        <option value="4">人民建议征集</option>
                        <option value="8">市委领导信箱</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td valign="top" align="right">信件内容</td>
                <td>
                    <textarea name="content" id="content" style="width: 780px;height: 400px;">${content }</textarea>
                    <script>
                        var editor = UE.ui.Editor();
                        editor.render("content");
                    </script>
                </td>
            </tr>
            <tr>
                <td width="90" align="right">收件人</td>
                <td><input type="text" name="addressee" value="${addressee }"/></td>
            </tr>
            <tr>
                <c:if test="${time != null}">
                    <td><input type="text" value="${submitTime }"/></td>
                </c:if>
                <c:if test="${time == null}">
                    <td><input type="hidden" name="submitTime" value="<%=new Date()%>"/></td>
                </c:if>
            </tr>
            <tr>
                <td><input type="submit" value="提交" name="submit"/></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
