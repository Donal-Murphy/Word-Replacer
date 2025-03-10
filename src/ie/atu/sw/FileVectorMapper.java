package ie.atu.sw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
/**
 * Implementaion of VectorMapperService specific to local text files. Parses 
 * comma separated text files for key:value pairs and stores them in local variables as HashMaps.
 */
public class FileVectorMapper implements VectorMapperService {

	//---------------------------------------------------------------------------------------------
    // FIELDS
    //---------------------------------------------------------------------------------------------

	private HashMap<String, double[]> embeddingsMap;
	private HashMap<String, double[]> replacementWordsMap;
	
	//---------------------------------------------------------------------------------------------
    // MAIN LOGIC METHODS
    //---------------------------------------------------------------------------------------------
	
	/**
	 * Reads embeddings file line by line and generates a HashMap where the first word in every line acts 
	 * as key. The remainder of the line is accumulated into an array of vectors and is stored as 
	 * the value in the map.
	 * Time Complexity: O(n). Reads file line by line and stores the values
	 * 
	 * @param path The location of the word embeddings file
	 * @return a HashMap with words as keys and arrays(vectors) as values
	 */
	private HashMap<String, double[]> embeddingsToMap(String path) {
	    HashMap<String, double[]> vectorMap = new HashMap<>(); //Store result

	    try (BufferedReader br = new BufferedReader(new FileReader(path))) { //read file
	        String line;
	        while ((line = br.readLine()) != null) { //line by line
	            String[] elements = line.split(","); //Seperate strings by ","
	            String key = elements[0]; // 0th element is the word/key
	            double[] vector = new double[elements.length - 1];
	            //Iterate through the remainder of the line and store it in an array of doubles
	            for (int i = 1; i < elements.length; i++) {
	                vector[i - 1] = Double.parseDouble(elements[i]);
	            }
	            vectorMap.put(key, vector); //O(log n)
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return vectorMap;
	}
		
	/**
	 * Reads replacement words file line by line and matches it with the corresponding vector 
	 * found in the embeddings map
	 * Time Complexity: O(n*m). reads the replacement words file line by line O(n) and for every 
	 * line, parses the embeddings map key by key to find the matching vector O(m)
	 * @param path The location of the replacement words file
	 * @return the map of replacement words
	 */
	private HashMap<String, double[]> replacementWordsToMap(String path) {
	    HashMap<String, double[]> vectorMap = new HashMap<>();
	    try (BufferedReader br = new BufferedReader(new FileReader(path))) { //read file
	        String line;
	        while ((line = br.readLine()) != null) { //line by line
	            double[] matchingVector = embeddingsMap.get(line); //Find the matching key in embeddings map
	            if (matchingVector != null) {
	                vectorMap.put(line, matchingVector); //Store the result (O(log n)
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return vectorMap;
	}
	
    //---------------------------------------------------------------------------------------------
    // GETTERS & SETTERS
    //---------------------------------------------------------------------------------------------
	/**
	 * {@inheritDoc}
	 * 
	 * This implementation retrieves the map from a local variable
	 * Time Complexity: 0(1). Simple variable retrieval.
	 */
	@Override
	public HashMap<String, double[]> getEmbeddingsMap() {
		return embeddingsMap;
	}
	/**
	 * {@inheritDoc}
	 * This implementation retrieves the map from a local variable
	 * Time Complexity: 0(1). Simple variable retrieval.
	 */
	@Override
	public HashMap<String, double[]> getReplacementWordsMap() {
		return replacementWordsMap;
	}
	/**
	 * {@inheritDoc}
	 * This implementation sets the map in a local variable
	 * Time Complexity: 0(n). Depends on embeddingsToMap()
	 */
	@Override
	public void setEmbeddingsMap(String path) {
		this.embeddingsMap = embeddingsToMap(path);
		
	}
	/**
	 * {@inheritDoc}
	 * This implementation sets the map in a local variable
	 * Time Complexity: 0(n*m). Depends on replacementWordsToMap()
	 */
	@Override
	public void setReplacementWordsMap(String path) {
		this.replacementWordsMap = replacementWordsToMap(path);
		
	}

}
