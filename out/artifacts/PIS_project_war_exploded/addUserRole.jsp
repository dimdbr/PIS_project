<%--
  Created by IntelliJ IDEA.
  User: dimon
  Date: 09.10.2021
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add User Roles</title>
</head>
<body>
Possible Roles for user ${userId}

<c:choose>
    <c:when test="${empty roles}">
        User have all roles
    </c:when>
    <c:otherwise>
        <form action="" method="post">
        <select name="role" id="">

            <c:forEach var="role" items="${roles}">
            <c:if test="${role.id!=selected}">
            <option value="${role.id}">${role.name}</option>
            </c:if>
            </c:forEach>
            <input type="submit" value="Add Role">
    </form>
        </select> </c:otherwise>

</c:choose>


</body>
</html>
