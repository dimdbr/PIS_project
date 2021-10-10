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
Creating request for user ${userId}
<form action="" method="post">
    Fix Price :<input type="number" name="fixPrice"/>
    <br></br>
    Description : <input type="text" name="description"/>
    <br>
    <input type="submit" value="Create Request">

</form>
</body>
</html>
