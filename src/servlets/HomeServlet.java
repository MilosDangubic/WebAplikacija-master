package servlets;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Apartment;
import dao.ApartmentCommentsDAO;
import dao.ApartmentDAO;
import dao.UserDAO;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String search = request.getParameter("search");
    	String sort = request.getParameter("sort");
    	Integer priceFrom = Integer.parseInt(request.getParameter("priceFrom") != null ? request.getParameter("priceFrom") : "0");
    	Integer priceTo = Integer.parseInt(request.getParameter("priceTo") != null ? request.getParameter("priceTo") : "500");
    	Integer roomFrom = Integer.parseInt(request.getParameter("roomFrom") != null ? request.getParameter("roomFrom") : "1");
    	Integer roomTo = Integer.parseInt(request.getParameter("roomTo") != null ? request.getParameter("roomTo") : "500");
    	Integer person = Integer.parseInt(request.getParameter("person") != null ? request.getParameter("person") : "5");
    	
		ServletContext context = getServletContext();
        String contextPath = context.getRealPath("");
        ApartmentDAO apartment = new ApartmentDAO(contextPath);
        ApartmentCommentsDAO comDAO = new ApartmentCommentsDAO(contextPath);
        
        Collection<Apartment> apartments = apartment.findActiveAndSearchFilter(search, sort, priceFrom, priceTo, roomFrom, roomTo, person);
        
        for(Apartment ap: apartments) {
        	ap.setComments(comDAO.findAllPublic(ap.getId()));
        }
        
        request.setAttribute("apartments", apartments);
        
        RequestDispatcher disp = request.getRequestDispatcher("/index.jsp");
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
