<!DOCTYPE html>
<html>
<head><title>Register</title>
<link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <form action="register" method="post">
        <label>Username: <input type="text" name="username" required></label>
        <label>Password: <input type="password" name="password" required></label>
        <label>Email: <input type="email" name="email" required></label>
        <button type="submit">Register</button>
        <p><%= request.getParameter("error") %></p>
    </form>
</body>
</html>
