<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Teacher</title>
</head>
<body>
    <h1>Add Teacher</h1>
    <form action="teachers" method="POST">
        <input type="hidden" name="action" value="create">
        First Name: <input type="text" name="firstName"><br>
        Last Name: <input type="text" name="lastName"><br>
        Subject: <input type="text" name="subject"><br>
        Email: <input type="email" name="email"><br>
        Role: <select name="role">
            <option value="TEACHER">Teacher</option>
            <!-- Add other role options as needed -->
        </select><br>
        <input type="submit" value="Add">
    </form>
    <a href="teachers">Back to Teachers List</a>
</body>
</html>
