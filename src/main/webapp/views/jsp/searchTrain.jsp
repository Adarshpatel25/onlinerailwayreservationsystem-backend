<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Trains</title>
<link rel="stylesheet" href="/css/searchTrainStyle.css">
</head>
<body>
	<div class="form-container">
		<h1>Search Trains</h1>

		<form action="handleSearch">
			<label for="from">From Station:</label><br>
			<!--             <input type="text" name="from" id="from"> -->
			<select id="from" name="from">
				<c:forEach var="dest" items="${names}">
					<option>${dest}</option>
				</c:forEach>
			</select> <br> <br> <label for="to">To Station:</label><br>
			<!--             <input type="text" name="to" id="to"> -->
			<select id="to" name="to">
				<c:forEach var="dest" items="${names}">
					<option>${dest}</option>
				</c:forEach>
			</select> <br> <br>
			<div class="btn">
				<button type="submit" id="search">Search</button>
			</div>
		</form>
	</div>
</body>
</html>