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
import beans.ApartmentContent;

public class AddressDAO {
	private Map<Long, Address> address = new HashMap<>();
	private String contextPath = "";
	public AddressDAO() {
		
	}
	
	public Address add(Address adr) {
		
		Long id = nextId();
		adr.setId(id);
		address.put(adr.getId(), adr);
		
		saveAdress();
		
		return adr;
	}
	
	public AddressDAO(String contextPath) {
		this.contextPath = contextPath;
		loadAddress(contextPath);
	}
	
	public Collection<Address> findAll() {
		return address.values();
	}
	
	public Address findByID(Long id) {
		return address.get(id);
	}

	private void loadAddress(String contextPath) {
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "/address.txt");
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
					String street = st.nextToken().trim();
					Integer number = Integer.valueOf(st.nextToken().trim());
					String city = st.nextToken().trim();
					Integer zipCode = Integer.valueOf(st.nextToken().trim());
					address.put(id, new Address(id,street,number,city,zipCode));
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
	private void saveAdress()
	{
		try {
			FileWriter writer = new FileWriter(contextPath + "/address.txt");
			System.out.println(contextPath + "/address.txt");
			
			for(Address ad : address.values())
			{
				String line = "";
				line += ad.getId() + ";";
				line += ad.getStreet() + ";";
				line += ad.getNumber() + ";";
				line += ad.getCity() + ";";
				line += ad.getZipCode() + "\n";
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
		
		for(Long currentId : address.keySet())
		{
			if(currentId > id) {
				id = currentId;
			}
		}
		
		return id + 1;
	}
}
