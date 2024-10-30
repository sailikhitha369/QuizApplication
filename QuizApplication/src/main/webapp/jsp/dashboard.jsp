<%@ page import="models.User" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page session="true" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
    }
%>
<!DOCTYPE html>
<html>
<head><title>Dashboard</title>
<link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <h1>Welcome, <%= user.getUsername() %></h1>
    <a href="quiz.jsp">Take a Quiz</a>
    <a href="leaderboard.jsp">Leaderboard</a>
</body>
</html>
