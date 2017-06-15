package DataBase;

public class Booking{
	private String begin;
	private String end;
	private String name;
	private String surname;
	private int persons;
	private int roomID;
	private int id;
	private boolean confirmed;
	public Booking(String begin,String end,String name,String surname,int persons,int roomID,int id,boolean confirmed){
		this.begin=begin;
		this.end=end;
		this.name=name;
		this.surname=surname;
		this.persons=persons;
		this.roomID=roomID;
		this.id=id;
		this.confirmed=confirmed;
			
	}
	public String getBegin() {
		return begin;
	}
	public void setBegin(String begin) {
		this.begin = begin;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getPersons() {
		return persons;
	}
	public void setPersons(int persons) {
		this.persons = persons;
	}
	public int getRoomID() {
		return roomID;
	}
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	
}
