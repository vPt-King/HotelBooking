package HotelBooking.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import HotelBooking.DB.DBconnect;
import HotelBooking.Entity.Room_images;

public class Room_imageDAO {

	public int InsertNewImageRoom(int room_id, String photoName) {
		int k = 1;
		Connection a=DBconnect.getJDBCConnection();
		String q = "insert into room_images(room_id,image) values(?,?)";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, room_id);
			ps.setString(2, photoName);
			k = ps.executeUpdate();
			ps.close();
			a.close();
			
		} catch (SQLException e) {
			k = 0;
			e.printStackTrace();
		}
		return k;
	}

	public List<Room_images> GetRoomImagesById(int id) {
		List<Room_images> list = new ArrayList<>();
		Connection a=DBconnect.getJDBCConnection();
		String q = "select * from room_images where room_id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				list.add(new Room_images(rs.getInt("id"),rs.getInt("room_id"),rs.getString("image"),rs.getInt("thumb")));
			}
			rs.close();ps.close();a.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int DeleteRoomImages(Room_images ri)
	{
		int k = 1;
		Connection a=DBconnect.getJDBCConnection();
		String q = "delete from room_images where id = ? and room_id = ? limit 100";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, ri.getId());
			ps.setInt(2,ri.getRoom_id());
			k = ps.executeUpdate();
			ps.close();a.close();
		} catch (SQLException e) {
			e.printStackTrace();
			k = 0;
		}
		return k;
	}

	
	public int ChangeThumbRoomImages(Room_images ri) {
		int k = 1;
		Connection a=DBconnect.getJDBCConnection();
		String q = "update room_images set thumb = 1 where room_id = ? and id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, ri.getRoom_id());
			ps.setInt(2, ri.getId());
			k = ps.executeUpdate();
			ps.close();a.close();
		} catch (SQLException e) {
			e.printStackTrace();
			k = 0;
		}		
		return k;
	}

	public void SetOffThumbOfRoomImages(Room_images ri) {
		Connection a=DBconnect.getJDBCConnection();
		String q = "update room_images set thumb = ? where room_id = ? limit 100";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, 0);
			ps.setInt(2, ri.getRoom_id());
			int k = ps.executeUpdate();
			ps.close();a.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	public void DeleteRoomImageByRoomId(int id) {
		Connection a = DBconnect.getJDBCConnection();
		String q = "delete from room_images where room_id = ? limit 100";
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
