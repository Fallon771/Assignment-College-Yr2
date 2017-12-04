package ie.lyit.hotel;
import java.io.Serializable;

public class Customer extends Person implements Serializable{// INHERITANCE - Customer IS-A Person
	
	/*  [UPDATE]
	 *  Singleton Design -- Only one instance ever created, getter is used to call.
	 *  Generate a consistent serial ID to avoid 'local class incompatible' errors
	 *  esp if different IDE's are used, or changes to class.
	 */
	private static final long serialVersionUID = -6270489614797641552L;
	
	// Customer has name, address, & phoneNumber from Person
	private String emailAddress;    // AND emailAddress
	private int number;			    // AND number
	private static int nextNumber = 1 ;// static for unique number - starts off at 1
	private static Customer customerInstance;
	
	// Default Constructor
	// Called when object is created like this ==> 
	//   Customer cObj = new Customer();	
	private Customer(){
		super();			// NOTE:Don't need to call super() explicitly.
		emailAddress=null;
		// Set number to static nextNumber before incrementing nextNumber
		number=nextNumber++;
	}
	
	// Initialization Constructor
	// Called when object is created like this ==>
	// Customer cObj = new Customer("Mr","Joe","Doe","12 Hi Rd,Letterkenny",
	//                              "0871234567","joe@hotmail.com");
	public Customer(String t, String fN, String sn, String address, 
			        String pNo, String email){
		// Call super class constructor - Passing parameters required by Person ONLY!
		super(t, fN, sn, address, pNo);
		// And then initialise Customers own instance variables
		emailAddress=email;
		// And finally set number to static nextNumber before incrementing nextNumber
		number=nextNumber++;
	}
	
	public static Customer getIntance(){
		
		if(customerInstance == null){
			customerInstance = new Customer();
		}
		return customerInstance;	
	}
	
	// OVERRIDING the Person toString() method!
	// Calling Persons toString() method, and adding additional bits
	@Override
	public String toString(){
		return super.toString() + "\nEmail:" + emailAddress+"\n";
	}

	// equals() method
	// ==> Called when comparing an object with another object, 
	//     e.g. - if(c1.equals(c2))				
	// ==> Probably sufficient to compare customer numbers as they're unique
	@Override
	public boolean equals(Object obj){
		Customer cObject;                                                       
		if (obj instanceof Customer)
		   cObject = (Customer)obj;
		else
		   return false;
		   
	    return(this.number==cObject.number);
	}

	// set() and get() methods
	public void setEmailAddress(String emailAddress){
		this.emailAddress=emailAddress;
	}
	public String getEmailAddress(){
		return this.emailAddress;
	}	
	// You shouldn't be able to setNumber() as it is unique, 
	// so don't provide a setNumber() method
	public int getNumber(){
		return number;
	}		
}
