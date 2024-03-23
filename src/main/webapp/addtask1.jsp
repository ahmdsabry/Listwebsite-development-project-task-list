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
    <!-- Sortable.js CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
<style>
    /* Your existing styles */
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
</style>
</head>


<body>

   <nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid" style="border: 1px solid black; width: 70%; padding: 7px;border-radius: 12px; background: #343a40; color: white;">
        <a class="navbar-brand" href="" style="color: white">Navbar</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav" style="margin-left: 250px">
                <a class="nav-link active" style="color: white; text-decoration: underline" aria-current="page" href="addtask1.jsp">Home</a>
                <form action="NewServlet" method="get">
                    <input type="hidden" name="log" value="View Summerize">
                    <input type="hidden" name="userid" value="${add}">
                    <input type="submit" class="nav-link" style="color: white; background: none; border: none; cursor: pointer;" onclick="return checkLogin(${add});" value="View Summerize">
                </form>
                <a class="nav-link" style="color: white" href="Search.jsp" onclick="return checkLogin(${add});">Search</a>
                <c:choose>
                    <c:when test="${add <= 0 }">
                        <a class="nav-link btn btn-success" style="color: white;margin-left: 350px; padding: 7px;  border-radius: 12px" href="Login.jsp">Login</a>
                    </c:when>
                    <c:otherwise>
                        <a class="nav-link btn btn-danger" style="color: white;margin-left: 350px; padding: 7px;  border-radius: 12px" href="index.jsp">Log out</a>
                    </c:otherwise>
                </c:choose>
                <!-- Dark Mode Toggle Icon -->
                
            </div>
        </div>
    </div>
         <button id="darkModeButton" class="btn btn-outline-light ml-auto" onclick="toggleDarkMode()" style="background-image: url('dark-mode.png'); background-size: cover; width: 42px; height: 42px;"></button>



</nav>
                
                
<div class="container">
    <h2 class="my-4">Task List</h2>
    <!-- Input form to add a new task -->
    <form action="NewServlet" id="taskForm" class="mb-4">
        <div class="form-group row">
            <label for="task" class="col-sm-2 col-form-label">Task</label>
            <div class="col-sm-6">
<textarea class="form-control" id="task" name="task" placeholder="Enter task" rows="5" cols="5"></textarea>
            </div>
        </div>
        <div class="form-group row">
            <label for="priority" class="col-sm-2 col-form-label">Priority</label>
            <div class="col-sm-6">
                <select class="form-control" id="priority" name="priority">
                    <option value="High">High</option>
                    <option value="Medium">Medium</option>
                    <option value="Low">Low</option>
                </select>
            </div>
        </div>
        
         <div class="form-group row">
            <label for="priority" class="col-sm-2 col-form-label">Date</label>
            <div class="col-sm-6">
                <input type="date" name="date" />
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-6 offset-sm-2">
                <input class="btn btn-primary" type="submit" name="log" onclick="return checkNumberOfRows();" value="SaveChanges">
                <input class="btn btn-success" type="submit" name="log" value="ViewTasks">
                <input class="btn btn-info" type="submit" name="log" value="view splited">
                <input class="btn btn-outline-warning" type="submit" name="log" value="Sortbydate">
                <input class="btn btn-outline-warning" style="margin-top: 34px;"type="submit" name="log" value="Group Similler">
                <input class="btn btn-outline-secondary" style="margin-top: 34px;"   type="submit" name="log" value="ExportToTxt">
            </div>
        </div>
    </form>

    ${NO1}   
    ${NO2}   
    ${add} 
    ${log}
<c:choose>
    <c:when test="${add > 0 }">
        <h4 style="font-weight: bold">Welcome ${fullname}</h4>
    </c:when>
    <c:otherwise>
        <h4 style="font-weight: bold">Welcome Guest</h4>
    </c:otherwise>
</c:choose>
         
    <!-- Table to display tasks -->
  <table class="table table-bordered table-striped">
    <thead class="thead-dark">
        <tr>
            <th>Id</th>
            <th>Note</th>
            <th>Status</th>
            <th>Priority</th>
            <th>Date</th>
            <th>Update status</th>
            <th>Delete Task</th>
            <th>Check Done</th>
        </tr>
    </thead>
    <c:choose>
       
<c:when test="${log eq 'Group Similler' && add > 0}">

    <!-- Render the table with task details -->
    <tbody id="your_table_body_id1">
        <!-- Iterate over the grouped notes map -->
        <c:forEach items="${notes}" var="entry">
            <!-- Iterate over the list of notes for each group -->
            <c:forEach items="${entry.value}" var="note">
                <tr>
                    <td>${note.noteid}</td>
                    <td>
                        <div id="note_${note.noteid}" class="note-container">
                            ${note.note}
                        </div>
                        <a href="#" onclick="toggleNoteVisibility(${note.noteid}); return false;">Read more</a>
                    </td>
                    <td <c:if test="${note.status eq 'Expired'}">style="color: red;"</c:if>>${note.status}</td>
                    <td>${note.priority}</td>
                    <td>${note.date}</td>
                    <c:choose>
                        <c:when test="${note.status eq 'pending'}">
                            <td>
                                <a href="EditStatus.jsp?note_id=${note.noteid}&status=pending&note=${note.note}" class="btn btn-info">Update status</a>
                            </td>
                        </c:when>
                        <c:when test="${note.status eq 'Expired'}">
                            <td style="color: red;">Not Allowed</td>
                        </c:when>
                        <c:otherwise>
                            <td>Done</td>
                        </c:otherwise>
                    </c:choose>
                    <td><a href="DeleteTaskServlet?note_id=${note.noteid}" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this task?')">Delete</a></td>
                    <c:choose>
                        <c:when test="${note.status eq 'Expired'}">
                            <td style="color: red">Not Allowed</td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="CheckDoneServlet?note_id=${note.noteid}" class="btn btn-outline-success">Done</a></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </c:forEach>
    </tbody>
</c:when>

    
      <c:when test="${add > 0 }">
            <!-- Render the table with task details -->
            <tbody id="your_table_body_id1">
                <!-- Iterate over the notes list and display task details -->
                <c:forEach items="${notes}" var="note">
                    <tr>
                        <td>${note.noteid}</td>
                        <td>
                            <div id="note_${note.noteid}" class="note-container">
                                ${note.note}
                            </div>
                            <a href="#" onclick="toggleNoteVisibility(${note.noteid}); return false;">Read more</a>
                        </td>
                        <td <c:if test="${note.status eq 'Expired'}">style="color: red;"</c:if>>${note.status}</td>
                        <td>${note.priority}</td>
                        <td>${note.date}</td>
                        <c:choose>
                            <c:when test="${note.status eq 'pending'}">
                                <td>
                                    <a href="EditStatus.jsp?note_id=${note.noteid}&status=pending&note=${note.note}" class="btn btn-info">Update status</a>
                                </td>
                            </c:when>
                            <c:when test="${note.status eq 'Expired'}">
                                <td style="color: red;">Not Allowed</td>
                            </c:when>
                            <c:otherwise>
                                <td>Done</td>
                            </c:otherwise>
                        </c:choose>
                        <td><a href="DeleteTaskServlet?note_id=${note.noteid}" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this task?')">Delete</a></td>
                        <c:choose>
                            <c:when test="${note.status eq 'Expired'}">
                                <td style="color: red">Not Allowed</td>
                            </c:when>
                            <c:otherwise>
                                <td><a href="CheckDoneServlet?note_id=${note.noteid}" class="btn btn-outline-success">Done</a></td>
                            </c:otherwise>
                        </c:choose>
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
                    <c:choose>
                        <c:when test="${note.status eq 'pending'}">
                            <td><a href="EditStatus.jsp?note_id=${note.noteid}&status=pending&note=${note.note}" class="btn btn-info">Update status</a></td>
                        </c:when>
                        <c:otherwise>
                            <td>Done</td>
                        </c:otherwise>
                    </c:choose>
                    <td><a href="DeleteTaskServlet?note_id=${notes.noteid}" class="btn btn-danger" >Delete</a></td>
                    <td><a href="CheckDoneServlet?note_id=${notes.noteid}" class="btn btn-outline-success" > Done</a></td>
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
    document.addEventListener('DOMContentLoaded', function () {
        var table = document.getElementById('your_table_body_id1');
        var sortable = new Sortable(table, {
            animation: 150, // Animation speed in milliseconds
        });
    });
</script>
<script>
    

    document.addEventListener('DOMContentLoaded', function () {
        var table = document.getElementById('your_table_body_id');
        var sortable = new Sortable(table, {
            animation: 150, // Animation speed in milliseconds
        });
    });
</script>

<script>
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

 function toggleNoteVisibility(noteId) {
        var noteDiv = document.getElementById('note_' + noteId);
        var readMoreLink = noteDiv.nextElementSibling;

        if (noteDiv.style.maxHeight === '3em') {
            noteDiv.style.maxHeight = 'none';
            readMoreLink.textContent = 'Read less';
        } else {
            noteDiv.style.maxHeight = '3em';
            readMoreLink.textContent = 'Read more';
        }
    }
    
    
    
       function checkLogin(add) {
        if (add <= 0) {
            alert("You should login before going to View Summerize or Search.");
                        window.location.href="Login.jsp"; // Redirect to login.jsp

            return false; // Cancel the link action
        }
        return true; // Allow the link action
    }
    
    
</script>


</body>
</html>
