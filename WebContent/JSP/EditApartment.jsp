<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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
	<h1>Add Apartment</h1>

<form action="/WebProgramiranje-master/EditApartmentServlet" method="POST">
<input type="hidden" name="apartmentId" value="${requestScope.apartment.id}">
<table>
	<tr>
		<td>Apartmant Type</td>
		<td>
			<select name="apartmentType" required>
	  			
	  			<c:choose>
	  				<c:when test="${requestScope.apartment.type == 'APARTMAN'}">
	  					<option value="APARTMAN" selected>Apartment</option>
	  				</c:when>
	  				<c:otherwise>
	  					<option value="APARTMAN">Apartment</option>
	  				</c:otherwise>
	  			</c:choose>
	  			
	  			<c:choose>
	  				<c:when test="${requestScope.apartment.type == 'SOBA'}">
	  					<option value="SOBA" selected>SOBA</option>
	  				</c:when>
	  				<c:otherwise>
	  					<option value="SOBA">Room</option>
	  				</c:otherwise>
	  			</c:choose>
			</select>
		</td>		
	</tr>
	<tr>
		<td>Rooms count</td>
		<td><input type="number" name ="roomsCount" required value="${requestScope.apartment.roomsCount}"/></td>
	</tr>
	<tr>
		<td>Guest count</td>
		<td><input type="number" name ="guestCount" required value ="${requestScope.apartment.guestCount }"/></td>
	</tr>
	<tr>
		<td>Address</td>
		<td><input type="text" name ="address" required value ="${requestScope.apartment.location.address.street }"/></td>
	</tr>
	<tr>
		<td>Number</td>
		<td><input type="number" name ="number" required value ="${requestScope.apartment.location.address.number }"/></td>
	</tr>
	<tr>
		<td>City</td>
		<td><input type="text" name ="city" required value ="${requestScope.apartment.location.address.city }"/></td>
	</tr>
	<tr>
		<td>Zip code</td>
		<td><input type="text" name ="zipCode" required value ="${requestScope.apartment.location.address.zipCode }"/></td>
	</tr>
	<tr>
		<td>Latitude</td>
		<td><input type="number" name ="lat" required value ="${requestScope.apartment.location.lat }"/></td>
	</tr>
	<tr>
		<td>Longitude</td>
		<td><input type="number" name ="lng" required value ="${requestScope.apartment.location.lng }"/></td>
	</tr>
	<tr>
		<td>Price per night</td>
		<td><input type="number" name ="pricePerNight" required value ="${requestScope.apartment.pricePerNight }"/></td>
	</tr>
	<tr>
		<td>Check in time</td>
		<td><input type="time" name ="checkInTime" required value ="${requestScope.apartment.checkInTime }"/></td>
	</tr>
	<tr>
		<td>Check out time</td>
		<td><input type="time" name ="checkOutTime" required value ="${requestScope.apartment.checkOutTime}"/></td>
	</tr>		
	
	<tr>
		<td><input type="submit" value="Izmeni"></td>	
	</tr>
	</table>
	</form>
	<a href="/WebProgramiranje-master/index.jsp" >Nazad </a>
</body>
</html>