<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
    <a href="<c:url value='/'/>">Home page</a><br>
    <c:if test="${sessionScope.role != null}">
        <a href="<c:url value='/logout' />">Logout</a>
        <h2>User Page</h2>
        <p>Ваше Имя: <c:out value="${requestScope.user.getName()}"/></p>
        <p>Ваш Логин: <c:out value="${requestScope.user.getLogin()}"/></p>
        <p>Ваш Пароль: <c:out value="${requestScope.user.getPassword()}"/></p>
        <hr />
    </c:if>
</body>
</html>
