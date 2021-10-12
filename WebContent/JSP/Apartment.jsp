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
<h2>Please fill out the blanks so we can register your apartment!</h2>
	<form>
		<table >
			<tr>
				<td>
					<label for="apartmentType">Apartment Type:  </label>
					<select id="apartmentType" name="apartmentType">
						<option>Apartment</option>
						<option>Room</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<label for="roomCount">Room count: </label>
					<input id = " roomCount" type="number" name="roomCount">
				</td>
			</tr>
			<tr>
				<td>
					<label for="guestCount">Guest count: </label>
					<input id = " guestCount" type="number" name="guestCount">
				</td>
			</tr>
			<tr>
				<td>
					<label for="slobodniDatumi">Availabel Dates: </label>
					<input id = " slobodniDatumi" type="date" name="AvailabelDa">
				</td>
			</tr>
			<tr>
				<td>
					<label for="hostName">Name of host: </label>
					<input id = " hostName" type="text" name="hostName">
				</td>
			</tr>
			<tr>
				<td>
					<label for="comment">Comment: </label>
					<textarea id="comment" name="Comment"  >Leave your comment here!</textarea>
					
				</td>
				
			</tr>
		</table>
	</form>
</body>
</html>