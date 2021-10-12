package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import beans.Address;
import beans.Ameneties;
import beans.Apartment;

public class AmenetiesDAO {
	private Map<Long, Ameneties> ameneties = new HashMap<>();
	private String contextPath = "";
	
	public AmenetiesDAO() {
		
	}
	public Ameneties add(Ameneties am) {
		
		Long id = nextId();
		am.setId(id);
		ameneties.put(am.getId(), am);
		
		saveAmeneties();
		
		
		return am;
	}
	public AmenetiesDAO(String contextPath) {
		this.contextPath = contextPath;
		loadAmeneties(contextPath);
	}
	
	public Collection<Ameneties> findAll() {
		ArrayList<Ameneties> result = new ArrayList<Ameneties>();
		for(Ameneties a : ameneties.values()) {
			if(!a.isDeleted()) {
				result.add(a);
			}
		}
		
		
		return result;
	}
	
	public Ameneties findByID(Long id) {
		return ameneties.get(id);
	}
	
	private Long nextId() {
		
		Long id = 1L;
		
		for(Long currentId : ameneties.keySet())
		{
			if(currentId > id) {
				id = currentId;
			}
		}
		
		return id + 1;
	}
	
	public void update(Ameneties amenetie) {
		
		ameneties.put(amenetie.getId(),amenetie);
		
		saveAmeneties();
	}
	
	private void saveAmeneties() {
		try {
			FileWriter writer = new FileWriter(contextPath + "/ameneties.txt");
			System.out.println(contextPath + "/ameneties.txt");
			
			for(Ameneties am : ameneties.values())
			{
				String line = "";
				line += am.getId() + ";";
				line += am.isDeleted() + ";";
				line += am.getName() + ";";
				System.out.println(line);
				writer.write(line);
			}
			
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	
	

	private void  loadAmeneties(String contextPath) {
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "/ameneties.txt");
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
					Boolean deleted = Boolean.valueOf(st.nextToken().trim());
					String name = String.valueOf(st.nextToken().trim());
					ameneties.put(id, new Ameneties(id,deleted,name));
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






}
