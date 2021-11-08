<%--
  Created by IntelliJ IDEA.
  User: dimon
  Date: 09.10.2021
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
Old password = <c:out value="${oldPassword}"/>
<form action="" method="post">
    <input type="hidden" name="postAction" value="updateUserPassword"/>
    Password : <input type="password" name="password">
    <br><br>
    <input type="submit" value="Update and Save">
</form>
</body>
</html>
