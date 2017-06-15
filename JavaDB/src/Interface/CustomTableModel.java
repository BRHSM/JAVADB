package Interface;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.table.DefaultTableModel;

import DataBase.Booking;
import Logics.Globals;

public class CustomTableModel extends DefaultTableModel{
	
	private Booking[] bookings;
	public CustomTableModel(Object[] data, int i){
		super(data,i);
		bookings = Globals.displayAllBookings();
	}

	public int getStatus(int row, int col) {
		for(Booking booking : bookings){
			System.out.println(booking.toString());
			int begin=0,end=0,current;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate currentDate  = LocalDate.parse(booking.getBegin(), formatter);
			try {
				begin = getDateNumber(booking.getBegin())+2;
				end = getDateNumber(booking.getEnd())+2;
				current = begin;
				while(booking.getRoomID()>=super.getRowCount())
					booking.setRoomID(booking.getRoomID()-1);
				while(current<=end){
					if(Globals.YEAR==currentDate.getYear()){
						if(current == col && booking.getRoomID() == row){
							if(booking.isConfirmed())
								return booking.getPersons();
							return booking.getPersons()+10;
						}
					}
					currentDate.plusDays(1);
					current++;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	public int getDateNumber(String dateString) throws ParseException{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date  = LocalDate.parse(dateString, formatter);
		return date.getDayOfYear();
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex){
		return false;
	}
}