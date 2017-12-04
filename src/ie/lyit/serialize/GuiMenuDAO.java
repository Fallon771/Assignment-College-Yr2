package ie.lyit.serialize;

public interface GuiMenuDAO {

	// Gui menu interface for validating user input
	
	public void guiMenu();
	public boolean checkInput(String input,String fn,String sn,String address,String pno,String email);
	
}
