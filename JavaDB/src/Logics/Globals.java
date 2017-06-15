package Logics;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import DataBase.Booking;
import DataBase.Connector;
import DataBase.DataCommunicatorBooking;
import DataBase.DataCommunicatorRoom;
import DataBase.Room;

import com.mysql.jdbc.Connection;

public class Globals {
	public static int YEAR = 2017;
	
	//raspberry user: MySystemUser
	public static final String USERNAME = "root";
	public static final String PASSWORD = "elshof11";
	
	public static Connection con;
	
	public static void initializeGlobals() throws ClassNotFoundException, SQLException{
		con = new Connector(USERNAME,PASSWORD).getConnection();
	}
	
	public static void insertBooking(String name,String surname,int persons,String begindate,String enddate,int roomID,boolean confirmed){
		try {
			DataCommunicatorBooking Communicator = DataCommunicatorBooking.getInctance();
			Communicator.insertBooking(name,surname,persons,begindate,enddate,roomID,confirmed);
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void deleteBooking(int id){
		try {
			DataCommunicatorBooking Communicator = DataCommunicatorBooking.getInctance();
			Communicator.deleteBooking(id);
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void updateBooking(String name,String surname,int persons,String begindate,String enddate,int roomID,int id,boolean confirmed){
		try {
			DataCommunicatorBooking Communicator = DataCommunicatorBooking.getInctance();
			Communicator.updateBooking(name, surname, persons, begindate, enddate, id,roomID,confirmed);
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public static Booking displayBooking(int id){
		Booking booking = null;
		try {
			DataCommunicatorBooking Communicator = DataCommunicatorBooking.getInctance();
			booking = Communicator.displayBooking(id);
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		return booking;
	}
	
	public static Booking[] displayAllBookings(){
		Booking[] booking = null;
		try {
			DataCommunicatorBooking Communicator = DataCommunicatorBooking.getInctance();
			booking = Communicator.displayAllBookings();
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}	
		return booking;
	}
	
	public static void insertRoom(String name,int maxPersons,float hCost,float cost){
		try {
			DataCommunicatorRoom Communicator = DataCommunicatorRoom.getInctance();
			Communicator.insertRoom(name,maxPersons,hCost,cost);
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void deleteRoom(int id){
		try {
			DataCommunicatorRoom Communicator = DataCommunicatorRoom.getInctance();
			Communicator.deleteRoom(id);
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void updateRoom(int idUpdate, String surnameupdate, int personsupdate,float hCost,float cost){
		try {
			DataCommunicatorRoom Communicator = DataCommunicatorRoom.getInctance();
			Communicator.updateRoom(surnameupdate, personsupdate,hCost,cost,idUpdate);
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public static Room displayRoom(int id){
		Room room = null;
		try {
			DataCommunicatorRoom Communicator = DataCommunicatorRoom.getInctance();
			room = Communicator.displayRoom(id);
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		return room;
	}
	
	public static Room[] displayAllRooms(){
		Room[] rooms = null;
				try {
					DataCommunicatorRoom Communicator = DataCommunicatorRoom.getInctance();
					rooms = Communicator.displayAllRooms();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
		return rooms;		
	}

	public static void deleteAllBookingsByID(int roomID) {
		try {
			DataCommunicatorBooking Communicator = DataCommunicatorBooking.getInctance();
			Communicator.deleteAllByRoomID(roomID);
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		
	}

	public static Booking getBookingByDateAndRoom(int roomID,int dateNumber) {
		Booking booking = null;
		try {
			DataCommunicatorBooking Communicator = DataCommunicatorBooking.getInctance();
			booking = Communicator.displayBookingByDateAndRoom(roomID,dateNumber);
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}	
		return booking;
	}

	public static boolean checkDate(int roomID, String beginDate, String endDate) throws ParseException {
		Booking[] bookings = null;
		try {
			DataCommunicatorBooking Communicator = DataCommunicatorBooking.getInctance();
			bookings = Communicator.displayBookingByRoomID(roomID);
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		if(bookings!=null)
			for(Booking booking : bookings){
				for(int i = getDateNumber(beginDate);i<=getDateNumber(endDate);i++)
					if(isIn(i,getDateNumber(booking.getBegin()),getDateNumber(booking.getEnd())))
						return false;
			}
		return true;
	}

	private static int getDateNumber(String dateString) throws ParseException{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date  = LocalDate.parse(dateString, formatter);
		return date.getDayOfYear();
	}
	
	private static boolean isIn(int check, int low, int high) {
		if(check>=low && check<=high)
			return true;
		return false;
	}

	public static boolean checkRoom(int roomID, int persons) {
		Room room;
		try {
			DataCommunicatorRoom Communicator = DataCommunicatorRoom.getInctance();
			room = Communicator.displayRoom(roomID);
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}	
		if(displayRoom(roomID).getMaxPersons()<persons)
			return false;
		return true;
	}
	
}


