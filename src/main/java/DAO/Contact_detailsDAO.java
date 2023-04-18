package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DB.DBconnect;
import Model.Contact_details;

public class Contact_detailsDAO {
	
	public Contact_details Get_Contact_details_ById(int id)
	{
		Connection a = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Contact_details cd = null;
		try {
			a = DBconnect.getJDBCConnection();
			String q1 = "select * from contact_details";
			ps = a.prepareStatement(q1);
			rs = ps.executeQuery();
			if(rs.next())
			{
				cd = new Contact_details(rs.getInt("id"),rs.getString("address"),rs.getString("gmap"),rs.getString("pn1"),rs.getString("pn2"),rs.getString("email"),rs.getString("fb"),rs.getString("insta"),rs.getString("tw"),rs.getString("iframe"));
			}
			rs.close();
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cd;		
	}
	
	public int Update_Contact_Details_ById(int id,String address,String gmap,String pn1, String pn2, String email, String fb, String insta, String tw , String iframe )
	{
		
		Connection a = null;
		PreparedStatement ps = null;
		String q1 = "update contact_details set address = ?, gmap = ?, pn1 = ?, pn2 = ?, email = ?, fb = ?, insta = ?, tw = ?, iframe = ? where id = ?";
		try {
			a = DBconnect.getJDBCConnection();
			ps = a.prepareStatement(q1);
			ps.setString(1, address);
			ps.setString(2, gmap);
			ps.setString(3, pn1);
			ps.setString(4, pn2);
			ps.setString(5, email);
			ps.setString(6, fb);
			ps.setString(7, insta);
			ps.setString(8, tw);
			ps.setString(9, iframe);
			ps.setInt(10, id);
			int k = ps.executeUpdate();
			ps.close();
			a.close();
			if(k == 0)
			{
				throw new SQLException("something happen with sql");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return 0;
		}
		return 1;
	}
	
}
