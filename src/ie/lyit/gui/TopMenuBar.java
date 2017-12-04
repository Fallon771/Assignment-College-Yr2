package ie.lyit.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ie.lyit.serialize.CustomerSerializer;
import ie.lyit.testers.GuiTester;

public class TopMenuBar extends JMenuBar implements ActionListener, ListSelectionListener{

	private static final long serialVersionUID = 7968744300849649674L;

	private JMenuItem openFile,quit,about,fonts,color,save,fontSize,connect,ftp;
	private JMenu file,help,appear,edit;
	private JRadioButtonMenuItem rbWhite,rbBlack,kitty;
	private JList listFonts,listFontSize;

	private JSeparator sep;
	private ImageIcon icon;

	private int fontNumber = 245;

	private JScrollPane fontScroll;
	private static boolean blackT;
	private static boolean whiteT;
	private static boolean kittyT;

	private String myFonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	private String size[] = {"10px","12px","14px","16px"};

	TextArea tex;
	CustomerSerializer cusSerial;

	public TopMenuBar() {

		//JMenuBar menuBar = new JMenuBar();

		file = new JMenu("File");
		file.setBorder(new EmptyBorder(5,5,5,5));
		file.setMnemonic('F');

		appear = new JMenu("Appearance");
		appear.setMnemonic('P');

		help = new JMenu("Help");
		help.setMnemonic('H');
		help.setBorder(new EmptyBorder(5,5,5,5));

		edit = new JMenu();
		edit.setText("Edit");
		edit.setMnemonic('e');
		edit.setBorder(new EmptyBorder(5,5,5,5));

		// Submenu's on menubar
		// File children
		openFile = new JMenuItem();
		openFile.addActionListener(this);
		openFile.setIcon(icon = new ImageIcon("..//Software-Implementation//SerialFiles//file.png"));
		openFile.setBorder(new EmptyBorder(5,3,5,5));
		openFile.setText("  Open File    ");
		openFile.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		openFile.setMnemonic('O');

		save = new JMenuItem();
		save.addActionListener(this);
		save.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		save.setIcon(icon = new ImageIcon("..//Software-Implementation//SerialFiles//save.png"));
		save.setBorder(new EmptyBorder(5,3,5,5));
		save.setText("  Save");
		save.setMnemonic('S');

		quit = new JMenuItem();
		quit.setBorder(new EmptyBorder(5,3,5,20));
		quit.setIcon(icon = new ImageIcon("..//Software-Implementation//SerialFiles//quit.png"));
		quit.addActionListener(this);
		quit.setText("  Quit");
		quit.setMnemonic('q');

		// Edit children
		// FTP ==> connect to a server with ftp( Not implemented yet)
		ftp = new JMenuItem();
		ftp.setBorder(new EmptyBorder(5,3,5,20));
		ftp.setText("Ftp");
		ftp.setIcon(icon = new ImageIcon("..//Software-Implementation//SerialFiles//ftp.png"));
		ftp.setMnemonic('F');
		ftp.addActionListener(this);

		// Connect to database
		connect = new JMenuItem();
		connect.setText("Connect to Database");
		connect.setBorder(new EmptyBorder(5,3,5,20));
		connect.setIcon(icon = new ImageIcon("..//Software-Implementation//SerialFiles//connect.png"));
		connect.setMnemonic('C');
		connect.addActionListener(this);

		// Fonts & font size  	
		listFonts = new JList(myFonts);
		listFonts.setSelectedIndex(6);
		listFonts.setVisibleRowCount(10);
		listFonts.setBorder(new EmptyBorder(5,5,5,5));
		listFonts.addListSelectionListener(this);

		// Place list in scroll pane
		fontScroll = new JScrollPane(listFonts);

		listFontSize = new JList(size);
		listFontSize.setBorder(new EmptyBorder(5,3,5,10));
		listFontSize.setSelectionMode(0);
		listFontSize.setVisibleRowCount(4);
		listFontSize.addListSelectionListener(this);

		// Apearance children
		fonts = new JMenu();
		fonts.setText("   Fonts");
		fonts.setIcon(icon = new ImageIcon("..//Software-Implementation//SerialFiles//font.gif"));
		fonts.setBorder(new EmptyBorder(5,3,5,20));
		fonts.addActionListener(this);

		fontSize = new JMenu();
		fontSize.setIcon(icon = new ImageIcon("..//Software-Implementation//SerialFiles//fontsize.png"));
		fontSize.setText("   Font size");
		fontSize.setBorder(new EmptyBorder(5,3,5,20));
		fontSize.addActionListener(this);

		// Color menu
		color = new JMenu();
		color.setText("   Color Themes");
		color.setIcon(icon = new ImageIcon("..//Software-Implementation//SerialFiles//color.png"));
		color.setBorder(new EmptyBorder(5,3,5,20));
		color.addActionListener(this);

		//Radio buttons -- white theme
		ButtonGroup group = new ButtonGroup();
		rbWhite = new JRadioButtonMenuItem();
		rbWhite.setBorder(new EmptyBorder(5,3,5,20));
		rbWhite.setText("White Theme");
		rbWhite.addActionListener(this);
		rbWhite.setSelected(true);

		// Black theme
		rbBlack = new JRadioButtonMenuItem();
		rbBlack.setSelected(true);
		rbBlack.addActionListener(this);
		rbBlack.setText("Dark Theme");
		blackT = true;

		// Hello kitty theme
		kitty = new JRadioButtonMenuItem();
		kitty.addActionListener(this);
		kitty.setText("Hello Kitty Theme");

		// Group radio buttons
		group.add(rbBlack);
		group.add(rbWhite);
		group.add(kitty);

		fonts.add(fontScroll);
		fontSize.add(listFontSize);
		// Adding radio buttons to parent
		color.add(rbBlack);
		color.add(rbWhite);
		color.add(kitty);

		// Help children
		about = new JMenuItem();
		about.setText("About");
		about.setBorder(new EmptyBorder(5,0,5,50));
		about.addActionListener(this);
		about.setIcon(icon = new ImageIcon("..//Software-Implementation//SerialFiles//help.png"));

		// Adding children to file
		file.add(openFile);
		file.addSeparator();
		file.add(save);
		file.addSeparator();
		file.add(quit);

		// Adding children to edit
		edit.add(ftp);
		edit.addSeparator();
		edit.add(connect);

		// children to appearance
		appear.add(fonts);
		appear.addSeparator();
		appear.add(fontSize);
		appear.addSeparator();
		appear.add(color);

		// Adding children to help
		help.add(about);

		// Adding Components to menubar
		add(file);
		add(edit);
		add(appear);
		add(help);
	}
	public static  boolean getBlackTheme(){
		if(blackT){
			return blackT;
		}
		else
			return false;
	}
	public static  boolean getWhiteTheme(){
		if(whiteT){
			return whiteT;
		}
		else
			return false;
	}
	public static  boolean getKittyTheme(){
		if(kittyT){
			return kittyT;
		}
		else
			return false;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object p = e.getSource();
		
		cusSerial = new CustomerSerializer();

		if(p == quit){
			// Ask if user wants to quit then write to file
			int x = JOptionPane.showConfirmDialog(null, "Quit?", "Quit", JOptionPane.YES_NO_OPTION);
			System.out.println(x);
			if(x == 0){
				//cusSerial.writeToFile();
				JFrameWindow.disposeFrame();		
			}
		}
		// Save to file
		if(p == save){
			cusSerial.readFromFile();
			cusSerial.writeToFile();
			JOptionPane.showMessageDialog(null, "Saved..","Saving customer list",JOptionPane.INFORMATION_MESSAGE);	
		}
		// Be be impemented at a later date when I have free time
		if(p == ftp || p == connect || p == openFile){
			JOptionPane.showMessageDialog(null, "Feature not implemented yet.\nWill try to finish at later date when I have more time","Future features",JOptionPane.WARNING_MESSAGE);
		}
		if(p == about){
			JOptionPane.showMessageDialog(null,"Hotel Mangement Software\n"
					+"------------------------------------------\n"
					+"        **    Version 1.00   **\n"
					+"------------------------------------------\n"
					+"1:Add customer to list\n"
					+"2:Edit customers in list\n"
					+"2:Delete a customer in list\n"
					+"3:View a specific customer\n"
					+"4:List all customers in list\n"
					+"------------------------------------------\n"
					+"# Writes list into file\n"
					+"# Read from file\n"
					+"# Change font in text area\n"
					+"# Change color theme in text area\n"
					+"------------------------------------------\n"
					+"Author J.Fallon\n"
					+"L00131059\nBsc Computing Yr.3","Info",JOptionPane.INFORMATION_MESSAGE);
		}
		// Color themes
		if(p == rbWhite){
			TextArea.getText().setBackground(Color.WHITE);
			TextArea.getText().setForeground(Color.BLACK);
			//menuBar.setBackground(new Color(212,218,212));

			whiteT = true;
			blackT = false;
			kittyT = false;
		}
		// Dark theme
		if(p == rbBlack){
			TextArea.getText().setBackground(Color.BLACK);
			TextArea.getText().setForeground(Color.GREEN);
			blackT = true;
			whiteT = false;
			kittyT = false;
		}
		// Hello kitty theme
		if(p == kitty){
			TextArea.getText().setBackground(new Color(206,91,187));
			TextArea.getText().setForeground(new Color(255,255,255));
			kittyT = true;
			blackT = false;
			whiteT = false;	
		}		
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		Object o = e.getSource();
		int size =  listFontSize.getSelectedIndex();

		if(o == listFonts){
			int num = e.getFirstIndex();
			fontNumber = num;  
			TextArea.setText(new Font(myFonts[num],Font.PLAIN,13));
		}   
		// Change font size according to array index number.. ie 0 = 10px
		if(size == 0){
			TextArea.getText().setFont(new Font(myFonts[fontNumber],Font.PLAIN,10));
		} 
		if(size == 1){
			TextArea.getText().setFont(new Font(myFonts[fontNumber],Font.PLAIN,12));
		}
		if(size == 2){
			TextArea.getText().setFont(new Font(myFonts[fontNumber],Font.PLAIN,14));
		} 
		if(size == 3){
			TextArea.getText().setFont(new Font(myFonts[fontNumber],Font.PLAIN,16));
		} 	
	}
}
