<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Edit a Travel Plan</title>

<link rel="icon" href="image18.png" type="image/x-icon">

<style>
body {
	background-image: url("image16.png");
	background-size: cover;
	height: 100%;
	background-repeat: no-repeat;
	font-family: "montserrat";
}

h1 {
	color: white;
	text-align: left;
	font-size: 60px;
	padding-left: 910px;
	margin-bottom: 0%;
	margin-top: 0%;

}

.container {
	margin-left: 66%;
	margin-right: 5%;
	border: 5px solid white;
	border-radius: 30px;
	display: flex;
	justify-content: center;
}

p {
	color: white;
	font-size: 25px;
	margin: 6px;
}

.input {
	border: 0;
	text-align: center;
	border: 2px solid white;
	background: white;
	width: 200px;
	color: black;
	border-radius: 25px;
	transition: 0.25s;
	font-size: 17px;
}

.input:focus {
	width: 220px;
	border-color: black;
	outline: none;
	font-size: 20px;
}

form {
	text-align: center;
}

.date {
	width: 100px;
	margin: 5px;
	border: 0;
	text-align: center;
	border: 2px solid white;
	background: white;
	color: black;
	border-radius: 25px;
	transition: 0.25s;
}

.btn {
	margin: 10px;
	border-radius: 25px;
	width: 175px;
	background-color: rgb(199, 199, 199);
	font-size: 20px;
}

.btn:hover {
	background-color: #e9ecf9;
	cursor: pointer;
	transition: 0.25s ease-in-out;
	font-size: 25px;
	border: none;
}

.select {
	border-style:none;
	border-radius: 10px;
	decoration: none;
	width: 200px;
	font-size: 13px;
}
</style>
</head>
<body>
<h1>Edit a Plan</h1>

<div class="container">

	<form action="EditPlanServlet" method="post" autocomplete = "off">
	
		<input type="hidden" name="id" value="${planToEdit.planId}">
	
		<p>New Name:</p>
		<input required type="text" name="planName" placeholder="${planToEdit.planName}" class="input">
		
		<p>New Date:</p>
		<input required type="text" name="travelMonth" placeholder="${travelMonth}" size="4" class="date">
		<input required type="text" name="travelDate" placeholder="${travelDate}" size="4" class="date">
		<input required type="text" name="travelYear" placeholder="${travelYear}" size="4" class="date">
		
		
		<p>New First Name:</p>
		<input required type="text" name="travelerFName" placeholder = "${planToEdit.traveler.firstName}" class="input">
		
		<p>New Last Name:</p> 
		<input type="text" name="travelerLName" placeholder = "${planToEdit.traveler.lastName}" class="input">
		
		<p>New Activity:</p>
		<input required type="text" name="travelActivity" placeholder="${planToEdit.activity}" class="input">
		
		<p>Available Parks:</p> 
		<select required name="allParksToAdd" multiple size="6" class = "select">
			<c:forEach items="${requestScope.allParks}" var="currentpark">
				<option value="${currentpark.id}">${currentpark.name} &#11162 ${currentpark.state}</option>
			</c:forEach>
		</select>
		
		<div><input type="submit" value="Save" class="btn"></div>
		
	</form>
	</div>
	
</body>
</html>