<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Change Status</title>
</head>
<body>
Possible Statuses
<form action="" method="post">
    <select name="status" id="">
        <c:forEach var="status" items="${statuses}">
            <option value="${status.id}">${status.status}</option>
        </c:forEach>
    </select>
    <input type="submit"value="Change Status">
</form>

</body>
</html>
