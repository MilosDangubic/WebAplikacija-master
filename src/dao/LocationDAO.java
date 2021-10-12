package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import beans.Address;
import beans.Location;
import beans.Role;

public class LocationDAO {
	
private Map<Long, Location> locations = new HashMap<>();
private String contextPath = "";
	
	public LocationDAO() {
		
	}
	
	public LocationDAO(String contextPath) {
		this.contextPath = contextPath;
		loadLocations(contextPath);
	}
	
	public Location add(Location location) {
		
		Long id = nextId();
		
		location.setId(id);
		
		locations.put(id, location);
		
		saveLocation();
		
		return location;
		
	}
	
	public Location find(Long id) {
		return locations.get(id);
	}
	
	public Collection<Location> findAll() {
		return locations.values();
	}

	private void loadLocations(String contextPath) {
		BufferedReader in = null;
		
		AddressDAO addressDAO = new AddressDAO(contextPath);
		
		try {
			File file = new File(contextPath + "/locations.txt");
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
					Float lat = Float.valueOf(st.nextToken().trim());
					Float lng = Float.valueOf(st.nextToken().trim());
					Long addressId = Long.valueOf(st.nextToken().trim());
					locations.put(id, new Location(id, lat, lng, addressDAO.findByID(addressId)));
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
	private void saveLocation()
	{
		try {
			FileWriter writer = new FileWriter(contextPath + "/locations.txt");
			System.out.println(contextPath + "/locations.txt");
			for(Location lo : locations.values())
			{
				String line = "";
				line+= lo.getId() + ";";
				line+= lo.getLat() + ";";
				line+= lo.getLng() + ";";
				line+= lo.getAddress().getId() + "\n";
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
		
		for(Long currentId : locations.keySet())
		{
			if(currentId > id) {
				id = currentId;
			}
		}
		
		return id + 1;
	}

}
