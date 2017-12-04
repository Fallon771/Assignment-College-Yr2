package ie.lyit.serialize;

import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import ie.lyit.hotel.Customer;

public class ValidInput {

	
	public boolean validateAddCustomer(GuiMenu guiMenu,CustomerSerializer cusSerial,Boolean check){
		int cancel = guiMenu.getCancelOption();
		
		// If they didt close window or press cancel
		if(cancel != -1 && cancel != 2){
			
			// Ask if they wish to confirm
			int result = JOptionPane.showConfirmDialog(null, "Are you sure you wish to store this customer?", 
					"Confirm customer", JOptionPane.INFORMATION_MESSAGE);
			if(result ==JOptionPane.NO_OPTION || result == JOptionPane.CANCEL_OPTION){
				check = false;
			}
			// If OK option was selected
			if(result == JOptionPane.OK_OPTION){

				// If fields were left blank (Phone number can be blank)...display message
				if(guiMenu.getFn().isEmpty() || guiMenu.getSn().isEmpty() || guiMenu.getAddress().isEmpty() || guiMenu.getEmail().isEmpty()){
					JOptionPane.showMessageDialog(null, "Please fill in all fields..","Fileds left blank",JOptionPane.WARNING_MESSAGE);
					check = false;
				}
				// Else just add customer write to file
				else{
					check = true;
				}
			} // End of inner if - else
		} // End of outer if - else
		return check;
	} // End of method
	
	// ==> Again pass in objects and get menu option,ask's customer to confirm if they want to edit customer details
	public boolean validateEditCustomer(GuiMenu guiMenu,CustomerSerializer cusSerial,LinkedList<Customer> cusList,Boolean check){
		int cancel = guiMenu.getCancelOption();
		if(cancel != -1 && cancel != 2){
			int result = JOptionPane.showConfirmDialog(null, "Are you sure you wish to store this customer?", 
					"Confirm customer", JOptionPane.INFORMATION_MESSAGE);
			if(result ==JOptionPane.NO_OPTION || result == JOptionPane.CANCEL_OPTION){
				check = true;
			}
			// If OK option selected..
			if(result == JOptionPane.OK_OPTION){

				// If some of the fields were left blank then loop till they exit or fill in
				if(guiMenu.getFn().isEmpty() || guiMenu.getSn().isEmpty() || guiMenu.getAddress().isEmpty() || guiMenu.getEmail().isEmpty()){
					JOptionPane.showMessageDialog(null, "Please fill in all fields..");
					check = true;
				}
				// Edit customer 
				else{
					cusList.get(guiMenu.getCustomerNumber()-1).setAddress(guiMenu.getAddress());
					cusList.get(guiMenu.getCustomerNumber()-1).setEmailAddress(guiMenu.getAddress());
					cusList.get(guiMenu.getCustomerNumber()-1).setPhoneNumber(guiMenu.getPno());
					cusList.get(guiMenu.getCustomerNumber()-1).getName().setFirstName(guiMenu.getFn());
					cusList.get(guiMenu.getCustomerNumber()-1).getName().setSurname(guiMenu.getSn());
					cusList.get(guiMenu.getCustomerNumber()-1).getName().setTitle(guiMenu.getT());
					cusSerial.writeToFile();
					System.out.println("File written too");
					check = false;
				}
			}
		}
		return check;
	} // End of method
}
