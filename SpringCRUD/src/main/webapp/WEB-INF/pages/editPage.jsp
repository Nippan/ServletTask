<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:if test="${empty user.login}">
        <title>Add</title>
    </c:if>
    <c:if test="${!empty user.login}">
        <title>Edit</title>
    </c:if>
</head>
<body>
<c:if test="${empty user.login}">
    <c:url value="/add" var="var"/>
</c:if>
<c:if test="${!empty user.login}">
    <c:url value="/edit" var="var"/>
</c:if>
<form action="${var}" method="POST">
    <c:if test="${!empty user.login}">
        <input type="hidden" name="id" value="${user.id}">
    </c:if>
    <label for="login">login</label>
    <input type="text" name="login" id="login"><br>
    <label for="name">name</label>
    <input type="text" name="name" id="name"><br>
    <label for="password">password</label>
    <input type="password" name="password" id="password"><br>
    <c:if test="${empty user.login}">
        <input type="submit" value="Add new user">
    </c:if>
    <c:if test="${!empty user.login}">
        <input type="submit" value="Edit user">
    </c:if>
</form>
</body>
</html>