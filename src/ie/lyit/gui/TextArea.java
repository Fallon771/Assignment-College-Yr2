package ie.lyit.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class TextArea  {
	
	public static JTextArea text;
	private String timeStamp;
	private static String welcome;
	private JPanel holder;
	private JScrollPane scroll;
	private String myFonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	private String size[] = {"10px","12px","14px","16px"};
	
	public TextArea() {
		init();
	}
	public void init(){
		
		holder = new JPanel();
	
		text = new JTextArea();	
		text.setBorder(new EmptyBorder(4,4,4,4));
		text.setBackground(Color.black);
		text.setForeground(Color.green);
		text.setEditable(false);
		text.setBorder(new EmptyBorder(10,10,10,10));
		welcome = "  ------------------\n" 
				+"    Welcome!!  \n"+"  ------------------\n" 
				+"       <-  )"+"\n        / (  \\" 
				+"\n        \\_\\_>"+"\n          \" \"\n"
				+"******************************************\n"
				+"[Features]\n"
				+"# Available in console or GUI  [Console is now Obsolete]\n"
				+"# Add,Edit,View,Delete,List Customers\n"
				+"# Change color theme & font\n"
				+"# Write data to file(customer.bin)\n"
				+"# Read from file\n"
				+"******************************************\n"
				+"[Updated Classes & Files]\n"
				+"# New GUI classes : GuiTester,CustomerSerializer,GuiMenu,ValidInput\n"
				+"# Console classes : CustomerSerializerTester,SerialMenu [Obsolete]\n"
				+"# SerialFiles (Folder): Contains icons and customer.bin\n\n"
				+"~ Author J.Fallon\n";
		timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		text.setText( welcome +"\n\nCurrent Time:\n"+timeStamp);
		
		// JScrollPane
		scroll = new JScrollPane(text);
		scroll.getHorizontalScrollBar().setEnabled(true);
		scroll.setWheelScrollingEnabled(true);	
		scroll.setVisible(true);
		scroll.setPreferredSize(new Dimension(500,600));	
		holder.add(scroll);		
	}
	public JPanel getPanel() {

	    return holder;
	}
	public static String getWelcome(){
		return welcome;
	}
	public static void setText(Font font) {
		text.setFont(font);
	}
	public void setColor(Color col){
		this.text.setBackground(col);
	}
	public static JTextArea getText() {
	    return text;
	}
}
