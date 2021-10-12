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

import beans.Gender;
import beans.Role;
import beans.User;


public class UserDAO {
	private Map<String, User> users = new HashMap<>();
	private String contextPath = "";
	
	public UserDAO() {
		
	}
	
	
	public UserDAO(String contextPath) {
		this.contextPath = contextPath;
		loadUsers(contextPath);
	}
	
	public void edit(User user) {
		users.replace(user.getUsername(), user);
		saveUsers();
	}
	

	public User find(String username, String password) {
		if (!users.containsKey(username)) {
			return null;
		}
		User user = users.get(username);
		if (!user.getPassword().equals(password)) {
			return null;
		}
		return user;
	}
	
	public Collection<User> search(String search) {
		
		ArrayList<User> result = new ArrayList<>();
		
		for(User user: users.values()) {
			
			if(user.getFirstName().contains(search) ||
					user.getLastName().contains(search)
					|| user.getEmail().contains(search))
			{
				result.add(user);
			}
		}
		
		return result;
	}
	
	public User find(String username) {
		
		return users.get(username);
	}
	
	public User save(User user) {
		if (users.containsKey(user.getUsername())) {
			return null;
		}	
		users.put(user.getUsername(), user);
		System.out.println(users.size());
		saveUsers();
		return user;
	}
	
	public Collection<User> findAll() {
		return users.values();
	}
	
	
	private void loadUsers(String contextPath) {
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "/users.txt");
			System.out.println(contextPath + "/users.txt");
			in = new BufferedReader(new FileReader(file));
			String line;
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					String firstName = st.nextToken().trim();
					String lastName = st.nextToken().trim();
					String email = st.nextToken().trim();
					String username = st.nextToken().trim();
					String password = st.nextToken().trim();
					Gender gender = Gender.valueOf(st.nextToken().trim());
					Role role = Role.valueOf(st.nextToken().trim());
					Boolean deleted = Boolean.valueOf(st.nextToken().trim());
					users.put(username, new User(firstName, lastName, email, username, password, gender, role, deleted));
					System.out.println(users.get(username));
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
	
	private void saveUsers() {
		try {
		      FileWriter writer = new FileWriter(contextPath + "/users.txt");
		      System.out.println(contextPath + "/users.txt");
		      
		      for (User u : users.values()) {
		    	  String line = "";
		    	  line += u.getFirstName() + ";";
		    	  line += u.getLastName() + ";";
		    	  line += u.getEmail() + ";";
		    	  line += u.getUsername()  + ";";
		    	  line += u.getPassword()  + ";";
		    	  line += u.getGender()  + ";";
		    	  line += u.getRole() + ";";
		    	  line += u.getDeleted() + "\n";
		    	  System.out.println(line);
		    	  writer.write(line);
		      }
		      
		      writer.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
}
