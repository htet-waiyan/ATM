<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<font color="red">${requestScope.error}</font>
	<form action="transfer" method="post">
		<fieldset>
			<legend>Fund Transfer</legend>
			<table>
				<tr>
					<td>Account Type</td>
					<td>
						<input type="hidden" name="type" value="${requestScope.type}" />
						<input type="text" name="accType" value="${requestScope.type}" disabled="disabled" />
						<a href="validator?action=transfer">Choose Account</a>
					</td>
				</tr>
				<tr>
					<td>Acc No</td>
					<td>
						<input type="hidden" name="fromAcc" value="${requestScope.fromAcc}"/>
						<input type="text" name="txtAccNo" value="${requestScope.fromAcc}" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<td>To Acc No</td>
					<td>
						<input type="text" name="toAcc">
					</td>
				</tr>
				<tr>
					<td>Amount</td>
					<td>
						<input type="text" name="amt">
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<input type="submit" value="Transfer" />
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>