<!DOCTYPE html>
<html>
<head><title>Login</title>
<link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <form action="login" method="post">
        <label>Username: <input type="text" name="username" required></label>
        <label>Password: <input type="password" name="password" required></label>
        <button type="submit">Login</button>
        <p><%= request.getParameter("error") %></p>
    </form>
</body>
</html>
