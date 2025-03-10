package ie.atu.sw;

import java.util.HashMap;
/**
 * Implementation if the TextSimplifier interface.
 * 
 * This class processes a text file by replacing words with the closest matching words from a
 * replacement words map based on vector similarity. It supports different similarity calculators,
 * including Dot Product, Cosine Similarity, and Euclidean Distance.
 */


public class TextFileSimplifier implements TextSimplifier{
	
    private HashMap<String, double[]> embeddingsMap = null;
    private HashMap<String, double[]> replacementWordsMap = null;
    private SimilarityCalculator similarityCalculator; 
    private CalculatorType calcType = CalculatorType.DOT_PRODUCT; //Default calculator type

    public TextFileSimplifier() {
        initialiseSimilarityCalculator();
    }
    
    /**
     * {@inheritDoc}
     * 
     * This implementation handles reading the text, simplifying each line, 
     * and returning the modified content.
     * 
     * Time complexity: O(n*m). Splitting file into lines takes O(n), where n is the number of lines. 
     * Each line is then split into m words O(m)
     * 
     * @see TextFileSimplifier#processText(String)
     * @param inputText the text to be simplified
     * @return the simplified text as a {@code String}
     */
    @Override
    public String processText(String inputText) {
    	
    	System.out.println("[INFO] Using calc type: " + calcType);
    	
        StringBuilder simplifiedContent = new StringBuilder();

        String[] lines = inputText.split("\\R"); // Split by new lines
        for (String line : lines) {
            String simplifiedLine = simplify(line);
            simplifiedContent.append(simplifiedLine).append(System.lineSeparator());
        }

        return simplifiedContent.toString().trim();
    }
    
    /**
     * Simplifies a single line of text by replacing words with their closest matches.
     * 
     * Time complexity: O(n). Splitting lines into words O(n), where n is the number of words. 
     * Each word is then sent to findReplacementWord() O(1), where m is the number of words in the replacement map
     * 
     * @param line the line of text to be simplified
     * @return the simplified line of text
     */
    private String simplify(String line) {
        String[] words = line.split("\\s+");
        StringBuilder simplifiedLine = new StringBuilder();

        for (String word : words) {
            String replacement = findReplacementWord(word);
            simplifiedLine.append(replacement).append(" ");
        }

        return simplifiedLine.toString().trim();
    }
    
    /**
     * Finds a replacement word for a given word by searching in the replacement words map.
     * If the word is not found, it finds the closest word based on vector similarity.
     * 
     * Time complexity: O(1). HashMap key lookup time.
     *
     * @param word the word to find a replacement for
     * @return the replacement word, or the original word if no suitable replacement is found
     */
    private String findReplacementWord(String word) {
        // Check if the word exists in the replacement words map
        if (replacementWordsMap.containsKey(word)) {
            return word; // If found, keep the word the same
        }

        // If the word is not in replacementWordsMap, check embeddingsMap
        double[] targetVector = embeddingsMap.get(word);
        if (targetVector == null) {
            return word; // If not found in embeddingsMap, keep the word the same
        }

        // Find the closest word in the replacementWordsMap
        String closestWord = findClosestWord(targetVector);
        return closestWord != null ? closestWord : word; // Return the closest word, or the original if none found
    }
    
    /**
     * Finds the closest word in the replacement words map to a given target vector based on the
     * selected similarity calculator.
     * 
     * Time complexity: O(n) Iterates through replacementWordsMap O(n)
     *
     * @param targetVector the vector representation of the target word
     * @return the closest matching word, or {@code null} if no suitable word is found
     */
    private String findClosestWord(double[] targetVector) {
        String closestWord = null;
        double bestScore = (calcType == CalculatorType.DOT_PRODUCT || calcType == CalculatorType.COSINE) 
                ? Double.MIN_VALUE  // For Cosine & Dot Product, use MIN_VALUE
                : Double.MAX_VALUE; // For Euclidean, use MAX_VALUE

        // Loop through replacement words map to find the closest word
        for (var entry : replacementWordsMap.entrySet()) {
            double similarityScore = similarityCalculator.calculate(targetVector, entry.getValue());

            if (calcType == CalculatorType.DOT_PRODUCT || calcType == CalculatorType.COSINE) {
                // For Cosine and Dot Product, get highest score
                if (similarityScore > bestScore) {
                    bestScore = similarityScore;
                    closestWord = entry.getKey();
                }
            } else if (calcType == CalculatorType.EUCLIDEAN) {
                // For Euclidean, get lowest score
                if (similarityScore < bestScore) {
                    bestScore = similarityScore;
                    closestWord = entry.getKey();
                }
            }
        }

        return closestWord;
    }
    
    /**
     * {@inheritDoc}
     * 
     * Calls for initialisation of the variable in order to use the specified calculator type 
     * 
     * @see CalculatorType
     * @see TextFileSimplifier
     * @param calc calcType the type of calculator to use
     */
    @Override
    public void setSimilarityCalculator(CalculatorType calc) {
    	this.calcType = calc;
    	initialiseSimilarityCalculator();
    }
    
    private void initialiseSimilarityCalculator() {
    	switch(calcType) {
    		case DOT_PRODUCT: 
    			this.similarityCalculator = new DotProductCalculator();
    			break;
    		case COSINE: 
    			this.similarityCalculator = new CosineCalculator();
    			break;
    		case EUCLIDEAN: 
    			this.similarityCalculator = new EuclideanCalculator();
    			break;
    	}
    }
    
    /**
     * {@inheritDoc}
     * 
     * Retrieves the value from local variable 
     */
    @Override
	public HashMap<String, double[]> getEmbeddingsMap() {
		return embeddingsMap;
	}
	
	/**
     * {@inheritDoc}
     * 
     * Retrieves the value from local variable 
     */
	@Override
	public HashMap<String, double[]> getReplacementWordsMap() {
		return replacementWordsMap;
	}
	
	/**
     * {@inheritDoc}
     * 
     * Stores the input parameter as local variable
     */
	@Override
	public void setEmbeddingsMap(HashMap<String, double[]> embeddingsMap) {
		this.embeddingsMap = embeddingsMap;
	}
	
	/**
     * {@inheritDoc}
     * 
     * Stores the input parameter as local variable
     */
	@Override
	public void setReplacementWordsMap(HashMap<String, double[]> replacementWordsMap) {
		this.replacementWordsMap = replacementWordsMap;
	}
}
