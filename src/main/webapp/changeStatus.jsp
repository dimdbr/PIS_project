<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Change Status</title>
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
Possible Statuses
<form action="" method="post">
    <input type="hidden" name="postAction" value="changeRequestStatus">
    <select name="status" id="">
        <c:forEach var="status" items="${statuses}">
            <option value="${status.id}">${status.status}</option>
        </c:forEach>
    </select>
    <input type="submit"value="Change Status">
</form>

</body>
</html>
