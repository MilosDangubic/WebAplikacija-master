package servlets;

import java.io.IOException;

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
 * Servlet implementation class DeleteApartmentAmenetieServlet
 */
@WebServlet("/DeleteApartmentAmenetieServlet")
public class DeleteApartmentAmenetieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteApartmentAmenetieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long idAm = Long.valueOf(request.getParameter("amenetiesId"));
		Long idA = Long.valueOf(request.getParameter("apartmentId"));
		
		ServletContext context = getServletContext();
        String contextPath = context.getRealPath("");
        
        ApartmentDAO apartmentDao = new ApartmentDAO(contextPath);
        AmenetiesDAO amenetiesDao = new AmenetiesDAO(contextPath);
        Ameneties ameneties = amenetiesDao.findByID(idAm);
        Apartment apartment = apartmentDao.findByID(idA);
        
        apartment.removeAmenities(ameneties);
        amenetiesDao.update(ameneties);
        apartmentDao.update(apartment);
        
        request.setAttribute("apartmentId", idA);
        RequestDispatcher disp = request.getRequestDispatcher("/ApartmentAmenetiesServlet");
    	disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
