<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <a href="<c:url value='/'/>">Home page</a><br>
    <a href="<c:url value='/logout' />">Logout</a>
    <h2>Admin Page!</h2>
    <p>Ваше Имя: <c:out value="${requestScope.admin.getName()}"/></p>
    <p>Ваш Логин: <c:out value="${requestScope.admin.getLogin()}"/></p>
    <p>Ваш Пароль: <c:out value="${requestScope.admin.getPassword()}"/></p>
    <hr />

    <a href="<c:url value='/registration' />">
        <button>Добавить пользователя</button>
    </a><br>

    <table border="1" align="center">
        <tr>
            <th>Имя</th>
            <th>Логин</th>
            <th>Пароль</th>
            <th>Роль</th>
            <th>Редактировать</th>
            <th>Удалить</th>
        </tr>
        <caption>Все пользователи</caption>
        <c:forEach var="user" items="${requestScope.allUsers}">
            <tr>
                <td>
                    <c:out value="${user.getName()}"/>
                </td>
                <td>
                    <c:out value="${user.getLogin()}"/>
                </td>
                <td>
                    <c:out value="${user.getPassword()}"/>
                </td>
                <td>
                    <c:out value="${user.getRole()}"/>
                </td>
                <td>
                    <form method="get" action="<c:url value='/update'/>">
                        <input type="text" hidden name="login" value="${user.getLogin()}" />
                        <input type="submit" value="Редактировать"/>
                    </form>
                </td>
                <td>
                    <form method="post" action="<c:url value='/delete'/>">
                        <input type="text" hidden name="login" value="${user.getLogin()}" />
                        <input type="submit" value="Удалить"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
