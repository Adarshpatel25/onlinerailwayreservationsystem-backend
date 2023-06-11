<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
	background-image: url('/images/loginImg.jpg');
}

form {
	background: white;
	border: 3px solid rgb(235, 235, 235);
	height: 300px;
	width: 400px;
	margin: 80px auto;
}

h2 {
	text-align: center;
	color: purple;
}

input[type=email], input[type=password] {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}

button {
	background-color: #04AA6D;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
}

button:hover {
	opacity: 0.8;
}

.cancelbtn {
	width: auto;
	padding: 10px 18px;
	background-color: #f44336;
}

.imgcontainer {
	text-align: center;
	margin: 24px 0 12px 0;
}

img.avatar {
	width: 15%;
	border-radius: 50%;
}

.container {
	padding: 16px;
}

span.psw {
	float: right;
	padding-top: 16px;
}

.error {
	visibility: hidden;
	font-size: 12px;
	color: red;
	padding: 0;
	margin: 0;
}

.error p {
	padding: 0;
	margin: 0;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
	span.psw {
		display: block;
		float: none;
	}
	.cancelbtn {
		width: 100%;
	}
}
</style>
</head>
<body>

	<h2>Login Form</h2>

	<form:form action="handleLogin" method="post"
		modelAttribute="userLogin">
		<div class="imgcontainer">
			<img src="pic.png" alt="Avatar" class="avatar">
		</div>

		<div class="container">
			<label for="uname"><b>Email</b></label> <input type="email"
				placeholder="Enter your email Id" id="email" name="email"
				value="${userLogin.email}" required> <label for="password"><b>Password</b></label>
			<input type="password" placeholder="Enter Password" id="password"
				name="password" value="${userLogin.password}" class="psw" required>

			<div class="error">
					<%
					String error = (String) request.getAttribute("isError");
					String message = error.equalsIgnoreCase("true") ? "Incorrect email or password" : "";
					%>
				<p id="para"><%=message%></p>
			</div>

			<button type="submit">Login</button>

		</div>
	</form:form>
	
	<script>
		
	</script>
	
</body>
</html>