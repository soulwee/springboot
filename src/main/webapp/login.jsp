<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--
    浏览器设置的是en_US还是en,这两个是不一样的。一个是英文(美国)一个是英文
--%>
<form action="/login" method="post">
    <fmt:message key="name"> </fmt:message>：<input type="text" name="name"><br/>
    <fmt:message key="password"> </fmt:message>：<input type="password" name="pass"><br/>
    <button type="submit"><fmt:message key="submit"> </fmt:message></button>
</form>


<a href="/login1?lan=zh_CN">中文</a>  |  <a href="/login1?lan=en_US">英文</a>
</body>
</html>
