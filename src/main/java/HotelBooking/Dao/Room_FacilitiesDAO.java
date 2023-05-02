package HotelBooking.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import HotelBooking.DB.DBconnect;

public class Room_FacilitiesDAO {
	public void InsertNewRoom_Facilities(int room_id, int facilities_id)
	{
		Connection a=DBconnect.getJDBCConnection();
		String q = "insert into room_facilities(room_id,facilities_id) values(?,?)";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, room_id);
			ps.setInt(2, facilities_id);
			int k = ps.executeUpdate();
			ps.close();
			a.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void DeleteOldOption(int room_id)
	{
		Connection a=DBconnect.getJDBCConnection();
		String q = "delete from room_facilities where room_id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, room_id);
			int k = ps.executeUpdate();
			ps.close();
			a.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void DeleteRoom_FacilitiesByRoomId(int id) {
		Connection a = DBconnect.getJDBCConnection();
		String q = "delete from room_facilities where room_id = ? limit 100";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1,id);
			int k = ps.executeUpdate();
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
