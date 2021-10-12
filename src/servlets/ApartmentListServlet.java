package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Role;
import beans.User;
import dao.ApartmentDAO;
import dao.UserDAO;

/**
 * Servlet implementation class ApartmentListServlet
 */
@WebServlet("/ApartmentListServlet")
public class ApartmentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApartmentListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher disp = request.getRequestDispatcher("/JSP/ApartmentList.jsp");
		String search = request.getParameter("search");
		String sort = request.getParameter("sort");
    	
		ServletContext context = getServletContext();
        String contextPath = context.getRealPath("");
        ApartmentDAO apartmentDAO = new ApartmentDAO(contextPath);
        
		request.setAttribute("apartments", apartmentDAO.findAll());
		
		HttpSession session = request.getSession(true);	    
        User user = (User)session.getAttribute("currentUser");
        
        if(user.getRole() == Role.GOST)
        {
        	request.setAttribute("apartments", apartmentDAO.findActiveAndSearch(search,sort));
        }
        else if(user.getRole() == Role.DOMACIN)
        {
        	request.setAttribute("apartments", apartmentDAO.userApartments(user, search,sort));
        }
        else
        {
        	request.setAttribute("apartments", apartmentDAO.findAll(search,sort));
        }
        
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
