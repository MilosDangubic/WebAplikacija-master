package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Apartment;
import beans.ApartmentType;
import beans.Reservation;
import beans.StatusOfReservation;
import beans.User;
import dao.ApartmentDAO;
import dao.ReservationDAO;

/**
 * Servlet implementation class AddReservationServlet
 */
@WebServlet("/AddReservationServlet")
public class AddReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddReservationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext context = getServletContext();
        String contextPath = context.getRealPath("");
		
		ApartmentDAO apartmentDao = new ApartmentDAO(contextPath);
		
		request.setAttribute("apartments", apartmentDao.findActiveAndSearch(null, null));
		
		RequestDispatcher disp = request.getRequestDispatcher("/JSP/AddReservation.jsp");
    	disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long apartmentId = Long.parseLong(request.getParameter("apartmentId"));
		String message = request.getParameter("message");
		Integer nightNumber = Integer.parseInt(request.getParameter("nightNumber"));
		LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
		
		ServletContext context = getServletContext();
        String contextPath = context.getRealPath("");
        
        ApartmentDAO apartmentDAO = new ApartmentDAO(contextPath);
        Apartment apartment = apartmentDAO.findByID(apartmentId);
        
        ReservationDAO reservationDAO = new ReservationDAO(contextPath);
        
        HttpSession session = request.getSession(true);	    
        User user = (User)session.getAttribute("currentUser");
        
        Reservation reservation = new Reservation();
        reservation.setApartment(apartment);
        reservation.setFirstDayOfReservation(startDate);
        reservation.setGuest(user);
        reservation.setMessageAfterReservation(message);
        reservation.setNumberOfNIghts(nightNumber);
        reservation.setStatus(StatusOfReservation.Created);
        reservation.setFinalPrice(nightNumber * apartment.getPricePerNight());
        
        reservationDAO.add(reservation);
        
        RequestDispatcher disp = request.getRequestDispatcher("/ReservationServlet");
    	disp.forward(request, response);
	}

}
