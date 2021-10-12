package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ApartmentComments;
import beans.ApartmentContent;
import dao.ApartmentCommentsDAO;
import dao.ApartmentDAO;

/**
 * Servlet implementation class AddCommentServlet
 */
@WebServlet("/AddCommentServlet")
public class AddCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("apartmentId"));
		
		request.setAttribute("apartmentId", id);
		
		RequestDispatcher disp = request.getRequestDispatcher("/JSP/AddComment.jsp");
        disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long apartmentId = Long.parseLong(request.getParameter("apartmentId"));
		String name = request.getParameter("name");
		String comment = request.getParameter("comment");
		Float rate = Float.parseFloat(request.getParameter("rate"));
		
		ServletContext context = getServletContext();
        String contextPath = context.getRealPath("");
        
        ApartmentDAO apratmentDAO = new ApartmentDAO(contextPath);
        ApartmentCommentsDAO comDAO = new ApartmentCommentsDAO(contextPath);
        
        ApartmentComments com = new ApartmentComments(name, apratmentDAO.findByID(apartmentId), comment, rate, false);
        
        comDAO.add(com);
        
        RequestDispatcher disp = request.getRequestDispatcher("/ReservationServlet");
        disp.forward(request, response);
	}

}
