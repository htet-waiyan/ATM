<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Bank Branch Info</h3>
	<form action="bankbranch" method="post" name="f1">
		<fieldset style="width:40%; margin:0px 0px 15px 0px;">
		<legend>Basic Info</legend>
		<input type="hidden" name="action"/>
		<p>${requestScope.msg}</p>
		<table>
		<tr>
			<td>Location</td>
			<td>
				<input type="text" name="location" value="${requestScope.branch.location}"/>
			</td>
		</tr>
		<tr>
			<td>Branch Name</td>
			<td>
				<input type="text" name="name" value="${requestScope.branch.branchName}" />
			</td>
		</tr>
		<tr>
			<td>Description</td>
			<td>
				<textarea rows="5" cols="30" value="${requestScope.branch.description}"></textarea>
			</td>
		</tr>
		<tr>
			<td>
				
			</td>
			<td>
				<input type="button" value="Edit" onclick="formEdit('edit');"/>
				<input type="submit" value="Save"/>
			</td>
		</tr>
	</table>
	</fieldset>
	</form>
</body>
</html>