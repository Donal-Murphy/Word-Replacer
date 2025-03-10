package ie.atu.sw;
/**
* Concrete class belonging to similarityCalculator that calculates the Euclidean distance 
* between 2 vectors. 
*/
public class EuclideanCalculator extends SimilarityCalculator {
	/**
	 * {@inheritDoc}
	 * 
	 * Calculates and returns the Euclidean distance of 2 vectors. It is an overridden method that 
	 * implements the calculation logic specific to Euclidean distance and returns the calculated 
	 * result as a double.
	 * 
	 * <b>Time Complexity: </b>O(n), because the loop runs n times and performs 0(1) operations each time
	 */
	@Override
	protected double calculateSimilarity(double[] vector1, double[] vector2) {
		double sum = 0.0d;
		
		// Iterate through vectors & calculate euclidean distance
		for (int i = 0; i < vector1.length; i++) {
			sum += Math.pow(vector1[i] - vector2[i], 2);
		}
		
		double result = Math.sqrt(sum);
		
		return result;
	}

}
