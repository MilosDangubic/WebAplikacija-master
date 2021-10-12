package servlets;

import java.io.IOException;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Address;
import beans.Apartment;
import beans.ApartmentType;
import beans.Gender;
import beans.Location;
import beans.Status;
import beans.User;
import dao.AddressDAO;
import dao.ApartmentDAO;
import dao.LocationDAO;
import dao.UserDAO;

/**
 * Servlet implementation class AddApartmentServlet
 */
@WebServlet("/AddApartmentServlet")
public class AddApartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddApartmentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher disp = request.getRequestDispatcher("/JSP/AddApartment.jsp");
    	disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ApartmentType apartmentType = ApartmentType.valueOf(request.getParameter("apartmentType"));
		Integer roomsCount = Integer.parseInt(request.getParameter("roomsCount"));
		Integer guestCount = Integer.parseInt(request.getParameter("guestCount"));
		String street = request.getParameter("address");
		String city = request.getParameter("city");
		Integer zipCode = Integer.parseInt(request.getParameter("zipCode"));
		Integer number = Integer.parseInt(request.getParameter("number"));
		Float lat = Float.parseFloat(request.getParameter("lat"));
		Float lng = Float.parseFloat(request.getParameter("lng"));
		Float pricePerNight = Float.parseFloat(request.getParameter("pricePerNight"));
		LocalTime checkIn = LocalTime.parse(request.getParameter("checkInTime"));
		LocalTime checkOut = LocalTime.parse(request.getParameter("checkOutTime"));
		
		ServletContext context = getServletContext();
        String contextPath = context.getRealPath("");
        
        AddressDAO addressDAO = new AddressDAO(contextPath);
        LocationDAO locationDAO = new LocationDAO(contextPath);
        ApartmentDAO apartmentDAO = new ApartmentDAO(contextPath);
		
		Address address = new Address(street, number, city, zipCode);
		address = addressDAO.add(address);
		
		Location location = new Location(lat, lng, address);
		location = locationDAO.add(location);
		
		Apartment apartment = new Apartment();
		apartment.setType(apartmentType);
		apartment.setRoomsCount(roomsCount);
		apartment.setGuestCount(guestCount);
		apartment.setLocation(location);
		apartment.setPricePerNight(pricePerNight);
		apartment.setCheckInTime(checkIn);
		apartment.setCheckOutTime(checkOut);
		apartment.setStatus(Status.NON_ACTIVE);
		
		HttpSession session = request.getSession(true);	    
        User user = (User)session.getAttribute("currentUser");
		
        apartment.setHost(user);
        
		apartment = apartmentDAO.add(apartment);
		
		response.sendRedirect("/WebProgramiranje-master/ApartmentListServlet");
	}

}
