<%--
  Created by IntelliJ IDEA.
  User: Rr
  Date: 2019/7/7
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
     <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="js/jquery-3.4.1.min.js"></script>
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

        $(function () {
            $("button[name='giveup']").click(function () {
                location.href="bookClassSelect.do";
            })
        })
    </script>
</head>
<body>
<form action="bookClassUpdate.do?id=${bookClass.id}" method="post">
    <label>编号:<input type="text" name="Id" value="${bookClass.id}" disabled></label><br>
    <label>原种类:<input type="text" name="oldname" value="${bookClass.className}" disabled></label><br>
    <label>更改为:<input type="text" id="name" name="name"  ><span id="addspan" style="color: red;"></span> <br>
    <input type="submit" value="submit" id="btnsubmit" disabled>
    <button type="button" name="giveup">放弃修改</button>
</form>
</body>
</html>
