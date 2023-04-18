package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DB.DBconnect;
import Model.Settings;

public class SettingsDAO {
	public static Settings GetSettingsById(int id)
	{
		Settings settings = null;
		Connection a = DBconnect.getJDBCConnection();
		String query = "select * from settings where id = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = a.prepareStatement(query);
			ps.setInt(1,id);
			rs = ps.executeQuery();
			if(rs.next())
			{
				settings = new Settings(rs.getInt(id),rs.getString("site_title"),rs.getString("site_about"),rs.getInt("shutdown"));
			}
			rs.close();
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return settings;
	}
	
	
	
	
	public static void Update_Settings_SiteTitle_SiteAbout_ById(int id,String site_title, String site_about)
	{
		Connection a = DBconnect.getJDBCConnection();
		String query = "update settings set site_title = ? , site_about = ? where id = ?";
		PreparedStatement ps = null;
		try {
			ps = a.prepareStatement(query);
			ps.setString(1, site_title);
			ps.setString(2, site_about);
			ps.setInt(3, id);
			int k = ps.executeUpdate();
			ps.close();
			a.close();
		} catch (SQLException e) {
			System.err.println("Trong Update_Settings_SiteTitle_SiteAbout_ById");
		}
	}
	
	
	public static void Update_Settings_Shutdown_ById(int id, int shutdown) {
		Connection a = DBconnect.getJDBCConnection();
		String query = "update settings set shutdown = ? where id = ?";
		PreparedStatement ps = null;
		try {
			ps = a.prepareStatement(query);
			ps.setInt(1, shutdown);
			ps.setInt(2, id);
			int k = ps.executeUpdate();
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
