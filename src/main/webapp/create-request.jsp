<%--
  Created by IntelliJ IDEA.
  User: dimon
  Date: 10.10.2021
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Create Request</title>
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
Creating request for user ${userId}
<form action="" method="post">
    <input type="hidden" name="postAction" value="createRequest"/>
    Fix Price :<input type="number"  required name="fixPrice"/>
    <br></br>
    Description : <input type="text" required name="description"/>
    <br>
    <input type="submit" value="Create Request">

</form>
</body>
</html>
