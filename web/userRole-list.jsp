<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Roles</title>

<body>
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
