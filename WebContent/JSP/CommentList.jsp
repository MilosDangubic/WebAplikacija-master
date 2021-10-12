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
			<th>Comment</th>
			<th>Rate</th>
			<th>Published</th>
		</tr>
		<c:forEach items="${requestScope.comments}" var="comment">
			<tr>
				<td><c:out value="${comment.guestCommName}"/></td>
				<td><c:out value="${comment.text}"/></td>
				<td><c:out value="${comment.rate}"/></td>
				<td><c:out value="${comment.approved}"/></td>
				<c:choose>
					<c:when test="${sessionScope.currentUser.role == 'DOMACIN' && comment.approved == 'false'}">
						<td>
							<a href="/WebProgramiranje-master/ChangeCommentServlet?commentId=${comment.id}&state=true">Publish</a>
						</td>
					</c:when>
					<c:when test="${sessionScope.currentUser.role == 'DOMACIN' && comment.approved == 'true'}">
						<td>
							<a href="/WebProgramiranje-master/ChangeCommentServlet?commentId=${comment.id}&state=fasle">Unpublish</a>
						</td>
					</c:when>
				</c:choose>
			</tr>
		</c:forEach>
	</table>
</body>
</html>