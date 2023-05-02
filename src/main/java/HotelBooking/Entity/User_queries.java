package HotelBooking.Entity;

import java.util.Date;

public class User_queries {
	private int id;
	private String name;
	private String email;
	private String subject;
	private String message;
	private String date;
	private int seen;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
//	public Date getDate() {
//		return date;
//	}
//	public void setDate(Date date) {
//		this.date = date;
//	}
	
	public int getSeen() {
		return seen;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setSeen(int seen) {
		this.seen = seen;
	}
	public User_queries(int id, String name, String email, String subject, String message, String date, int seen) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.subject = subject;
		this.message = message;
		this.date = date;
		this.seen = seen;
	}
	
}
