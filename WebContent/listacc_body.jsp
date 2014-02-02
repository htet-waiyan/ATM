<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Accounts Info</h3>
	<table width="80%">
		<tr>
			<th>Acc Type</th>
			<th>Acc No</th>
			<th>Balance</th>
		</tr>
		<c:forEach var="acc" items="${sessionScope.accLst}">
			<tr width="10">
				<td>${acc.value.type}</td>
				<td><a href="txnhistory?accNo=${acc.key}" style="color:blue;">${acc.key}</a></td>
				<td><f:formatNumber value="${acc.value.balance}" type="CURRENCY"/></td>
			</tr>
		</c:forEach>
	</table>
	<p>
		To View The Transactions, Click On The Account Numbers.
	</p>
</body>
</html>