<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Online Railway Reservation - HomePage</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
  <style>
    @import url('https://fonts.googleapis.com/css2?family=Sriracha&display=swap');

    body {
      margin: 0;
      box-sizing: border-box;
    }

    /* CSS for header */
    .header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      background-color: #f5f5f5;
    }

    .header .logo {
      font-size: 25px;
      font-family: 'Sriracha', cursive;
      color: #000;
      text-decoration: none;
      margin-left: 30px;
    }

    .nav-items {
      display: flex;
      justify-content: space-around;
      align-items: center;
      background-color: #f5f5f5;
      margin-right: 20px;
    }

    .nav-items a {
      text-decoration: none;
      color: #000;
      padding: 35px 20px;
    }

    /* CSS for main element */
    .intro {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      width: 100%;
      height: 520px;
      background: linear-gradient(to bottom, rgba(0, 0, 0, 0.5) 0%, rgba(0, 0, 0, 0.5) 100%), url("https://wallpaperset.com/w/full/4/1/1/509748.jpg");
      background-size: cover;
      background-position: center;
      background-repeat: no-repeat;
    }

    .intro h1 {
      font-family: sans-serif;
      font-size: 60px;
      color: #fff;
      font-weight: bold;
      text-transform: uppercase;
      margin: 0;
    }

    .intro p {
      font-size: 20px;
      color: #d1d1d1;
      text-transform: uppercase;
      margin: 20px 0;
    }

    .intro button {
      background-color: #5edaf0;
      color: #000;
      padding: 10px 25px;
      border: none;
      border-radius: 5px;
      font-size: 20px;
      font-weight: bold;
      cursor: pointer;
      box-shadow: 0px 0px 20px rgba(255, 255, 255, 0.4)
    }

    .topnav {
      overflow: hidden;
      background-color: #e9e9e9;
    }

    /* Style the links inside the navigation bar */
    .topnav a {
      float: left;
      display: block;
      color: black;
      text-align: center;
      padding: 14px 16px;
      text-decoration: none;
      font-size: 17px;
    }

    /* Change the color of links on hover */
    .topnav a:hover {
      background-color: #ddd;
      color: black;
    }

    /* Style the "active" element to highlight the current page */
    .topnav a.active {
      background-color: #2196F3;
      color: white;
    }

    /* Style the search box inside the navigation bar */
    .topnav input[type=text] {
      float: right;
      padding: 6px;
      border: none;
      margin-top: 8px;
      margin-right: 16px;
      font-size: 17px;
    }

    /* When the screen is less than 600px wide, stack the links and the search field vertically instead of horizontally */
    @media screen and (max-width: 600px) {

      .topnav a,
      .topnav input[type=text] {
        float: none;
        display: block;
        text-align: left;
        width: 100%;
        margin: 0;
        padding: 14px;
      }

      .topnav input[type=text] {
        border: 1px solid #ccc;
      }
    }

    CSS for footer .footer {
      display: flex;
      justify-content: space-between;
      align-items: center;
      background-color: #302f49;
      padding: 40px 80px;
    }

    .footer .copy {
      color: #131212;
    }
    #main2{
      height: 80vh;
      width: 100vw;
      background-color: #2196F3;
    }
  </style>
</head>

<body>
  <header class="header">
    <a href="#" class="logo">Online Railway Reservation</a>
  </header>
  <div class="topnav">
    <a class="active" href="#home">Home</a>
    <a href="login">Login</a>
    <a href="registration">Register</a>
    <a href="#about">About</a>

    <input type="text" placeholder="Search..">
  </div>
  <main>
    <div class="intro">
      <h1>Online Ticket Booking</h1>
      <p>Book Ticket At Your Own Convinience.</p>
  </main>
  <div id="main2">

  </div>
  <footer class="footer">
    <div class="copy">&copy; 2023 CGASSIGNMENT</div>

    </div>
  </footer>
</body>
</html>