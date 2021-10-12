package servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Ameneties;
import dao.AmenetiesDAO;

/**
 * Servlet implementation class DeleteAmenetieServlet
 */
@WebServlet("/DeleteAmenetieServlet")
public class DeleteAmenetieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAmenetieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.valueOf(request.getParameter("amenetiesId"));
		
		ServletContext context = getServletContext();
        String contextPath = context.getRealPath("");
        
        AmenetiesDAO amenetiesDao = new AmenetiesDAO(contextPath);
        Ameneties ameneties = amenetiesDao.findByID(id);
        
        ameneties.setDeleted(true);
        
        amenetiesDao.update(ameneties);
        
        response.sendRedirect("/WebProgramiranje-master/AmenetiesListServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
