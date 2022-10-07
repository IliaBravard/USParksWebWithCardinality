<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Telling the JSP that we are going to use the tag library -->
<!DOCTYPE html>

<!-- Author: Ilia Garrett Bravard 
	 Date: 10/02/2022
	 Course: Java II Programming Course -->

<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>National Parks List</title>

<link rel="icon" href="image18.png" type="image/x-icon">

<script type="text/javascript"> // JavaScript for exiting the application
	function disableBack() { window.history.forward(); }
		setTimeout("disableBack()", 0);
		window.onunload = function () { null 
	};
</script>
</head>

<style>
body {
	background-image: url("image10.png");
	background-size: cover;
	height: 100%;
	background-repeat: no-repeat;
	font-family: "montserrat";
}

h1 {
	color: white;
	position: absolute;
	padding-left: 865px;
	font-size: 60px;
	margin-top: 5px;
}

form {
	color: black;
	position: absolute;
	top: 17%;
	left: 64%;
	border-radius: 25px;
	border: 3px solid white;
	width: 435px;
	height: 450px;
	padding: 10px;
	font-size: 20px;
	justify-content: center;
	background-image: url("image11.png");
	background-size: cover;
	background-repeat: no-repeat;
}

.box input[type="text"] {
	border: 0;
	text-align: center;
	vartical-align: center;
	border: 2px solid white;
	background: white;
	width: 200px;
    color: black;
	border-radius: 25px;
	transition: 0.25s;
    font-size: 20px;
}

.buttons {
	margin-top: 20px;
	display: grid;
	grid-template-columns: 1fr 1fr;
	grid-gap: 20px;
}

.btn1, .btn2 {
	text-transform: uppercase;
	height: 45px;
	border-radius: 25px;
	border: none;
	background-color: #6e5c40;
	border: 1px solid black;
	cursor: pointer;
	color: white;
}

.btn:hover, .btn1:hover, .btn2:hover {
	background-color: #abbfc0;
	transition: 0.35s ease;
	color: black;
}

.pk {
	border-radius: 5px;
	border: none
}

a {
	text-transform: uppercase;
	height: 35px;
	border-radius: 25px;
	border: none;
	background-color: #6e5c40;
	border: 1px solid black;
	cursor: pointer;
	color: white;
	text-align: center;
	text-decoration: none;
	font-size: 15px;
	margin-top: 7px;
	padding-top: 10px;
}
</style>

<body>
	<h1>View the Parks</h1>
	<form action="NavigateServlet" method="post" class="box">
		<table>
			<!-- The 'c' tag allows us to iterate through the list -->
			<!-- 'allParks' is the name of the list -->
			<!-- 'currentpark represents each captured park from the list -->
			<c:forEach items="${requestScope.allParks}" var="currentpark">
				<tr>
					<td><input type="button" value="${currentpark.id}" class="pk"></td>
					<td>${currentpark.name}</td>
					<td>${currentpark.state}</td>
					<td>${currentpark.area}</td>
				</tr>
			</c:forEach>
		</table>
		<input required type="text" name="userId" autocomplete="off" placeholder="Enter the ID" style="margin-top: 15px;">
		
		<div class="buttons">
			<input type="submit" value="edit" name="doThisToPark" class="btn1"> <input
			type="submit" value="delete" name="doThisToPark" class="btn2">
			<a href="options.html" class="btn">BACK</a>
			<a class="btn" href="exit.html">EXIT</a>
			
		</div>
	</form>
</body>
</html>