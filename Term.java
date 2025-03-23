
//Represents a term in a polynomial with an exponent and a coefficient
public class Term implements Comparable<Term> {
	private int exponent;
	private float coefficient;

	// Constructs a Term with the specified exponent and coefficient.
	public Term(int exponent, float coefficient) {
		setExponent(exponent);
		setCoefficient(coefficient);

	}

	// Returns the exponent of this term.
	public int getExponent() {
		return exponent;
	}

	// Returns the coefficient of this term.
	public float getCoefficient() {
		return coefficient;
	}

	// Set the exponent of this term.
	public void setExponent(int other) {
		this.exponent = other;
	}

	// Set the coefficient of this term.
	public void setCoefficient(float other) {
		this.coefficient = other;
	}

	// Compares this term with the specified term for order.
	// The comparison is in descending order of exponents.
	@Override
	public int compareTo(Term other) {
		// If both coefficient and exponent are the same, the terms are equal.
		if (this.getCoefficient() == other.getCoefficient() && this.getExponent() == other.getExponent()) {
			return 0;
		} // Compare based solely on exponent in descending order.
		if (this.getExponent() > other.getExponent()) {
			return -1;
		}
		return 1;

	}

	// Compare only the exponent
	public int compareExponents(Term other) {
		return Integer.compare(other.getExponent(), this.getExponent());
	}

	// Returns a string representation of the term .
	@Override
	public String toString() {
		int exp = this.getExponent();
		float coeff = this.getCoefficient();
		String message = "";

		// If coefficient is zero, return empty (meaning no term).
		if (coeff == 0) {
			return "";
		}

		// If coefficient is negative, add a minus sign
		if (coeff < 0) {
			message += "-";
		}

		// Use the absolute value of the coefficient for further processing
		coeff = Math.abs(coeff);

		// If exponent is 0, display the coefficient only
		if (exp == 0) {
			message += coeff;
			return message;
		}
		// If coefficient is not 1, add the coefficient value.
		// When coefficient is 1, omit it for display (e.g., "x" instead of "1x")
		else if (coeff != 1) {
			message += coeff;
		}

		// Add the variable part
		message += "x";

		// Append the exponent if it is not 1 (i.e., x^exp for exp > 1)
		if (exp != 1) {
			message += "^" + exp;
		}

		return message;
	}

	// Overrides the equals method to compare two Term objects for equality.
	// Two Term objects are considered equal if they have the same exponent and
	// coefficient
	@Override
	public boolean equals(Object other) {
		// If the current object is the same as the one being compared, return true.
		if (this == other) {
			return true;
		}
		// If the other object is null or not of the same class, return false.
		if (other == null || getClass() != other.getClass()) {
			return false;
		}
		Term term = (Term) other;
		// Compare the exponent and coefficient.
		return exponent == term.exponent && Float.compare(term.coefficient, coefficient) == 0;
	}

	// Overrides the hashCode method to generate a hash based on the exponent and
	// coefficient.
	@Override
	public int hashCode() {
		int result = Integer.hashCode(exponent);
		result = 31 * result + Float.hashCode(coefficient);
		return result;
	}

}
