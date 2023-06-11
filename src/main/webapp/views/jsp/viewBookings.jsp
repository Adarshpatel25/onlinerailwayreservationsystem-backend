<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Bookings</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<link rel="stylesheet" href="/css/viewbookingStyle.css">
</head>
<body>

	<div class="container">
		<div class="showBookings">

			<c:forEach var="booking" items="${bookings}">
				<div class="booking">
					<h2>${booking.trainName}</h2>
					<span id="ticketfee">${booking.amount}</span> <span
						id="fromStation">${booking.fromStation}</span> <span
						class="material-symbols-outlined arrow"> east </span> <span
						id="toStation">${booking.toStation}</span> <span id="time">${booking.time}</span>
					<span id="date">${booking.date}</span>
				</div>
			</c:forEach>
		</div>
	</div>

	<script src="/js/viewBookingScript.js"></script>

</body>
</html>