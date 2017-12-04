package ie.lyit.serialize;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ie.lyit.gui.CustomerButtons;
import ie.lyit.testers.GuiTester;

public class GuiMenu implements GuiMenuDAO{

	private String t,fn,sn,address,pno,email;
	private String[] titles = {"dr","prof","mr","mrs","miss","ms"};

	private int cancelOption;
	private int customerNumber;
	private boolean goodInput = false;
	
	JTextField cusNum,title,firstName,surName,addr,phone,mail;
	CustomerSerializer cusSerial = new CustomerSerializer();
	//CustomerButtons cusBut;
	
	@Override
	public boolean checkInput(String input,String fn,String sn,String address,String pno,String email){
		for(int i=0;i< titles.length;i++){
			if(titles[i].equals(input.toLowerCase())){
				return true;		
			}	
		}
		if(fn == null){
			JOptionPane.showMessageDialog(null, "Please enter data for all fields");
		}
		JOptionPane.showMessageDialog(null, "Please enter a valid title!\nMr - Mrs - Miss - Dr - Ms - Prof");
		return false;		
	}
	@Override
	public void guiMenu(){
		
		boolean menuChoice = CustomerButtons.getStatus(); // This flag is used to check if ADD or Edit was press(Display correct customer number)
		
		cusNum = new JTextField();
		cusNum.setEditable(false);
	
		// Check to see if add customer or edit customer was selected...to display correct customer number in JTextField
		if(menuChoice == true){
		cusNum.setText(""+(cusSerial.getNum()+1));
		}
		else{
		cusNum.setText(""+customerNumber);
		}
		title = new JTextField();
		firstName = new JTextField();
		surName = new JTextField();
		addr = new JTextField();
		phone = new JTextField();
		mail = new JTextField();
		
		// Store in a object
		Object[] message = {
				"Customer Number:",cusNum,
				"Title",title,
				"First Name",firstName,
				"Surname",surName,
				"Address",addr,
				"Phone Number",phone,
				"Email",mail
		};
	
		do{
		int x = JOptionPane.showConfirmDialog(null, message,"Enter Customer Details:",JOptionPane.OK_CANCEL_OPTION);
		// If window close or cancel pressed..
		if(x == JOptionPane.CANCEL_OPTION || x == JOptionPane.CLOSED_OPTION){
			goodInput = true;
			cancelOption = x;
		}
		// if OK button was pressed and valid input ==> store info
			if(x == JOptionPane.OK_OPTION){
				
				this.t = title.getText();
				this.fn = firstName.getText();
				this.sn = surName.getText();
				this.address = addr.getText();
				this.pno = phone.getText();
				this.email = mail.getText();
				goodInput = checkInput(title.getText(),firstName.getText(),surName.getText(),addr.getText(),phone.getText(),mail.getText());	
				cancelOption = x;
			 }
		}while(!goodInput);		
	}
	
	// Method to get the user to enter a customer number ==> Used in delete, edit & view
	// Returns a number that must be checked
	public int getCusNumMenu() 
	{
		
		int number = -1;
		boolean badInput = true;
		cusNum = new JTextField();
		Object[] message = {"Customer Number:",cusNum};
		
		do{
		int x = JOptionPane.showConfirmDialog(null, message,"Enter Customer Number:",JOptionPane.OK_CANCEL_OPTION);
			try{
			// They entered a string,so we need to parse to an Integer
			number = Integer.parseInt(cusNum.getText());

			// They entered good input.. so break
			badInput = false;
			customerNumber = number;
			return number;
			}catch(Exception e){
				// If cancel or close window break loop
				if(x == JOptionPane.CANCEL_OPTION || x == JOptionPane.CLOSED_OPTION){
					badInput = false;
					break;
				}	
				JOptionPane.showMessageDialog(null, "Enter a valid number..");
				badInput = true;	
			} // End of try/catch		
		}while(badInput);
		
		// This will update the customer number in the add customer 
		customerNumber = cusSerial.getNum();
		return number;
	}
	
	// Getter's & Setter's
	public String getT() {
		return t;
	}
	public String getFn() {
		return fn;
	}
	public String getSn() {
		return sn;
	}
	public String getAddress() {
		return address;
	}
	public String getPno() {
		return pno;
	}
	public String getEmail() {
		return email;
	}
	public int getCancelOption(){
		return cancelOption;
	}
	public boolean getOption(){
		return goodInput;
	}
	public int getCustomerNumber(){
		return customerNumber;
	}
}
