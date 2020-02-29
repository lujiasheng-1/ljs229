<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/18
  Time: 9:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/statics/js/jquery-1.8.3.js"></script>
    <script type="text/javascript">
        $(function(){
            $.ajax({
                url:'/show',
                type:'POST',
                success:function(result){
                    alert(result);
                }
            })
        });
    </script>
</head>
<body>
<div></div>
</body>
</html>
