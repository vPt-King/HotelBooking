package HotelBooking.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import HotelBooking.DB.DBconnect;
import HotelBooking.Entity.Facilities;
import HotelBooking.Entity.Features;

public class FacilitiesDAO {
	public void InsertFacilities(String icon, String name, String description)
	{
		Connection a = DBconnect.getJDBCConnection();
		String q = "insert into facilities(icon,name,description) values(?,?,?)";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setString(1, icon);
			ps.setString(2, name);
			ps.setString(3, description);
			int k = ps.executeUpdate();
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Facilities> GetFacilities()
	{
		List<Facilities> list = new ArrayList<>();
		Connection a = DBconnect.getJDBCConnection();
		String q = "select * from facilities";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				list.add(new Facilities(rs.getInt("id"),rs.getString("icon"),rs.getString("name"),rs.getString("description")));
			}
			rs.close();
			ps.close();
			a.close();
		} catch (SQLException e) {
			System.err.println("err in get all facilities");
			e.printStackTrace();
		}
		return list;
	}
	
	public void DeleteFacilities(Facilities qq)
	{
		Connection a = DBconnect.getJDBCConnection();
		String q = "delete from facilities where id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1,qq.getId());
			int k = ps.executeUpdate();
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public List<Facilities> GetListFacilitiesByRoomId(int room_id)
	{
		List<Facilities> list = new ArrayList<>();
		Connection a = DBconnect.getJDBCConnection();
		String q = "select facilities.id as id, facilities.name as name from facilities, room_facilities where room_facilities.room_id = ? and room_facilities.facilities_id = facilities.id ";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, room_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				list.add(new Facilities(rs.getInt("id"),rs.getString("name")));
			}
			rs.close();
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
}
