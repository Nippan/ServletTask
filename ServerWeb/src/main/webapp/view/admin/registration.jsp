<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <h2>Добаить пользователя</h2><br />

    <form method="POST" action="<c:url value='/registration'/>">
        Имя: <input type="text" name="name"/><br>
        Логин: <input type="text" name="login"/><br>
        Пароль: <input type="password" name="password"/><br>
        Роль:
        <select name="role">
            <option selected>user</option>
            <option>admin</option>
        </select>
        <input type="submit" value="Добавить"/>
    </form>
</body>
</html>
