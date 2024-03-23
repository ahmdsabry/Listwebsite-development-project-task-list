<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task List</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    
    <style>
        
           .dark-mode {
    background-color: #333; /* Dark background color */
    color: #fff; /* Light text color */
        }
        
        .dark-mode table {
    color: #fff; /* Light text color for table content */
}
 .dark-mode tr {
    color: white; /* Light text color for table content */
}

        .dark-mode .navbar {
            background-color: #343a40;
            border-color: #222;
        }

        .dark-mode .navbar .nav-link {
            color: #fff;
        }

        .dark-mode .navbar .nav-link:hover {
            color: #aaa;
        }
        
        
          .note-container {
        max-width: 300px; /* Adjust the width as needed */
        max-height: 3em; /* Adjust the height as needed */
        overflow: hidden;
        white-space: pre-line;
        word-wrap: break-word;
    }

    .note-full {
        max-height: none;
    }

    @keyframes slideIn {
        from {
            opacity: 0;
            transform: translateY(-20px);
        }
        to {
            opacity: 1;
            transform: translateY(0);
        }
    }

    /* Apply animation to table rows */
    tbody tr {
        animation: slideIn 0.9s ease;
    }
    </style>
</head>


<body>

<nav class="navbar navbar-expand-lg bg-body-tertiary"  >
        <div class="container-fluid" style="border: 1px solid black ; width: 70%; padding: 7px;border-radius: 12px; background: #343a40; color: white;"  >
    <a class="navbar-brand" href="" style="color: white">Navbar</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav" style="margin-left: 250px">
          <a class="nav-link active" style="color: white; " aria-current="page" href="addtask1.jsp">Home</a>
          <a class="nav-link" style="color: white;" href="ViewSummerize.jsp">View Summerize</a>
        <a class="nav-link" style="color: white;text-decoration: underline" href="Search.jsp">Search</a>
        <a   class="nav-link btn btn-danger" style="color: white;margin-left: 350px; padding: 7px;  border-radius: 12px " href="index.jsp">Log out</a>
      </div>
    </div>
  </div>
    
    
             <button id="darkModeButton" class="btn btn-outline-light ml-auto" onclick="toggleDarkMode()" style="background-image: url('dark-mode.png'); background-size: cover; width: 42px; height: 42px;"></button>

</nav>
<div class="container">
    <h2 class="my-4">Search Page</h2>
    <!-- Input form to add a new task -->
    <form action="NewServlet" id="taskForm"  class="mb-4">
        <div class="form-group row">
            <label for="task" class="col-sm-2 col-form-label">Task</label>
            <div class="col-sm-6">
                <input required class="form-control" id="task" name="task" placeholder="Search by Note or Priority or Date " >
            </div>
        </div>
         
        
         
        <div class="form-group row">
            <div class="col-sm-6 offset-sm-2">
<input  class="btn btn-success" type="submit" name="log"  value="Search">
              </div>
        </div>
        
        
    </form>


 ${NO1}   
 ${NO2}   
 
    ${add}
          
    <!-- Table to display tasks -->
    <table class="table table-bordered table-striped">
        <thead class="thead-dark">
            <tr>
                <th>Id</th>
                <th>Note</th>
                <th>Status</th>
                <th>Priority</th>
                <th>Date</th>
          
            </tr>
        </thead>
 
        
        
        <c:choose>
    <c:when test="${add > 0}">
        <!-- Render the table with task details -->
        <tbody>
            <!-- Iterate over the notes list and display task details -->
            <c:forEach items="${notes}" var="note">
                <tr>
                    <td>${note.noteid}</td>
                    <td>${note.note}</td>
                    <td>${note.status}</td>
                    <td>${note.priority}</td>
                    <td>${note.date}</td>
                </tr>
            </c:forEach>
        </tbody>
    </c:when>
    <c:otherwise>
        <!-- Render an empty table to allow adding tasks locally -->
        <tbody id="your_table_body_id">
            <!-- Iterate over the notes list and display task details -->
            <c:forEach items="${notess}" var="notes">
                <tr>
                    <td>${notes.noteid}</td>
                    <td>${notes.note}</td>
                    <td>${notes.status}</td>
                    <td>${notes.priority}</td>
               <td>${notes.date}</td>


                </tr>
            </c:forEach>
        </tbody>
    </c:otherwise>
</c:choose>


    </table>
</div>

<!-- Bootstrap JS (optional) -->

<script src="https://kit.fontawesome.com/a076d05399.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Sortable/1.14.0/Sortable.min.js"></script>


<script>
    
    
    
    
    
    
        function toggleDarkMode() {
        var body = document.body;
        body.classList.toggle("dark-mode");

        // Get the icon element
        var darkModeIcon = document.getElementById("darkModeIcon");

        // Check if dark mode is enabled
        if (body.classList.contains("dark-mode")) {
            // If dark mode is enabled, change the icon to sun
            darkModeIcon.classList.remove("fa-moon");
            darkModeIcon.classList.add("fa-sun");
        } else {
            // If dark mode is disabled, change the icon to moon
            darkModeIcon.classList.remove("fa-sun");
            darkModeIcon.classList.add("fa-moon");
        }
    }
    
    
    
    
    
function checkNumberOfRows() {
    var tableBody = document.getElementById("your_table_body_id");
    var numberOfRows = tableBody.getElementsByTagName("tr").length;
    if (numberOfRows >= 5) {
        var confirma = confirm("Are you sure you want to save changes? The table already has 5 or more rows.");
        if (confirma) {
            alert("Redirecting to Login.jsp...");
            window.location.href="Login.jsp"; // Redirect to login.jsp
            return false; // Cancel the form submission
        } else {
            return false; // Cancel the form submission
        }
    }
    return true; // Allow the form submission if rows are less than 5
}
</script>


</body>
</html>
