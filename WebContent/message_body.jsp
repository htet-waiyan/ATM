<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>${requestScope.txnMsg}</h3>
	<table>
		<tr>
			<td>${requestScope.txn.bankaccount.accountNumber}-${requestScope.txn.bankaccount.type}</td>
		</tr>
		<tr>
			<td>${requestScope.txn.txnType}</td>
		</tr>
		<tr>
			<td><f:formatNumber value="${requestScope.txn.txnAmount}" type="CURRENCY"/></td>
		</tr>
		<tr>
			<td>${requestScope.txn.txnTime}</td>
		</tr>
	</table>
</body>
</html>