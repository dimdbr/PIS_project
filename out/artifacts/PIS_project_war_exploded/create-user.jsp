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
<form action="" method="post">
    <input type="hidden" name="postAction" value="createUser"/>
    Firstname: <input name="firstName" />
    <br><br>
    Lastname: <input name="lastName" />
    <br><br>
    Login: <input name="login" />
    <br><br>
    Password: <input name="password" type="password"/>
    <br><br>
    <input type="submit" value="Submit" />

</form>
</body>
</html>
