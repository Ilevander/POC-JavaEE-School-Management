<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List of Teachers</title>
</head>
<body>
    <h1>List of Teachers</h1>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Subject</th>
                <th>Email</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="teacher" items="${teachers}">
                <tr>
                    <td>${teacher.id}</td>
                    <td>${teacher.firstName}</td>
                    <td>${teacher.lastName}</td>
                    <td>${teacher.subject}</td>
                    <td>${teacher.email}</td>
                    <td>
                        <a href="teachers?action=edit&id=${teacher.id}">Edit</a>
                        <a href="teachers?action=delete&id=${teacher.id}" onclick="return confirm('Are you sure?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="add-teacher.jsp">Add Teacher</a>
</body>
</html>
