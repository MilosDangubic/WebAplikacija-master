package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Ameneties;
import beans.Apartment;
import dao.AmenetiesDAO;
import dao.ApartmentDAO;

/**
 * Servlet implementation class ApartmentAmenetiesServlet
 */
@WebServlet("/ApartmentAmenetiesServlet")
public class ApartmentAmenetiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApartmentAmenetiesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long id = Long.parseLong(request.getParameter("apartmentId"));
		
		ServletContext context = getServletContext();
        String contextPath = context.getRealPath("");
        
        AmenetiesDAO amenetiesDao = new AmenetiesDAO(contextPath);
        ApartmentDAO apartmentDao = new ApartmentDAO(contextPath);
        
        
        request.setAttribute("ameneties", amenetiesDao.findAll());
        request.setAttribute("apartment", apartmentDao.findByID(id));
        
        RequestDispatcher disp = request.getRequestDispatcher("/JSP/ApartmentAmeneties.jsp");
        disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long idA = Long.valueOf(request.getParameter("apartmentId"));
		Long idAm = Long.valueOf(request.getParameter("amenetieId"));
		
		ServletContext context = getServletContext();
        String contextPath = context.getRealPath("");
        
        AmenetiesDAO amenetiesDao = new AmenetiesDAO(contextPath);
        ApartmentDAO apartmentDao = new ApartmentDAO(contextPath);
        
		Apartment apartment = apartmentDao.findByID(idA);
		
		Ameneties ameneties  = amenetiesDao.findByID(idAm);
        
		apartment.addAmenities(ameneties);
		
		apartmentDao.update(apartment);
		
		request.setAttribute("apartmentId", idA);
		doGet(request, response);
	}

}
