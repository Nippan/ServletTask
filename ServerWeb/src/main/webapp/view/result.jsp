<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>result</title>
    </head>
    <body>
        <h2>Message: ${message}</h2><br>
        <a href="<c:url value='/'/>">Home page</a><br>
        <c:if test="${sessionScope.role == 'admin'}">
            <a href="<c:url value='/admin'/>">Admin page</a><br>
            <a href="<c:url value='/user'/>">User page</a>
        </c:if>
    </body>
</html>
