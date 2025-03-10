package ie.atu.sw;

import static java.lang.System.out;

/**
 * Menu class responsible for presenting information to the user and handles 
 * interactions between TextSimplifier and Filemanager by passing objects between them. 
 * Also launches the text simplifier calculations as well as various getters and setters for configuration
 */

import java.util.Scanner;

public class Menu {


	//---------------------------------------------------------------------------------------------
    //                                    FIELDS
    //---------------------------------------------------------------------------------------------

	// Store references
	private Scanner s = new Scanner(System.in);;
	private TextSimplifier textSimplifier;
	private boolean keepRunning = true;
	private FileManagerService fileManager;
	
	/**
	 * Initialises fileManager and textSimplifier objects with dependency injection
	 * @param fileManager injected instance of filemanager
	 * @param textSimplifier injected instance of textsimplifier
	 */
	public Menu(FileManagerService fileManager, TextSimplifier textSimplifier) {
		this.fileManager = fileManager;
		this.textSimplifier = textSimplifier;
	}
	

	//---------------------------------------------------------------------------------------------
    //                                 MAIN LOGIC METHODS
    //---------------------------------------------------------------------------------------------

	/**
	 * Logic for main menu interactions
	 * Time complexity: 0(1). Simple switch case
	 */
	public void mainMenu() {
		while(keepRunning) {
		printMainMenu(); // Display main menu to user

		int choice = menuInputValidation(6); // Validate user input

		// Execute methods/sub-menus based on user input
		switch (choice) {
			case 1 -> configureFilePath(FileType.EMBED_FILE); // Specify embedding file
			case 2 -> configureFilePath(FileType.REPLACEMENT_FILE); // Specify a Google1000 file
			case 3 -> configureFilePath(FileType.TEXT_FILE); // Specify a Text file
			case 4 -> configureFilePath(FileType.OUTPUT_FILE); // Specify an output file
			case 5 -> chooseDistanceCalculator();
			case 6 -> runTextSimplifier();
			case 7 -> { // Close Program
				out.println(ConsoleColour.BLACK_BRIGHT);
				out.println("[INFO] Exiting... Bye!");
				keepRunning = false;
			}
			default -> criticalError(); // Shouldn't occur; menuInputValidation prevents invalid
										// choices
		}
		}

	}
	
	/**
	 * Logic for distance calculator submenu interactions
	 * Time complexity: 0(1). Simple switch case
	 */
    private void chooseDistanceCalculator() {
       
    	printCalculatorMenu(); //display the submenu
    	
        int choice = menuInputValidation(3);

        switch (choice) {
            case 1 -> {
                textSimplifier.setSimilarityCalculator(CalculatorType.DOT_PRODUCT); //set dot product
                out.println(ConsoleColour.GREEN);
                out.println("[INFO] Dot Product Calculator selected.");
            }
            case 2 -> {
            	textSimplifier.setSimilarityCalculator(CalculatorType.COSINE); //set cosine
                out.println(ConsoleColour.GREEN);
                out.println("[INFO] Cosine Similarity Calculator selected.");
            }
            case 3 -> {
            	textSimplifier.setSimilarityCalculator(CalculatorType.EUCLIDEAN); //set euclidean
                out.println(ConsoleColour.GREEN);
                out.println("[INFO] Euclidean Distance Calculator selected.");
            }
            default -> criticalError();
        }
    }

	//---------------------------------------------------------------------------------------------
    //                                    DISPLAY METHODS
    //---------------------------------------------------------------------------------------------

    /**
     * 	UI for main menu
     */
	// Display main menu
	private void printMainMenu() {
		out.println(ConsoleColour.WHITE);
		out.println("************************************************************");
		out.println("*     ATU - Dept. of Computer Science & Applied Physics    *");
		out.println("*                                                          *");
		out.println("*             Virtual Threaded Text Simplifier             *");
		out.println("*                                                          *");
		out.println("************************************************************");
		out.println("(1) Specify Embeddings File");
		out.println("(2) Specify Google 1000 File");
		out.println("(3) Specify a Text File");
		out.println("(4) Specify an Output File");
		out.println("(5) Choose Distance Calculator");
		out.println("(6) Execute, Analyse and Report");
		out.println("(7) Quit");
	}
	
	/**
	 * UI for calculator options sub menu
	 */
	private void printCalculatorMenu() {
		 out.println(ConsoleColour.WHITE);
	        out.println("Choose a Distance Calculator:");
	        out.println("(1) Dot Product");
	        out.println("(2) Cosine Similarity");
	        out.println("(3) Euclidean Distance");
	}

	//---------------------------------------------------------------------------------------------
    //                                    HELPER METHODS
    //---------------------------------------------------------------------------------------------

	/**
	 * Logic for configuration of filepaths. Uses fileType Enum to set the appropriate local 
	 * variable for file paths in FileManager
	 * @see FileManagerService
	 * @see FileType
	 * @param fileType
	 */
	// Change input/output files
	private void configureFilePath(FileType fileType) {
	    while (true) {
	        String currentFilePath = "";
	        String filePrompt = "";

	        // Determine the current file path and prompt message based on fileType
	        if (fileType == FileType.EMBED_FILE) {
	            currentFilePath = fileManager.getEmbeddingsFilePath();
	            filePrompt = "Please Enter the Full Path to the Desired Embeddings File>";
	        } else if (fileType == FileType.REPLACEMENT_FILE) {
	            currentFilePath = fileManager.getReplacementWordsFilePath();
	            filePrompt = "Please Enter the Full Path to the Desired Google1000 File>";
	        } else if (fileType == FileType.OUTPUT_FILE) {
	            currentFilePath = fileManager.getOutputFilePath();
	            filePrompt = "Please Enter the Full Path to the Desired Output File>";
	        } else if (fileType == FileType.TEXT_FILE) {
	            currentFilePath = fileManager.getTextFilePath();
	            filePrompt = "Please Enter the Full Path to the Desired Text File>";
	        } else {
	            out.println("[Error] Unknown file type.");
	            break;
	        }

	        // Display the current file path and prompt the user for input
	        out.println("\nCurrent File: " + currentFilePath);
	        out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
	        out.println(filePrompt);
	        String path = s.nextLine().trim();  // User input

	        // Validate user input
	        if (path.isEmpty()) {
	            out.print(ConsoleColour.RED);
	            System.out.println("[Error] Input was empty. Please enter a valid option.");
	            continue;
	        }

	        // Set the appropriate file path
	        try {
		        switch (fileType) {
		            case EMBED_FILE:
						fileManager.setEmbeddingsFilePath(path);
		                break;
		            case REPLACEMENT_FILE:
		                fileManager.setReplacementWordsFilePath(path);
		                break;
		            case OUTPUT_FILE:
		                fileManager.setOutputFilePath(path);
		                break;
		            case TEXT_FILE:
		                fileManager.setTextFilePath(path);
		                break;
		        }
	        }catch (Exception e) {
	        	e.printStackTrace();
	        }

	        // Exit the loop after successful input
	        break;
	    }
	}
	
	/**
	 * Initialises FileManager and TextSimplifier variables before Executing TextSimplifier by 
	 * calling .processTextFile. This method first calls fileManager.generateVectorMaps() to 
	 * generate HashMap variables. It also retrieves the path names from filemanager to check that 
	 * they are not empty. It then calls on the filemanager to retrieve the content from the files 
	 * before passing it to textsimplifier. Finally it calls on textsimplifier to 
	 * write the resulting text to the output file
	 */
	private void runTextSimplifier() {
		 try {	
			 	//Generate vector maps
			 	fileManager.generateVectorMaps();
			 	
			 	 // Ensure maps are loaded before creating TextFileSimplifier
	            if (fileManager.getEmbeddingsMap() == null || fileManager.getReplacementWordsMap() == null) {
	                out.println("[ERROR] Please load the necessary files first.");
	                return;
	            }

	            // Send maps to TextSimplifier
	            textSimplifier.setEmbeddingsMap(fileManager.getEmbeddingsMap());
	            textSimplifier.setReplacementWordsMap(fileManager.getReplacementWordsMap());

	            out.println("[INFO] Text Simplifier Initialized Successfully!");

	            String inputFilePath = fileManager.getTextFilePath();
	            String outputFilePath = fileManager.getOutputFilePath();

	            if (inputFilePath.isEmpty() || outputFilePath.isEmpty()) {
	                out.println(ConsoleColour.RED);
	                out.println("[Error] Please specify both the input and output file paths first.");
	                return;
	            }
	            
	            String content = fileManager.readFile(inputFilePath);
	            String simplifiedText = textSimplifier.processText(content);
	            fileManager.writeToFile(simplifiedText, outputFilePath);
	            
	            
	            //fileManager.writeOutputFile(inputFilePath, outputFilePath, textSimplifier);
	        } catch (Exception e) {
	            out.println(ConsoleColour.RED);
	            out.println("[Error] An issue occurred during text simplification: " + e.getMessage());
	        }
	    }
	/**
	 * Used to Validate the user input for menu navigation. Does not allow  the user to submit empty
	 *  values and checks that input values are withing the correct integer range
	 * @param numOptions The number of options available to the user in each menu/submenu
	 * @return an integer containing the users input
	 */
	// Validates user input for menu options
	private int menuInputValidation(int numOptions) {
	    boolean validInput = false;

	    while (!validInput) {
	        out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
	        out.println("Select Option [1-" + numOptions + "]>"); // present options

	        String input = s.nextLine().trim();  // Read and trim input

	        if (input.isEmpty()) {  // Check if input is empty
	            out.print(ConsoleColour.RED);
	            System.out.println("[Error] Input was empty. Please enter a valid option.");
	        } else if (input.matches("\\d+")) {  // Check if input is an integer
	            int choice = Integer.parseInt(input);
	            if (choice >= 1 && choice <= numOptions) {  // Check if the integer is within bounds
	                validInput = true;
	                return choice;
	            } else {  // If user has entered an out-of-bounds integer
	                out.print(ConsoleColour.RED);
	                out.println("[Error] Not a valid integer. Please enter a number between 1 and "
	                + numOptions + ".");
	            }
	        } else {  // If user has entered a non-integer
	            out.print(ConsoleColour.RED);
	            System.out.println("[Error] Input was not an integer. Please enter a valid option.");
	        }
	    }

	    return -1;
	}
	
	//---------------------------------------------------------------------------------------------
    //                                    UTILITY METHODS
    //---------------------------------------------------------------------------------------------

	/**
	 * Method to handle any unexpected errors. Should not occur.
	 */
	// Displays an error if something unexpected happens during user input for menu options
	// Mostly for debug purposes as menuInputValidation should prevent this
	private void criticalError() {
		out.print(ConsoleColour.RED_BOLD);
		out.println("[CRITICAL ERROR] Something unexpected happened while processing user input."
				+ " Please check code.");
		System.exit(0); // Close program
	}

}
