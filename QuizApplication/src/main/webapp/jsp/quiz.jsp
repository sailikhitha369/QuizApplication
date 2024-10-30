<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Select a Quiz</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    
</head>
<body>
    <h1>Available Quizzes</h1>
    
    <c:if test="${not empty quizzes}">
        <form action="quiz" method="post">
            <table border="1">
                <tr>
                    <th>Quiz Title</th>
                    <th>Description</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="quiz" items="${quizzes}">
                    <tr>
                        <td>${quiz.title}</td>
                        <td>${quiz.description}</td>
                        <td>
                            <button type="submit" name="quizId" value="${quiz.quizId}">Start Quiz</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </c:if>

    <c:if test="${empty quizzes}">
        <p>No quizzes are available at this time.</p>
    </c:if>

</body>
</html>
