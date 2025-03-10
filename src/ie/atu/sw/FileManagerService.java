package ie.atu.sw;

import java.io.IOException;
import java.util.HashMap;

/**
 * Interface method used for I/O operations. Includes calls to create vector maps and 
 * Reads/Writes to files/locations.
 */

public interface FileManagerService {
	
	/**
	 * Method for reading a file/location. 
	 * @param filePath path to the file/location to be read
	 * @return Returns a string of text read in from the file/location.
	 * @throws IOException If file/location cannot be read
	 */
	String readFile(String filePath) throws IOException;

	/**
	 * Method to write string to file/location
	 * @param text String to be written to file/location
	 * @param outputFilePath Path for file/location to be written to
	 */
	void writeToFile(String text, String outputFilePath);

	/**
	 * Method to call for vector maps to be generated
	 */
	void generateVectorMaps();

	/**
	 * Getter method to return file/location path for word embeddings(dictionary) 
	 * @return String path to file/location of word embeddings(dictionary) 
	 */
	String getEmbeddingsFilePath();

	/**
	 * Setter method to set the path of the file/location of word embeddings
	 * @param path String path to file/location
	 * @throws Exception if the file/location path is invalid
	 */
	void setEmbeddingsFilePath(String path) throws Exception;

	/**
	 * Getter method to return file/location path for replacement words
	 * @return String path to file/location for replacement words
	 */
	String getReplacementWordsFilePath();

	/**
	 * Setter method to set the path of the file/location of replacement words
	 * @param path String path to file/location for replacement words
	 * @throws Exception if the file/location path is invalid
	 */
	void setReplacementWordsFilePath(String path) throws Exception;

	/**
	 * Getter method to return file/location path of file/location containing the text to be replaced
	 * @return String path to file/location of text
	 */
	String getTextFilePath();
	
	/**
	 * Setter method to set file/location path of file/location containing the text to be replaced
	 * @param path String path to file/location of text
	 * @throws Exception if the file/location path is invalid
	 */
	void setTextFilePath(String path) throws Exception;
	
	/**
	 * Getter method to return file/location path of file/location where the results are to be written
	 * @return path String path to file/location of output
	 */
	String getOutputFilePath();

	/**
	 * Setter method to set file/location path of file/location where the results are to be written
	 * @param path String path to file/location of output
	 * @throws Exception If path is invalid
	 */
	void setOutputFilePath(String path) throws Exception;

	/**
	 * Getter method that calls for the vector map of replacement words to be retrieved
	 * @return HashMap of word:vector pairs for replacement words
	 */
	HashMap<String, double[]> getReplacementWordsMap();

	/**
	 * Getter method that calls for the vector map of word embeddings to be retrieved
	 * @return HashMap of word:vector pairs for word embeddings
	 */
	HashMap<String, double[]> getEmbeddingsMap();

}