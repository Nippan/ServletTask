<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: studi
  Date: 29.10.2019
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Authorization</h2>
<c:if test="${error}">Введены неверно логин или пароль.</c:if>
<form action="/login" method="POST">
    <input type="text" placeholder="login" name="login" required><br>
    <input type="password" placeholder="password" name="password" required><br>
    <input type="submit" value="Login"/>
</form>

</body>
</html>
