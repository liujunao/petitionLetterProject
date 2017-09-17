<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/9/8
  Time: 9:11
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
    <script>
        var xmlhttp;
        function hadoop() {
            //1. 创建 XMLHttpRequest 对象
            if (window.XMLHttpRequest) {
                xmlhttp = new XMLHttpRequest();
                if (xmlhttp.overrideMimeType) {
                    xmlhttp.overrideMimeType("text/html");
                }
            } else if (window.ActiveXObject) {
                var activeXName = ["MSXML2.XMLHTTP", "Miscrosoft.XMLHTTP"];
                for (var i = 0; i < activeXName.length; i++) {
                    xmlhttp = new ActiveXObject(activeXName[i]);
                }
            }
            if (xmlhttp == undefined || xmlhttp == null) {
                alert("当前浏览器不支持创建 XMLHttpRequest对象！");
                return false;
            }
            //2. 注册回调函数
            xmlhttp.onreadystatechange = callback;
            var username = document.getElementById("username").value;
            username = encodeURI(encodeURI(username));
            //设置和服务器交互的参数
            xmlhttp.open("POST", "${pageContext.request.contextPath}/", true);
            //设置向服务器端发送的数据，启动和服务器端的交互
            xmlhttp.send();
        }

        function callback() {
            //5.判断和服务器端的交互是否完成，还要判断服务器端是否正确返回了数据
            if (xmlhttp.readyState == 4) {
                if (xmlhttp.status == 200) {
                    //纯文本数据的接受方法
                    var message = xmlhttp.responseText;

                    //向 div 标签中填充文本内容的方法
                    var div = document.getElementById("m_username");
                    if (message == "") {
                        document.getElementById("username").focus();
                        document.getElementById("username").select();
                    }
                    div.innerHTML = message;
                }
            }
        }

        function CheckUserName() {
            var username = document.getElementById("username").value;
            if (CheckString("username") == true) {
                document.getElementById("m_username").innerHTML = "<font class='orange'>用户名不能包含空格</font>";
                return;
            }
            if (username == "") {
                document.getElementById("m_username").innerHTML = "<font class='orange'>用户名不能为空</font>";
                return;
            }
            if (username.length < 4 || username.length > 18) {
                document.getElementById("m_username").innerHTML = "<font class='orange'>必须是4-18个字符</font>";
                return;
            }
            document.getElementById("username").value = username;
            document.getElementById("m_username").innerHTML = "<font class='orange'>用户名填写正确</font>";
            return true;
        }

        function CheckString(string) {
            if (string.indexOf(" ") > -1) {
                return true;
            } else {
                return false;
            }
        }

        function CheckPassword() {
            var password = document.getElementById("password").value;
            if (password.length < 6) {
                document.getElementById("password").value = "";
                document.getElementById("m_password").innerHTML = "<font class='orange'>密码至少6位</font>";
                return;
            }
            var regular = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9a-zA-Z]{6,18}$/;
            if (!password.match(regular)) {
                document.getElementById("m_password").innerHTML = "<font class='orange'>密码只能且必须包含字母和数字</font>";
                return;
            }
            document.getElementById("m_password").innerHTML = "<font class='orange'>密码填写正确</font>";
            return true;
        }

        function CheckPassword2() {
            var password2 = document.getElementById("password2").value;
            if (password2 != document.getElementById("password").value) {
                document.getElementById("password2").value = "";
                document.getElementById("m_password2").innerHTML = "<font class='orange'>前后两次密码不一致</font>";
                return;
            }
            document.getElementById("m_password2").innerHTML = "<font class='orange'>两次密码填写一致</font>";
            return true;
        }

        function CheckName() {
            var name = document.getElementById("textName").value.trim();

            if (name == "") {
                document.getElementById("m_textName").innerHTML = "<font class='orange'>姓名不能为空</font>";
                return;
            }
            var regularCN = /^[\u4E00-\u9FA5]+$/;
            var regularEN = /^[a-zA-Z]+$/;
            if (name.match(regularCN)) {
                if (CheckString(name) == true) {
                    document.getElementById("m_textName").innerHTML = "<font class='orange'>姓名不能包含空格</font>";
                    return;
                } else {
                    document.getElementById("textName").value = name;
                    document.getElementById("m_textName").innerHTML = "<font class='orange'>姓名填写正确</font>";
                    return true;
                }
            } else if (name.match(regularEN)) {
                document.getElementById("textName").value = name;
                document.getElementById("m_textName").innerHTML = "<font class='orange'>姓名填写正确</font>";
                return true;
            }
        }

        function CheckIDNumber() {
            var id = document.getElementById("IDNumber").value;

            if (id == "" || id.length != 18) {
                document.getElementById("m_IDNumber").innerHTML = "<font class='orange'>身份证填写有误</font>";
                return;
            }
            var yyyy = id.slice(6, 10), mm = id.slice(10, 12), dd = id.slice(12, 14);
            var format = new Date(yyyy, mm, dd), now = new Date();
            var year = format.getFullYear(), month = format.getMonth(), day = format.getDate();
            if (year != yyyy || month != mm || day != dd|| format > now || year < 1900) {
                document.getElementById("m_IDNumber").innerHTML = "<font class='orange'>身份证出生日期填写有误</font>";
                return;
            }

            document.getElementById("m_IDNumber").innerHTML = "<font class='orange'>身份证填写正确</font>";
            return true;
        }

        function CheckMobile() {
            var mobile = document.getElementById("mobile").value;
            if (mobile == "" || mobile.length < 7 || mobile.length > 15) {
                document.getElementById("mobile").value = "";
                document.getElementById("m_mobile").innerHTML = "<font class='orange'>手机号码填写有误</font>";
                return;
            }
            document.getElementById("mobile").value = mobile;
            document.getElementById("m_mobile").innerHTML = "<font class='orange'>填写正确</font>";
            return true;
        }

        function CheckEmail() {
            var email = document.getElementById("email").value;
            if (email == "") {
                document.getElementById("m_email").innerHTML = "<font class='orange'>电子邮箱不能为空</font>";
                return;
            }
            var regular = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
            if (!email.match(regular)) {
                document.getElementById("email").value = "";
                document.getElementById("m_email").innerHTML = "<font class='orange'>邮箱格式有误</font>";
                return;
            }
            document.getElementById("email").value = email;
            document.getElementById("m_email").innerHTML = "<font class='orange'>邮箱填写正确</font>";
            return true;
        }

        function CheckAddress() {
            var address = document.getElementById("address").value;
            document.getElementById("address").value = address;
            document.getElementById("m_address").innerHTML = "<font class='orange'>地址填写正确</font>";
            return true;
        }

        function CheckForm() {
            if (!CheckUserName) {
                document.getElementById("username").value = "";
                alert("用户名填写有误！");
            }
            if (!CheckPassword()) {
                document.getElementById("password").value = "";
                alert("密码填写有误！");
                return;
            }
            if (!CheckPassword2()) {
                document.getElementById("password2").value = "";
                alert("密码确认填写有误！");
                return;
            }
            if (!CheckName()) {
                document.getElementById("textName").value = "";
                alert("姓名填写有误！");
                return;
            }
            if (!CheckEmail()) {
                document.getElementById("email").value = "";
                alert("邮箱填写有误！");
                return;
            }
            if (!CheckAddress()) {
                document.getElementById("address").value = "";
                alert("地址填写有误！");
                return;
            }
            if (!CheckIDNumber()) {
                document.getElementById("IDNumber").value = "";
                alert("身份证填写有误！");
                return;
            }
            if (!CheckMobile()) {
                document.getElementById("mobile").value = "";
                alert("电话号码填写有误！");
                return;
            }
            return true;
        }
    </script>
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

                    <form action="${pageContext.request.contextPath}/adminUserInfo/register" method="post">

                        <div class="page-header page-header-2 news-title no-margin">
                            <h2 class="margin-top10"><span>新用户注册</span></h2>
                        </div>
                        <div class="row-fluid margin-bottom15">
                            <div class="span3 text-right">
                                <label><span class="text-danger">*</span>用户名</label>
                            </div>
                            <div class="span6"><input name="userName" id="username" type="text" placeholder="请输入用户名，必填项"
                                                      maxlength="18" onblur="CheckUserName()"/></div>
                            <div class="span3"><em class="orange" id="m_username"><font class="orange">(4-18个字符)</font>
                            </em></div>
                        </div>
                        <div class="row-fluid margin-bottom15">
                            <div class="span3 text-right">
                                <label><span class="text-danger">*</span>密码 </label>
                            </div>
                            <div class="span6"><input name="password" id="password" placeholder="请输入密码，必填项"
                                                      type="password" onblur="CheckPassword()"/>
                                <em class="orange" id="m_password"></em>
                            </div>
                        </div>
                        <div class="row-fluid margin-bottom15">
                            <div class="span3 text-right"><label><span class="text-danger">*</span>确认密码 </label></div>
                            <div class="span6"><input name="password2" id="password2" type="password"
                                                      placeholder="请确认密码，必填项" onblur="CheckPassword2()"/>
                                <em class="orange" id="m_password2"></em>
                            </div>
                        </div>
                        <div class="row-fluid margin-bottom15">
                            <div class="span3 text-right"><label><span class="text-danger">*</span>姓名 </label></div>
                            <div class="span6"><input name="name" id="textName" type="text" placeholder="请输入姓名，必填项"
                                                      onblur="CheckName()"/>
                                <em class="orange" id="m_textName"></em>
                            </div>
                        </div>
                        <div class="row-fluid margin-bottom15">
                            <div class="span3 text-right"><label><span class="text-danger">*</span>身份证号 </label></div>
                            <div class="span6"><input name="identityCard" id="IDNumber" type="text"
                                                      placeholder="请输入身份证号，必填项" onblur="CheckIDNumber()"/>
                                <em class="orange" id="m_IDNumber"></em>
                            </div>
                        </div>
                        <div class="row-fluid margin-bottom15">
                            <div class="span3 text-right"><label><span class="text-danger">*</span>联系电话 </label></div>
                            <div class="span6"><input name="mobileNumber" id="mobile" type="text" placeholder="请输入联系电话"
                                                      onblur="CheckMobile()"/>
                                <em class="orange" id="m_mobile"></em>
                            </div>
                        </div>
                        <div class="row-fluid margin-bottom15">
                            <div class="span3 text-right"><label><span class="text-danger">*</span>性别 </label></div>
                            <div class="span6">
                                <table style="width: 300px;" border="0">
                                    <tbody>
                                    <tr>
                                        <td><input name="gender" type="radio" value="1" id="femail"/>
                                            <label for="femail">男</label>
                                        </td>
                                        <td><input name="gender" type="radio" value="0" id="mail"/>
                                            <label for="mail">女</label>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="row-fluid margin-bottom15">
                            <div class="span3 text-right"><label><span class="text-danger">*</span>邮箱地址 </label></div>
                            <div class="span6"><input name="email" id="email" type="text" placeholder="请输入邮箱地址，必填项"
                                                      onblur="CheckEmail()"/>
                                <em class="orange" id="m_email"></em>
                            </div>
                        </div>
                        <div class="row-fluid margin-bottom15">
                            <div class="span3 text-right">
                                <label><span class="text-danger">*</span>联系地址</label>
                            </div>
                            <div class="span6"><input name="address" type="text" id="address" placeholder="请输入住所地址"
                                                      maxlength="40" onblur="CheckAddress()"/></div>
                            <div class="span3"><em class="orange" id="m_address"><font class="orange">(40个字符以内)</font>
                            </em></div>
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
                                <input type="submit" onclick="CheckForm();" value="提交"/>
                                <input type="submit" value="取消" src="/views/login.jsp"/>
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
