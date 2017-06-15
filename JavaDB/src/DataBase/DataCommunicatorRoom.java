package DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Logics.Globals;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DataCommunicatorRoom {

	private static DataCommunicatorRoom MainLogicInctance;

	private ResultSet result;
	
	private Connection con; 
	
	public DataCommunicatorRoom(Connection con) throws ClassNotFoundException, SQLException {
		this.con = con;
	}
	
	public void insertRoom(String name,int maxPersons,float hCost,float cost) throws SQLException, ClassNotFoundException{
		PreparedStatement posted = (PreparedStatement) con.prepareStatement("INSERT INTO rooms (Name,maxPersons,hCost,cost) VALUES ('"+name+"','"+maxPersons+"','"+hCost+"','"+cost+"')");
			
		posted.execute();
	}
	
	public void deleteRoom(int id) throws ClassNotFoundException, SQLException{
		PreparedStatement posted = (PreparedStatement) con.prepareStatement("DELETE FROM rooms WHERE id='"+id+"'");
		
		posted.execute();
}

	public Room displayRoom(int id) throws ClassNotFoundException, SQLException{
		
		String name = null;
		int maxPersons = 0;
		float hCost = 0,cost = 0;
		
		PreparedStatement posted = (PreparedStatement) con.prepareStatement("SELECT * FROM rooms WHERE id='"+id+"'");
		result = posted.executeQuery();
		while(result.next()){
			name = result.getString("name");
			maxPersons = result.getInt("maxPersons");
			hCost = result.getFloat("hCost");
			cost = result.getFloat("cost");
		}
		return new Room(name,maxPersons,hCost,cost,id);
	}
	
	public Room[] displayAllRooms() throws ClassNotFoundException, SQLException{
		
		ArrayList<Room> rooms = new ArrayList<Room>(); 
		
		String name = null;
		int maxPersons = 0,roomId;
		float hCost = 0,cost = 0;
		
		PreparedStatement posted = (PreparedStatement) con.prepareStatement("SELECT * FROM rooms");
		result = posted.executeQuery();
		while (result.next()){
			name = result.getString("name");
			maxPersons = result.getInt("maxPersons");
			hCost = result.getFloat("hCost");
			cost = result.getFloat("cost");
			roomId = result.getInt("id");
			Room temp =  new Room(name,maxPersons,hCost,cost,roomId);
			rooms.add(temp);
		}
		return rooms.toArray(new Room[rooms.size()]);
	}
	
	public void updateRoom(String name,int maxPersons,float hCost,float cost,int id) throws ClassNotFoundException, SQLException{
		PreparedStatement posted = (PreparedStatement) con.prepareStatement("UPDATE rooms SET Name = '"+name+"',maxPersons = '"+maxPersons+"',hCost = '"+hCost+"',cost = '"+cost+"' WHERE id="+id+"");
			
		posted.execute();
	}
	
	public int getRoomIDByName(String name)  throws ClassNotFoundException, SQLException{
		
		int maxPersons = 0;
		
		PreparedStatement posted = (PreparedStatement) con.prepareStatement("SELECT * FROM rooms WHERE name='"+name+"'");
		result = posted.executeQuery();
		while(result.next()){
			return result.getInt("id");
		}
		return 0;
	}
	
	public static DataCommunicatorRoom getInctance() throws ClassNotFoundException, SQLException {
	     if (MainLogicInctance == null)
	    	 MainLogicInctance = new DataCommunicatorRoom(new Connector(Globals.USERNAME,Globals.PASSWORD).getConnection());
	     return MainLogicInctance;
	}
}
