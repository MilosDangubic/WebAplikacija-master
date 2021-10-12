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
			<th>Apartment</th>
			<th>Date</th>
			<th>Nights</th>
			<th>Price</th>
			<th>Message</th>
		</tr>
		<c:forEach items="${requestScope.reservations}" var="reservation">
			<tr>
				<td><c:out value="${reservation.apartment.location.address.street}"/></td>
				<td><c:out value="${reservation.firstDayOfReservation}"/></td>
				<td><c:out value="${reservation.numberOfNIghts}"/></td>
				<td><c:out value="${reservation.finalPrice}"/></td>
				<td><c:out value="${reservation.messageAfterReservation}"/></td>
				<c:choose>		
					<c:when test = "${sessionScope.currentUser.role == 'DOMACIN'}">
						<c:choose>
							<c:when test = "${reservation.status == 'Created'}">
								<td>
									<a href="/WebProgramiranje-master/ChangeReservationStatusServlet?reservationId=${reservation.id}&status=Accepted">Accept</a>
								</td>				
							</c:when>		
							<c:when test = "${reservation.status == 'Created'}">
								<td>
									<a href="/WebProgramiranje-master/ChangeReservationStatusServlet?reservationId=${reservation.id}&status=Rejected">Reject</a>
								</td>				
							</c:when>
							<c:when test = "${reservation.status == 'Accepted'}">
								<td>
									<a href="/WebProgramiranje-master/ChangeReservationStatusServlet?reservationId=${reservation.id}&status=Rejected">Reject</a>
								</td>
								<td>
									
									<a href="/WebProgramiranje-master/ChangeReservationStatusServlet?reservationId=${reservation.id}&status=Completed">Complete</a>
								</td>				
							</c:when>
						</c:choose>
					</c:when>
				</c:choose>
				<c:choose>		
					<c:when test = "${sessionScope.currentUser.role == 'GOST'}">
						<c:choose>		
							<c:when test = "${reservation.status == 'Created' || reservation.status == 'Accepted'}">
								<td>
									<a href="/WebProgramiranje-master/ChangeReservationStatusServlet?reservationId=${reservation.id}&status=Cancellation">Cancel</a>
								</td>				
							</c:when>
							<c:when test = "${reservation.status == 'Completed' || reservation.status == 'Rejected'}">
								<td>
									<a href="/WebProgramiranje-master/AddCommentServlet?apartmentId=${reservation.apartment.id}">Add Comment</a>
								</td>				
							</c:when>
						</c:choose>
					</c:when>
				</c:choose>
			</tr>
		</c:forEach>
	</table>
</body>
</html>