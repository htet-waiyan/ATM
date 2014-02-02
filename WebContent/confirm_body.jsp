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
	<form action="validator" method="post">
		<input type="hidden" name="action" value="${param.action}">
		<table>
			<tr>
				<td>Account No</td>
				<td>
				<select name="accNo">
				<c:forEach var="acc" items="${sessionScope.accLst}">
					<option value="${acc.key}-${acc.value.type}">
						${acc.key}-${acc.value.type}
					</option>
				</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<td>PIN</td>
				<td><input type="password" name="txtPIN"/></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Enter"/></td>
			</tr>
		</table>
	</form>
</body>
</html>