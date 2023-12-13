<!DOCTYPE html>
<%@page import="dto.Task"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<style>
div {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
}

form {
	background-color: cyan;
}

body {
	background-color: salmon;
}
</style>
<body>
	<div>
	<%Task task=(Task)request.getAttribute("task"); %>
		<form  action="update-task" method="post">
			<fieldset>
				<legend>Edit  here</legend>
				<input type="hidden" name="id" value="<%= task.getId()%>">
				<table>
					<tr>
						<th>Task Name: </th>
						<th><input type="text" name="tname"> value="<%= task.getName()%>"</th>
					</tr>
					<tr>
						<th>Task Description: </th>
						<th><input type="text" name="tdesc" value="<%= task.getDescription()%>"></th>
					</tr>
					<tr>
						<th><button type="submit"  id="btn1">Update</button></th>
						<th><button type="reset" id="btn2">Cancel</button></th>
					</tr>
		</table>
		</fieldset>
		</form>
		<br>
		<br>
		<a href="home"><button>back</button></a>
	</div>
</body>
</html>