<%--
  Created by IntelliJ IDEA.
  User: Rr
  Date: 2019/7/13
  Time: 9:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <title>Title</title>
</head>
<meta charset="UTF-8">
<title>添加图书</title>
<link rel="stylesheet" href="css/addBook.css">
</head>
<body>
<div id="title">
    <span>四川省  成都市</span>------<strong>华轩书海商城</strong>
    <div>
        欢迎你，admin
    </div>
</div>
<div id="box">
    <div id="box1">
        <div>
            <h3>返回首页</h3>
            <p><a href="">返回首页</a></p>
        </div>
        <div>
            <h3>我的购物车</h3>
            <p><a href="">购物车</a></p>
            <p><a href="">我的订单</a></p>
        </div>
        <div>
            <h3>用户管理</h3>
            <p><a href="">用户列表</a></p>
            <p><a href="">在线用户</a></p>
        </div>
        <div>
            <h3>图书管理</h3>
            <p><a href="selectBookList.do">查询图书</a></p>
            <p><a href="">添加图书</a></p>
            <p><a href="">修改图书</a></p>
            <p><a href="">删除图书</a></p>
        </div>
    </div>
    <div id="box2">
        <h1>添加图书</h1>
        <div>
            <form action="bookUpdate.do" method="post" enctype="multipart/form-data">
                选择类别:
                <select name="className">
                    <option value="儿童">儿童</option>
                    <option value="老人">老人</option>
                    <option value="年轻">年轻</option>
                </select><br>
                书籍名字：<input type="text" name="bookname" value="${book.name}"><br>
                书籍价格：<input type="text" name="money" value="${book.price}"><br>
                书籍数量：<input type="text" name="num" value="${book.inventory}"><br>
                书籍作者：<input type="text" name="author" value="${book.author}"><br>
                书籍出版：<input type="text" name="address" value="${book.press}"><br>
                书籍描述：<textarea name="information" type="text">value="${book.express}</textarea><br>
                书面封皮：<img src="${pageContext.request.contextPath}${book.imagePath}" width="200px" height="200px">
                书面封皮：<input type="file" name="nfile"><br>

                <input type="submit" value="添加">
            </form>
        </div>
    </div>
</div>
</body>
</html>