<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<form action="${pageContext.request.contextPath}/finish" method="post">
    用户名：<input type="text" name="userName"/> <br><br>
    年龄：<input type="text" name="age"/> <br><br>
    <input type="submit" value="提交"/>
</form>
</body>
</html>
