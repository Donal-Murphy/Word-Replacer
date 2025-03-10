package ie.atu.sw;

import java.io.File;
import java.io.FileNotFoundException;
/**
 * Implementation of PathValidator Service specific to locally stored .txt files. Checks that the 
 * path is vaild by ensuring that it exists, is a directory, can be read and is a .txt file
 */
public class PathValidator implements PathValidatorService {
	
	/**
	 * {@inheritDoc}
	 * 
	 * This implementation is specific to local .txt files. Checks that the 
	 * path is vaild by ensuring that it exists, is a directory, can be read and is a .txt file
	 * Time complexity: O(1). Simple boolean check
	 */
	@Override
	// Validates the input path (file must exist, be readable, writable, and a .txt file)
	public void validateInputPath(String path) throws Exception {
        File file = new File(path);
        if (!file.exists() || file.isDirectory()) {
            throw new FileNotFoundException("Input file not found or is a directory: " + path);
        }
        if (!file.canRead()) {
            throw new SecurityException("Cannot read the input file: " + path);
        }
        if (!path.endsWith(".txt")) {
            throw new IllegalArgumentException("Input file must be a .txt file.");
        }else {
        	System.out.println("input file succesfully validated: " + path);
        }
    }
	
	/**
	 * {@inheritDoc}
	 * 
	 * This implementation is specific to local .txt files.  Checks that the 
	 * path is vaild by ensuring that it exists, is a directory, can be read and is a .txt file
	 * Time complexity: O(1). Simple boolean check
	 */
	@Override
    // Validates the output path (must be a valid directory and a .txt file)
	public void validateOutputPath(String path) throws Exception {
        File file = new File(path);
        File directory = file.getParentFile();
        if (directory == null || !directory.exists()) {
            throw new FileNotFoundException("Output directory not found: " + directory.getAbsolutePath());
        }
        if (!path.endsWith(".txt")) {
            throw new IllegalArgumentException("Output file must be a .txt file.");
        }else {
        	System.out.println("input file succesfully validated: " + path);
        }
    }
	
		
}
