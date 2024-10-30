<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quiz Results</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    
</head>
<body>
    <h1>Quiz Results</h1>
    
    <p>Total Questions: ${totalQuestions}</p>
    <p>Your Score: ${score} / ${totalQuestions}</p>

    <c:choose>
        <c:when test="${score == totalQuestions}">
            <p>Congratulations! You scored a perfect score!</p>
        </c:when>
        <c:otherwise>
            <p>Good job! You can try again to improve your score.</p>
        </c:otherwise>
    </c:choose>
    
    <a href="quiz">Take Another Quiz</a>
    <a href="dashboard">Back to Dashboard</a>
</body>
</html>
