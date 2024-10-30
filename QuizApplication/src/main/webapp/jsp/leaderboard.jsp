<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Leaderboard</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    
</head>
<body>
    <h1>Leaderboard</h1>
    
    <c:if test="${not empty leaderboard}">
        <table border="1">
            <tr>
                <th>Username</th>
                <th>Quiz Title</th>
                <th>Score</th>
            </tr>
            <c:forEach var="entry" items="${leaderboard}">
                <tr>
                    <td>${entry.username}</td>
                    <td>${entry.quizTitle}</td>
                    <td>${entry.score}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${empty leaderboard}">
        <p>No leaderboard data available.</p>
    </c:if>

    <a href="dashboard">Back to Dashboard</a>
</body>
</html>
