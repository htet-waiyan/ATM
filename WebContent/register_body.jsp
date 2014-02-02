<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="resources/js/validate.js"></script>
</head>
<body>

	<link rel="stylesheet"
		href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />

	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>

	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

	<script>
		$(function() {

			$("#dobdatepicker").datepicker();

		});

		$(function() {

			$("#dojdatepicker").datepicker();

		});
	</script>
	<script type="text/javascript">
	</script>
	<title>create customer</title>
<body>
		<h1>Create Customer</h1>
		<b>${requestScope['error_msg']}</b>
		<form action="customer" method="post" onsubmit="return validate()" name="f1" >
		<input type="hidden" name="action" value="register">
		<table>
			<tr>
				<td>Name</td>
				<td align="center"><input name="cusname" required="required" pattern="[a-zA-Z ]{3,}" type="text" /></td>
			</tr>
			<tr>
				<td>UserID</td>
				<td align="center"><input name="userID" type="text" pattern="[a-zA-Z0-9]{3,}" required="required" /></td>
			</tr>
			<tr>
				<td>NRIC</td>
				<td align="center"><input name="NRIC" required="required" pattern="[a-zA-Z-0-9]{3,}"  type="text" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td align="center"><input name="passwd" required="required" type="password" /></td>
			</tr>
			<tr>
				<td>Nationality</td>
				<td align="center"><input name="nationality" pattern="[a-zA-Z ]{2,}" required="required" type="text" /></td>
			</tr>
			<tr>
				<td>Date of birth</td>
				<td align="center"><input type="text" id="dobdatepicker" required="required"
					name="dob"></td>
			</tr>
			<tr>
				<td>Gender</td>
				<td>
					<input name="gender" required="required" type="radio" value="M"/>Male
					<input name="gender" required="required" type="radio" value="F">Female</td>
				</td>
			</tr>
			<tr>
				<td>Date of Joining</td>
				<td align="center">
				<input type="text" id="dojdatepicker" required="required"
					name="doj">
				</td>
			</tr>
			<tr>
				<td>Address</td>
				<td align="center"><textarea rows="5"  cols="22" name="address"></textarea></td>
			</tr>
			<tr>
				<td align="right"><input type="button" value="Cancel" />
				<td><input type="button" value="Reset" /> <input type="submit"
					value="Continue" align="right"/></td>
			</tr>
		</table>
		</form>
</body>

</html>