<%--
  Created by IntelliJ IDEA.
  User: dimon
  Date: 10.10.2021
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>All requests</title>
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
<table>
    <tr>
        <th>Id</th>
        <th>Fix Price</th>
        <th>Status Id</th>
        <th>User Id</th>
        <th>Description</th>
    </tr>
    <c:forEach var="request" items="${listRequests}">
        <tr>
            <td><c:out value="${request.id}"/></td>
            <td><c:out value="${request.fixPrice}"/></td>
            <td><c:out value="${request.statusId}"/></td>
            <td><c:out value="${request.userId}"/></td>
            <td><c:out value="${request.description}"/></td>
            <td><a href="changeStatus?id=<c:out value="${request.id}"/>">Change Status</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
