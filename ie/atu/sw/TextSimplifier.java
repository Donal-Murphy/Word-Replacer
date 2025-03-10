package ie.atu.sw;

import java.util.HashMap;

/**
 * Used to process text by replacing words found in an one map with the closest equivalent words 
 * from another map with the goal of simplifying the text.
 */

public interface TextSimplifier {
	
	/**
	 * Main method for executing the simplifying of text
	 * @param inputText The text to be simplified
	 * @return Returns a string of the simplified text
	 */
	public String processText(String inputText);
	
	/**
	 * Setter Method for setting the Enum CalculatorType in order to choose which calculation method
	 * to use when calculating the similarity of two vectors
	 * @see CalculatorType
	 * @param calcType the enum representing the calculator type
	 */
    public void setSimilarityCalculator(CalculatorType calcType);
    
    /**
     * Getter method that returns the embedding map
     * @return Embeddings map
     */
    public HashMap<String, double[]> getEmbeddingsMap();
    
    /**
     * Getter method that returns the replacement words map
     * @return  Replacement words map
     */
	public HashMap<String, double[]> getReplacementWordsMap();
	
	/**
	 * Setter for setting the embeddings map local variable
	 * @param embeddingsMap  Embeddings map
	 */
	public void setEmbeddingsMap(HashMap<String, double[]> embeddingsMap);
	
	/**
	 * Setter for setting the Replacement words map local variable
	 * @param replacementWordsMap  Replacement words map
	 */
	public void setReplacementWordsMap(HashMap<String, double[]> replacementWordsMap);
}
