package ie.atu.sw;
/**
 * Concrete class belonging to similarityCalculator that calculates the Cosine Similarity score 
 * between 2 vectors. 
 */
public class CosineCalculator extends SimilarityCalculator {
	
	/**
	 * {@inheritDoc}
	 * 
	 * Calculates and returns the cosine similarity of 2 vectors. It is an overridden method that 
	 * implements the calculation logic specific to cosine similarity and returns the calculated 
	 * result as a double.
	 * 
	 * <b>Time Complexity: </b>O(n), because the loop runs n times and performs 0(1) operations each time
	 * 
	 */
	@Override
	protected double calculateSimilarity(double[] vector1, double[] vector2) {
		double numerator = 0.0d; //For storing sum of plroducts
		double sumV1squared = 0.0d; //For storing sum of vector1 squared
		double sumV2squared = 0.0d; //For storing sum of vector2 squared

		// Iterate through vectors & calculate cosine distance
		for (int i = 0; i < vector1.length; i++) {
			numerator += vector1[i]*vector2[i];
			sumV1squared += Math.pow(vector1[i], 2.0d);
			sumV2squared += Math.pow(vector2[i], 2.0d);
		}
		
		double result = numerator/(Math.sqrt(sumV1squared) * Math.sqrt(sumV2squared));
		
		return result;
	}

}
