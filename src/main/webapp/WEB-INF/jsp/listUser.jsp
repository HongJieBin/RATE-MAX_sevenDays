<%@ page import="com.memory.pojo.User" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户列表</title>
</head>
<body>
	<h1 >用户管理</h1>
	<br><br>
		<table id = "tab">
			<thead>
				<tr class="success">
					<th>ID</th>
					<th>用户名称</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>			
				<%	
				List<User> users = (List)request.getAttribute("users");
					for (User user : users) {
				%>
				<tr>
					<td><%= user.getUserId()%></td>
					<td><%= user.getNickname()%></td>
					<td><input type="button" value="封禁" /></td>
				</tr>			
				<%
					}
				%>
			</tbody>
		</table>
</body>
</html>