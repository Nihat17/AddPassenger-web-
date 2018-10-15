<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>World Adventures Airlines!</title>
</head>
<body>
	<div class="container">
	
       <div class = "title">Add a passenger</div>
       <%
       		if(request.getAttribute("errors") != null){
        %>
        	<fieldset>
        	<legend>Errors</legend>
        	<ul>
        		<%if(request.getAttribute("firstName_error") != null){ %>
        			<li class="error">First name error </li>
        		<%} %>
        		
        		<%if(request.getAttribute("lastName_error") != null){ %>
        			<li class="error">Last name error </li>
        		<%} %>
        		
        		<%if(request.getAttribute("date_format_error") != null){ %>
        			<li class="error">Date of birth is invalid </li>
        		<%} %>
        		
        	</ul>
        	</fieldset>
        <%
        	}
         %>
	<fieldset>
		<legend>Passenger Details</legend>
		<form action="AddPassenger" method="post">
			<div class="inputField">
				<label for="first-name" class="inputLabel">First Name:</label> <input
					name="first-name" type="text" value ="<%= request.getAttribute("firstName") %>"></input>
			</div>
			<div class="inputField">
				<label for="last-name" class="inputLabel">Last Name:</label> <input
					name="last-name" type="text" value = "<%= request.getAttribute("lastName") %>"></input>
			</div>
			<div class="inputField">
				<label for="dob" class="inputLabel">Date of birth:</label> <input
					name="dob" type="text" value = "<%= request.getAttribute("dob") %>"></input>
			</div>
			<div class="inputField">
				<label for="first-name" class="inputLabel">Gender:</label> <select
					name="gender">
					<option value="Male">Male</option>
					<option value="Female">Female</option>
				</select>
			</div>
	</div>
 	</fieldset>
	<div class="inputField" id="submitField">
		<input id="submitBtn" type="submit" value="Add new Passenger"></input>
	</div>
	</form>

</body>
</html>