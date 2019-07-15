<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2019/7/13
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${adminNotDeliverOrderList}" var="o">
    时间：${o.createTime}<br>
    总价：${o.price}<br><br>
    <c:forEach items="${adminNotDeliverOrderDetailList}" var="od">
        <c:if test="${o.id eq od.orderId}">
            书号${od.bookId}<br>
            价格${od.bookPrice}<br><br>
        </c:if>
    </c:forEach>
    <hr>
</c:forEach>

</body>
</html>
