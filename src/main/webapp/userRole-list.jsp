<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Roles</title>

<body>
<a href="createUser">Create New User</a>
<br>
<a href="getAllUsers">Get All Users</a>
<br>
<a href="getAllUsersRoles">Get All Users Roles</a>
<br>
<a href="getAllRequests">Get All Requests</a>
<br>
<table width="100%">
    <tr>
        <th>Id</th>
        <th>UserId</th>
        <th>RoleId</th>
    </tr>
    <c:forEach var="userRole" items="${listUserRoles}">
        <tr>
            <td><c:out value="${userRole.id}"/></td>
            <td><c:out value="${userRole.userId}"/></td>
            <td><c:out value="${userRole.roleId}"/></td>
            <td><a href="deleteUserRole?uid=<c:out value ='${userRole.userId}'/>&rid=
<c:out value="${userRole.roleId}"/>">Delete Users Role</a></td>

        </tr>
    </c:forEach>
</table>
</head>
</body>
</html>
