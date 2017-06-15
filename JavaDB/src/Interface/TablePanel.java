package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import DataBase.Booking;
import DataBase.Room;
import Logics.Globals;

public class TablePanel extends JPanel{
	private static TablePanel MainLogicInctance;
	private JTable table;
	private CustomTableModel model;
	private JPopupMenu popup;
	private JMenuItem view;
	private JMenuItem confirm;
	private JMenuItem unconfirm;
	private JMenuItem edit;
	private JMenuItem delete;
	int x=0,y=0;
	public TablePanel(){
		model = new CustomTableModel(getObjectArray(), 0);
		popup = new JPopupMenu();
		view = new JMenuItem("view");
		confirm = new JMenuItem("confirm");
		unconfirm = new JMenuItem("unconfirm");
		edit = new JMenuItem("edit");
		delete = new JMenuItem("delete");
		popup.add(view);
		popup.add(confirm);
		popup.add(unconfirm);
		popup.add(new JPopupMenu.Separator());
		popup.add(edit);
		popup.add(delete);
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(255,255,255));
		table = new JTable(model);
		MouseAdapter adapter = new MouseAdapter() {
		    @Override
		    public void mousePressed(MouseEvent e) {
		    	if(e.getSource() != view && e.getSource() != confirm && e.getSource() != unconfirm && e.getSource() != edit && e.getSource() != delete){
		    		x=table.rowAtPoint(e.getPoint());
		    		y=table.columnAtPoint(e.getPoint());
		    		System.out.println(Integer.toString(x)+"|"+Integer.toString(y));
		    	}
		    	if(e.getSource() == view){
		    		Booking booking = Globals.getBookingByDateAndRoom(x, y);
		    		System.out.println(Integer.toString(x)+"|"+Integer.toString(y));
		    		System.out.println(booking.toString());
		    		if(booking!=null){
		    			JFrame frame = new PopupFrame("view",booking);
		    		}
		    	}
		    	if(e.getSource() == confirm){
		    		Booking booking = Globals.getBookingByDateAndRoom(x, y);
		    		System.out.println(Integer.toString(x)+"|"+Integer.toString(y));
		    		if(booking!=null){
		    			Globals.updateBooking(booking.getName(), booking.getSurname(), booking.getPersons(), booking.getBegin(), booking.getEnd(), booking.getRoomID(), booking.getId(), true);
		    			TablePanel.getInctance().updateAll();
		    		}
		    	}
		    	if(e.getSource() == unconfirm){
		    		Booking booking = Globals.getBookingByDateAndRoom(x, y);
		    		if(booking!=null){
		    			Globals.updateBooking(booking.getName(), booking.getSurname(), booking.getPersons(), booking.getBegin(), booking.getEnd(), booking.getRoomID(), booking.getId(), false);
		    			TablePanel.getInctance().updateAll();
		    		}
		    	}
		    	if(e.getSource() == edit){
		    		Booking booking = Globals.getBookingByDateAndRoom(x, y);
		    		if(booking!=null){
		    			JFrame frame = new PopupFrame("edit booking",booking);
		    			TablePanel.getInctance().updateAll();
		    		}
		    	}
		    	if(e.getSource() == delete){
		    		Booking booking = Globals.getBookingByDateAndRoom(x, y);
		    		if(booking!=null){
		    			JFrame frame = new PopupFrame("delete booking",booking);
		    			TablePanel.getInctance().updateAll();
		    		}
		    	}
		    }
		};

		table.addMouseListener(adapter);
		view.addMouseListener(adapter);
		confirm.addMouseListener(adapter);
		unconfirm.addMouseListener(adapter);
		edit.addMouseListener(adapter);
		delete.addMouseListener(adapter);
		
		table.setComponentPopupMenu(popup);
		table.setEnabled(false);
		table.getColumnModel().setColumnSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setWidth(0);
		this.add(table.getTableHeader(), BorderLayout.NORTH);
		this.setAllColoumnsRenderer();
		this.add(table,BorderLayout.CENTER);
	}
	private void setAllColoumnsRenderer(){
		for(int i=0; i<368;i++){
			 table.getColumnModel().getColumn(i).setCellRenderer(new CustomRenderer());
		}
	}
	
	private Object[] getObjectArray() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("Recources/TableData/HeaderData.txt"));
			String s;
			s = reader.readLine();
		
			StringTokenizer str = new StringTokenizer(s,",");
			String tmp;
			int i=0;
			Object[] resault = new Object[368];
			while(str.hasMoreTokens() && i<368){
				tmp=str.nextToken();
				if(!tmp.isEmpty()){
					resault[i]=tmp;
				}else{
					resault[i]=" - ";
				}
				i++;
			}
			return resault;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Object[] getRoomObjectArray(String room,int roomId) {
		Object[] resault = new Object[549];
		resault[0]=roomId;
		resault[1]=room;
		return resault;
	}
	
	
	public void addRoom(String roomname,int roomId){
		this.remove(table);
		((CustomTableModel) model).addRow(getRoomObjectArray(roomname,roomId));
		table = new JTable(model);
		((CustomTableModel) model).fireTableRowsInserted(0, ((CustomTableModel)model).getRowCount());
		this.add(table);
		this.revalidate();
		this.repaint();
	}
	public static TablePanel getInctance() {
	     if (MainLogicInctance == null)
	    	 MainLogicInctance = new TablePanel();
	     return MainLogicInctance;
	}
	
	public JTable getTable(){
		return table;
	}
	
	public void updateAll() {
		System.out.println("forceUpdateRunning");
		int rowCount = model.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
		    ((CustomTableModel) model).removeRow(i);
		}
		Room rooms[] = Globals.displayAllRooms();
		for(Room room : rooms){
			this.addRoom(room.getName(),room.getRoomId());
		}
		Booking bookings[] = Globals.displayAllBookings();
		for(Booking booking : bookings){
			this.addBooking(booking);
		}
		System.out.println("Time To Update!! :)");
		table.revalidate();
		((CustomTableModel)table.getModel()).fireTableDataChanged();
		table.repaint(); 
	}
	
	public void addBooking(Booking booking){
		int begin=0,end=0,current;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate currentDate  = LocalDate.parse(booking.getBegin(), formatter);
		try {
			begin = getDateNumber(booking.getBegin())+2;
			end = getDateNumber(booking.getEnd())+2;
			current = begin;
			while(booking.getRoomID()>=model.getRowCount())
				booking.setRoomID(booking.getRoomID()-1);
			while(current<=end){
				if(Globals.YEAR==currentDate.getYear()){
					if(current == begin){
						setCell(current,booking.getRoomID(),booking.getName());
					}
					if(current-1 == begin){
						setCell(current,booking.getRoomID(),booking.getSurname());
					}
				}
				currentDate.plusDays(1);
				current++;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public int getDateNumber(String dateString) throws ParseException{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date  = LocalDate.parse(dateString, formatter);
		return date.getDayOfYear();
	}
	
	public int getYear(String dateString){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date  = LocalDate.parse(dateString, formatter);
		return date.getYear();
	}
	
	public void setCell(int x,int y,String name){
		model.setValueAt(name, y, x);
	}	
}
