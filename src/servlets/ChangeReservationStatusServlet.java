package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Reservation;
import beans.StatusOfReservation;
import dao.ReservationDAO;

/**
 * Servlet implementation class ChangeReservationStatusServlet
 */
@WebServlet("/ChangeReservationStatusServlet")
public class ChangeReservationStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeReservationStatusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long reservationId = Long.parseLong(request.getParameter("reservationId"));
		StatusOfReservation status = StatusOfReservation.valueOf(request.getParameter("status"));
		
		ServletContext context = getServletContext();
        String contextPath = context.getRealPath("");
        ReservationDAO reservationDAO = new ReservationDAO(contextPath);
        
        Reservation reservation = reservationDAO.findByID(reservationId);
        reservation.setStatus(status);
        
		reservationDAO.update(reservation);
		
		RequestDispatcher disp = request.getRequestDispatcher("/ReservationServlet");
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
