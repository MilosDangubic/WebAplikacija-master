package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import beans.Apartment;
import beans.Gender;
import beans.Reservation;
import beans.Role;
import beans.StatusOfReservation;
import beans.User;

public class ReservationDAO {
private Map<Long, Reservation> reservation = new HashMap<>();
private String contextPath = "";
	
	
	public ReservationDAO() {
		
	}
	
	
	public ReservationDAO(String contextPath) {
		this.contextPath = contextPath;
		loadReservations(contextPath);
	}
	
	public Reservation findByID(Long id)
	{
		return reservation.get(id);
	}
	
	public void update(Reservation res) {
		
		reservation.put(res.getId(), res);
		
		saveReservation();
	}
	
	public void add(Reservation res) {
		
		Long id = nextId();
		
		res.setId(id);
		
		reservation.put(res.getId(), res);
		saveReservation();
	}
	
	public Collection<Reservation> findGuest(User user) {
		
		ArrayList<Reservation> result = new ArrayList<Reservation>();
		
		for(Reservation r: reservation.values()) {
			if(r.getGuest() != null && r.getGuest().getUsername().equals(user.getUsername())) {
				result.add(r);
			}
		}
		
		return result;
	}
	
	public Collection<Reservation> findHost(User user) {
		
		ArrayList<Reservation> result = new ArrayList<Reservation>();
		
		ApartmentDAO apartmentDAO = new ApartmentDAO(this.contextPath);
		
		for(Apartment a: apartmentDAO.findAll()) {
			
			if(a.getHost() != null && a.getHost().getUsername().equals(user.getUsername())) {
				
				for(Reservation r: reservation.values()) {
					if(a.getId().equals(r.getApartment().getId())) {
						result.add(r);
					}
				}
				
			}
		}
		
		return result;
	}

	public Collection<Reservation> findAll() {
	
		return reservation.values();
	}
	
	
	private void loadReservations(String contextPath) {
		BufferedReader in = null;
		
		UserDAO userDAO = new UserDAO(contextPath);
		ApartmentDAO apartmentDAO = new ApartmentDAO(contextPath);
		
		try {
			File file = new File(contextPath + "/reservation.txt");
			in = new BufferedReader(new FileReader(file));
			String line;
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					Long id = Long.valueOf(st.nextToken().trim());
					Long apartmentId = Long.valueOf(st.nextToken().trim());
					Apartment apartment = apartmentDAO.findByID(apartmentId);
					
					LocalDate firstDayOfReservation = LocalDate.parse(st.nextToken().trim());
					
					Integer numberOfNIghts = Integer.valueOf(st.nextToken().trim());
					Float finalPrice  = Float.valueOf(st.nextToken().trim());
					String messageAfterReservation = st.nextToken().trim();
					
					String userId = st.nextToken().trim();
					User user = userDAO.find(userId);
					
					StatusOfReservation status = StatusOfReservation.valueOf(st.nextToken().trim());
					
					reservation.put(id, new Reservation(apartment, firstDayOfReservation, numberOfNIghts, finalPrice, messageAfterReservation, user, status, id));
				}
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				}
				catch (Exception e) { }
			}
		}
	
	}
	private void saveReservation() {
		try {
		      FileWriter writer = new FileWriter(contextPath + "/reservation.txt");
		      System.out.println(contextPath + "/reservation.txt");
		      
		      for (Reservation r : reservation.values()) {
		    	  String line = "";
		    	  line += r.getId() + ";";
		    	  line += r.getApartment().getId() + ";";
		    	  line += r.getFirstDayOfReservation() + ";";
		    	  line += r.getNumberOfNIghts() + ";";
		    	  line += r.getFinalPrice()  + ";";
		    	  line += r.getMessageAfterReservation()  + ";";
		    	  line += r.getGuest().getUsername() + ";";
		    	  line += r.getStatus()+ "\n";
		    	  System.out.println(line);
		    	  writer.write(line);
		      }
		      
		      writer.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
private Long nextId() {
		
		Long id = 1L;
		
		for(Long currentId : reservation.keySet())
		{
			if(currentId > id) {
				id = currentId;
			}
		}
		
		return id + 1;
	}
}

