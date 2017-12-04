package ie.lyit.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import ie.lyit.interfaces.ConstructJFrame;
import ie.lyit.serialize.CustomerSerializer;

public class JFrameWindow implements ConstructJFrame {

	//private static JFrame frame;
	JMenuBar bar;
	static JFrame frame;
	CustomerSerializer cusSerial = new CustomerSerializer();
	JPanel text,tab;

	public JFrameWindow() {
		
		initComponents();
	}
	@Override
	public void initComponents(){
		
		frame = new JFrame();
		System.out.println(System.getProperty("user.dir"));
		
		frame.add(text = new TextArea().getPanel(),new BorderLayout().EAST);
		frame.add(tab = new TabbedPane().getPanel(),new BorderLayout().WEST);
		frame.setJMenuBar(bar = new TopMenuBar());
		
		frame.setSize(1200,600);
		frame.setTitle("Hotel Management Software");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setMinimumSize(new Dimension(800,500));
		frame.setVisible(true);

	}
	// Method for other classes to dispose of frame without invoking constructor
	public static void disposeFrame(){
		frame.dispose();
	}
	
}
