<%@ page import="com.thirdgroup.po.Book" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 王君子
  Date: 2019/7/12
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <script src="js/jquery-1.12.4.js"></script>
    <script src="js/shopping.js"></script>
    <link rel="stylesheet" href="css/cartStyle.css">
</head>
<body>
<%
    List<Book> list2=(List<Book>)request.getAttribute("list");
%>
    <div class="content">
        <div class="logo">
            <img src="images/dd_logo.jpg"><span onclick="close_plan();">关闭</span>
        </div>
        <div class="cartList" id="cartList">
            <ul>
                <li>商品图片</li>
                <li>商品信息</li>
                <li>单价</li>
                <li>数量</li>
                <li>总价</li>
                <li>操作</li>
            </ul>
            <ul>
                <%
                    if (list2==null){
                        out.print("<h6>出现错误，请稍后再试或者联系管理员</h6>");
                    }else if (list2.size()==0){
                        out.print("<h6>抱歉，没有找到相关图书</h6>");
                    }else {
                        int n=0;
                        for (Book book:list2) {
                %>
                <li><img src='<%=book.getImagePath()%>'></li>
                <li><%=book.getExpress()%></li>
                <li>¥<input type="text" name="price" value="<%=book.getPrice()%>"></li>
                <li><input type="button" name="minus" value="-" onclick="minus(0);">
                    <input type="text" name="amount" value="1"><input type="button" name="plus" value="+" onclick="plus(0);"></li>
                <li id="price0">¥<%=book.getPrice()%></li>
                <li><p  onclick="collection();">移入收藏</p><p onclick="del(this);">删除</p></li>
                <%
                        n++;
                        }
                    }
                %>
            </ul>
            <ul>
                <li><img src="image/02.jpg"></li>
                <li>灰霾来了怎么办</li>
                <li>¥<input type="text" name="price" value="24.00"></li>
                <li><input type="button" name="minus" value="-" onclick="minus(1);"><input type="text" name="amount" value="1"><input type="button" name="plus" value="+" onclick="plus(1);"></li>
                <li id="price1">¥24.00</li>
                <li><p  onclick="collection();">移入收藏</p><p onclick="del(this);">删除</p></li>
            </ul>
            <ol>
                <li id="totalPrice">商品总计：<span></span></li>
                <li><span>结 算</span></li>
            </ol>
        </div>
    </div>
    <script type="text/javascript" src="js/shopping.js"></script>
</body>
</html>
