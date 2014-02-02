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
	<form action="editacc" method="post">
		<table>
			<input type="hidden" name="accNo" value="${param.acc}" />
			<input type="hidden" name="type" value="${param.type}" />
			<tr>
				<td>PIN</td>
				<td>
					<input type="text" name="pin" required />
				</td>
			</tr>
			<tr>
				<td>
					<c:choose>
						<c:when test="Checking">Overdraft Amount</c:when>
						<c:otherwise>Minimum Balance</c:otherwise>
					</c:choose>
				</td>
				<td>
					<input type="text" name="amt" required/>
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" value="Save" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>