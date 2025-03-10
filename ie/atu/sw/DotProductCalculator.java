package ie.atu.sw;
/**
* Concrete class belonging to similarityCalculator that calculates the dot product 
* between 2 vectors. 
*/
public class DotProductCalculator extends SimilarityCalculator {
	/**
	 * {@inheritDoc}
	 * 
	 * Calculates and returns the Dot Product of 2 vectors. It is an overridden method that 
	 * implements the calculation logic specific to dot product and returns the calculated 
	 * result as a double.
	 * 
	 * <b>Time Complexity: </b>O(n), because the loop runs n times and performs 0(1) operations each time
	 */
	@Override
	protected double calculateSimilarity(double[] vector1, double[] vector2) {
		// Calculate the number of vectors minus the query vector
		double result = 0.0; // Store calculated results

		// Iterate through vectors & calculate dot product
		for (int i = 0; i < vector1.length; i++) {
			result += vector1[i]*vector2[i];

		}
		return result;
	}

}
