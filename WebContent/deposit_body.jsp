<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Deposit</title>
</head>
<body>
	<h1>Deposit</h1>
	<form method="post" action="cashdeposit">
		<fieldset>
			<legend>Deposit</legend>
			<table>
				<tr>
					<td>Account Type</td>

					<!--<td><select name="accountType">
						<option  value="savingAccount">Saving Account</option>
						<option  value="checkingAccount">Checking Account</option>

				</select></td>-->

					<td><input type="hidden" name="type"
						value="${requestScope.type}" /> <input type="text" name="accType"
						value="${requestScope.type}" disabled="disabled" /> <a
						href="validator?action=${param.action}">Choose Account</a>
					</td>
				</tr>

				<tr>
					<td>Account No</td>

					<td><input type="hidden" name="fromAcc"
						value="${requestScope.fromAcc}" /> <input type="text"
						name="txtAccNo" value="${requestScope.fromAcc}"
						disabled="disabled" /></td>
				</tr>
				<tr>
					<td>Amount</td>

					<td><input type="number" step="any" name="amount" value="" required="required" /></td>
				</tr>

				<tr>
					<td align="justify"><input type="submit" name="deposit"
						value="Deposit"></td>
					<td align="justify"><input type="reset" name="cancel"
						value="Cancel"></td>
				</tr>


			</table>
		</fieldset>
	</form>


</body>
</html>