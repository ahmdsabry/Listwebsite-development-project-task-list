<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Include Animate.css for additional animations -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
    <style>
        /* Custom CSS for background */
        body {
            background-color: darkgrey ; /* Add your desired gradient colors here */
            /* Adjust background properties as needed */
            color: white; /* Text color for better visibility */
        }
        .container {
            margin-top: 100px; /* Adjust vertical margin as needed */
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row justify-content-center mt-5">
        <div class="col-md-6">
            <h1 class="text-center text-black mb-4 animate__animated animate__fadeInDownBig" style="color: black">Welcome to ToDoList  </h1>
            <!-- Buttons to navigate to different pages -->
            <div class="text-center">
                <!-- Button to go to addtask page -->
                <form action="NewServlet">
                    <input type="submit" name="log" value="Add Task" class="btn btn-secondary btn-lg btn-block mb-3 animate__animated animate__fadeInLeft"/>
                </form>
                <!-- Button to go to register page -->
                <a href="Register.jsp" class="btn btn-dark btn-lg btn-block mb-3 animate__animated animate__fadeInUp">Register</a>
                <!-- Button to go to login page -->
                <a href="Login.jsp" class="btn btn-light btn-lg btn-block animate__animated animate__fadeInRight">Login</a>
            </div>
        </div>
    </div>
</div>
<!-- Include Bootstrap JS (optional, if you need JavaScript functionality) -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
