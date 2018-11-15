<%--
  Created by IntelliJ IDEA.
  User: 16320
  Date: 2018/11/13
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>导入excel到数据库</title>
</head>
<body>
<form action="ImportExcel" enctype="multipart/form-data" method="post">
    选择文件： <input type="file" name="excelfilename"><br>
    <input type="submit" value="数据入库"/>
</form>
</body>
</html>
