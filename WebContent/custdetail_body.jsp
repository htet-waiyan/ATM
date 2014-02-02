<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		function editForm(action) {
			console.log("Edit method");

			document.f1.txtName.disabled = false;
			document.f1.btnSave.disabled = false;
			document.f1.txtPassNRIC.disabled = false;
			document.f1.userID.disabled = false;
			document.f1.txtAddr.disabled = false;
			document.f1.btnCancel.disabled = false;
			document.f1.pwd.readonly=false;
			document.f1.action.value = action;
		}

		function cancelEdit() {
			console.log("Cancel method");

			document.f1.txtName.disabled = true;
			document.f1.btnSave.disabled = true;
			document.f1.txtPassNRIC.disabled = true;
			document.f1.txtID.disabled = true;
			document.f1.txtAddr.disabled = true;
		}
	</script>
	<div id="content">
		<div>
			<form action="custsearch" method="get">
				<input type="text" name="txtSearch" /> <input type="submit"
					value="Search" />
			</form>
			<fieldset style="width: 40%; height: auto;">
				<legend>Customer Info</legend>
				<form action="edit" name="f1" method="post">
					<p>${requestScope.error}</p>
					<input type="hidden" name="action" />
					<table border="0" cellspacing="0" cellpadding="5">
						<tr>
							<td><label>Name :</label> <input type="text"
								disabled="disabled" name="txtName" id="Name"
								value="${sessionScope.cust.name}"></td>
							<td><label>Passport/NRIC :</label> <input type="text"
								disabled="disabled" name="txtPassNRIC" id="PassNRIC"
								value="${sessionScope.cust.nric}"></td>
						</tr>
						<tr>
							<td><label>User ID :</label> <input type="text"
								disabled="disabled" name="userID" id="ID"
								value="${sessionScope.cust.userId}" /></td>
							<td><label>Gender :</label> <input type="text"
								disabled="disabled" name="txtGender" id="Gender"
								value="${sessionScope.cust.gender}" /></td>

						</tr>
						<tr>
							<td><label>DOB :</label> <input type="text" name="txtDOB"
								disabled="disabled" id="DOB"
								value="${sessionScope.cust.birthdate}" /></td>
							<td><label>Join Date :</label> <input type="text"
								name="txtDOJ" disabled="disabled" id="DOJ"
								value="${sessionScope.cust.joinDate}" /></td>

						</tr>
						<tr>
							<td valign="top"><label>Nationality :</label> <input
								type="text" name="txtNat" disabled="disabled" id="Nat"
								value="${sessionScope.cust.nationality}" /></td>
							<td><label>Password :</label> <input type="password"
								name="passwd" readonly="readonly" id="pwd"
								value="${sessionScope.cust.password}"></td>
						</tr>
						<tr>
							<td><label>Address :</label></br> <textarea rows="5" cols="25"
									disabled="disabled" id="Addr" name="txtAddr">${sessionScope.cust.address}</textarea>
							</td>

							<td><input type="button" value="Edit"
								onclick="editForm('edit');" name="btnEdit" id="edit" /> <input
								type="submit" value="Save" disabled="disabled" name="btnSave"
								id="save" /> <!-- <input type="button" value="Cancel" disabled="disabled" name="btnCancel" id="cancel" onclick="cancelEdit();"> -->
							</td>
						</tr>
					</table>
				</form>
			</fieldset>
		</div>
		<div style="float: left;">
			<fieldset style="width: 496px; height: auto;">
				<legend>Bank Account</legend>
				<table border="0" cellpadding="10" cellspacing="0" width="80%" >
					<tr width="30%" align="left">
						<th>Acc No</th>
						<th>Acc Type</th>
						<th colspan="2"></th>
					</tr>
					<c:forEach var="acc" items="${sessionScope.cust.accountList}">
						<tr align="left">
							<td>${acc.accountNumber}</td>
							<td>${acc.type}</td>
							<td><a
								href="<c:url value='editacc.jsp?acc=${acc.accountNumber}&type=${acc.type}' />">Edit&nbsp;&nbsp;</a>
								
							</td>
						</tr>
					</c:forEach>
					<tr align="left">
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
				<form action="bankacc" method="post">
					<input type="hidden" name="action" value="request" /> <input
						type="submit" value="Add New" />
				</form>
			</fieldset>
		</div>
	</div>
</body>
</html>