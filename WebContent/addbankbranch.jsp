<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<form method="post" action="createBankBranch"></form>


	<table>
		<tr>
			<td>BranchId</td>
			<td><input name="bb_id" type="text" /></td>
		</tr>
		<tr>
			<td>Branch Name</td>
			<td><input name="bb_name" type="text" /></td>
		</tr>
		<tr>
			<td>Branch Location</td>

			<td><select name="bb_location">
					<option value="clementi">Clementi</option>
					<option value="cityhall">City Hall</option>
					<option value="yewtee">Yew Tee</option>
					<option value="chochukhang">Cho Chu Khang</option>
					<option value="bishan">Bishan</option>
					<option value="khatib">Khatib</option>

			</select></td>

		</tr>

		<tr>
			<td>Description</td>
			<td><textarea name="bb_desc" rows="6"></textarea></td>


		</tr>



		<tr>

			<td colspan="2" align="center"><input type="submit"
				value="Create" /></td>
		</tr>
	</table>



</body>
</html>