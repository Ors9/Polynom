import java.util.ArrayList;
import java.util.Scanner;

/**
 * The UserInterface class handles user interaction for performing operations on
 * two polynomials. It receives input from the user, constructs Polynom objects
 * from the input, and performs various operations such as addition,
 * subtraction, derivative, and equality check.
 */
public class UserInterface {
	private Polynom userPoly1;
	private Polynom userPoly2;
	private Scanner scanner;

	/**
	 * Constructs a new UserInterface and initializes the scanner.
	 */
	public UserInterface() {
		scanner = new Scanner(System.in);
	}

	/**
	 * Starts the user interaction process: prompts the user for two polynomials and
	 * displays the results of various operations on them.
	 */
	public void startUserOperations() {
		getUserPolynoms();
		printPolynomOperations();
		scanner.close();
	}

	/**
	 * Reads a polynomial from user input in the format of alternating coefficients
	 * and exponents (e.g., "3 5 4 6"). Constructs a {@code Polynom} object from the
	 * input after sorting the terms.
	 *
	 * @return a Polynom object built from the user's input.
	 */
	private void getUserPolynoms() {
		System.out.println("Enter the first polynomial (format: coef exp coef exp ...)");
		System.out.println("Example: 3 5 4 6 (which means 3x^5 + 4x^6)");

		userPoly1 = readPolynomialFromInput();

		System.out.println("Enter the second polynomial (format: coef exp coef exp ...)");
		System.out.println("Example: 3 5 4 6 (which means 3x^5 + 4x^6)");

		userPoly2 = readPolynomialFromInput();
	}

	/**
	 * Displays the result of various operations between the two polynomials: -
	 * Printing the original polynomials - Checking equality - Addition and
	 * subtraction - Derivatives of both polynomials
	 */
	public Polynom readPolynomialFromInput() {
		ArrayList<Term> userTerms = new ArrayList<Term>();
		String input = scanner.nextLine().trim();
		String[] terms = input.split("\\s+");

		// export from terms array the exponent and coefficient
		for (int i = 1; i < terms.length; i += 2) {
			int exponent = Integer.parseInt(terms[i]);
			float coefficient = Float.parseFloat(terms[i - 1]);
			userTerms.add(new Term(exponent, coefficient));
		}

		Polynom.sortPolynom(userTerms);
		return new Polynom(userTerms);
	}

	// Helper function to print the polynomial operations.
	public void printPolynomOperations() {
		System.out.println("====================================");
		System.out.println("Print user Operations:");
		System.out.println("====================================");
		System.out.println("\nFirst Polynomial using toString method:");
		System.out.println(userPoly1.toString());
		System.out.println("\nSecond Polynomial using toString method:");
		System.out.println(userPoly2.toString());
		System.out.println("\nCheck if the first polynomial equals second polynomial:");
		System.out.println(userPoly1.equals(userPoly2));
		System.out.println("\nThe sum of the Polynomials:");
		System.out.println(userPoly1.plus(userPoly2));
		System.out.println("\nThe subtract of the Polynomials:");
		System.out.println(userPoly1.minus(userPoly2));
		System.out.println("\nThe derivative of the First Polynomial:");
		System.out.println(userPoly1.derivative());
		System.out.println("\nThe derivative of the Second Polynomial:");
		System.out.println(userPoly2.derivative());
	}

}
