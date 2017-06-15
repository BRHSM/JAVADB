package DataBase;

public class Room {
	private String name;
	private int maxPersons;
	private float hCost;
	private float cost;
	private int roomId;
	
	public Room(String name, int maxPersons,float hCost2,float cost2) {
		this.name = name;
		this.maxPersons = maxPersons;
		this.hCost = hCost2;
		this.cost = cost;
	}
	
	public Room(String name, int maxPersons,float hCost2,float cost2,int roomId) {
		this.name = name;
		this.maxPersons = maxPersons;
		this.hCost = hCost2;
		this.cost = cost;
		this.roomId=roomId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMaxPersons() {
		return maxPersons;
	}
	public void setMaxPersons(int maxPersons) {
		this.maxPersons = maxPersons;
	}
	public float gethCost() {
		return hCost;
	}
	public void sethCost(int hCost) {
		this.hCost = hCost;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	
}
