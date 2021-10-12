<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<HEAD>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <TITLE>Reservation Software</TITLE>
    <link rel="stylesheet" href="JSP/style.css">
</HEAD>
<body>
<div class="menu">
	<c:choose>
		<c:when test = "${not empty sessionScope.currentUser}">
			<c:choose>		
				<c:when test = "${sessionScope.currentUser.role == 'ADMINISTRATOR'}">
					<a href="/WebProgramiranje-master/UserAdminServlet">Users</a>	
					<a href="/WebProgramiranje-master/AmenetiesListServlet">AmenetiesList</a>
					<a href="/WebProgramiranje-master/AddAmenetiesServlet">Add Ameneties</a>
				</c:when>
				<c:when test = "${sessionScope.currentUser.role == 'DOMACIN'}">
					<a href="/WebProgramiranje-master/AddApartmentServlet">Add Apartment</a>
				</c:when>
			</c:choose>
			<a href="/WebProgramiranje-master/ApartmentListServlet">Apartment List</a>
			<a href="/WebProgramiranje-master/ReservationServlet">Reservation List</a>
			<a href="/WebProgramiranje-master/AddReservationServlet">Add Reservation</a>
		    <a href="/WebProgramiranje-master/ChangePasswordServlet">Izmena passworda</a>
		    <a href="/WebProgramiranje-master/ProfileServlet">Profile</a>
		    <a href="/WebProgramiranje-master/LogoutServlet">Logout</a>
	    </c:when>
	    <c:otherwise>
	    	<a href="/WebProgramiranje-master/LoginServlet">Login</a>
			<a href="/WebProgramiranje-master/RegisterServlet">Registracija</a>
	    </c:otherwise>
			
	</c:choose>
</div>
<form action="/WebProgramiranje-master/RegisterServlet" method="POST">

<h1>Registracija</h1>

<table>
	<tr>
		<td>Username</td>
		<td><input type="text" name ="username" required/></td>
	</tr>
	<tr>
		<td>First name</td>
		<td><input type="text" name ="firstName" required/></td>
	</tr>
	<tr>
		<td>Last name</td>
		<td><input type="text" name ="lastName" required/></td>
	</tr>
	<tr>
		<td>Email</td>
		<td><input type="text" name ="email" required/></td>
	</tr>
	
	<tr>
		<td>Password</td>
		<td><input type="password" name ="password" required/></td>
	</tr>
	
	<tr>
		<td>Confirm Password</td>
		<td><input type="password" name ="password1" required/></td>
	</tr>
		
	<tr>
		<td>Gender</td>
		<td>
			<select name="gender" required>
	  			<option value="MALE">Male</option>
	  			<option value="FEMALE">Female</option>
			</select>
		</td>		
	</tr>
	
	<tr><td><input type="submit" value="Login"></td></tr>
</table>
</form>
	<!-- Prikaži grešku, ako je bilo -->
	<% if (request.getAttribute("error") != null) { %>
		<p style="color: red"><%=request.getAttribute("error")%></p>
	<% } %>
</body>
</html>