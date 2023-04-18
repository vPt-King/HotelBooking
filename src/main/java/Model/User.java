package Model;

public class User {
	private int id;
	private String name;
	private String password;
	private int isAdmin;
	public User() {
		
	}
	public User(int id, String name, String password,int isAdmin) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.isAdmin = isAdmin;
	}
	
	public User(String name, String password, int isAdmin) {
		this.name = name;
		this.password = password;
		this.isAdmin = isAdmin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	
}
