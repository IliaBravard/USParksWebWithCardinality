<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<!-- Author: Ilia Garrett Bravard 
	 Date: 10/05/2022
	 Course: Java II Programming Course -->

<html>
<head>
<meta charset="UTF-8">

<title>View the Plans</title>

<link rel="icon" href="image18.png" type="image/x-icon">

<script type="text/javascript"> // JavaScript for exiting the application
	function disableBack() { window.history.forward(); }
		setTimeout("disableBack()", 0);
		window.onunload = function () { null };
</script>

</head>

<style>
body {
	background-image: url("image17.png");
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
	background-color: #7e8a8c;
	border: 1px solid black;
	cursor: pointer;
	color: white;
}

.btn:hover, .btn1:hover, .btn2:hover {
	background-color: #c4cec9;
	transition: 0.35s ease;
	color: black;
}

a {
	text-transform: uppercase;
	height: 35px;
	border-radius: 25px;
	border: none;
	background-color: #7e8a8c;
	border: 1px solid black;
	cursor: pointer;
	color: white;
	text-align: center;
	text-decoration: none;
	font-size: 15px;
	margin-top: 7px;
	padding-top: 10px;
}

.subheading {
	margin: 5px;
	color: #6e5c40;
}

table {
	font-size: 18px;
}
</style>

<body>
	<h1>View the Plans</h1>
	
	<form method="post" action="PlanNavigationServlet" class="box">
	
		<table>
			<c:forEach items="${requestScope.allPlans}" var="currentplan">
				<tr>
					<td><input type="radio" name="id" value="${currentplan.planId}"></td>
					<td><h2 class="subheading">${currentplan.planName}</h2></td>
				</tr>
				
				<tr>
					<td colspan="3">Trip Date: ${currentplan.travelDate}</td>
				</tr>
				
				<tr>
					<td colspan="3">Traveler: ${currentplan.traveler.firstName} ${currentplan.traveler.lastName}</td>
				</tr>
				
				<tr>
					<td colspan="3">Email: ${currentplan.traveler.email}</td>
				</tr>
				
				<tr>
					<td colspan="3">Activity: ${currentplan.activity}</td>
				</tr>
				<c:forEach var="parkVal" items="${currentplan.parkList}">
					<tr>
						<td></td>
						<td colspan="3">${parkVal.name}, ${parkVal.state}</td>
					</tr>
				</c:forEach>
			</c:forEach>
		</table>
		
		<div class="buttons">
			<input type="submit" value="edit" name="doThisToPlan" class="btn1">
			<input type="submit" value="delete" name="doThisToPlan" class="btn2">
			<a href="options.html" class="btn">BACK</a>
			<a class="btn" href="exit.html">EXIT</a>
		</div>
	</form>
	
</body>
</html>