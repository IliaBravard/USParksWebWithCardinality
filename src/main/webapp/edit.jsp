<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Telling the JSP that we are going to use the tag library -->
<!DOCTYPE html>

<!-- Author: Ilia Garrett Bravard 
	 Date: 10/02/2022
	 Course: Java II Programming Course -->

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Park</title>
    <link rel="icon" href="image18.png" type="image/x-icon">
</head>

<style>
    body {
	background-image: url("background.png");
	background-size: cover;
	background-repeat: no-repeat;
	font-family: "montserrat";
}

h1 {
	color: white;
	position:absolute;
    padding-left: 942px;
	font-size: 60px;
    }

    .form-container {
        position: absolute;
        left: 71%;
        top: 14%;
        border: 5px solid white;
        padding: 35px;
        border-radius: 30px;
        width: 280px;
    }

    p {
        color: white;
        font-size: 25px;
        padding-left: 62px;
    }

    .padding {
        margin-top: 0px;
    }

    h1 {
        margin-top: 5px;
    }

    .box input[type="text"] {
	    border: 0;
	    text-align: center;
	    border: 2px solid white;
	    background: white;
	    width: 200px;
	    color: black;
	    border-radius: 25px;
	    transition: 0.25s;
        font-size: 20px;
        margin-left: 32px;
}

.box input[type="text"]:focus {
	width: 220px;
	border-color: black;
	outline: none;
    font-size: 23px;
}

.btn {
    position: absolute;
    top: 92%;
    left: 74.8%;
    border-radius: 20px;
    text-transform: uppercase;
    width: 250px;
    font-size: 25px;
    background: white;
    cursor: pointer;
}

.btn:hover {
    background-color: rgb(190, 190, 253);
    border: none;
    transition: 0.25s ease;
}

</style>

<body>
    <h1>Update Park</h1>
    <form action="EditParkServlet" method="post" class="box" autocomplete="off">
        <div class="form-container">
        <p class="padding">New Name:</p>
        <input required type = "text" name="name" placeholder="${toEdit.name}">
        
        <p>New State:</p>
        <input required type = "text" name="state" placeholder="${toEdit.state}">
        
        <p>New Area:</p>
        <input required type = "text" name="area" placeholder="${toEdit.area}">

        <p>Reenter ID:</p>
		<input required type="text" name="userId" placeholder="${toEdit.id}">
        </div>
        
		<input type="submit" class="btn" value="Save">
    </form>
</body>
</html>