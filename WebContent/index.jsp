<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<HTML>
<HEAD>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <TITLE>Reservation Software</TITLE>
    <link rel="stylesheet" href="JSP/style.css">
</HEAD>
<BODY>

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
<form action="HomeServlet" method="get">
	<input type="text" name="search">
	<label>Sort</label>
	<select name="sort">
		<option value="CITY">City</option>
		<option value="ADDRESS">Address</option>
		<option value="FIRST_NAME">First Name</option>
		<option value="LAST_NAME">Last Name</option>
	</select>
	<label>Person</label>
	<select name="person">
		<option value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5" selected>5</option>
		<option value="6">6</option>
		<option value="7">7</option>
		<option value="8">8</option>
		<option value="9">9</option>
		<option value="9">10</option>
	</select>
	<label>Price</label>
	<input type="text" name="priceFrom" value="0" required>
	<input type="text" name="priceTo" value="50000" required>
	<label>Rooms</label>
	<input type="text" name="roomFrom" value="1" required>
	<input type="text" name="roomTo" value="10" required>
	<input type="submit" value="Search">
</form>
<div>
<table>
<tr>
	<th>Address</th>
	<th>Number</th>
	<th>City</th>
	<th>zipCode</th>
	<th>Price</th>
	<th>Person</th>
	<th>Rooms</th>
	<th>Comments</th>
</tr>
<c:forEach items="${requestScope.apartments}" var="apartment">
        
	        <tr>
	            <td><c:out value="${apartment.location.address.street}"/></td> 
	            <td><c:out value="${apartment.location.address.number}"/></td>
	            <td><c:out value="${apartment.location.address.city}"/></td>
	            <td><c:out value="${apartment.location.address.zipCode}"/></td>
	            <td><c:out value="${apartment.pricePerNight}"/></td>
	            <td><c:out value="${apartment.guestCount}"/></td>
	            <td><c:out value="${apartment.roomsCount}"/></td>
	            <td colspan="5">
	        		<c:forEach items="${apartment.comments}" var="comment">
	        			<p>
	        				<c:out value="${comment.text}"/>
	        			</p>
	        		</c:forEach>
	        	</td>
	        </tr>
        
    </c:forEach>
    </table>
</div>
</body>
</html>
