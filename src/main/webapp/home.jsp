<%@page import="java.sql.Date"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="dto.Task"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Todo Home Page</title>
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

#btn1 {
	background-color: lightgreen;
}

#btn2 {
	background-color: red;
}
</style>
</head>
<body>

	<%
	List<Task> list = (List<Task>) request.getAttribute("list");
	%>
	<div>
		<fieldset>
			<legend>Home Page here</legend>
			<h1>home page</h1>
			<table border="2px solid black">
				<tr>
					<th>Task Name</th>
					<th>Task Description</th>
					<th>Date Created</th>
					<th>Status</th>
					<th>Delete</th>
					<th>Edit</th>
				</tr>
				<%
				if (list != null) {
					DateTimeFormatter formatter =DateTimeFormatter.ofPattern("dd MMM YYYY hh:mm");
					for (Task task : list) {
				%>
				<tr>
					<th><%=task.getName()%></th>
					<th><%=task.getDescription()%></th>
					<th><%=task.getCreatedTime().format(formatter)%></th>
					<th>
						<% if(task.isStatus()){%>
						 Completed<%
						 }
					   else{%>
					<a href="change-status?id=<%=task.getId() %>"><button>Completed</button></a> 
					 <%
					}
						 %>
					</th>
					<th><a href="change-delete?id=<%=task.getId() %>"><button>Delete</button></th></a>
					<th><a href="Edit?id=<%=task.getId() %>"><button>Delete</button></th></a>
					
				</tr>
				<%
				}
				}
				%>
			</table>
		</fieldset>
		<br> <br> <a href="session-add-task"><button>Add
				Task</button></a> <br> <br> <a href="logout"><button>Logout</button></a>
	</div>
</body>
</html>