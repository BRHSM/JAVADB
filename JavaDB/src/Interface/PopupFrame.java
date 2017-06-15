package Interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import DataBase.Booking;
import DataBase.DataCommunicatorRoom;
import DataBase.Room;
import Logics.Globals;

public class PopupFrame extends JFrame implements ActionListener{

	//=============ROOM================
	private JButton confirmRoom = new JButton("Confirm");
	private JButton cancelRoom = new JButton("Cancel");
	private JButton deleteRoom = new JButton("Delete");
	
	private JLabel NameLabelRoom = new JLabel("Room Name: ");
	private JLabel MaxPersoneLabelRoom = new JLabel("Max Amount of Persons: ");
	private JLabel hCostLabelRoom = new JLabel("High Season Cost: ");
	private JLabel CostLabelRoom = new JLabel("low Season Cost: ");
	
	private JTextField nameFieldRoom = new JTextField();
	private JNumberTextField maxPersonsFieldRoom = new JNumberTextField();
	private JNumberTextField hCostFieldRoom = new JNumberTextField();
	private JNumberTextField costFieldRoom = new JNumberTextField();
	
	private JLabel selectRoom = new JLabel("Select Room");
	
	private JList listRoom = new JList();
	DefaultListModel<String> listModelRoom = getDefaultRoomListModel();
	
	private JComboBox<Room> listRooms = getRoomList();
	JPanel buttonPanelRoom = new JPanel();
	
	//============BOOKING==============
	private JButton confirmBooking = new JButton("Confirm");
	private JButton cancelBooking = new JButton("Cancel");
	private JButton deleteBooking = new JButton("Delete");
	
	private JLabel nameLabelBooking = new JLabel("Name:");
	private JLabel surnameLabelBooking = new JLabel("Surname:");
	private JLabel personsLabelBooking = new JLabel("Persons:");
	private JLabel beginDateLabelBooking = new JLabel("Begin Date:");
	private JLabel endDateLabelBooking = new JLabel("End Date:");
	private JLabel roomLabelBooking = new JLabel("Room: ");
	private JLabel confirmLabelBooking = new JLabel("Confirm: ");
	
	private JTextField nameFieldBooking = new JTextField();
	private JTextField surnameFieldBooking = new JTextField();
	private JNumberTextField personsFieldBooking = new JNumberTextField();
	
	private UtilDateModel beginModelBooking = new UtilDateModel();
	private UtilDateModel endModelBooking = new UtilDateModel();
	private Properties pBooking = new Properties();
	private JDatePanelImpl beginDatePanelBooking;
	private JDatePanelImpl endDatePanelBooking;
	private JDatePickerImpl beginDatePickerBooking;
	private JDatePickerImpl endDatePickerBooking;
	
	private JComboBox roomComboBoxBooking = new JComboBox(getRoomNames());
	private String action;
	
	private JLabel selectBooking = new JLabel("Select Booking");
	
	private JList listBooking = new JList();
	DefaultListModel<String> listModelBooking = getDefaultBookingListModel();
	
	private JComboBox<Booking> listBookings = getBookingList();
	JPanel buttonPanelBooking = new JPanel();
	
	private JCheckBox confirmedBooking = new JCheckBox();
	
	public PopupFrame(String action) {
		super(action);
		this.action=action;
		//=============ROOM================
		confirmRoom.addActionListener(this);
		cancelRoom.addActionListener(this);
		deleteRoom.addActionListener(this);
		
		listRooms.setEditable(false);
		listRooms.addActionListener(this);
		
		//============BOOKING==============
		pBooking.put("text.today", "Today");
		pBooking.put("text.month", "Month");
		pBooking.put("text.year", "Year");
		
		beginDatePanelBooking = new JDatePanelImpl(beginModelBooking, pBooking);
		endDatePanelBooking = new JDatePanelImpl(endModelBooking, pBooking);
		beginDatePickerBooking = new JDatePickerImpl(beginDatePanelBooking, new DateLabelFormatter());
		endDatePickerBooking = new JDatePickerImpl(endDatePanelBooking, new DateLabelFormatter());
		
		roomComboBoxBooking.setEditable(false);
		
		confirmBooking.addActionListener(this);
		cancelBooking.addActionListener(this);
		deleteBooking.addActionListener(this);
		
		listBookings.setEditable(false);
		listBookings.addActionListener(this);
		
		//=============ROOM================
		if(action.equals("Add room")){
			this.setLayout(new GridLayout(5,2));
			this.setSize(new Dimension(240,199));
			this.setResizable(false);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
			this.setIconImage(new ImageIcon("Recources/Images/Icon.png").getImage());
			this.add(NameLabelRoom);
			this.add(nameFieldRoom);
			this.add(MaxPersoneLabelRoom);
			this.add(maxPersonsFieldRoom);
			this.add(hCostLabelRoom);
			this.add(hCostFieldRoom);
			this.add(CostLabelRoom);
			this.add(costFieldRoom);
			this.add(confirmRoom);
			this.add(cancelRoom);
			this.setVisible(true);
		}
		if(action.equals("edit room")){
			this.setLayout(new GridLayout(6,2));
			this.setSize(new Dimension(240,227));
			this.setResizable(false);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
			this.setIconImage(new ImageIcon("Recources/Images/Icon.png").getImage());
			this.add(selectRoom);
			this.add(listRooms);
			this.add(NameLabelRoom);
			this.add(nameFieldRoom);
			this.add(MaxPersoneLabelRoom);
			this.add(maxPersonsFieldRoom);
			this.add(hCostLabelRoom);
			this.add(hCostFieldRoom);
			this.add(CostLabelRoom);
			this.add(costFieldRoom);
			this.add(confirmRoom);
			this.add(cancelRoom);
			this.setVisible(true);
		}
		
		if(action.equals("delete room")){
			this.setLayout(new BorderLayout());
			this.setSize(new Dimension(240,232));
			this.setResizable(false);
			listRoom.setModel(listModelRoom);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
			this.setIconImage(new ImageIcon("Recources/Images/Icon.png").getImage());
			buttonPanelRoom.setLayout(new GridLayout(1,2));
			buttonPanelRoom.add(deleteRoom);
			buttonPanelRoom.add(cancelRoom);
			this.add(listRoom,BorderLayout.CENTER);
			this.add(buttonPanelRoom,BorderLayout.SOUTH);
			this.setVisible(true);
		}
		
		
		//============BOOKING==============
		if(action.equals("Add booking")){
			this.setLayout(new GridLayout(8,2));
			this.setSize(new Dimension(240,269));
			this.setResizable(false);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
			this.setIconImage(new ImageIcon("Recources/Images/Icon.png").getImage());
			this.add(nameLabelBooking);
			this.add(nameFieldBooking);
			this.add(surnameLabelBooking);
			this.add(surnameFieldBooking);
			this.add(personsLabelBooking);
			this.add(personsFieldBooking);
			this.add(beginDateLabelBooking);
			this.add(beginDatePickerBooking);
			this.add(endDateLabelBooking);
			this.add(endDatePickerBooking);
			this.add(roomLabelBooking);
			this.add(roomComboBoxBooking);
			this.add(confirmLabelBooking);
			this.add(confirmedBooking);
			this.add(confirmBooking);
			this.add(cancelBooking);
			this.setVisible(true);
		}
		
		if(action.equals("edit booking")){
			this.setLayout(new GridLayout(9,2));
			this.setSize(new Dimension(240,290));
			this.setResizable(false);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
			this.setIconImage(new ImageIcon("Recources/Images/Icon.png").getImage());
			this.add(selectBooking);
			this.add(listBookings);
			this.add(nameLabelBooking);
			this.add(nameFieldBooking);
			this.add(surnameLabelBooking);
			this.add(surnameFieldBooking);
			this.add(personsLabelBooking);
			this.add(personsFieldBooking);
			this.add(beginDateLabelBooking);
			this.add(beginDatePickerBooking);
			this.add(endDateLabelBooking);
			this.add(endDatePickerBooking);
			this.add(roomLabelBooking);
			this.add(roomComboBoxBooking);
			this.add(confirmLabelBooking);
			this.add(confirmedBooking);
			this.add(confirmBooking);
			this.add(cancelBooking);
			this.setVisible(true);
		}
		
		if(action.equals("delete booking")){
			this.setLayout(new BorderLayout());
			this.setSize(new Dimension(240,232));
			this.setResizable(false);
			listBooking.setModel(listModelBooking);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
			this.setIconImage(new ImageIcon("Recources/Images/Icon.png").getImage());
			buttonPanelBooking.setLayout(new GridLayout(1,2));
			buttonPanelBooking.add(deleteBooking);
			buttonPanelBooking.add(cancelBooking);
			this.add(listBooking,BorderLayout.CENTER);
			this.add(buttonPanelBooking,BorderLayout.SOUTH);
			this.setVisible(true);
		}
	}

	public PopupFrame(String action, Booking booking) {	
		this.action = action;
		pBooking.put("text.today", "Today");
		pBooking.put("text.month", "Month");
		pBooking.put("text.year", "Year");
		
		beginDatePanelBooking = new JDatePanelImpl(beginModelBooking, pBooking);
		endDatePanelBooking = new JDatePanelImpl(endModelBooking, pBooking);
		beginDatePickerBooking = new JDatePickerImpl(beginDatePanelBooking, new DateLabelFormatter());
		endDatePickerBooking = new JDatePickerImpl(endDatePanelBooking, new DateLabelFormatter());
		
		roomComboBoxBooking.setEditable(false);
		
		confirmBooking.addActionListener(this);
		cancelBooking.addActionListener(this);
		deleteBooking.addActionListener(this);
		
		listBookings.setEditable(false);
		listBookings.addActionListener(this);
		
		if(action.equals("view")){
			this.setLayout(new GridLayout(8,2));
			this.setSize(new Dimension(240,269));
			this.setResizable(false);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
			this.setIconImage(new ImageIcon("Recources/Images/Icon.png").getImage());
			this.add(nameLabelBooking);
			this.add(new JLabel(booking.getName()));
			this.add(surnameLabelBooking);
			this.add(new JLabel(booking.getSurname()));
			this.add(personsLabelBooking);
			this.add(new JLabel(Integer.toString(booking.getPersons())));
			this.add(beginDateLabelBooking);
			this.add(new JLabel(booking.getBegin()));
			this.add(endDateLabelBooking);
			this.add(new JLabel(booking.getEnd()));
			this.add(roomLabelBooking);
			this.add(new JLabel(Globals.displayRoom(booking.getRoomID()).getName()));
			this.add(confirmLabelBooking);
			
			JCheckBox box = new JCheckBox();
			box.setSelected(false);
			
			MouseListener[] ml = (MouseListener[])box.getListeners(MouseListener.class);
			for (int i = 0; i < ml.length; i++)
			    box.removeMouseListener( ml[i] );
			InputMap im = box.getInputMap();
			im.put(KeyStroke.getKeyStroke("SPACE"), "none");
			im.put(KeyStroke.getKeyStroke("released SPACE"), "none");
			
			if(booking.isConfirmed())
				box.setSelected(true);
			this.add(box);
			this.add(new JPanel());
			this.add(cancelBooking);
			this.setVisible(true);
		}
		if(action.equals("edit booking")){
			this.setLayout(new GridLayout(8,2));
			this.setSize(new Dimension(240,290));
			this.setResizable(false);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
			this.setIconImage(new ImageIcon("Recources/Images/Icon.png").getImage());
			
			nameFieldBooking.setText(booking.getName());
			surnameFieldBooking.setText(booking.getSurname());
			personsFieldBooking.setInt(booking.getPersons());
			
			try {
				LocalDate beginFormat = new SimpleDateFormat("dd-MM-yyyy").parse(booking.getBegin()).toInstant().atOffset(ZoneOffset.UTC).toLocalDate();
				LocalDate endFormat = new SimpleDateFormat("dd-MM-yyyy").parse(booking.getEnd()).toInstant().atOffset(ZoneOffset.UTC).toLocalDate();
				beginDatePanelBooking.getModel().setDay(beginFormat.getDayOfMonth()+1);
				beginDatePanelBooking.getModel().setMonth(beginFormat.getMonthValue()-1);
				beginDatePanelBooking.getModel().setYear(beginFormat.getYear());
				endDatePanelBooking.getModel().setDay(endFormat.getDayOfMonth()+1);
				endDatePanelBooking.getModel().setMonth(endFormat.getMonthValue()-1);
				endDatePanelBooking.getModel().setYear(endFormat.getYear());
				beginDatePanelBooking.getModel().setSelected(true);
				endDatePanelBooking.getModel().setSelected(true);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			roomComboBoxBooking.setSelectedItem(Globals.displayRoom(booking.getRoomID()).getName());
			if(booking.isConfirmed()){
				confirmedBooking.setSelected(true);
			}
			
			this.add(nameLabelBooking);
			this.add(nameFieldBooking);
			this.add(surnameLabelBooking);
			this.add(surnameFieldBooking);
			this.add(personsLabelBooking);
			this.add(personsFieldBooking);
			this.add(beginDateLabelBooking);
			this.add(beginDatePickerBooking);
			this.add(endDateLabelBooking);
			this.add(endDatePickerBooking);
			this.add(roomLabelBooking);
			this.add(roomComboBoxBooking);
			this.add(confirmLabelBooking);
			this.add(confirmedBooking);
			this.add(confirmBooking);
			this.add(cancelBooking);
			this.setVisible(true);
		}
		if(action.equals("delete booking")){
			Globals.deleteBooking(booking.getId());
		}
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		//============BOOKING==============
		if(actionEvent.getSource()==confirmBooking){
			if(action.equals("edit booking")){
				if(this.updateBooking() == false){
					JOptionPane.showMessageDialog(null, "Booking Could Not Be Edited");
					TablePanel.getInctance().updateAll();
				}else{
					setVisible(false);
					dispose();
				}
			}else{
				if(this.insertBooking() == false){
					JOptionPane.showMessageDialog(null, "Booking Could Not Be Added");
					TablePanel.getInctance().updateAll();
				}else{
					setVisible(false);
					dispose();
				}
			}
		}
			
		if(actionEvent.getSource()==cancelBooking){
			this.setVisible(false);
			this.dispose();
		}
		
		if(actionEvent.getSource()==deleteBooking){
			if(this.deleteBooking() == false){
				JOptionPane.showMessageDialog(null, "Booking Could Not Be Deleted");
				TablePanel.getInctance().updateAll();
			}else{
				TablePanel.getInctance().updateAll();
				setVisible(false);
				dispose();
			}
		}
		
		//=============ROOM================
		if(actionEvent.getSource()==confirmRoom){
			if(action.equals("edit room")){
				if(this.updateRoom() == false){
					JOptionPane.showMessageDialog(null, "Room Could Not Be Edited");
					TablePanel.getInctance().updateAll();
				}else{
					setVisible(false);
					dispose();
				}
			}else{
				if(this.insertRoom() == false){
					JOptionPane.showMessageDialog(null, "Room Could Not Be Added");
					TablePanel.getInctance().updateAll();
				}else{
					setVisible(false);
					dispose();
				}
			}
		}
		
		if(actionEvent.getSource()==cancelRoom){
			this.setVisible(false);
			this.dispose();
		}
		
		if(actionEvent.getSource()==deleteRoom){
			if(this.deleteRoom() == false){
				JOptionPane.showMessageDialog(null, "Room Could Not Be Deleted");
				TablePanel.getInctance().updateAll();
			}else{
				TablePanel.getInctance().updateAll();
				setVisible(false);
				dispose();
			}
		}
		
		if(actionEvent.getSource()==listRooms){
			int id = getRoomID(listRooms.getSelectedItem().toString());
			Room room = Globals.displayRoom(id);
			nameFieldRoom.setText(room.getName());
			maxPersonsFieldRoom.setInt(room.getMaxPersons());
			hCostFieldRoom.setInt(Math.round(room.gethCost()));
			costFieldRoom.setInt(Math.round(room.getCost()));
		}
		
		if(actionEvent.getSource()==listBookings){
			int id = getBookingID(listBookings.getSelectedItem().toString());
			Booking booking = Globals.displayBooking(id);
			nameFieldBooking.setText(booking.getName());
			surnameFieldBooking.setText(booking.getSurname());
			personsFieldBooking.setInt(booking.getPersons());
			
			try {
				LocalDate beginFormat = new SimpleDateFormat("dd-MM-yyyy").parse(booking.getBegin()).toInstant().atOffset(ZoneOffset.UTC).toLocalDate();
				LocalDate endFormat = new SimpleDateFormat("dd-MM-yyyy").parse(booking.getEnd()).toInstant().atOffset(ZoneOffset.UTC).toLocalDate();
				beginDatePanelBooking.getModel().setDay(beginFormat.getDayOfMonth()+1);
				beginDatePanelBooking.getModel().setMonth(beginFormat.getMonthValue()-1);
				beginDatePanelBooking.getModel().setYear(beginFormat.getYear());
				endDatePanelBooking.getModel().setDay(endFormat.getDayOfMonth()+1);
				endDatePanelBooking.getModel().setMonth(endFormat.getMonthValue()-1);
				endDatePanelBooking.getModel().setYear(endFormat.getYear());
				beginDatePanelBooking.getModel().setSelected(true);
				endDatePanelBooking.getModel().setSelected(true);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			roomComboBoxBooking.setSelectedItem(Globals.displayRoom(booking.getRoomID()).getName());
			if(booking.isConfirmed()){
				confirmedBooking.setSelected(true);
			}
		}
	}

	//============BOOKING==============
	public boolean insertBooking(){
		String name = this.nameFieldBooking.getText();
		String surname = this.surnameFieldBooking.getText();
		int persons = (int) this.personsFieldBooking.getNumber();
		String beginDate = new SimpleDateFormat("dd-MM-yyyy").format((Date) this.beginDatePanelBooking.getModel().getValue());
		String endDate = new SimpleDateFormat("dd-MM-yyyy").format((Date) this.endDatePanelBooking.getModel().getValue());
		int roomID;
		try {
			roomID = new DataCommunicatorRoom(Globals.con).getRoomIDByName((String)roomComboBoxBooking.getSelectedItem());
			boolean confirmed = false;
			if(confirmedBooking.isSelected())
				confirmed = true;
			if(Globals.checkDate(roomID,beginDate,endDate) && Globals.checkRoom(roomID,persons)){
				Globals.insertBooking(name,surname,persons,beginDate,endDate,roomID,confirmed);
			}else{
				return false;
			}
		} catch (ClassNotFoundException | SQLException | ParseException e) {
			return false;
		}
		return true;
	}
	
	private String[] getRoomNames() {
		Room rooms[] = Globals.displayAllRooms();
		ArrayList<String> roomNames= new ArrayList<String>();
		for(Room room : rooms){
			roomNames.add(room.getName());
		}
		return roomNames.toArray(new String[roomNames.size()]);
		
	}
	
	private JComboBox<Booking> getBookingList() {
		return new JComboBox(getBookingObjects(Globals.displayAllBookings()).toArray());
		
	}

	private ArrayList<Object> getBookingObjects(Booking[] Booking) {
		ArrayList<Object> model = new ArrayList<Object>();
		for(Booking booking : Booking){
			model.add(booking.getId() + " " + booking.getName());
		}
		return model;
	}
	
	private int getBookingID(String data) {
		return Integer.parseInt(data.replaceAll("[\\D]", ""));
	}
	
	private boolean updateBooking() {
		int id = getBookingID(listBookings.getSelectedItem().toString());
		String name = this.nameFieldBooking.getText();
		String surname = this.surnameFieldBooking.getText();
		int persons = (int) this.personsFieldBooking.getNumber();
		String beginDate = new SimpleDateFormat("dd-MM-yyyy").format((Date) this.beginDatePanelBooking.getModel().getValue());
		String endDate = new SimpleDateFormat("dd-MM-yyyy").format((Date) this.endDatePanelBooking.getModel().getValue());
		try {
			int roomID = new DataCommunicatorRoom(Globals.con).getRoomIDByName((String)roomComboBoxBooking.getSelectedItem());
			boolean confirmed = false;
			if(confirmedBooking.isSelected())
				confirmed = true;
			Globals.updateBooking(name,surname,persons,beginDate,endDate,roomID,id,confirmed);
		} catch (ClassNotFoundException | SQLException e) {
			return false;
		}
		
		return true;
	}
	
	private DefaultListModel<String> getDefaultBookingListModel() {
		DefaultListModel<String> listModel = new DefaultListModel<>();
		Booking[] bookings = Globals.displayAllBookings();
		for (Booking booking : bookings){
			listModel.addElement(booking.getId() + " " + booking.getName());
		}
		return listModel;
	}
	
	private boolean deleteBooking() {
		List<Object> list = listBooking.getSelectedValuesList();
		for(Object object : list){
			Globals.deleteBooking(getBookingID(((String)object)));
		}
		return true;
	}
	
	//=============ROOM================
	
	private boolean insertRoom() {
		String name = this.nameFieldRoom.getText();
		int persons = (int) this.maxPersonsFieldRoom.getNumber();
		int hCost = (int) this.hCostFieldRoom.getFloat();
		int cost = (int) this.costFieldRoom.getFloat();
		Globals.insertRoom(name,persons,hCost,cost);
		return true;
	}
	
	private JComboBox<Room> getRoomList() {
		return new JComboBox(getRoomObjects(Globals.displayAllRooms()).toArray());
		
	}

	private ArrayList<Object> getRoomObjects(Room[] displayAllRooms) {
		ArrayList<Object> model = new ArrayList<Object>();
		for(Room room : displayAllRooms){
			model.add(room.getRoomId() + " " + room.getName());
		}
		return model;
	}
	
	private int getRoomID(String data) {
		return Integer.parseInt(data.replaceAll("[\\D]", ""));
	}
	
	private boolean updateRoom() {
		int id = getRoomID(listRooms.getSelectedItem().toString());
		String name = this.nameFieldRoom.getText();
		int persons = (int) this.maxPersonsFieldRoom.getNumber();
		int hCost = (int) this.hCostFieldRoom.getFloat();
		int cost = (int) this.costFieldRoom.getFloat();
		Globals.updateRoom(id,name,persons,hCost,cost);
		return true;
	}
	
	private DefaultListModel<String> getDefaultRoomListModel() {
		DefaultListModel<String> listModel = new DefaultListModel<>();
		Room[] rooms = Globals.displayAllRooms();
		for (Room room : rooms){
			listModel.addElement(room.getRoomId() + " " + room.getName());
		}
		return listModel;
	}
	
	private boolean deleteRoom() {
		List<Object> list = listRoom.getSelectedValuesList();
		for(Object object : list){
			Globals.deleteAllBookingsByID(getRoomID(((String)object)));
			Globals.deleteRoom(getRoomID(((String)object)));
		}
		return true;
	}
	
}