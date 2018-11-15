<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2018/9/18
  Time: 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.css">
<link rel="stylesheet" href="bootstrapvalidator/css/bootstrapValidator.css">
<script src="jquery/jquery-2.2.4.min.js" type="text/javascript"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
<!-- 表单验证 -->
<script src="bootstrapvalidator/js/bootstrapValidator.js" type="text/javascript"></script>
<head>
    <base href="<%=basePath%>">
    <title>主页面</title>
    <script type="text/javascript">
        $(function(){
            $("#1").click(function(){
                window.location.href='/excel';
            });
            $("#3").click(function(){
                window.location.href='/ImportExcel.jsp';
            });
        })
    </script>
</head>
<body>
<div class="form-group">
    <div class="col-sm-offset-2 col-sm-2 col-xs-6">
        <button class="btn btn-success" type="submit" id="1">下载</button>
    </div>
    <div class="col-sm-offset-2 col-sm-2 col-xs-6">
        <button class="btn btn-primary" type="submit" id="3">导入excel</button>
    </div>
</div>

</body>
</html>
