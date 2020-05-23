<%@ page import="com.memory.pojo.User" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>用户列表</title>
    <script src="https://cdn.bootcss.com/jquery/3.0.0/jquery.min.js"></script>
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
					<td><input type="button" value="封禁" id = <%= user.getUserId()%> onclick="func()"/></td>
					<td><input type="button" value="解封" id = <%= user.getUserId()%> onclick="disBan()"/></td>
				</tr>			
				<%
					}
				%>
			</tbody>
		</table>
	<script>
		function func(){
            var id = event.target.id;
            //alert(id);
            $.ajax({
                url: '/ban/banUser',//地址
                dataType:'json',
                data: {
                    userId:id,
					text:"text"
                },
                contentType: "application/json;charset = UTF-8",
                type: 'POST',//类型
                timeout: 1000,//超时
                //请求成功
                success: function (data) {
                    alert(data);
                    //alert(status);
                },
                //失败/超时
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("请求错误");
                }
            });
        }

        function disBan(){
            var id = event.target.id;
            //alert(id);
            $.ajax({
                url: '/ban/disBan',//地址
                dataType: 'json',//数据类型
                data: {
                    userId: id
                },
                contentType: "application/json; charset=utf-8",
                type: 'POST',//类型
                timeout: 1000,//超时
                //请求成功
                success: function (data) {
                    alert(data);
                    //alert(status);
                },
                //失败/超时
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("请求错误");
                }
            });
        }
	</script>
</body>
</html>