package Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Logics.Globals;

public class browserPanel extends JPanel implements MouseListener{
	private ImagePanel addRoom = new ImagePanel("Recources/Images/AddSeason.png", Color.WHITE);
	private ImagePanel editRoom = new ImagePanel("Recources/Images/editBooking.png", Color.WHITE);
	private ImagePanel deleteRoom = new ImagePanel("Recources/Images/deleteBooking.png", Color.WHITE);
	private ImagePanel addBooking = new ImagePanel("Recources/Images/AddRoom.png", Color.WHITE);
	private ImagePanel editBooking = new ImagePanel("Recources/Images/editBooking.png", Color.WHITE);
	private ImagePanel deleteBooking = new ImagePanel("Recources/Images/deleteBooking.png", Color.WHITE);
	private ImagePanel addConsumption = new ImagePanel("Recources/Images/AddBooking.png", Color.WHITE);
	private ImagePanel BookingScreen = new ImagePanel("Recources/Images/BookingScreen.png", Color.WHITE);
	private ImagePanel ConsumptionScreen = new ImagePanel("Recources/Images/ConsumptionScreen.png", Color.WHITE);
	private ImagePanel Left = new ImagePanel("Recources/Images/ArrowLeft.png", Color.WHITE);
	private ImagePanel Right = new ImagePanel("Recources/Images/ArrowRight.png", Color.WHITE);
	private ImagePanel Refresh = new ImagePanel("Recources/Images/Refresh.png", Color.WHITE);
	private JLabel room = new JLabel("Rooms:");
	private JLabel booking = new JLabel("Bookings:");
	private JLabel screens = new JLabel("screens:");
	private JLabel space = new JLabel("      ");
	private JLabel year = new JLabel(String.valueOf(Globals.YEAR));
	public browserPanel(){
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		addRoom.setPreferredSize(new Dimension(20,20));
		addRoom.addMouseListener(this);
		addRoom.setToolTipText("Add a room");
		addRoom.setBorder(BorderFactory.createLineBorder(Color.black));
		editRoom.setPreferredSize(new Dimension(20,20));
		editRoom.addMouseListener(this);
		editRoom.setToolTipText("Edit a/more Room");
		editRoom.setBorder(BorderFactory.createLineBorder(Color.black));
		deleteRoom.setPreferredSize(new Dimension(20,20));
		deleteRoom.addMouseListener(this);
		deleteRoom.setToolTipText("Delete a/more Room");
		deleteRoom.setBorder(BorderFactory.createLineBorder(Color.black));
		addBooking.setPreferredSize(new Dimension(20,20));
		addBooking.addMouseListener(this);
		addBooking.setToolTipText("Add a booking");
		addBooking.setBorder(BorderFactory.createLineBorder(Color.black));
		editBooking.setPreferredSize(new Dimension(20,20));
		editBooking.addMouseListener(this);
		editBooking.setToolTipText("Edit a/more booking");
		editBooking.setBorder(BorderFactory.createLineBorder(Color.black));
		deleteBooking.setPreferredSize(new Dimension(20,20));
		deleteBooking.addMouseListener(this);
		deleteBooking.setToolTipText("Delete a/more booking");
		deleteBooking.setBorder(BorderFactory.createLineBorder(Color.black));
		addConsumption.setPreferredSize(new Dimension(20,20));
		addConsumption.addMouseListener(this);
		addConsumption.setToolTipText("Add a consumption");
		addConsumption.setBorder(BorderFactory.createLineBorder(Color.black));
		BookingScreen.setPreferredSize(new Dimension(20,20));
		BookingScreen.addMouseListener(this);
		BookingScreen.setToolTipText("Show the booking screen and manage bookings");
		BookingScreen.setBorder(BorderFactory.createLineBorder(Color.black));
		Refresh.setPreferredSize(new Dimension(20,20));
		Refresh.addMouseListener(this);
		Refresh.setToolTipText("Refresh the table");
		Refresh.setBorder(BorderFactory.createLineBorder(Color.black));
		ConsumptionScreen.setPreferredSize(new Dimension(20,20));
		ConsumptionScreen.addMouseListener(this);
		ConsumptionScreen.setToolTipText("Show consumptions");
		ConsumptionScreen.setBorder(BorderFactory.createLineBorder(Color.black));
		Left.setPreferredSize(new Dimension(20,20));
		Left.addMouseListener(this);
		Left.setToolTipText("Show last year");
		Left.setBorder(BorderFactory.createLineBorder(Color.black));
		Right.setPreferredSize(new Dimension(20,20));
		Right.addMouseListener(this);
		Right.setToolTipText("Show next year");
		Right.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBackground(new Color(200,200,200));
		this.add(room);
		this.add(addRoom);
		this.add(editRoom);
		//this.add(deleteRoom);
		this.add(booking);
		this.add(addBooking);
		this.add(editBooking);
		this.add(deleteBooking);
		//this.add(addConsumption);
		//this.add(BookingScreen);
		//this.add(ConsumptionScreen);
		this.add(space);
		this.add(Left);
		this.add(year);
		this.add(Right);
		this.add(space);
		this.add(Refresh);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==addRoom){
			JFrame frame = new PopupFrame("Add room");
		}
		if(e.getSource()==editRoom){
			JFrame frame = new PopupFrame("edit room");
		}
		if(e.getSource()==deleteRoom){
			JFrame frame = new PopupFrame("delete room");
		}
		if(e.getSource()==addBooking){
			JFrame frame = new PopupFrame("Add booking");
		}
		if(e.getSource()==addConsumption){
			JFrame frame = new PopupFrame("Add consumption");
		}
		if(e.getSource()==editBooking){
			JFrame frame = new PopupFrame("edit booking");
		}
		if(e.getSource()==deleteBooking){
			JFrame frame = new PopupFrame("delete booking");
		}
		if(e.getSource()==Left){
			removeYear();
		}
		if(e.getSource()==Right){
			addYear();
		}
		if(e.getSource()==Refresh){
			System.out.println("forceUpdateManual");
			TablePanel.getInctance().updateAll();
		}
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	private void addYear() {
		Globals.YEAR++;
		year.setText(String.valueOf(Globals.YEAR));
		TablePanel.getInctance().updateAll();
	}
	
	private void removeYear() {
		Globals.YEAR--;
		year.setText(String.valueOf(Globals.YEAR));		
		TablePanel.getInctance().updateAll();
	}
}
