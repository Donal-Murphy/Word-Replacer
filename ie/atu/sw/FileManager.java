package ie.atu.sw;

import java.io.*;
import java.util.HashMap;

/**
 * Implementation of FileManagerService. This class contains logic specific to the reading and 
 * writing of text files. Works with VectorMapperService to map text to HashMaps and 
 * PathValidatorService to validate the paths.
 * 
 * @see PathValidatorService
 * @see VectorMapperService
 */

public class FileManager implements FileManagerService {

	
	//---------------------------------------------------------------------------------------------
    // FIELDS
    //---------------------------------------------------------------------------------------------

	private final VectorMapperService vectorMapper; //Maps vectors to map
	private final PathValidatorService pathValidator; //Validates paths
	private String embeddingsFilePath = ""; //Location of word embeddings
	private String replacementWordsFilePath = ""; //Location of replacement words
	private String outputFilePath = ""; //Location of output file
	private String textFilePath = ""; //Location of text file

    
    //---------------------------------------------------------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------------------------------------------------------
	
	/**
	 * Initialises Filemanager with an instance of PathValidatorService and VectorMapperService
	 */
	public FileManager(){
		this.vectorMapper = new FileVectorMapper();
		this.pathValidator = new PathValidator();
	}
	
	//---------------------------------------------------------------------------------------------
    // MAIN LOGIC METHODS
    //---------------------------------------------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 * 
	 * This implementation is designed to work specifically with local text files.
	 * Time complexity: O(n) as the file is read line by line
	 */
	@Override
    public String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder(); //String builder to store content
        //Read file line by line and append to stringbuilder
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator()); //Include line separators
            }
        }
        return content.toString().trim();
    }
    
	/**
	 * {@inheritDoc}
	 * 
	 * This implementation is designed to work specifically with local text files
	 * Time Complexity: O(n) as the file is written line by line
	 */
	@Override
    // Method to write the processed text to an output file
    public void writeToFile(String simplifiedText, String outputFilePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            writer.write(simplifiedText);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

		
	//---------------------------------------------------------------------------------------------
	// HELPER METHODS
	//---------------------------------------------------------------------------------------------
    
	/**
	 * {@inheritDoc}
	 * 
	 * This implementation uses vectorMapper to load maps from the vectors of the files provided
	 * @see VectorMapperService
	 * 
	 * Time complexity: O(n+m), where n is the number of embeddings and m is the number of 
	 * replacement words. Both are linear operations of O(n)/O(m)
	 */
	@Override
    // Generates HashMaps that Map words to vectors
 	public void generateVectorMaps() {
 		if(embeddingsFilePath != null && replacementWordsFilePath != null) {
 			//Load the embeddings map
 			vectorMapper.setEmbeddingsMap(embeddingsFilePath);
 			//Load the google words map
 			vectorMapper.setReplacementWordsMap(replacementWordsFilePath);
 			
 			
 		}
 	}
    
    //---------------------------------------------------------------------------------------------
    // GETTERS & SETTERS
    //---------------------------------------------------------------------------------------------
	
	/**
	 * {@inheritDoc}
	 * 
	 * This implementation returns the local variable embeddingsFilePath
	 * Time Complexity: 0(1). Simple variable retrieval operation.
	 */
    @Override
	public String getEmbeddingsFilePath() {
		return embeddingsFilePath;
	}
	
    /**
	 * {@inheritDoc}
	 * 
	 * This implementation makes use of PathValidatorService to validate the path before setting it
	 * in a local variable.
	 * Time Complexity: 0(1) simple variable storage operation.
	 * @see PathValidatorService
	 */
    @Override
	public void setEmbeddingsFilePath(String embeddingsFilePath) throws Exception {
    	pathValidator.validateInputPath(embeddingsFilePath); //validate before setting
    	this.embeddingsFilePath = embeddingsFilePath;
    }
    
    /**
	 * {@inheritDoc}
	 * 
	 * This implementation returns the local variable replacementWordsFilePath
	 * Time Complexity: 0(1). Simple variable retrieval.
	 */
	@Override
	public String getReplacementWordsFilePath() {
		return replacementWordsFilePath;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * This implementation makes use of PathValidatorService to validate the path before setting it
	 * in a local variable.
	 * Time Complexity: 0(1) simple variable storage operation.
	 * @see PathValidatorService
	 */
    @Override
	public void setReplacementWordsFilePath(String googleWordsFilePath) throws Exception {
    	pathValidator.validateInputPath(googleWordsFilePath); //Validate before setting
        this.replacementWordsFilePath = googleWordsFilePath;
    }
    
    /**
	 * {@inheritDoc}
	 * 
	 * This implementation returns the local variable textFilePath
	 * Time Complexity: 0(1). Simple variable retrieval.
	 */
   	@Override
	public String getTextFilePath() {
   		return textFilePath;
   	}
   	
   	/**
	 * {@inheritDoc}
	 * 
	 * This implementation makes use of PathValidatorService to validate the path before setting it
	 * in a local variable. 
	 * Time Complexity: 0(1) simple variable storage operation.
	 * @see PathValidatorService
	 */
 	@Override
 	public void setTextFilePath(String textFilePath) throws Exception {
 		pathValidator.validateInputPath(textFilePath);
 		this.textFilePath = textFilePath;
 	}
   	
   	/**
	 * {@inheritDoc}
	 * 
	 * This implementation returns the local variable outputFilePath
	 * Time Complexity: 0(1). Simple variable retrieval.
	 */
	@Override
	public String getOutputFilePath() {
		return outputFilePath;
	}
   	
	/**
	 * {@inheritDoc}
	 * 
	 * This implementation makes use of PathValidatorService to validate the path before setting it
	 * in a local variable.
	 * Time Complexity: 0(1) simple variable storage operation.
	 * @see PathValidatorService
	 */
    @Override
	public void setOutputFilePath(String outputFilePath) throws Exception {
    	pathValidator.validateOutputPath(outputFilePath);
        this.outputFilePath = outputFilePath;
        
    }

    /**
	 * {@inheritDoc}
	 * 
	 * This implementation calls the local instance of VectorMapperService to create the vector map
	 * Time Complexity: 0(1). Simple variable retrieval.
	 * @see VectorMapperService
	 */
    @Override
	public HashMap<String, double[]> getReplacementWordsMap() {
    	return vectorMapper.getReplacementWordsMap();
    }
    
	/**
	 * {@inheritDoc}
	 * 
	 * This implementation calls the local instance of VectorMapperService to create the vector map
	 * Time Complexity: 0(1). Simple variable retrieval.
	 * @see VectorMapperService
	 */
    @Override
	public HashMap<String, double[]> getEmbeddingsMap() {
    	return vectorMapper.getEmbeddingsMap();
    }

}