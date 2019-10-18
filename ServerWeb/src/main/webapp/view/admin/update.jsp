<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UpdateUser</title>
</head>
<body>
    <h2>Изменение пользователя</h2>
    <form method="post">
        <input type="text" hidden name="id" value="${requestScope.user.getId()}"/><br>
        Имя: <input type="text" name="name" value="<c:out value="${requestScope.user.getName()}"/>"/><br>
        Пароль: <input type="password" name="password" value="<c:out value="${requestScope.user.getPassword()}"/>"/><br>
        <input type="submit" value="Change user">
    </form>
</body>
</html>
