package ie.atu.sw;

/**
 * Abstract class that is used to verify and calculate the similarity of 2
 * vectors. Also Contains validation logic to check that two vectors are of
 * equal length
 */
abstract class SimilarityCalculator {

	/**
	 * Template for method for calculating vector similarity. The vectors are first
	 * sent to validateVectors() to ensure that they are of the same length before
	 * returning the result of calculateSimilarity().
	 * 
	 * @param vector1 The first vector in the comparison. It should be a non-null
	 *                array with the same length as vector2.
	 * @param vector2 The second vector in the comparison. It should be a non-null
	 *                array with the same length as vector1.
	 * @return The resulting similarity score.
	 */
	// Template for calculating distance
	public final double calculate(double[] vector1, double[] vector2) {
		validateVectors(vector1, vector2);
		return calculateSimilarity(vector1, vector2);
	}
	
	/**
	 * Method to be implemented by concrete classes.
	 * Calculates the distance between two vectors in a multi-dimensional space.
	 * 
	 * @param vector1 The first vector in the comparison.
	 *               It should be a non-null array with the same length as vector2.
	 * @param vector2 The second vector in the comparison.
	 *               It should be a non-null array with the same length as vector1.
	 * @return The similarity score between the two vectors.
	 */
	// Method to be implemented by subclasses
	protected abstract double calculateSimilarity(double[] vector1, double[] vector2);
	
	/**
	 * Validates that the vectors are non-null and of the same length. Common to all subclasses.
	 * Throws IllegaArgumentException if they are not of the same length or are null
	 * 
	 * @param vector1 The first vector in the comparison. It should be a non-null
	 *                array with the same length as vector2.
	 * @param vector2 The second vector in the comparison. It should be a non-null
	 *                array with the same length as vector1.
	 */
	// Validation logic common to all subclasses
	private void validateVectors(double[] vector1, double[] vector2) {
		// Check that vectors are not null
		if (vector1 == null || vector2 == null) {
			throw new IllegalArgumentException("Error: Vectors are null");
		}
		// Check that vectors are of the same length
		if (vector1.length != vector2.length) {
			throw new IllegalArgumentException("Error: Vectors are not same length");
		}
	}

}
