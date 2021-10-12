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
import beans.Reservation;
import beans.ApartmentContent;

public class ApartmentContentDAO {
	private Map<Long, ApartmentContent> aContent = new HashMap<>();
	private String contextPath = "";
	public ApartmentContentDAO() {
		
	}
	
	public ApartmentContentDAO(String contextPath) {
		this.contextPath = contextPath;
		loadApartmentContents(contextPath);
	}
	
	public Collection<ApartmentContent> findAll() {
		return aContent.values();
	}

	private void loadApartmentContents(String contextPath) {
		BufferedReader in = null;
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
					String content = st.nextToken().trim();
					aContent.put(id, new ApartmentContent(id, content));
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
	private void saveApartmentContent()
	{
		try {
			FileWriter writer = new FileWriter(contextPath + "/aContent.txt");
			System.out.println(contextPath + "/aContent.txt");
			
			for(ApartmentContent aC : aContent.values())
			{
				String line = "";
				line+=aC.getId() + ";";
				line+=aC.getContent() + "\n";
				System.out.println(line);
				writer.write(line);
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
