<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body style="align-content: center">
    <h2>Это главная страница</h2>
    <c:if test="${sessionScope.role != null}">
        <a href="<c:url value='/admin'/>">Admin page</a><br>
        <a href="<c:url value='/user'/>">User page</a>
    </c:if>

    <c:if test="${sessionScope.role == null}">
        <h2>Авторизация</h2>

        <form method="post" action="<c:url value='/login'/>">
            Логин:  <input type="text" name="login"/><br>
            Пароль: <input type="password" name="password"/><br>
            <input type="submit" value="Войти">
        </form>
    </c:if>
</body>
</html>
