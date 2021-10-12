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
import dao.ApartmentCommentsDAO;

/**
 * Servlet implementation class ChangeCommentServlet
 */
@WebServlet("/ChangeCommentServlet")
public class ChangeCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Long commentId = Long.parseLong(request.getParameter("commentId"));
		boolean status = Boolean.parseBoolean(request.getParameter("state"));
		
		ServletContext context = getServletContext();
        String contextPath = context.getRealPath("");
        
        ApartmentCommentsDAO comDAO = new ApartmentCommentsDAO(contextPath);
		ApartmentComments com = comDAO.findByID(commentId);
        com.setApproved(status);

        comDAO.update(com);
		
		RequestDispatcher disp = request.getRequestDispatcher("/CommentListServlet?apartmentId=" + com.getApartmant().getId());
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
