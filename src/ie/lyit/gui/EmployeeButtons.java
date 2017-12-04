package ie.lyit.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class EmployeeButtons implements ActionListener, MouseListener{
	
	private JButton addEmp,salary,work;
	private JPanel employPanel,holder;

	public EmployeeButtons() {
		
		employPanel = new JPanel();
		holder = new JPanel();
		//holder.setPreferredSize(new Dimension(200,120));
		
		employPanel.setLayout(new GridLayout(2,2));
		employPanel.setPreferredSize(new Dimension(350,120));

		addEmp = new JButton("Add Employee");
		addEmp.setForeground(Color.BLACK);
		addEmp.setMnemonic('A');
		addEmp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		addEmp.addActionListener(this);
		addEmp.addMouseListener(this);

		salary = new JButton("Employee Salary");
		salary.setForeground(Color.BLACK);
		salary.setMnemonic('E');
		salary.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		salary.addActionListener(this);
		salary.addMouseListener(this);

		// Edit customer button
		work = new JButton("Work Schedule");
		work.setForeground(Color.BLACK);
		work.setMnemonic('W');
		work.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		work.addActionListener(this);
		work.addMouseListener(this);
		
		employPanel.add(addEmp);
		employPanel.add(salary);
		employPanel.add(work);
		holder.add(employPanel,new BorderLayout().WEST);
	}
	public JPanel getPanel(){
		return holder;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
