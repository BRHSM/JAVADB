package Interface;

import java.awt.Component; 
import java.awt.event.MouseAdapter; 
import java.awt.event.MouseWheelEvent; 
import javax.swing.JScrollBar; 
import javax.swing.JScrollPane; 
class HorizontalScrollPane extends JScrollPane { 
	public HorizontalScrollPane(Component component){
		super(component); 
		final JScrollBar horizontalScrollBar = getHorizontalScrollBar(); 
		final JScrollBar verticalScrollBar = getVerticalScrollBar(); 
		horizontalScrollBar.setUnitIncrement(32);
		verticalScrollBar.setUnitIncrement(2);
		setWheelScrollingEnabled(false); 
		addMouseWheelListener(new MouseAdapter() { 
			public void mouseWheelMoved(MouseWheelEvent evt) { 
				if (evt.getWheelRotation() >= 1){ 
					int iScrollAmount = evt.getScrollAmount(); 
					int iNewValue = (int) (horizontalScrollBar.getValue() + horizontalScrollBar.getBlockIncrement() * iScrollAmount * Math.abs(evt.getWheelRotation())*30); 
					if (iNewValue <= horizontalScrollBar.getMaximum()) { 
						horizontalScrollBar.setValue(iNewValue); 
						}else {
							horizontalScrollBar.setValue(horizontalScrollBar.getMaximum());
						}
					} else if (evt.getWheelRotation() <= -1){ 
						int iScrollAmount = evt.getScrollAmount(); 
						int iNewValue = (int) (horizontalScrollBar.getValue() - horizontalScrollBar.getBlockIncrement() * iScrollAmount * Math.abs(evt.getWheelRotation())*30); 
						if (iNewValue >= 0) { 
							horizontalScrollBar.setValue(iNewValue); 
						}else {
							horizontalScrollBar.setValue(0);
						}
					} 
			}
		}); 
		
	
	}
}