<%--
  Created by IntelliJ IDEA.
  User: dimon
  Date: 09.10.2021
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add User</title>
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
<form action="" method="post">
    <input type="hidden" name="postAction" value="createUser"/>
    Firstname: <input name="firstName" required/>
    <br><br>
    Lastname: <input name="lastName" required/>
    <br><br>
    Login: <input name="login" required/>
    <br><br>
    Password: <input name="password"  required type="password"/>
    <br><br>
    <input type="submit" value="Submit" />

</form>
</body>
</html>
