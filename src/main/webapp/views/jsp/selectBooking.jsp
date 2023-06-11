
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Date"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Select Booking</title>

<link rel="stylesheet" href="/css/selectBookingStyle.css">

</head>
<body>

	<div class="container">
		<form:form action="transactionPage" method="post"
			modelAttribute="myBookings">
			<div class="mini1">
				<span id="trainNo">${train.trainNo}</span> | <span id="trainName">${train.trainName}</span>
				<span id="fromTiming">${train.fromStationTiming}</span> <span
					id="fromStation">${train.fromStation}</span> <span id="toTiming">${train.toStationTiming}</span>
				<span id="toStation">${train.toStation}</span> <label for="date">Enter
					Date of Travel</label> <input type="date" name="reservationDate" id="date">
			</div>
			<div class="mini2">

				<div class="invisible">
					<input type="hidden" value="${train.trainNo}" name="trainNo">
					<input type="hidden" value="${train.trainName}" name="trainName">
					<input type="hidden" value="${train.fromStation}"
						name="fromStation"> <input type="hidden"
						value="${train.toStation}" name="toStation">
					<%
					Locale locale = new Locale("en", "IN");
					DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
					DateFormat dateFormat1 = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
					String time = dateFormat.format(new Date()).toString();
					String date = dateFormat1.format(new Date()).toString();
					%>
					<input type="hidden" value="<%=time%>" name="time"> <input
						type="hidden" value="<%=date%>" name="date">
				</div>
				<div class="passenger">
					<label for="name">Passenger Name</label><br> <input
						type="text" name="passengerName" id="name"><br> <label
						for="age">Passenger Age</label><br> <input type="number"
						name="passengerAge" id="age">
				</div>
				<%
				double fee = (double) request.getAttribute("ticketFee");
				%>
				<input type="hidden" name="amount" value="<%=fee%>">
				<div class="button">

					<label for="radio"><span id="class">First Class</span> <span
						id="fees"><%=fee * 2.5%></span></label> <input type="radio" id="radio"
						name="seatCoach" value="First class">

				</div>
				<div class="button">
					<label for="radio"><span id="class">Second Class</span> <span
						id="fees"><%=fee * 1.8%></span> </label> <input type="radio" id="radio"
						name="seatCoach" value="Second class">
				</div>
				<div class="button">
					<label for="radio"><span id="class">Third Class</span> <span
						id="fees"><%=fee%></span> </label> <input type="radio" id="radio"
						name="seatCoach" value="Third class">
				</div>
			</div>
			<div id="btn-cont">
				<a href="#" id="btn" onclick="bookTicket()">Book Ticket</a>
			</div>
			<div id="popup">
				<h2>Confirm Ticket</h2>
				<button type="submit" id="confirm">Confirm</button>
				<a href="#" id="cancel" onclick="cancelBooking()">Cancel</a>
			</div>
		</form:form>
	</div>

	<script src="/js/selectBookingScript.js"></script>

</body>
</html>