package ie.lyit.interfaces;

public interface Payable {
	public abstract double calculateWage(double taxPercentage);
	// Don't have to put in public abstract
	double incrementSalary(double incrementAmount);
}

// A Java interface can contain only abstract 
// methods, i.e. methods without a body