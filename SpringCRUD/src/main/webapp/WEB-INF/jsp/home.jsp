<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
    <style>
        td {
            margin-right: 15px;
        }
    </style>
</head>
<body>
<h2>Administrator Page</h2>

<form action="/logout" method="post">
    <input value="Logout" type="submit">
</form>
<table>
    <tr>
        <th>User name</th>
        <th>Login</th>
        <th>Password</th>
    </tr>
    <c:forEach items="${usersFromServer}" var="user">
        <tr>
            <td>${user.name}</td>
            <td>${user.login}</td>
            <td>${user.password}</td>
            <td>
                <form action="/admin/delete/${user.id}" method="POST">
                    <input type="submit" value="Delete"/>
                </form>
            <td>
            <td>
                <form action="/admin/edit/${user.id}" method="POST">
                    <input type="text" placeholder="name" name="name" required>
                    <input type="text" placeholder="login" name="login" required>
                    <input type="password" placeholder="password" name="password" required>
                    <input type="submit" value="Edit"/>
                </form>
            <td>
        </tr>
    </c:forEach>
</table>

<a href="/admin/save">Add user</a><br>

</body>
</html>
