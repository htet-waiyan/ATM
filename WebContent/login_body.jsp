<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Login</h3>
	<b>${requestScope.msg}</b>
	<form action="login" method="post">
		<b style="font:red;">${requestScope.error}</b>
		<table>
			<tr>
				<td>User ID</td>
				<td>
					<input type="text" name="txtID" required/>
				</td>
			</tr>
			<tr>
				<td>Password</td>
				<td>
					<input type="password" name="txtPasswd" required="required">
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" value="Login">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>