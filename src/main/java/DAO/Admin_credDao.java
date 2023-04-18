package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.annotation.Bean;

import DB.DBconnect;
import Model.Admin_cred;
public class Admin_credDao {
	public static Admin_cred GetAdminByName(String name)
	{
		Admin_cred admin = null;
		Connection a = DBconnect.getJDBCConnection();
		String query = "select * from admin_cred where admin_name = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = a.prepareStatement(query);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if(rs.next())
			{
				admin = new Admin_cred(rs.getInt("id"),rs.getString("admin_name"),rs.getString("admin_pass"));
			}
			a.close();
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return admin;
	}
}
