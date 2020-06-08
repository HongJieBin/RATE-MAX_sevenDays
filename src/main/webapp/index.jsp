<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>管理员登录</title>
		<style type="text/css">
			.divForm{
				position: absolute;
				width: 300px;
				height: 300px;
				border: 1px solid black;
				text-align: center;
				left: 50%;
				margin-top: 50px;
				margin-left: -150px;
			}
		</style>

	</head>
	<body>
		<div class="divForm">
			<form action="/seven_days/admin/logIn" method="post" font-size=10px >
				<span>
					<h1>管理员登录</h1>
				</span>
				<input type="text" id="adminName" name="adminName" placeholder="用户名">
				<br><br>
				<input type="password" id="adminPassword" name="adminPassword" placeholder="密码">
				<br><br>
				<button type="submit" id="submit">登录</button>
			</form>
		</div>
	</body>
</html>