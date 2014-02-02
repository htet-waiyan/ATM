<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Transaction History</h1>
	<form action="searchtxn" method="post">
		<input type="text" name="accNo" /> <input type="submit" value="Search" />
	</form>
	<table border="1" cellpadding="5" cellspacing="0">
		<tr>
			<th>Transaction ID</th>
			<th>Transaction Date</th>
			<td>Account No</td>
			<th>Transaction Type</th>
			<th>To Account No</th>
			<th>Transaction Amount</th>
		</tr>
		<c:forEach var="txn" items="${requestScope.txnLst}">
			<tr>
				<td>${txn.txnID}</td>
				<td><f:formatDate value="${txn.txnTime}" type="both"
						dateStyle="short" timeStyle="short" /></td>
				<td>${txn.bankaccount.accountNumber}
				<td>${txn.txnType}</td>
				<td><c:choose>
						<c:when test="${txn.txnType eq 'Transfer'}">${txn.toAccount}</c:when>
						<c:otherwise>-</c:otherwise>
					</c:choose></td>
				<td><f:formatNumber value="${txn.txnAmount}" type="CURRENCY" /></td>
			</tr>
		</c:forEach>

	</table>
	</center>
</body>
</html>