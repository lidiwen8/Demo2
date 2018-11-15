<%--
  Created by IntelliJ IDEA.
  User: 李弟文
  Date: 2018/9/26
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.css">
    <link rel="stylesheet" href="bootstrapvalidator/css/bootstrapValidator.css">
</head>
<title>显示界面</title>
<body>
<%
    request.setCharacterEncoding("utf-8");
    String username=request.getParameter("username");
    String t=request.getParameter("time");
%>
<div class="text-center">
    <div class="row">
        <div class="col-sm-offset-3 col-sm-6 text-center">
            <h3>servlet传递到-jsp的参数第一种</h3>
        </div>
    </div>
    <form class="form-horizontal col-sm-offset-3">
    <div class="form-group">
        <label for="username" class="col-sm-2 control-label">姓名：</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" value="<%=username%>" readonly="readonly">
        </div>
    </div>
    <div class="form-group">
        <label for="number" class="col-sm-2 control-label">数字：</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" value="<%=t%>" readonly="readonly">
        </div>
    </div>
        <%--<div class="form-group">--%>
            <%--<label for="password" class="col-sm-2 control-label">日期：</label>--%>
            <%--<div class="col-sm-4">--%>
                <%--<input type="text" class="form-control" value="<%=date%>" readonly="readonly">--%>
            <%--</div>--%>
        <%--</div>--%>
    </form>
        <div class="col-sm-offset-2 col-sm-2 col-xs-6">
            <button class="btn btn-info" style="margin-left: 460px" onclick="window.location.href='index2.jsp'">退出</button>
        </div>
</div>
</body>
</html>

