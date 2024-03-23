<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Status</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">


</head>
<body>
    <h2>Edit Status</h2>
    <%
    String noteContent = request.getParameter("note");
%>



    <!-- Form to edit the status -->
    <form action="NewServlet"  >
        
        <input type="hidden" name="note_id" value="${param.note_id}">
        <!-- Hidden field to store the note ID -->
  <div class="form-group row">
        <label for="note" class="col-sm-2 col-form-label">Note</label>
        <div class="col-sm-6">
<textarea class="form-control"  required id="note" name="note" placeholder="Enter task" rows="5" cols="5">   <%= noteContent %> </textarea>
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
                <input required type="date" name="date" />
            </div>
        </div>
    <div class="form-group row">
        <div class="col-sm-6 offset-sm-2">
            <input class="btn btn-primary" type="submit" name="log" value="Save Changes">
        </div>
    </div>
    </form>
</body>


</html>
