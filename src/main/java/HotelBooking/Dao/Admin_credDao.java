package HotelBooking.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import HotelBooking.DB.DBconnect;
import HotelBooking.Entity.Admin_cred;

public class Admin_credDao{
	public Admin_cred FindByAdmin_nameAndAdmin_pass(String name, String pass)
	{
		Admin_cred admin = null;
		Connection a = DBconnect.getJDBCConnection();
		String q = "select * from admin_cred where admin_name = ? and admin_pass = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setString(1,name);
			ps.setString(2,pass);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				admin = new Admin_cred(rs.getInt("id"),rs.getString("admin_name"),rs.getString("admin_pass"));
			}
			rs.close();
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return admin;
	}
}