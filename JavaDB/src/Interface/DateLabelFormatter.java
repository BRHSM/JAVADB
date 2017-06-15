package Interface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFormattedTextField.AbstractFormatter;

public class DateLabelFormatter extends AbstractFormatter {

	private String datePattern = "dd-MM-yyyy";
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
	
	@Override
	public Object stringToValue(String arg0) throws ParseException {
		return dateFormatter.parseObject(arg0);
	}

	@Override
	public String valueToString(Object arg0) throws ParseException {
		if (arg0 != null){
			Calendar cal = (Calendar) arg0;
			return dateFormatter.format(cal.getTime());
		}
		return "";
	}

}
