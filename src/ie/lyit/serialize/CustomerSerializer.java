package ie.lyit.serialize;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import ie.lyit.gui.*;
import ie.lyit.hotel.Customer;
import ie.lyit.interfaces.*;

public class CustomerSerializer implements CustomerSerialDAO{

	private LinkedList <Customer> customerList = new LinkedList<>(); // Create our customer array list to store customer objects
	private  static int numOfCustomers;   // Store the number of customers in array,used as customer number
	private String timeStamp;
	
	//Customer cus = new Customer(); // Customer object
	Customer cus;
	ValidInput valid = new ValidInput();;
	GuiMenu guiMenu;
	//String otherFile;
	CustomerSerializer cusSerial;

	// Used to display the correct cusotmer number in ADD or Edit
	private static boolean displayNum = true;

    @Override
	public void addCustomer(){

		displayNum = true;
		boolean check = false;
		guiMenu = new GuiMenu();
		guiMenu.guiMenu();
		do{
		// Validate user input, returns true if fields are correct	
		check = valid.validateAddCustomer(guiMenu, cusSerial, check);
		if(check){
		customerList.add(numOfCustomers,new Customer(guiMenu.getT(),guiMenu.getFn(),guiMenu.getSn(),guiMenu.getAddress(),guiMenu.getPno(),guiMenu.getEmail())); // Create new customer after last customer in array
		numOfCustomers++; 
		writeToFile();
		listCustomer();
		  }
		}while(check == false  && guiMenu.getCancelOption() != -1 && guiMenu.getCancelOption() != 2);		
	}
    @Override
	@SuppressWarnings("unchecked")
	public void readFromFile(){
		LinkedList <Customer> list; // Create another list to read customer objects into

		// Try read from the file
		try{
			final String FILENAME = "..//Software-Implementation//SerialFiles//customer.bin";
			Path p = Paths.get(FILENAME);  // Create our path object

			// If file exists read data
			if(Files.exists(p)) {
				System.out.println("----------------------------");
				System.out.println("Reading from file...");

				FileInputStream fi = new FileInputStream(FILENAME);
				ObjectInputStream os = new ObjectInputStream(fi);

				// Read data & store data into array-list (list)
				list = (LinkedList<Customer>)os.readObject();

				// Loop through list and copy data into the customerList array 
				for(int i = 0; i < list.size();i++){

					customerList.add(list.get(i));
					// Set the number of customers to array size
					numOfCustomers = list.size();
				}		
				System.out.print("Data loaded sucessfully...\n");	
				System.out.println("----------------------------\n");
				// Close streams
				os.close();
				fi.close();	
			}
		}
		catch(FileNotFoundException fNf){
			System.out.println("Can't find file...");
			System.out.println(fNf.getLocalizedMessage());
		}
		catch(IOException io){
			System.out.println(io.getMessage());
			System.out.println("Check serial ID...\nCheck data being read");
		}	
		catch(Exception e){
			System.out.println(e.getLocalizedMessage());
		}	
	}	
    @Override
	public void writeToFile(){

		numOfCustomers = customerList.size(); // Get the number of customers stored in array list
		final String FILENAME = "..//Software-Implementation//SerialFiles//customer.bin";
		Path p = Paths.get(FILENAME);
		// If array-list objects implements serializable & file exists..
		if(customerList instanceof Serializable && Files.exists(p)){
			try{

				FileOutputStream fo = new FileOutputStream(FILENAME);
				ObjectOutputStream os = new ObjectOutputStream(fo);

				// Write customerList to customer.bin
				os.writeObject(customerList);
				// Write the number of customers we have in the array-list
				os.writeInt(numOfCustomers);

				System.out.println("\nSaving data...");
				// Close streams	
				os.close();
				fo.close();	
			}
			// Catch Exceptions
			catch(FileNotFoundException e){
				System.out.println("File not found...\n"+e.getMessage());
			}
			catch(IOException io){
				System.out.println("Error initalizing stream...");
			}
			catch(Exception w){
				System.out.println("Wrinting to file error...\n\n"+w.getMessage());
			}
		}	
	} 
    @Override
	public void viewCustomer(){

		int cusNum;
		guiMenu = new GuiMenu();
		cusNum = guiMenu.getCusNumMenu();
		cus = Customer.getIntance();

		try {
			if(cusNum < -1){
				JOptionPane.showMessageDialog(null, "Customer not found!","Error",JOptionPane.ERROR_MESSAGE);		
			}
			else if((cusNum > customerList.size() )){
				JOptionPane.showMessageDialog(null, "Customer not found!","Error",JOptionPane.ERROR_MESSAGE);	
			}
			// Check of customer is't already removed from system
			if(customerList.get(cusNum-1).getName().getFirstName().equals("REMOVED FROM SYSTEM")) {
				JOptionPane.showMessageDialog(null, "Customer not longer exists!","Error",JOptionPane.ERROR_MESSAGE);
			}
			// Display customer 
			else{
				cus = customerList.get(cusNum-1);
				TextArea.text.setText("Customer Number:"+cusNum+cus);
			}
		}
		catch(IndexOutOfBoundsException y) {
		}
	}
    @Override
	public void editCustomer(){

		boolean check = true;
		guiMenu = new GuiMenu();

		do{
			// Call Menu to get user input
			try {
				// Check to see if user enters a valid customer number
				int cusNum = guiMenu.getCusNumMenu();
				System.out.println("Debug 0:"+cusNum);
				if(cusNum > customerList.size()+1 || cusNum < -1 || cusNum == 0){
					JOptionPane.showMessageDialog(null, "Customer not found!","Error",JOptionPane.ERROR_MESSAGE);
				}
				// If cancel or close pressed in window..just exit
				if(cusNum == -1){	
					check = false;		
				}
				// If they select a user that is removed...loop again
				else if(customerList.get(cusNum-1).getName().getFirstName().equals("REMOVED FROM SYSTEM")){
					JOptionPane.showMessageDialog(null, "Customer no longer exists!","Error",JOptionPane.ERROR_MESSAGE);					
				}
				// Else call menu
				else{
					guiMenu.guiMenu();
					//Call validateCustomer ==> validate and store customer
					check = valid.validateEditCustomer(guiMenu,cusSerial,customerList, check);
				}
				if(cusNum == JOptionPane.CANCEL_OPTION){	
					check = false;
					System.out.println("Debug 1\n");
					System.out.println(check);
				}
			} 
			catch (Exception e1) {
				System.out.println(e1.getMessage());
			} // End of try - catch
			// Check if menu fields contain blank input..if yes then loop
			System.out.println("Print check:"+check);
		}while(check == true  && guiMenu.getCancelOption() != -1 && guiMenu.getCancelOption() != 2);	
	}
    @Override
	public void deleteCustomer(){

		int cusNum = 0;
		guiMenu = new GuiMenu();

		// Check if valid number was entered
		try {
			cusNum = guiMenu.getCusNumMenu();
			// Check to see if valid number was entered and customer ist already removed from system

			if(cusNum > customerList.size() || cusNum < -1 || cusNum == 0 || customerList.get(cusNum-1).getName().getFirstName().equals("REMOVED FROM SYSTEM")){
				JOptionPane.showMessageDialog(null, "Customer not found!","Error",JOptionPane.ERROR_MESSAGE);
			}
			else if(cusNum == -1){	
				System.out.println("DEBUG: Error in delete customer - return -1");
			}
			// Remove customer(To keep each customer number unique,I just put the fields blank instead of rearranging array-list)
			else{
				// Confirm if user wants to delete...
				int confirm = JOptionPane.showConfirmDialog(null, "Remove customer: "+cusNum+"?"+"\n-------------------------------"
						+"\n Title:"+customerList.get(cusNum -1).getName().getTitle()
						+"\n First Name:"+customerList.get(cusNum -1).getName().getFirstName()
						+"\n Surname:"+customerList.get(cusNum -1).getName().getSurname()
						+"\n Address:"+customerList.get(cusNum -1).getAddress()
						+"\n Phone:"+customerList.get(cusNum -1).getPhoneNumber()
						+"\n Email:"+customerList.get(cusNum -1).getEmailAddress()
						, 
						"Remove Customer", JOptionPane.WARNING_MESSAGE);

				// If OK...then set fields to blank values (Removing completely shifts array-list order,so this is a solution to fix)
				if(confirm == JOptionPane.OK_OPTION){

					customerList.get(cusNum -1).setAddress("");
					customerList.get(cusNum -1).setEmailAddress("");
					customerList.get(cusNum -1).setPhoneNumber("");
					customerList.get(cusNum -1).getName().setFirstName("REMOVED FROM SYSTEM");
					customerList.get(cusNum -1).getName().setSurname("");
					customerList.get(cusNum -1).getName().setTitle("");
					numOfCustomers = customerList.size();
					writeToFile();
					TextArea.text.setText("--------------------------------------------------\n"
							+"Customer: "+cusNum+" removed from system.\n"
							+"--------------------------------------------------");
				}
			}
		}
		catch(Exception ex){

			// Create a timestamp with error
			timeStamp = new SimpleDateFormat("dd-MM-yyyy || HH:mm:ss").format(Calendar.getInstance().getTime());
			System.out.println(timeStamp+"\nBug caught: AWT index -2 out of bounds error\nLevel:Not serious");
		}	
	}
    
    public void deleteCustomers(){
    	
    	/* [ EXAMPLE ]
    	 * 
    	 *  Do some logic to remove a customer,then print to View
    	 *  
    	 */
    	TextArea.text.setText("Cusotmer Deleted");			
    }
    @Override
	public void listCustomer(){
	
		int num = 0;
		TextArea.getText().setText("Listing Customers Stored In File...\n");
		for(int i = 0 ;i < customerList.size();i++){
		
			// Loop through list and count number of removed customers that match string
			if(customerList.get(i).getName().getFirstName().equals("REMOVED FROM SYSTEM")){
				num++;		
			}
			// Display current customers stored in system
			else{
				TextArea.getText().append("-------------------------------------------");
				TextArea.getText().append("\nCustomer Number: "+(i+1)+customerList.get(i));
			}
		}
		// Display number of removed customers
		TextArea.getText().append("\n-------------------------------------------\n");
		TextArea.getText().append("Removed: "+num+ " customers in total.");
	}

	// Getters's & Setter's
	public int getNum(){
		return numOfCustomers;
	}
	public void setNum(int number){
		numOfCustomers = number;
	}
	public LinkedList<Customer> getList(){
		return customerList;
	}
	public static boolean getStatus(){
		return displayNum;
	}
} // End of class
