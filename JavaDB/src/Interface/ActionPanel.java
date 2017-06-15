package Interface;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;


public class ActionPanel extends JPanel {
	private JScrollPane pane;
	public ActionPanel(){
		this.setLayout(new BorderLayout());
		pane = new HorizontalScrollPane(TablePanel.getInctance().getTable());
		TablePanel.getInctance().updateAll();
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.add(pane,BorderLayout.CENTER);
	}
}
