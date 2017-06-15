package DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Interface.TablePanel;
import Logics.Globals;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DataCommunicatorBooking {

	private static DataCommunicatorBooking MainLogicInctance;

	private ResultSet result;
	
	private Connection con;

	
	public DataCommunicatorBooking(Connection con) throws ClassNotFoundException, SQLException {
		this.con = con;
	}
		
	public void insertBooking(String name,String surname,int persons,String begindate,String enddate, int roomID,boolean confirmed) throws ClassNotFoundException, SQLException{
		PreparedStatement posted = (PreparedStatement) con.prepareStatement("INSERT INTO bookings (Name,Surname,Persons,begindate,enddate,roomID,confirmed) VALUES ('"+name+"','"+surname+"','"+persons+"','"+begindate+"','"+enddate+"','"+roomID+"','"+getValue(confirmed)+"')");
			
		posted.execute();
	}

	public void deleteBooking(int id) throws ClassNotFoundException, SQLException{
		PreparedStatement posted = (PreparedStatement) con.prepareStatement("DELETE FROM bookings WHERE idBooking='"+id+"'");
		
		posted.execute();
}

	public Booking displayBooking(int id) throws ClassNotFoundException, SQLException{
		String name = null,surname = null,begindate = null,enddate = null;
		int persons = 0,roomID = 0;
		boolean confirmed = false;
		
		PreparedStatement posted = (PreparedStatement) con.prepareStatement("SELECT * FROM bookings WHERE idBooking='"+id+"'");
		result = posted.executeQuery();
		while(result.next()){
			begindate = result.getString("begindate");
			enddate = result.getString("enddate");
			name = result.getString("Name");
			surname = result.getString("Surname");
			persons = result.getInt("persons");
			roomID = result.getInt("roomId");
			confirmed = getBool(result.getInt("confirmed"));
		}
		return new Booking(begindate,enddate,name,surname,persons,roomID,id,confirmed);
	}

	public Booking[] displayAllBookings() throws ClassNotFoundException, SQLException{
		
		ArrayList<Booking> bookings = new ArrayList<Booking>(); 
		
		String name = null,surname = null,begindate = null,enddate = null;
		int persons = 0,roomID = 0,id=0;
		boolean confirmed = false;
	
		PreparedStatement posted = (PreparedStatement) con.prepareStatement("SELECT * FROM bookings");
		result = posted.executeQuery();
		while (result.next()){
			begindate = result.getString("begindate");
			enddate = result.getString("enddate");
			name = result.getString("Name");
			surname = result.getString("Surname");
			persons = result.getInt("persons");
			roomID = result.getInt("roomID");
			id = result.getInt("idBooking");
			confirmed = getBool(result.getInt("confirmed"));
			Booking temp =  new Booking(begindate,enddate,name,surname,persons,roomID,id,confirmed);
			bookings.add(temp);
		}
		return bookings.toArray(new Booking[bookings.size()]);
	}
	
	public void updateBooking(String name,String surname,int persons,String begindate,String enddate,int id,int roomID,boolean confirmed) throws ClassNotFoundException, SQLException{
		PreparedStatement posted = (PreparedStatement) con.prepareStatement("UPDATE bookings SET Name = '"+name+"',Surname = '"+surname+"',Persons = '"+persons+"',begindate= '"+begindate+"',enddate = '"+enddate+"',roomID = '"+roomID+"',confirmed = '"+getValue(confirmed)+"' WHERE idBooking="+id+"");
		posted.execute();
	}
	
	public void deleteAllByRoomID(int roomID) throws SQLException {
		PreparedStatement posted = (PreparedStatement) con.prepareStatement("DELETE FROM bookings WHERE roomID='"+roomID+"'");
		
		posted.execute();
	}

	public Booking displayBookingByDateAndRoom(int roomID, int dateNumber) throws SQLException {
		ArrayList<Booking> bookings = new ArrayList<Booking>(); 
		
		String name = null,surname = null,begindate = null,enddate = null;
		int persons = 0,id=0;
		boolean confirmed = false;
		
		PreparedStatement posted = (PreparedStatement) con.prepareStatement("SELECT * FROM bookings WHERE roomID='"+roomID+"'");
		System.out.println("ho"+ Integer.toString(roomID)+"|"+Integer.toString(dateNumber));
		result = posted.executeQuery();
		while (result.next()){
			begindate = result.getString("begindate");
			enddate = result.getString("enddate");
			name = result.getString("Name");
			surname = result.getString("Surname");
			persons = result.getInt("persons");
			id = result.getInt("idBooking");
			confirmed = getBool(result.getInt("confirmed"));
			Booking temp =  new Booking(begindate,enddate,name,surname,persons,roomID,id,confirmed);
    		System.out.println("hey");
			bookings.add(temp);
		}
		
		for(Booking booking : bookings){
			if(getDateNumber(booking.getBegin())<dateNumber && getDateNumber(booking.getEnd())>dateNumber){
				return booking;
			}
		}
		return null;
		
	}
	
	private int getDateNumber(String dateString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date  = LocalDate.parse(dateString, formatter);
		return date.getDayOfYear();
	}

	private String getValue(boolean confirmed) {
		if(confirmed)
			return "1";
		return "0";
	}
	
	private boolean getBool(int confirmed) {
		if(confirmed!=0)
			return true;
		return false;
	}

	public Booking[] displayBookingByRoomID(int roomID) throws SQLException {
		
		ArrayList<Booking> bookings = new ArrayList<Booking>(); 
		
		String name = null,surname = null,begindate = null,enddate = null;
		int persons = 0,id=0;
		boolean confirmed = false;
		
		PreparedStatement posted = (PreparedStatement) con.prepareStatement("SELECT * FROM bookings WHERE roomID='"+roomID+"'");
		result = posted.executeQuery();
		while (result.next()){
			begindate = result.getString("begindate");
			enddate = result.getString("enddate");
			name = result.getString("Name");
			surname = result.getString("Surname");
			persons = result.getInt("persons");
			roomID = result.getInt("roomID");
			id = result.getInt("idBooking");
			confirmed = getBool(result.getInt("confirmed"));
			Booking temp =  new Booking(begindate,enddate,name,surname,persons,roomID,id,confirmed);
			bookings.add(temp);
		}
		return bookings.toArray(new Booking[bookings.size()]);
	}
	
	public static DataCommunicatorBooking getInctance() throws ClassNotFoundException, SQLException {
	     if (MainLogicInctance == null)
	    	 MainLogicInctance = new DataCommunicatorBooking(new Connector(Globals.USERNAME,Globals.PASSWORD).getConnection());
	     return MainLogicInctance;
	}
}
