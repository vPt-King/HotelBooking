package HotelBooking.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import HotelBooking.DB.DBconnect;
import HotelBooking.Entity.Features;

public class FeaturesDAO {
	public int InsertFeatures(Features uq)
	{
		Connection a = DBconnect.getJDBCConnection();
		String q= "insert into features(name) values(?)";
		int k = 1;
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setString(1, uq.getName());
			k = ps.executeUpdate();
			ps.close();
			a.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return k;
	}
	
	public int CheckIfUseFeature(Features fea)
	{
		int k  = 1;
		Connection a = DBconnect.getJDBCConnection();
		String q= "select * from room_features where features_id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, fea.getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				k = 0;
			}
			rs.close();
			ps.close();
			a.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return k;
	}
	
	public List<Features> GetFeatures()
	{
		List<Features> list = new ArrayList<>();
		Connection a = DBconnect.getJDBCConnection();
		String q = "select * from features";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				list.add(new Features(rs.getInt("id"),rs.getString("name")));
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
	
	public void DeleteFeturesById(Features qq)
	{
		Connection a = DBconnect.getJDBCConnection();
		String q = "delete from features where id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, qq.getId());
			int k = ps.executeUpdate();
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Features> GetListFeaturesByRoomId(int room_id)
	{
		List<Features> list = new ArrayList<>();
		Connection a = DBconnect.getJDBCConnection();
		String q = "select features.id as id, features.name as name from features, room_features where room_features.room_id = ? and room_features.features_id = features.id ";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, room_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				list.add(new Features(rs.getInt("id"),rs.getString("name")));
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

