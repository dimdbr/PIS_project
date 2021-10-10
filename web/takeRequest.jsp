<%--
  Created by IntelliJ IDEA.
  User: dimon
  Date: 10.10.2021
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>s
<html>
<head>
    <title>Take Request as a Worker</title>
</head>
<body>
<c:choose>
    <c:when test="${isMaster==false}">You dont have have Master Role</c:when>
    <c:otherwise>
        <form action="" method="post">
            <select name="possibleRequests" id="">
                <c:forEach var="request" items="requests">
                    <option value="${request.id}">Price=${request.fixPrice}</option>
                </c:forEach>
                <input type="submit" value="Select this request">
            </select>
        </form>
    </c:otherwise>
</c:choose>
</body>

</html>
