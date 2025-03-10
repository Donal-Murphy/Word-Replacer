package ie.atu.sw;
/**
 * @author donal murphy
 * Contains main method. Initialises instances of FileManagerService, TextSimplifier and Menu.
 * Prompts menu to display main menu
 */
public class Runner {
	
	public static void main(String[] args) throws Exception {
		 
		FileManagerService fileManager = new FileManager();
		TextSimplifier textSimplifier = new TextFileSimplifier();
		
		Menu menu = new Menu(fileManager, textSimplifier);
		menu.mainMenu();

	}

	
}