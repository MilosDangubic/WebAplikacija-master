package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Gender;
import beans.Role;
import beans.User;
import dao.UserDAO;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public RegisterServlet() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	ServletContext context = getServletContext();
    	String contextPath = context.getRealPath("");
    	context.setAttribute("users", new UserDAO(contextPath));
    }
    /***
     * Preusmerava korisnika na login stranicu.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RequestDispatcher disp = request.getRequestDispatcher("/JSP/register.jsp");
    	disp.forward(request, response);
    }
    
    /***
     * Prihvata korisnièko ime i lozinku iz forme i pokušava da uloguje korisnika. 
     * Pri neuspešnom loginu preusmerava korisnika nazad na login stranicu, sa porukom greške.
     */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String password1 = request.getParameter("password1");
        String email = request.getParameter("email");
        Gender gender = Gender.valueOf(request.getParameter("gender"));
        
        RequestDispatcher disp = request.getRequestDispatcher("/JSP/register.jsp");
   
        if (!password.equals(password1)) {
        	request.setAttribute("error", "Passwords don't match");
        	disp.forward(request, response);
        }
        
        ServletContext context = getServletContext();
        UserDAO userDAO= (UserDAO)context.getAttribute("users");
        
        User user = new User(firstName, lastName, email, username, password, gender, Role.GOST, false);
        User savedUser = userDAO.save(user);
        
        if (savedUser == null) {
        	request.setAttribute("error", "Username already exists");
        	disp.forward(request, response);
        }

        response.sendRedirect("index.jsp"); 

	}
}
