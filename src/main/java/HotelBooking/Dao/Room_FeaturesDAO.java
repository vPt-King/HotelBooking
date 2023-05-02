package HotelBooking.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import HotelBooking.DB.DBconnect;

public class Room_FeaturesDAO {
	public void InsertNewRoom_Features(int room_id, int features_id)
	{
		Connection a=DBconnect.getJDBCConnection();
		String q = "insert into room_features(room_id,features_id) values(?,?)";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, room_id);
			ps.setInt(2, features_id);
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
		String q = "delete from room_features where room_id = ?";
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
	
	public void DeleteRoom_FeaturesByRoomId(int id)
	{
		Connection a = DBconnect.getJDBCConnection();
		String q = "delete from room_features where room_id = ? limit 100";
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
