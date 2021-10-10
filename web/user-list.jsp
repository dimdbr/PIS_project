<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All users</title>

</head>
<body>
<a href="createUser">Create New User</a>
<table width="100%">

    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Second Name</th>
        <th>Login</th>
        <th>Password</th>
    </tr>

    <c:forEach var="user" items="${listUser}">
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.first_name}"/></td>
            <td><c:out value="${user.last_name}"/></td>
            <td><c:out value="${user.login}"/></td>
            <td><c:out value="${user.password}"/></td>
            <td><a href="updatePassword?id=<c:out value = '${user.id}'/>">Update Password</a></td>
            <td><a href="deleteUser?id=<c:out value ='${user.id}'/>">Delete User</a></td>
            <td><a href="addUserRole?id=<c:out value ='${user.id}'/>">Add User Roles</a></td>
            <td><a href="createRequest?id=<c:out value = '${user.id}'/>">Create Request</a></td>
        </tr>
    </c:forEach>
</table>


</body>
</html>
