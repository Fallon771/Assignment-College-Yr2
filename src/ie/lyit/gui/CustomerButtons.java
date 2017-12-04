package ie.lyit.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ie.lyit.hotel.Customer;
import ie.lyit.hotel.Name;
import ie.lyit.serialize.CustomerSerializer;
import ie.lyit.serialize.GuiMenu;
import ie.lyit.serialize.ValidInput;
import ie.lyit.testers.GuiTester;

public class CustomerButtons extends JPanel implements ActionListener, MouseListener,Serializable{

	private static final long serialVersionUID = -2215090344094526024L;
	private JButton addCus,editCus,delCus,viewCus,list,exit;
	private static boolean displayNum  = true;

	TextArea text;
	GuiMenu guiMenu = new GuiMenu();
	CustomerSerializer cusSerial = new CustomerSerializer();
	ValidInput valid = new ValidInput();
	TopMenuBar top;
	LinkedList<Customer> cusList = cusSerial.getList();
	
	public CustomerButtons() {

		setLayout(new GridLayout(5,2));

		addCus = new JButton("Add Customer");
		addCus.setForeground(Color.BLACK);
		addCus.setMnemonic('A');
		addCus.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		addCus.addActionListener(this);
		addCus.addMouseListener(this);

		// Edit customer button
		editCus = new JButton("Edit Customer");
		editCus.setForeground(Color.BLACK);
		editCus.setMnemonic('E');
		editCus.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		editCus.addActionListener(this);
		editCus.addMouseListener(this);

		// Delete button
		delCus = new JButton("Delete Customer");
		delCus.setForeground(Color.BLACK);
		delCus.setMnemonic('D');
		delCus.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		delCus.addActionListener(this);
		delCus.addMouseListener(this);

		// View Button
		viewCus = new JButton("View Customer");
		viewCus.setForeground(Color.BLACK);
		viewCus.setMnemonic('V');
		viewCus.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		viewCus.addActionListener(this);
		viewCus.addMouseListener(this);

		// List button
		list = new JButton("List All");
		list.setForeground(Color.BLACK);
		list.setMnemonic('L');
		list.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		list.addActionListener(this);
		list.addMouseListener(this);

		// Exit Button
		exit = new JButton("Quit");
		exit.setForeground(Color.BLACK);
		exit.setMnemonic('Q');
		exit.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		exit.addActionListener(this);
		exit.addMouseListener(this);

		add(addCus);
		add(editCus);
		add(delCus);
		add(viewCus);
		add(list);
		add(exit);

		setBorder(new EmptyBorder(10,5,5,25));
		cusSerial.readFromFile();
	}
	
	public JPanel getPanel(){
		return this;
	}
	public static boolean getStatus(){
		return displayNum;
	}
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		boolean check = true;
		
		if(o == addCus){	
			displayNum = true;
			cusSerial.addCustomer();
		}
		// Edit customer details
		if(o == editCus){	
			displayNum = false;
			cusSerial.editCustomer();		
		}
		// List a customer (Only list actual customer that are stored on system,not removed)
		if(o == list){	
			cusSerial.listCustomer();			
		}
		// Delete a customer
		if(o == delCus){
			cusSerial.deleteCustomer();
		}
		// If view customer is selected
		if(o == viewCus){
			cusSerial.viewCustomer();
		}	
		// Yes / No option for quit program...dispose of JFrame & write to file
		if(o == exit){
			int x = JOptionPane.showConfirmDialog(null, "Save & Quit?", "Quit", JOptionPane.YES_NO_OPTION);
			if(x == 0){
				cusSerial.writeToFile();
				JOptionPane.showMessageDialog(null, "Saving work...\nGoodbye!","Saving customer list",JOptionPane.INFORMATION_MESSAGE);	
				JFrameWindow.disposeFrame();
			}	
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		Object obj;
		obj = e.getSource();
		// Customer button mouse over
		if(obj == addCus && TopMenuBar.getBlackTheme()){
			addCus.setBackground( new Color(0,230,0));
		}
		if(obj == addCus && TopMenuBar.getWhiteTheme()){
			addCus.setBackground( new Color(212,218,212));
		}
		if(obj == addCus && TopMenuBar.getKittyTheme()){
			addCus.setBackground( new Color(255,185,244));
		}
		// Edit button mouse over
		if(obj == editCus && TopMenuBar.getBlackTheme()){
			editCus.setBackground( new Color(0,230,0));
		}
		if(obj == editCus && TopMenuBar.getWhiteTheme()){
			editCus.setBackground( new Color(212,218,212));
		}
		if(obj == editCus && TopMenuBar.getKittyTheme()){
			editCus.setBackground( new Color(255,185,244));
		}
		// Delete customer button
		if(obj == delCus && TopMenuBar.getBlackTheme()){
			delCus.setBackground( new Color(0,230,0));
		}
		if(obj == delCus && TopMenuBar.getWhiteTheme()){
			delCus.setBackground( new Color(212,218,212));
		}
		if(obj == delCus && TopMenuBar.getKittyTheme()){
			delCus.setBackground( new Color(255,185,244));
		}

		//View Customer button
		if(obj == viewCus && TopMenuBar.getBlackTheme()){
			viewCus.setBackground( new Color(0,230,0));
		}
		if(obj == viewCus && TopMenuBar.getWhiteTheme()){
			viewCus.setBackground( new Color(212,218,212));
		}
		if(obj == viewCus && TopMenuBar.getKittyTheme()){
			viewCus.setBackground( new Color(255,185,244));
		}

		// List customer button
		if(obj == list && TopMenuBar.getBlackTheme()){
			list.setBackground( new Color(0,230,0));
		}
		if(obj == list && TopMenuBar.getWhiteTheme()){
			list.setBackground( new Color(212,218,212));
		}
		if(obj == list && TopMenuBar.getKittyTheme()){
			list.setBackground( new Color(255,185,244));
		}	
		// Exit button
		if(obj == exit && TopMenuBar.getBlackTheme()){
			exit.setBackground( new Color(0,230,0));
		}
		if(obj == exit && TopMenuBar.getWhiteTheme()){
			exit.setBackground( new Color(212,218,212));
		}
		if(obj == exit && TopMenuBar.getKittyTheme()){
			exit.setBackground( new Color(255,185,244));
		}	
	}
	public void mouseExited(MouseEvent e) {

		// Reset colors when hover
		Object hover = e.getSource();
		hover = e.getSource();
		if(hover == addCus){
			addCus.setBackground(null);
		}
		if(hover == delCus){
			delCus.setBackground(null);
		}
		if(hover == exit){
			exit.setBackground(null);
		}
		if(hover == editCus){
			editCus.setBackground(null);
		}
		if(hover == viewCus){
			viewCus.setBackground(null);
		}
		if(hover == list){
			list.setBackground(null);
		}

	}

}
