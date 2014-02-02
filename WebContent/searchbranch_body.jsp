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
	<form action="editbranch" method="get">
		<input type="hidden" name="action" value="search"/>
		<input type="text" name="search"/>
		<input type="submit" name="search" value="Search"/>
	</form>
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
			<th>BBID</th>
			<th>Location</th>
			<th>Branch Name</th>
			<th>Description</th>
			<th>&nbsp;</th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach var="bb" items="${sessionScope.bbLst}">
			<tr>
				<td>${bb.BBID}</td>
				<td>${bb.location}</td>
				<td>${bb.branchName}</td>
				<td>${bb.description}</td>
				<td>
					<a href="<c:url value='editbranch?action=edit&id=${bb.BBID}'/>">Edit</a>
				</td>
				<td>
					<a href="<c:url value='editbranch?action=edit&id=${bb.BBID}'/>">Delete</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>