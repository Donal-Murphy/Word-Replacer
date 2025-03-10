package ie.atu.sw;
/**
 * Validates paths to files depending on whether it is considered an input file or an  output file
 */
public interface PathValidatorService {

	/**
	 * Checks whether an input path is valid
	 * @param path location of the input
	 * @throws Exception If path is not valid
	 */
	public void validateInputPath(String path) throws Exception;
	
	/**
	 * Checks whether an output path is valid
	 * @param path location of the output
	 * @throws Exception If path is not valid
	 */
	public void validateOutputPath(String path) throws Exception;
}
