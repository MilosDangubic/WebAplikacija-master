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
import dao.ReservationDAO;

/**
 * Servlet implementation class ReservationServlet
 */
@WebServlet("/ReservationServlet")
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext context = getServletContext();
        String contextPath = context.getRealPath("");
        ReservationDAO reservationDAO = new ReservationDAO(contextPath);
		
		HttpSession session = request.getSession(true);	    
        User user = (User)session.getAttribute("currentUser");
        
        if(user.getRole() == Role.GOST)
        {
        	request.setAttribute("reservations", reservationDAO.findGuest(user));
        }
        else if(user.getRole() == Role.DOMACIN)
        {
        	request.setAttribute("reservations", reservationDAO.findHost(user));
        }
        else
        {
        	request.setAttribute("reservations", reservationDAO.findAll());
        }
		
		RequestDispatcher disp = request.getRequestDispatcher("/JSP/ReservationList.jsp");
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
