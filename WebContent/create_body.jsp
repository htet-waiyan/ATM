<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
	function chooseType(){
		
		if(document.f1.AccType.options[document.f1.AccType.selectedIndex].value=="Checking"){
			document.f1.overAmt.disabled=false;
			document.f1.minbal.disabled=true;
		}
		else if(document.f1.AccType.options[document.f1.AccType.selectedIndex].value="Saving"){
			document.f1.overAmt.disabled=true;
			document.f1.minbal.disabled=false;
		}
	}
</script>
</head>
<body>
	<center>
		<h1>Create Account</h1>
		<b style="color:red;">${requestScope.msg}</b>
		<form action="bankacc" method="post" name="f1">
		<input type="hidden" name="action" value="create">
		<table>
			<tr>
				<td>User ID</td>
				<td><input type="text" name="userID" pattern="[a-zA-Z]{3,}" readonly="readonly" required value="${sessionScope.cust.userId}"/></td>
			</tr>
			<tr>
				<td>Acc No</td>
				<td><input type="text" name="accNo" required value="${sessionScope.accNo}" readonly="readonly"/></td>
			</tr>
			<tr>
				<td>PIN Number</td>
				<td align="center"><input name="pinNo" required pattern="[0-9]{6,6}" type="text" /></td>
			</tr>
			<tr>
				<td>Account Type</td>
				<td align="center">
					<select name="AccType" onchange="chooseType();">
						<option value="Checking">Checking Account</option>
						<option value="Saving">Savings Account</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>Minimum Balance</td>
				<td align="center"><input name="minbal" pattern="[0-9]{1,}" required type="text" disabled="disabled" /></td>
			</tr>
			<tr>
				<td>Creation Date</td>
				<td align="center">
				<input type="text" id="dojdatepicker" required="required"
					name="doj">
				</td>
			</tr>
			<tr>
				<td>Overdraft Amount</td>
				<td align="center"><input name="overAmt" type="text" required="required" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Create" /></td>
			</tr>
		</table>
		</form>
	</center>

</body>
</html>