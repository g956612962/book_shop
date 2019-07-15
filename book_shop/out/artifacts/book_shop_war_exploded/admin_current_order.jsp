<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/7/13
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
总价：${order.price}<br>
时间：${order.createTime}<br><br><br>

<c:forEach items="${orderDetailList}" var="od">
    书价${od.bookPrice}<br>
    书号${od.bookId}<br><br>
</c:forEach>
</body>
</html>
