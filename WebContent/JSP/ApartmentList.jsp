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
<h1>Apartment List</h1>
<form action="ApartmentListServlet" method="get">
	<input type="text" name="search">
	<select name="sort">
		<option value="CITY">City</option>
		<option value="ADDRESS">Address</option>
		<option value="FIRST_NAME">First Name</option>
		<option value="LAST_NAME">Last Name</option>
	</select>
	<input type="submit" value="Search">
</form>

<table >
	<tr>
		<th>Room count</th>
		<th>Street</th>
		<th>Number</th>
		<th>City</th>
		<th>Zip Code</th>
		<th>Add dates</th>
	</tr>

    <c:forEach items="${requestScope.apartments}" var="apartment">
        <tr>
        <td><c:out value="${apartment.roomsCount}"/></td>
            <td><c:out value="${apartment.location.address.street}"/></td> 
            <td><c:out value="${apartment.location.address.number}"/></td>
            <td><c:out value="${apartment.location.address.city}"/></td>
            <td><c:out value="${apartment.location.address.zipCode}"/></td>
            <td><c:out value="${apartment.status}"/></td>
            <td>
            	<a href="/WebProgramiranje-master/CommentListServlet?apartmentId=${apartment.id}">Comments</a>
            </td>
            <c:choose>		
				<c:when test = "${sessionScope.currentUser.role == 'ADMINISTRATOR' || apartment.host.email == sessionScope.currentUser.email }">
					<td>
		            	<a href="/WebProgramiranje-master/EditApartmentServlet?apartmentId=${apartment.id}">Edit</a>
		            </td>
		            <td>
		            	<a href="/WebProgramiranje-master/DeleteApartmentServlet?apartmentId=${apartment.id}">Delete</a>
		            </td>	
				</c:when>
			</c:choose>
			<c:choose>		
				<c:when test = "${sessionScope.currentUser.role == 'ADMINISTRATOR'}">
					<td>
		            	<a href="/WebProgramiranje-master/FreeApartmentDates?apartmentId=${apartment.id}">Add dates</a>
		            </td>
		            
		            <td>
		            	<a href="/WebProgramiranje-master/ApartmentAmenetiesServlet?apartmentId=${apartment.id}">Add ameneties</a>
		            </td>
		             <td>
		             	<c:choose>
			             	<c:when test = "${apartment.status == 'ACTIVE'}">
			             		<a href="/WebProgramiranje-master/DeactiveApartmentServlet?apartmentId=${apartment.id}">Deactivate</a>
			            	</c:when>
			            	<c:otherwise>
			            	 	<a href="/WebProgramiranje-master/ActiveApartmentServlet?apartmentId=${apartment.id}">Active</a>
			            	</c:otherwise>
		             	</c:choose>
		            </td>	
				</c:when>
			</c:choose>
        </tr>
    </c:forEach>
</table>
</body>
</html>