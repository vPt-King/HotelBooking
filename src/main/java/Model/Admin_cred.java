package Model;

public class Admin_cred {
	private int id;
	private String admin_name;
	private String admin_pass;
	
	
	public Admin_cred(int id, String admin_name, String admin_pass) {
		this.id = id;
		this.admin_name = admin_name;
		this.admin_pass = admin_pass;
	}
	
	
	public Admin_cred(String admin_name, String admin_pass) {
		this.admin_name = admin_name;
		this.admin_pass = admin_pass;
	}


	public Admin_cred() {
	
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getAdmin_pass() {
		return admin_pass;
	}
	public void setAdmin_pass(String admin_pass) {
		this.admin_pass = admin_pass;
	}
	
	
	
}
