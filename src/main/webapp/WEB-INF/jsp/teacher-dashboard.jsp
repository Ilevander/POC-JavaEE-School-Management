
<!DOCTYPE html>
<html>
<head>
    <title>Teacher Dashboard</title>
</head>
<body>
    <h1>Welcome, Teacher!</h1>

    <!-- Authorization Check -->
    <c:if test="${sessionScope.userRole == 'TEACHER'}">
        <!-- Display CRUD operations and other functionalities -->
        <a href="add-student.jsp">Add Student</a>
        <a href="view-students.jsp">View Students</a>
        <!-- Other teacher-specific links -->
    </c:if>
    <c:if test="${sessionScope.userRole != 'TEACHER'}">
        <p>You are not authorized to access this page.</p>
    </c:if>

    <!-- Other content for the teacher dashboard -->
</body>
</html>
