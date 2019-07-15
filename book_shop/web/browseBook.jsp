<%@ page import="org.bookstore.entiy.Book" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 王君子
  Date: 2019/7/12
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>浏览图书界面</title>
    <script src="js/jquery-1.12.4.js"></script>
    <script src="js/addCart.js"></script>
</head>
<body>
<%
    List<Book> list1=(List<Book>)request.getAttribute("list");
%>
    <div id="header">
        <%@include file="html/header.html"%>
    </div>

    <div id="section" style="height: 500px">
        <%
            if (list1==null){
                out.print("<h6>出现错误，请稍后再试或者联系管理员</h6>");
            }else if (list1.size()==0){
                out.print("<h6>抱歉，没有找到相关图书</h6>");
            }else {
                int n=0;
                for (Book book:list1) {
        %>
        <li>
                <input type="hidden" name="opr" value="add">
                <a href='#'> <img src='<%=book.getImagePath()%>'> </a>
                <span>作者：<%=book.getAuthor()%></span>
                <span>￥：<%=book.getPrice()%></span>
                <span>
                    <input type="submit" id="Cart" name="cart" value="加入购物车" onclick="addCart()">
                </span>
        </li>
        <%
                    n++;
                    if (n%4==0){
                        out.print("<br/>");
                    }
                }
            }
        %>
    </div>
    <div id="bottom">
        <%@include file="html/bottom.html"%>
    </div>
</body>
</html>
