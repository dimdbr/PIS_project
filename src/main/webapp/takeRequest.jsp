<%--
  Created by IntelliJ IDEA.
  User: dimon
  Date: 10.10.2021
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Take Request as a Worker</title>
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
<c:choose>
    <c:when test="${isMaster==false}">You dont have have Master Role</c:when>
    <c:otherwise>
        <form action="" method="post">
            <input type="hidden" name="postAction" value="takeRequest">
            <select name="possibleRequest" id="">
                <c:forEach var="request" items="${requests}">
                    <option value="${request.id}">Price=${request.fixPrice}</option>
                </c:forEach>
                <input type="submit" value="Select this request">
            </select>
        </form>
    </c:otherwise>
</c:choose>
</body>

</html>
