<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Train Result</title>
<link href="<c:url value="/css/trainResultStyle.css" />" rel = "stylesheet"/>
</head>
<body>
<div class="form-container">
		<h1>Available Trains</h1> 
		<table>
            <tr>
                <th>Train Name</th>
                <th>Start time</th>
                <th>Destination time</th>
                <th>Select Train</th>
            </tr>
            <c:forEach var="train" items="${trains}">
            <tr>
       			<td><c:out value="${train.trainName}"/></td>
       			<td><c:out value="${train.fromStationTiming}"/></td>
       			<td><c:out value="${train.toStationTiming}"/></td>
       			<td><a href="selectBooking?trainName=${train.trainName}&fromStation=${train.fromStation}&toStation=${train.toStation}">Select</a></td>
            </tr>
            </c:forEach>
        </table>
	</div>
</body>
</html>