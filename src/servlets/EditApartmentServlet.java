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

import beans.Apartment;
import beans.ApartmentType;
import dao.ApartmentDAO;

/**
 * Servlet implementation class EditApartmentServlet
 */
@WebServlet("/EditApartmentServlet")
public class EditApartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditApartmentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher disp = request.getRequestDispatcher("/JSP/EditApartment.jsp");
		
		Long id = Long.parseLong(request.getParameter("apartmentId"));
		
		ServletContext context = getServletContext();
        String contextPath = context.getRealPath("");
        
        ApartmentDAO apartmentDao = new ApartmentDAO(contextPath);
        Apartment apartment = apartmentDao.findByID(id);
		
        request.setAttribute("apartment", apartment);
        
    	disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
		
		Long id = Long.parseLong(request.getParameter("apartmentId"));
		
		ServletContext context = getServletContext();
        String contextPath = context.getRealPath("");
        
        ApartmentDAO apartmentDAO = new ApartmentDAO(contextPath);
        
        Apartment apartment = apartmentDAO.findByID(id);
        
        apartment.setRoomsCount(roomsCount);
        apartment.setGuestCount(guestCount);
        apartment.getLocation().getAddress().setStreet(street);
        apartment.getLocation().getAddress().setCity(city);
        apartment.getLocation().getAddress().setZipCode(zipCode);
        apartment.getLocation().getAddress().setNumber(number);
        apartment.getLocation().setLat(lat);
        apartment.getLocation().setLng(lng);
        apartment.setPricePerNight(pricePerNight);
        apartment.setCheckInTime(checkIn);
        apartment.setCheckOutTime(checkOut);
        
        
        apartmentDAO.update(apartment);
		
        response.sendRedirect("/WebProgramiranje-master/ApartmentListServlet");
	}

}
