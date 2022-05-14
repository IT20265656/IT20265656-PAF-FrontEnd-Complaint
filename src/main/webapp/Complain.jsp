<%@page import="com.Complain"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<% Class.forName("com.mysql.cj.jdbc.Driver"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

	<title>ElectroGrid-Complaints</title>
	<link rel="stylesheet" href="View/css/bootstrap.css">
	<link rel="stylesheet" href="css/Complain.css">
	<script src="Components/jquery.min.js"></script>
	<script src="Components/Complain.js"></script>
	

</head>
<body>
	<form id = "Complaint"  method='post' action='Complain.jsp'>
		<div class="container" style="background-color:white">
			<h2>Customer Complaints</h2>		    
			 <div class="mb-3">
			  <label for="exampleFormControlInput1" class="form-label">User ID</label>
			  <input type="text" class="form-control form-control-lg" id="Userid" name="Userid" placeholder="">
			</div>
			<div class="mb-3">
			  <label for="exampleFormControlTextarea1" class="form-label">Complaints</label>
			  <textarea class="form-control" id="combox" name="combox" rows="3"></textarea>
			</div>
			<div class="mb-3">
			  <label for="exampleFormControlTextarea1" class="form-label">Date</label>
			  <input type="date" class="form-control" id="comDate" name="comDate" ></input>
			</div>
			
			<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>
			
			
		 	<input id="btnSave" name="btnSave" type="button" value="Send" class="button">
		 	
		 	
		 	<input type="hidden" id="hidcomIDSave" name="hidcomIDSave" value="">
		</div>

	</form>
	
		<%
		 //out.print(session.getAttribute("statusMsg")); 
		%>
		<br>
	<div id= "comGrid">
		<%
		Complain comObj = new Complain(); 
		out.print(comObj.readComplain()); 
		%>
	</div>
</body>
</html>