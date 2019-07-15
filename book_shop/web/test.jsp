<%--
  Created by IntelliJ IDEA.
  User: Rr
  Date: 2019/7/13
  Time: 8:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style type="text/css">
        ul {
            width: 280px;
            height: 300px;
            display: inline-block;
            border: 1px solid black;
            list-style: none;
            text-align: center;
        }

        li {

        }
    </style>
    <title>Title</title>
</head>
<body>
<a href="clearCart.do">清空购物车</a>
<a href="checkOutCart.do">结算</a>

<form method="post" action="removeBookFromCart.do">
    <input type="checkbox" name="bookId" value="2">
    <input type="checkbox" name="bookId" value="3">
    <input type="submit" value="submit">
</form>


<form method="post" action="checkOutCart.do">
    <input type="checkbox" name="bookId" value="2">
    <input type="checkbox" name="bookId" value="3">
    <input type="submit" value="submit">
</form>


<c:forEach items="${bookList}" var="book" varStatus="st">



    <c:choose>
        <c:when test="${st.index % 4 != 0 }">
            <ul>
                <li><img src="${pageContext.request.contextPath}${book.imagePath}" width="200px" height="200px"></li>
                <li>${book.name}</li>
                <li>${book.price}</li>
                <li><a href="bookInfo.do?id=${book.id}">更新</a> <a href="bookDelete.do?id=${book.id}">删除</a></li>
                <li><a href="addBookToCart.do?bookId=${book.id}">加购物车</a>
                    <a href="bookDelete.do?id=${book.id}">删除</a></li>
            </ul>
        </c:when>
        <c:otherwise>
            <br>
            <ul>
                <li><img src="${pageContext.request.contextPath}${book.imagePath}" width="200px" height="200px"></li>
                <li>${book.name}</li>
                <li>${book.price}</li>
                <li><a href="bookInfo.do?id=${book.id}">更新</a> <a href="bookDelete.do?id=${book.id}">删除</a></li>
            </ul>
        </c:otherwise>
    </c:choose>

</c:forEach>sad

</body>
</html>
