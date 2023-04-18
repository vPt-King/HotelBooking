package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import DB.DBconnect;
import Model.Team_details;

public class Team_detailsDAO {   
	public List<Team_details> Get_All_Members()
	{
		List<Team_details> list = new ArrayList<>();
		Connection a = null;
		PreparedStatement ps = null;
		String q = "select * from team_details";
		try {
			a = DBconnect.getJDBCConnection();
			ps = a.prepareStatement(q);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				list.add(new Team_details(rs.getInt("id"),rs.getString("name"),rs.getString("picture")));
			}
			rs.close();
			ps.close();
			a.close();
		}catch (SQLException e) {
            System.err.println("wrong in sql select from team_details");
        }
		return list;
	}
	
	public void Delete_Team_Details(int id)
	{
		Connection a = null;
		PreparedStatement ps = null;
		String q = "delete from team_details where id = ?";
		try {
			a = DBconnect.getJDBCConnection();
			ps = a.prepareStatement(q);
			ps.setInt(1,id);
			int k = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("delete error");
		}
		
	}
    
    public void Insert_Team_details( String name, String picture) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO team_details (name, picture) VALUES (?, ?)";
        try {
            conn = DBconnect.getJDBCConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, picture);
            int k = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
}
