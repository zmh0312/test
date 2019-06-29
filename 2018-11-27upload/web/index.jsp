<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2018/11/27
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
<form action="/UploadServlet" method="post" enctype="multipart/form-data">
  <input type="text"name="username"><br>
  <input type="text"name="userpwd"><br>
  <input type="file"name="textname"><br>
  <input type="submit" value="上传">
</form>
  </body>
</html>
