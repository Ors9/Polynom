import java.util.ArrayList;
import java.util.Collections;

//Represents a polynomial composed of multiple terms.
public class Polynom {
	// List of terms that make up the polynomial.
	private ArrayList<Term> polynomial = new ArrayList<>();

	// Copy constructor
	public Polynom(Polynom poly) {
		this(poly.getPolynom());
	}

	// Constructs a polynomial from an existing list of terms.
	public Polynom(ArrayList<Term> poly) {
		// Create a copy of the list to ensure encapsulation.
		this.polynomial = new ArrayList<>(poly);
		// Sort it .
		sortPolynom(this.polynomial);
	}

	// Sorts the given list of Term objects in place using Term.compareTo().
	public static void sortPolynom(ArrayList<Term> polyToSort) {
		Collections.sort(polyToSort);
	}

	// Return the string represention of polynom.
	@Override
	public String toString() {
		String message = "";

		// Iterate through each term.
		for (int i = 0; i < polynomial.size(); i++) {
			// For positive terms (except the first term), prepend a '+' sign.
			if (polynomial.get(i).getCoefficient() > 0 && i > 0) {
				message += '+';
			}
			message += polynomial.get(i).toString();
		}
		return message;

	}

	// Get the Terms list of polynom
	public ArrayList<Term> getPolynom() {
		ArrayList<Term> copy = new ArrayList<>();
		for (int i = 0; i < polynomial.size(); i++) {
			Term t = polynomial.get(i);
			copy.add(new Term(t.getExponent(), t.getCoefficient()));
		}
		return copy;
	}

	// Checks if the polynomial is equal to another polynomial
	public boolean equals(Object poly) {
		if (this == poly) {
			return true;
		}
		if (!(poly instanceof Polynom)) {
			return false;
		}
		Polynom other = (Polynom) poly;
		ArrayList<Term> thisTerms = new ArrayList<>(this.polynomial);
		ArrayList<Term> otherTerms = new ArrayList<>(other.polynomial);

		// sort the Polynoms
		sortPolynom(thisTerms);
		sortPolynom(otherTerms);
		return thisTerms.equals(otherTerms);
	}

	// Returns the sum of two polynomials
	public Polynom plus(Polynom other) {
		ArrayList<Term> polySum = new ArrayList<>();

		// Make copies of both term lists so we don't mutate original data
		ArrayList<Term> poly1 = this.getPolynom();
		ArrayList<Term> poly2 = other.getPolynom();

		int exponent;
		float coefficientSum;

		// Sort the given polynomial.
		sortPolynom(poly1);
		sortPolynom(poly2);
		int i = 0;
		int j = 0;

		// Merge the two polynomials by comparing exponents.
		while (i < poly1.size() && j < poly2.size()) {
			// If the exponents are equal, add the coefficients
			if (poly1.get(i).compareExponents(poly2.get(j)) == 0) {
				exponent = poly1.get(i).getExponent();
				coefficientSum = poly1.get(i).getCoefficient() + poly2.get(j).getCoefficient();
				i++;
				j++;
				// poly1 has a larger exponent.
			} else if (poly1.get(i).compareExponents(poly2.get(j)) < 0) {
				exponent = poly1.get(i).getExponent();
				coefficientSum = poly1.get(i).getCoefficient();
				i++;
			} else {// poly2 has a larger exponent.
				exponent = poly2.get(j).getExponent();
				coefficientSum = poly2.get(j).getCoefficient();
				j++;
			}
			// Only add non-zero terms.
			if (coefficientSum != 0) {
				polySum.add(new Term(exponent, coefficientSum));
			}
		}

		// Append remaining terms from poly1 and poly2
		while (i < poly1.size()) {
			polySum.add(poly1.get(i));
			i++;
		}

		while (j < poly2.size()) {
			polySum.add(poly2.get(j));
			j++;
		}

		return new Polynom(polySum);
	}

	// Returns the difference of this polynomial minus the given polynomial.
	public Polynom minus(Polynom other) {
		// Get a defensive copy of the other polynomial's terms.
		ArrayList<Term> negatedTerms = other.getPolynom();
		// Negate each term in the copy.
		for (int i = 0; i < negatedTerms.size(); i++) {
			Term t = negatedTerms.get(i);
			t.setCoefficient(((-1) * t.getCoefficient()));
		}
		// Create a new Polynom from the negated terms.
		Polynom negatedPoly = new Polynom(negatedTerms);
		// Return the sum of this polynomial and the negated polynomial.
		return this.plus(negatedPoly);
	}

	// Returns the derivative of the polynomial
	public Polynom derivative() {
		ArrayList<Term> terms = this.getPolynom();
		// Sort the polynomial.
		sortPolynom(terms);
		ArrayList<Term> derivativePolynom = new ArrayList<Term>();
		int exp;
		float coeff;
		// Compute the derivative term by term.
		for (int i = 0; i < terms.size(); i++) {
			exp = terms.get(i).getExponent();
			coeff = terms.get(i).getCoefficient();

			// Skip constant terms (derivative of a constant is 0)
			if (terms.get(i).getExponent() != 0) {
				// Apply the power rule: new coefficient = exponent * coefficient,
				// new exponent = exponent - 1.
				coeff = exp * coeff;
				exp = exp - 1;

				// Add the derived term to the derivative polynomial
				derivativePolynom.add(new Term(exp, coeff));
			}

		}

		return new Polynom(derivativePolynom);
	}

}
