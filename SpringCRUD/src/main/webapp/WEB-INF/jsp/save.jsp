<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add user</title>
</head>
<body>
<h2>Add User</h2>
<form action="/admin/save" method="POST">
    <input type="text" placeholder="name" name="name"><br>
    <input type="text" placeholder="login" name="login"><br>
    <input type="password" placeholder="password" name="password"><br>
    <select name="role">
        <option>USER</option>
        <option>ADMIN</option>
    </select>
    <input type="submit" value="Add">
</form>

</body>
</html>
