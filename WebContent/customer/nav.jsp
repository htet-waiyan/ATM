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
	<div>
	<c:choose>
		<c:when test="${sessionScope.isNew}">
			<a href="<c:url value='/JavaCA/login.jsp'/>">Login></a>
		</c:when>
		<c:otherwise>
			<a href="<c:url value='../logout'/>">Logout</a>
		</c:otherwise>
	</c:choose>
	</div>
	<div>
		<h3>Account</h3>
		<a href="<c:url value='../listAcc?action=listacc'/>">View Balance</a>
	</div>
	<div>
		<h3>Transaction</h3>
		<a href="<c:url value='../validator?action=transfer'/>">Fund Transfer</a>
		<a href="<c:url value='../validator?action=deposit'/>">Deposit</a>
		<a href="<c:url value='../validator?action=withdraw'/>">Withdraw</a>
	</div>
</body>
</html>