package HotelBooking.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import HotelBooking.DB.DBconnect;
import HotelBooking.Entity.Rooms;

public class RoomsDAO {
	public void InsertNewRoom(Rooms r)
	{
		Connection a=DBconnect.getJDBCConnection();
		String q = "insert into rooms(name,area,price,quantity,adult,children,description) values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setString(1, r.getName());
			ps.setInt(2, r.getArea());
			ps.setInt(3, r.getPrice());
			ps.setInt(4, r.getQuantity());
			ps.setInt(5, r.getAdult());
			ps.setInt(6, r.getChildren());
			ps.setString(7, r.getDescription());
			int k = ps.executeUpdate();
			ps.close();
			a.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int FindRoom_idByName(String name)
	{
		int k = 0;
		Connection a=DBconnect.getJDBCConnection();
		String q = "select * from rooms where name = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				k = rs.getInt("id");
			}
			rs.close();
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k;
	}
	public List<Rooms> GetAllRooms()
	{
		List<Rooms> list = new ArrayList<>();
		Connection a=DBconnect.getJDBCConnection();
		String q = "select * from rooms where removed = 0 and status = 1";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Rooms tmp = new Rooms(rs.getInt("id"),rs.getString("name"),rs.getInt("area"),rs.getInt("price"),rs.getInt("quantity"),rs.getInt("adult"),rs.getInt("children"),rs.getString("description"),rs.getInt("status"));
				list.add(tmp);
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
	public void ChangeStatusById(Rooms room)
	{
		Connection a=DBconnect.getJDBCConnection();
		String q = "update rooms set status = ? where id = ?";
		try {

			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, room.getStatus());
			ps.setInt(2,room.getId());
			int k = ps.executeUpdate();
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Rooms GetRoomById(Rooms r)
	{
		Rooms room = null;
		Connection a=DBconnect.getJDBCConnection();
		String q = "select * from rooms where id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, r.getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				room = new Rooms(rs.getInt("id"),rs.getString("name"),rs.getInt("area"),rs.getInt("price"),rs.getInt("quantity"),rs.getInt("adult"),rs.getInt("children"),rs.getString("description"),rs.getInt("status"));
			}
			rs.close();
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return room;
	}
	
	public int UpdateRoomById(Rooms r)
	{
		int k = 0;
		Rooms room = null;
		Connection a= DBconnect.getJDBCConnection();
		String q = "update rooms set name = ?, area = ? , price = ?, quantity = ? , adult = ?, children = ?, description = ? where id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setString(1, r.getName());
			ps.setInt(2, r.getArea());
			ps.setInt(3, r.getPrice());
			ps.setInt(4, r.getQuantity());
			ps.setInt(5, r.getAdult());
			ps.setInt(6, r.getChildren());
			ps.setString(7, r.getDescription());
			ps.setInt(8, r.getId());
			k = ps.executeUpdate(); 
			System.out.println(r.getId());
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k;
	}
	public int SetRemovedRoomById(int id) {
		int k = 1;
		Connection a = DBconnect.getJDBCConnection();
		String q = "update rooms set removed = 1 where id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1,id);
		    k = ps.executeUpdate();
			ps.close();
			a.close();
		} catch (SQLException e) {
			k = 0;
			e.printStackTrace();
		}
		return k;
	}
}
