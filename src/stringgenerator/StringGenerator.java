package stringgenerator;

import java.util.Scanner;

public class StringGenerator {
	
	public static void printMenu() {
		System.out.println("MENU");
		System.out.println("0: Generate strings");
		System.out.println("1: View settings");
		System.out.println("2: Toggle iteration/random");
		System.out.println("3: Set qty. strings");
		System.out.println("4: Set string length");
		System.out.println("5: Set core");
		System.out.println("6: Toggle letters");
		System.out.println("7: Toggle numbers");
		System.out.println("8: Save settings");
		System.out.println("9: Load settings");
		System.out.println();
		System.out.print("Choose an option: ");
	}

	public static void main(String[] args) {
		
		// Initialize settings
		Settings.loadSettings();
		Scanner user = new Scanner(System.in);
		int option;
		String input;
		
		while(true) {
			StringGenerator.printMenu();
			try {
				option = Integer.parseInt(user.next());
			} catch(NumberFormatException e) {
				System.out.println("Closing program.");
				break;
			}
			
			if(option == 0) {
				Generator.setChars();
				Generator.generateOutput();
				Generator.saveOutput();
			} // TODO: Rewrite setters so input received in this thread
			else if(option == 1) { Settings.printSettings(); }
			else if(option == 2) { Settings.setGenerationType(); }
			else if(option == 3) {
				System.out.print("Enter the new qty: ");
				input = user.next();
				Settings.setQty(input);
			}
			else if(option == 4) {
				System.out.print("Enter the new length: ");
				input = user.next();
				Settings.setLength(input);
			}
			else if(option == 5) {
				System.out.print("Enter the new core with quotes (\"\" for no core): ");
				input = user.next();
				Settings.setCore(input);
			}
			else if(option == 6) { Settings.setLetters();
			}
			else if(option == 7) { Settings.setNumbers(); }
			else if(option == 8) { Settings.saveSettings(); }
			else if(option == 9) { Settings.loadSettings(); }
			else {
				System.out.println("PROGRAM CLOSED");
				break;
			}
			System.out.println();
		}
		user.close();
	}

}
