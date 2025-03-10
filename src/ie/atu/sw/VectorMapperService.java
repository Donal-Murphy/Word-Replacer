package ie.atu.sw;

import java.util.HashMap;


/**
 * Maps words and their corresponding vectors to maps in key:value pairs obtained from a file/location
 */
public interface VectorMapperService {
	
	/**
	 * Getter method to retrieve Map of word embeddings
	 * @return HashMap of word:vector pairs
	 */
	public HashMap<String, double[]> getEmbeddingsMap();
	
	/**
	 * Getter method to retrieve Map of replacement words
	 * @return HashMap of word:vector pairs
	 */
	public HashMap<String, double[]> getReplacementWordsMap();
	
	/**
	 * Setter method to store vector map of word embeddings
	 * @param path path to file/location where embeddings are located 
	 */
	public void setEmbeddingsMap(String path);
	
	/**
	 * Setter method to store vector map of replacement words
	 * @param path path to file/location where replacement words are located 
	 */
	public void setReplacementWordsMap(String path);

	
}
