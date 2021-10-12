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

	<table>
		<tr>
			<th>Name</th>
		</tr>
		<c:forEach items="${requestScope.apartment.amenities}" var="amenetie">
			<tr>
				<td><c:out value="${amenetie.name}"/></td>
				<td><a href="/WebProgramiranje-master/DeleteApartmentAmenetieServlet?amenetiesId=${amenetie.id}&apartmentId=${requestScope.apartment.id}">Remove</a></td>
			</tr>
			
		</c:forEach>
	</table>
	
	<form action="/WebProgramiranje-master/ApartmentAmenetiesServlet" method="post">
		<input type="hidden" name="apartmentId" value="${requestScope.apartment.id}">
		<select name="amenetieId" >
			<c:forEach items="${requestScope.ameneties}" var="item">
				<option value="${item.id}">${item.name}</option>
			</c:forEach>
		</select>
		
		<input type="submit" value="Add">
	</form>
	
</body>
</html>