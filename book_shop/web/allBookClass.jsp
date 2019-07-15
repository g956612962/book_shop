<%--
  Created by IntelliJ IDEA.
  User: Rr
  Date: 2019/7/13
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="js/index.js"></script>
    <title>Title</title>
    <script>
        $(function(){
            $('#name').blur(function(){
                $.ajax({
                    type: "get",
                    url: "bookClassIfexit.do",
                    data: "bookClassName="+$("#name").val(),
                    dataType: "text",
                    success: function(data){
                        if(data=='true'){
                            $("#addspan").html("该主题名称已存在，请重新输入!")
                            $("#btnsubmit").attr("disabled",true)
                        }else if (data=='false') {
                            $("#addspan").html("该主题名称可用!")
                            $("#btnsubmit").removeAttr("disabled")
                        }else {
                            $("#addspan").html("")
                            $("#btnsubmit").attr("disabled",true)
                        }
                    }
                });
            });
        });
    </script>
</head>
<body>
<input  hidden type="text" value="${info}" id="info"><span>${infoDelete}</span>
<c:forEach  items="${bookClassList}"  var="bookclass"
            varStatus="status">
    <tr <c:if test="${status.index % 2 == 1 }">
        style="background-color:rgb(219,241,212);"</c:if>>
        <td>${bookclass.id}</td><td>${bookclass.className}</td><span ><a href="bookClassDelete.do?id=${bookclass.id}&bookClassName=${bookclass.className}">删除</a></span>
        <span ><a href="getBookClassinfo.do?bookClassName=${bookclass.className}">修改</a></span>
    </tr>
</c:forEach>
<form action="bookClassAdd.do" method="post">
    <input type="text" id="name" name="name"  >
    <input type="submit" value="submit" id="btnsubmit" disabled>
    <span id="addspan" style="color: red;"></span>
</form>
</body>
</html>
