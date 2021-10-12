package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Apartment;
import dao.ApartmentDAO;

/**
 * Servlet implementation class FreeApartmentDates
 */
@WebServlet("/FreeApartmentDates")
public class FreeApartmentDatesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeApartmentDatesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher disp = request.getRequestDispatcher("/JSP/FreeApartmentDates.jsp");
		Integer id = Integer.parseInt(request.getParameter("apartmentId"));
		request.setAttribute("apartmentId", id);
		disp.forward(request, response);
    	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Long id = Long.parseLong(request.getParameter("apartmentId"));
		LocalDateTime from = LocalDateTime.parse(request.getParameter("dateFrom") + "T00:00:00"); 
		LocalDateTime to = LocalDateTime.parse(request.getParameter("dateTo") + "T00:00:00");
		
		
		ServletContext context = getServletContext();
        String contextPath = context.getRealPath("");
        
        ApartmentDAO apartmentDao = new ApartmentDAO(contextPath);
        Apartment apartment = apartmentDao.findByID(id);
        
        for (LocalDateTime date = from; date.isBefore(to); date = date.plusDays(1))
        {
            apartment.getAvailableDates().add(date);
        }
        
        apartmentDao.update(apartment);
		doGet(request, response);
	}

}
