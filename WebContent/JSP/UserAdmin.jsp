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
<form action="UserAdminServlet" method="get">
	<input type="text" name="search">
	<input type="submit" value="Search">
</form>

<table >
	<tr>
		<th>Username</th>
		<th>FirstName</th>
		<th>LastName</th>
		<th>Email</th>
		<th>Gender</th>
		<th>Role</th>
	</tr>

    <c:forEach items="${requestScope.users}" var="user">
        <tr>
            <td><c:out value="${user.username}"/></td> 
            <td><c:out value="${user.firstName}"/></td>
            <td><c:out value="${user.lastName}"/></td>
            <td><c:out value="${user.email }"/></td>
            <td><c:out value="${user.gender }"/></td>
            <td><c:out value="${user.role }"/></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>