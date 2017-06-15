package Interface;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomRenderer extends DefaultTableCellRenderer {
	  @Override
	  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

	    JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

	    CustomTableModel tableModel = (CustomTableModel) table.getModel();
	    //====================Confirmed================
	    if (tableModel.getStatus(row,col) == 1) {
	      l.setBackground(new Color(255, 128, 128));
	    } else if (tableModel.getStatus(row,col) == 2) {
		      l.setBackground(new Color(255, 102, 102));
		} else if (tableModel.getStatus(row,col) == 3) {
		      l.setBackground(new Color(255, 77, 77));
		} else if (tableModel.getStatus(row,col) == 4) {
		      l.setBackground(new Color(255, 51, 51));
		} else if (tableModel.getStatus(row,col) == 5) {
		      l.setBackground(new Color(255, 26, 26));
		} else if (tableModel.getStatus(row,col) == 6) {
		      l.setBackground(new Color(255, 0, 0));
		} else if (tableModel.getStatus(row,col) == 7) {
		      l.setBackground(new Color(230, 0, 0));
		} else if (tableModel.getStatus(row,col) == 8) {
		      l.setBackground(new Color(204, 0, 0));
		//===================Not Confirmed=============
		} else if (tableModel.getStatus(row,col) == 11) {
			  l.setBackground(new Color(255, 204, 128));
		} else if (tableModel.getStatus(row,col) == 12) {
		      l.setBackground(new Color(255, 194, 102));
		} else if (tableModel.getStatus(row,col) == 13) {
		      l.setBackground(new Color(255, 184, 77));
		} else if (tableModel.getStatus(row,col) == 14) {
		      l.setBackground(new Color(255, 173, 51));
		} else if (tableModel.getStatus(row,col) == 15) {
		      l.setBackground(new Color(255, 163, 26));
		} else if (tableModel.getStatus(row,col) == 16) {
		      l.setBackground(new Color(255, 153, 0));
		} else if (tableModel.getStatus(row,col) == 17) {
		      l.setBackground(new Color(230, 138, 0));
		} else if (tableModel.getStatus(row,col) == 18) {
		      l.setBackground(new Color(204, 122, 0));
		//=====================empty===================
		} else if(col>1){
	      l.setBackground(new Color(102, 255, 51));
	    }
	  return l;

	}
}