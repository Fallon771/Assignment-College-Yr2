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

public class RoomButtons implements ActionListener, MouseListener{
	
	private JButton available,booked,check;
	private JPanel room,holder;

	public RoomButtons() {
		
		room = new JPanel();
		holder = new JPanel();
		//holder.setPreferredSize(new Dimension(200,120));
		
		room.setLayout(new GridLayout(2,2));
		room.setPreferredSize(new Dimension(350,120));

		available = new JButton("Available Rooms");
		available.setForeground(Color.BLACK);
		available.setMnemonic('A');
		available.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		available.addActionListener(this);
		available.addMouseListener(this);

		booked = new JButton("Booked Rooms");
		booked.setForeground(Color.BLACK);
		booked.setMnemonic('B');
		booked.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		booked.addActionListener(this);
		booked.addMouseListener(this);

		// Edit customer button
		check = new JButton("Check Room");
		check.setForeground(Color.BLACK);
		check.setMnemonic('C');
		check.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		check.addActionListener(this);
		check.addMouseListener(this);
		
		room.add(available);
		room.add(booked);
		room.add(check);
		holder.add(room,new BorderLayout().WEST);
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
