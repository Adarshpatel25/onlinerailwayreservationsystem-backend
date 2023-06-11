<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
    <style>
     body {
            font-family: Arial, sans-serif;
            background-image: url(https://wallpaperset.com/w/full/4/1/1/509748.jpg);
            background-size: cover;
            background-position: center;
        }
        h1 {
            text-align: center;
            margin-top: 50px;
            color: #003366;
        }
        form {
            max-width: 500px;
            margin: 0 auto;
            background-color: #FFFFFF;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px #888888;
        }
        label {
            display: block;
            margin-bottom: 10px;
            color: #003366;
            font-weight: bold;
        }
        input, select {
            display: block;
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            border: none;
            margin-bottom: 20px;
            box-shadow: 0px 0px 5px #888888;
        }
        input[type=submit] {
            background-color: #003366;
            color: #FFFFFF;
            font-weight: bold;
            cursor: pointer;
        }
        input[type=submit]:hover {
            background-color: #00264C;
        }
    </style>
</head>
<body>
    <h1>Register Here</h1>
    <form action="handleRegistration" method="post">
        <label for="name">Full Name:</label>
        <input type="text" id="name" name="name" required>

        <label for="email">Email Address:</label>
        <input type="email" id="email" name="email" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <label for="confirm-password">Confirm Password:</label>
        <input type="password" id="confirm-password" required>

        <label for="dob">Age</label>
        <input type="number" id="age" name="age" required>

        <label for="gender">Gender:</label>
        <select id="gender" name="gender">
            <option value="Male">Male</option>
            <option value="Female">Female</option>
            <option value="Other">Other</option>
        </select>

        <input type="submit" value="Register">
    </form>
</body>
</html>