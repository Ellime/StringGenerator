package stringgenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Generator {
	
	private static String letters = "abcdefghijklmnopqrstuvwxyz";
	private static String numbers = "0123456789";
	private static String chars = ""; // Valid chars
	private static String[] output;
	private static String filename = "strings.txt";
	
	
	// HELPER FUNCTIONS
	
	public static void setChars() {
		chars = "";
		// Call each time before generating strings.
		if(Settings.getLetters().equalsIgnoreCase("on")) {
			chars = chars.concat(letters);
		}
		if(Settings.getNumbers().equalsIgnoreCase("on")) {
			chars = chars.concat(numbers);
		}
	}
	
	public static char generateRandomChar() {
		Random r = new Random();
		int l = chars.length();
		char c = chars.charAt(r.nextInt(l));
		return c;
	}
	
	public static String generateRandomString() {
		int length = Settings.getLength();
		String output = Settings.getCore();
		int toGenerate = length - output.length();
		
		for(int i = 0; i < toGenerate; i++) {
			char c = generateRandomChar(); // Parallelize here?
			output += c; // Shared resource
		}
		
		return output;
	}
	
	public static int[] generateIterationIndices(int itr) {
		// Input: iterator value
		// Converts iterator into an array of ints to be used to index the range of chars
		int base = chars.length();
		int length = Settings.getLength();
		String indicesString = Integer.toString(itr, base);
		
		// Pad indicesString with leading zeros
		int remaining = length - indicesString.length();
		for(int i = 0; i < remaining; i++) {
			indicesString = "0" + indicesString;
		}
		
		// Conversion to array
		int indices[] = new int[length];
		for(int i = 0; i < length; i++) {
			String c = Character.toString(indicesString.charAt(i));
			indices[i] = Integer.parseInt(c, base);
		}
		
		return indices;
	}
	
	public static String generateIterationString(int[] indices) {
		int length = Settings.getLength();
		String output = Settings.getCore();
		
		for(int i = 0; i < length; i++) {
			int index = indices[i];
			char c = chars.charAt(index);
			output += c;
		}
		
		return output;
	}
	
	public static void generateOutput() {

		// Check chars contains chars to iterate with
		if(chars.length() == 0) {
			System.out.println("No chars have been selected to generate with - cannot generate.");
			return;
		}
		
		int qty = Settings.getQty();
		output = new String[qty];
		String generationType = Settings.getGenerationType();
		
		if(generationType.equalsIgnoreCase("random")) {
			for(int i = 0; i < qty; i++) { output[i] = generateRandomString(); }
		}
		
		else if(generationType.equalsIgnoreCase("iteration")) {
			
			// Create starting iteration value
			int itr = 0;
			int indices[];
			
			for(int i = 0; i < qty; i++) {
				indices = generateIterationIndices(itr);
				output[i] = generateIterationString(indices);
				itr++;
			}
		}
		
//		return output;
	}
	
	public static void saveOutput() {
		String newline = System.getProperty("line.separator");
		try {
			FileWriter strings = new FileWriter(filename, false);
			for(int i = 0; i < output.length; i++) {
				strings.write(output[i]);
				strings.write(newline);
			}
			strings.close();
		} catch(IOException e) {
			System.out.println("Error when saving strings.");
		}
		
	}
}
