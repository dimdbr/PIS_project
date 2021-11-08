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
<a href="createUser">Create New User</a>
<br>
<a href="getAllUsers">Get All Users</a>
<br>
<a href="getAllUsersRoles">Get All Users Roles</a>
<br>
<a href="getAllRequests">Get All Requests</a>
<br>
Possible Roles for user ${userId}

<c:choose>
    <c:when test="${empty roles}">
        User have all roles
    </c:when>
    <c:otherwise>
        <form action="" method="post">
            <input type="hidden" name="postAction" value="addUserRole"/>
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
