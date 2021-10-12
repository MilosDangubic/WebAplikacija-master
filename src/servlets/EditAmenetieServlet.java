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
 * Servlet implementation class EditAmenetieServlet
 */
@WebServlet("/EditAmenetieServlet")
public class EditAmenetieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditAmenetieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher disp = request.getRequestDispatcher("/JSP/EditAmenetie.jsp");
		
		Long id = Long.parseLong(request.getParameter("amenetiesId"));
		
		ServletContext context = getServletContext();
        String contextPath = context.getRealPath("");
        
        AmenetiesDAO amenetiesDao = new AmenetiesDAO(contextPath);
        Ameneties ameneties = amenetiesDao.findByID(id);
		
        request.setAttribute("ameneties", ameneties);
        
    	disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name  = String.valueOf(request.getParameter("name"));
		Long id = Long.valueOf(request.getParameter("amenetiesId"));
	
		ServletContext context = getServletContext();
        String contextPath = context.getRealPath("");
        
        AmenetiesDAO amenetiesDao = new AmenetiesDAO(contextPath);
        
        Ameneties ameneties = amenetiesDao.findByID(id);
        
        ameneties.setName(name);
        
        amenetiesDao.update(ameneties);
		
        response.sendRedirect("/WebProgramiranje-master/AmenetiesListServlet");
        
        
	}

}
