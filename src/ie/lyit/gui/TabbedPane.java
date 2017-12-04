package ie.lyit.gui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabbedPane {
	
	private JTabbedPane tab;
	
	CustomerButtons cusButt;
	JPanel employ,room,holder;

	public TabbedPane() {
		
		tab = new JTabbedPane();
		holder = new JPanel();
		
		tab.setPreferredSize(new Dimension(400,300));
		tab.addTab("Guests", cusButt = new CustomerButtons());
		tab.addTab("Employees", employ = new EmployeeButtons().getPanel());
		tab.addTab("Bookings", room = new RoomButtons().getPanel());
		//tab.addTab("Weddings",cusButt = new CustomerButtons());
		//tab.addTab("E-Mail",blank);
		tab.setVisible(true);
		
		holder.add(tab);	
	}	
	public JPanel getPanel(){
		return holder;
	}
}
