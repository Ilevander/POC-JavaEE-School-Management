<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Teacher</title>
</head>
<body>
    <h1>Edit Teacher</h1>
    <form action="teachers" method="POST">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${teacher.id}">
        First Name: <input type="text" name="firstName" value="${teacher.firstName}"><br>
        Last Name: <input type="text" name="lastName" value="${teacher.lastName}"><br>
        Subject: <input type="text" name="subject" value="${teacher.subject}"><br>
        Email: <input type="email" name="email" value="${teacher.email}"><br>
        Role: <select name="role">
            <option value="TEACHER" selected>Teacher</option>
            <!-- Add other role options as needed -->
        </select><br>
        <input type="submit" value="Update">
    </form>
    <a href="teachers">Back to Teachers List</a>
</body>
</html>
