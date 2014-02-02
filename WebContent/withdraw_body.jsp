<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Withdraw</title>
</head>
<body>
<h1>WithDraw</h1>
	<form method="post" action="/JavaCA/cashwithdrawl">
		<p>${requestScope.error}</p>
		<table>
			<tr>
				<td>Account Type</td>
			
				<td><input type="hidden" name="type"
						value="${requestScope.type}" /> <input type="text" name="accType"
						value="${requestScope.type}" disabled="disabled" /> <a
						href="/JavaCA/validator?action=${param.action}">Choose Account</a>
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
			
				<td><input type="text" name="amount" value="" /></td>
			</tr>
				
			<tr>
				<td align="justify"><input type="submit" name="Withdraw"
					value="Withdraw"></td>
				<td align="justify"><input type="reset" name="cancel"
					value="Cancel"></td>
			</tr>

		</table>
	</form>


</body>
</html>