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
import dao.AmenetiesDAO;

/**
 * Servlet implementation class AmenetiesServlet
 */
@WebServlet("/AddAmenetiesServlet")
public class AddAmenetiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAmenetiesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher disp = request.getRequestDispatcher("/JSP/AddAmeneties.jsp");
    	disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name  = String.valueOf(request.getParameter("name"));
		
		ServletContext context = getServletContext();
        String contextPath = context.getRealPath("");
        
        AmenetiesDAO amenetiesDao = new AmenetiesDAO(contextPath);
        
        Ameneties ameneties = new Ameneties();
        
        ameneties.setName(name);
        ameneties.setDeleted(false);
        ameneties = amenetiesDao.add(ameneties);
		
        response.sendRedirect("/WebProgramiranje-master/AmenetiesListServlet");
	}

}
