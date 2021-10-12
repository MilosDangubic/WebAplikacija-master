package dao;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.sun.xml.internal.ws.util.StringUtils;

import beans.Ameneties;
import beans.Apartment;
import beans.ApartmentComments;
import beans.ApartmentContent;
import beans.ApartmentType;
import beans.Location;
import beans.Reservation;
import beans.Status;
import beans.User;

public class ApartmentDAO {
	
	private Map<Long, Apartment> apartments = new HashMap<>();
	private String contextPath = "";
	
	
	public ApartmentDAO() {
		
	}
	
	public Apartment add(Apartment apartment) {
		
		Long id = nextId();
		
		apartment.setId(id);
		apartments.put(id, apartment);
		
		saveApartments();
		
		return apartment;
	}
	
	public void update(Apartment apartment) {
		
		apartments.put(apartment.getId(), apartment);
		
		saveApartments();
	}
	
	public boolean isSearchOK(Apartment ap, String search) {
		
		if(search == null) {
			return true;
		}
		
		
		return ap.getLocation().getAddress().getCity().contains(search) ||
				ap.getHost().getFirstName().contains(search) || ap.getHost().getLastName().contains(search) ||
				ap.getLocation().getAddress().getStreet().contains(search);
	}
	
	public void sortApartments(List<Apartment> aparmnets, String sort) {
		
		if(sort == null)
		{
			return;
		}
		
		
		if(sort.equals("CITY")) {
			Collections.sort(aparmnets, new Comparator<Apartment>() {
			    @Override
			    public int compare(Apartment o1, Apartment o2) {
			        return o1.getLocation().getAddress().getCity().compareTo(o2.getLocation().getAddress().getCity());
			    }
			});
		}
		if(sort.equals("ADDRESS")) {
			Collections.sort(aparmnets, new Comparator<Apartment>() {
			    @Override
			    public int compare(Apartment o1, Apartment o2) {
			        return o1.getLocation().getAddress().getStreet().compareTo(o2.getLocation().getAddress().getStreet());
			    }
			});
		}
		if(sort.equals("FIRST_NAME")) {
			Collections.sort(aparmnets, new Comparator<Apartment>() {
			    @Override
			    public int compare(Apartment o1, Apartment o2) {
			        return o1.getHost().getFirstName().compareTo(o2.getHost().getFirstName());
			    }
			});
		}
		if(sort.equals("LAST_NAME")) {
			Collections.sort(aparmnets, new Comparator<Apartment>() {
			    @Override
			    public int compare(Apartment o1, Apartment o2) {
			        return o1.getHost().getLastName().compareTo(o2.getHost().getLastName());
			    }
			});
		}
	}
	
	
	
	public List<Apartment> findActiveAndSearchFilter(String search, String sort, Integer priceFrom, Integer priceTo,
			Integer roomFrom, Integer roomTo, Integer person)
	{
		List<Apartment> activeApartments = new ArrayList<Apartment>();
		for(Apartment ap : apartments.values())
		{
			if(ap.getStatus() != Status.ACTIVE || ap.isDeleted() == true)
			{
				continue;
			}
			
			if(ap.getPricePerNight() < priceFrom || ap.getPricePerNight() > priceTo) {
				continue;
			}
			
			if(ap.getRoomsCount() < roomFrom || ap.getRoomsCount() > roomTo) {
				continue;
			}
			
			if(ap.getGuestCount() != person) {
				continue;
			}
			
			if(isSearchOK(ap, search))
			{
				activeApartments.add(ap);	
			}
		}
		
		sortApartments(activeApartments, sort);
		
		return activeApartments;
	}
	
	public List<Apartment> findActiveAndSearch(String search, String sort)
	{
		List<Apartment> activeApartments = new ArrayList<Apartment>();
		for(Apartment ap : apartments.values())
		{
			if(ap.getStatus() != Status.ACTIVE || ap.isDeleted() == true)
			{
				continue;
			}
			
			if(isSearchOK(ap, search))
			{
				activeApartments.add(ap);	
			}
		}
		
		sortApartments(activeApartments, sort);
		
		return activeApartments;
	}
	
	
	public List<Apartment> userApartments(User u, String search, String sort)
	{
		List<Apartment> userApartments = new ArrayList<Apartment>();
		
		for(Apartment apU : apartments.values())
		{
			if(apU.getHost() != null && apU.getHost().getEmail().equals(u.getEmail()))
			{
				if(isSearchOK(apU, search))
				{
					userApartments.add(apU);	
				}
			}
		}
		
		sortApartments(userApartments, sort);
		
		return userApartments;
	}
	
	public ApartmentDAO(String contextPath) {
		this.contextPath = contextPath;
		loadApartmentss(contextPath);
	}
	
	public Collection<Apartment> findAll() {
		return findAll(null, null);
	}
	
	public Collection<Apartment> findAll(String search, String sort) {
		
		ArrayList<Apartment> result = new ArrayList<Apartment>();
		
		for(Apartment a: apartments.values()) {
			if(!a.isDeleted()) {
				
				if(isSearchOK(a, search))
				{
					result.add(a);	
				}
				
			}
		}
		
		
		sortApartments(result, sort);
		
		return result;
	}
	
	public Apartment findByID(Long id)
	{
		return apartments.get(id);
	}

	private void loadApartmentss(String contextPath) {
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "/apartments.txt");
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
					
					ApartmentType type = ApartmentType.valueOf(st.nextToken().trim());
					
					Integer roomsCount = Integer.valueOf(st.nextToken().trim());
					Integer guestCount = Integer.valueOf(st.nextToken().trim());
					
					Long locationId = Long.valueOf(st.nextToken().trim());
					
					String userEmail = st.nextToken().trim();
				
					Float pricePerNight = Float.valueOf(st.nextToken().trim());
					LocalTime checkInTime = LocalTime.parse(st.nextToken().trim());
					LocalTime checkOutTime = LocalTime.parse(st.nextToken().trim());
					
					Status status = Status.valueOf(st.nextToken().trim());
					
					LocationDAO locationDAO = new LocationDAO(this.contextPath);
					UserDAO userDAO = new UserDAO(this.contextPath);
					
					Location location = locationDAO.find(locationId);
					User user = userDAO.find(userEmail);
					
					boolean deleted = Boolean.parseBoolean(st.nextToken().trim());
					
					StringTokenizer stt;
					stt = new StringTokenizer(st.nextToken().trim(), "|");
					List<LocalDateTime> dates = new ArrayList<LocalDateTime>();
					while (stt.hasMoreTokens()) {
						String dateString = stt.nextToken();
						
						if(dateString.equals("")) {
							continue;
						}
						
 						dates.add(Instant.ofEpochMilli(Long.parseLong(dateString)).atZone(ZoneId.systemDefault()).toLocalDateTime());
					}
					
					StringTokenizer sttt;
					sttt = new StringTokenizer(st.nextToken().trim(), "|");
					ArrayList<Ameneties> ameneties = new ArrayList<Ameneties>();
					AmenetiesDAO amenetiesDAO = new AmenetiesDAO(this.contextPath);
					while (sttt.hasMoreTokens()) {
						String amString = sttt.nextToken();
						
						if(amString.equals("")) {
							continue;
						}
						
						Long idA = Long.parseLong(amString);
						
 						ameneties.add(amenetiesDAO.findByID(idA));
					}
					
					Apartment apartment = new Apartment();
					
					apartment.setId(id);
					apartment.setType(type);
					apartment.setRoomsCount(roomsCount);
					apartment.setGuestCount(guestCount);
					apartment.setLocation(location);
					apartment.setPricePerNight(pricePerNight);
					apartment.setCheckInTime(checkInTime);
					apartment.setCheckOutTime(checkOutTime);
					apartment.setStatus(status);
					apartment.setHost(user);
					apartment.setDeleted(deleted);
					apartment.setAvailableDates(dates);
					apartment.setAmenities(ameneties);
					apartments.put(id, apartment);
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
	private void saveApartments()
	{
		try {
			FileWriter writer = new FileWriter(contextPath + "/apartments.txt");
			System.out.println(contextPath + "/apartments.txt");
			
			for(Apartment ap : apartments.values())
			{
				String line = "";
				line+=ap.getId() + ";";
				line+=ap.getType() + ";";
				line+=ap.getRoomsCount() + ";";
				line+=ap.getGuestCount() + ";";
				line+=ap.getLocation().getId() + ";";
				line+=ap.getHost().getUsername() + ";";
				line+=ap.getPricePerNight() + ";";
				line+=ap.getCheckInTime() + ";";
				line+=ap.getCheckOutTime() + ";";
				line+=ap.getStatus() + ";";
				line+=ap.isDeleted() + ";";
				
				String availableDate = "";
				
				for(LocalDateTime date: ap.getAvailableDates()) {
					
					availableDate += date.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli() + "|";
				}
				
				if(availableDate.equals("")) {
					availableDate = "|";	
				}
				
				line+= availableDate + ";";
				
				
				String amenetiesString = "";
				
				if(ap.getAmenities() != null) {
					for(Ameneties am: ap.getAmenities()) {
						amenetiesString += am.getId() + "|";
					}	
				}
				
				
				if(amenetiesString.equals("")) {
					amenetiesString = "|";	
				}
				
				line+= amenetiesString + ";";
				
				System.out.println(line);
				writer.write(line);
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
private Long nextId() {
		
		Long id = 1L;
		
		for(Long currentId : apartments.keySet())
		{
			if(currentId > id) {
				id = currentId;
			}
		}
		
		return id + 1;
	}
}
