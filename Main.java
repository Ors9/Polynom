/**
 * The Main class serves as the entry point of the application. It initializes
 * the user interface and starts the process of reading polynomials from the
 * user and performing operations on them.
 */
public class Main {

	/**
	 * The main method of the application. This method is the first to be executed
	 * when the program starts.
	 *
	 */
	public static void main(String[] args) {
		UserInterface userInterface = new UserInterface();
		userInterface.startUserOperations();
	}

}
