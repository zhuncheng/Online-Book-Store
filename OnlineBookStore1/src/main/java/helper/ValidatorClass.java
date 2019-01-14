package helper;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ValidatorClass {
	public static boolean isInteger(JTextField textField, String name) {
		try {
			Integer.parseInt(textField.getText());
			return true;
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, name + " must be an Integer.", "Error Entry", JOptionPane.ERROR_MESSAGE);
			textField.requestFocus();
			return false;
		}
	}
	
	public static boolean isDouble(JTextField textField, String name) {
		try {
			Double.parseDouble(textField.getText());
			return true;
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, name + " must be a Double.", "Error Entry", JOptionPane.ERROR_MESSAGE);
			textField.requestFocus();
			return false;
		}
	}
	
	public static boolean isPresent(JTextField textField, String name) {
		if(textField.getText().equals("")) {
			JOptionPane.showMessageDialog(null, name + " is required a field.", "Error Entry", JOptionPane.ERROR_MESSAGE);
			textField.requestFocus();
			return false;
		}
		return true;
	}

}
