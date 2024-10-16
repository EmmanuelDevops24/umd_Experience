package programs;

/**
 * This class represents a person's passport. It has three instance variables
 * representing the first, last and middle name (all are String variables). A
 * character instance variable representing a separator (to be used for
 * formatting purposes) is also part of the class. In addition, the class has a
 * StringBuffer instance variable that represents the passport stamps the person
 * has received.
 * 
 * For this class you need to define and use a private method called
 * validateAndFormat that takes a character as a parameter.
 * 
 * The class will keep track of the number of instances created by using a
 * private static field called objectCount.
 * 
 * @author CS
 *
 */
public class Passport {
	private String firstname;
	private String middlename;
	private String lastname;
	private char separator;
	private StringBuffer stamps;
	private static int objectCount = 0; 
	
	public Passport(String firstname, String middlename, String lastname) {
		
		this.firstname= validateAndFormat(firstname);
		if (firstname==null) {
			return;}
		
		if (middlename.isBlank()) {
			this.middlename="";
		}else {
		this.middlename=validateAndFormat(middlename);
		 
		}
		
		this.lastname=validateAndFormat(lastname);
		if(lastname==null) {
			return;
		}
		this.separator=',';
		this.stamps= new StringBuffer();
		
	objectCount++;
	}
	
	
@Override
	public String toString() {
	String result = "";
    if (lastname != null) {
        result += lastname + separator;
    }

    if ((middlename == null) || (middlename.isBlank())) {
        result += firstname;
    } else {
        result += firstname + separator;
    }

    if (firstname != null) {
        result += middlename;
    }

		return result;
	}


	public Passport(String firstname, String lastname) {
	this(firstname,"",lastname);
	
	}
	

	public Passport() {
		this("SAMPLEFIRSTNAME","SAMPLEMIDDLENAME","SAMPLELASTNAME");
		
	}
	

	public Passport(Passport passport) {
		if (passport == null) {
	        return;  // Early return if the input passport is null
	    }

	    // Copy fields directly from the provided passport object
	    this.firstname = passport.firstname;
	    this.middlename = passport.middlename;
	    this.lastname = passport.lastname;
	    
	    // Copy the separator and stamps
	    this.separator = passport.separator;
	    this.stamps = new StringBuffer(passport.stamps.toString());
	    
	    // Increment the object count
	    objectCount++;
	}
	

	public Passport addStamp(String stamp) {
		if ((stamp==null)||(stamp.isBlank())) {
			return this;}
		else {
		stamps.append(stamp);
		}
		return this;
	}
	

	public StringBuffer getStamps() {
		 // Convert the original StringBuffer to a String
	    String content = stamps.toString(); 
	    //covert the string back to a StringBuffer
		StringBuffer stampsCopy = new StringBuffer(content);
		return stampsCopy;
	}

	public char getSeparator() {
		return separator;
	}
	

	public boolean setSeparator(char separator) {
		if (this.separator == separator) {
			return true;
		}
		if (separator == '@' || Character.isSpaceChar(separator) || Character.isLetter(separator)) {
			return false;
		} else {
			this.separator = separator;
		}
		return true;
	}
	

	public boolean equals(Object obj) {
		 if (this == obj) {
		        return true;
		    }

		    // Check if the object is an instance of Passport
		    if (!(obj instanceof Passport)) {
		        return false;
		    }

		    // Cast the object to a Passport
		    Passport other = (Passport) obj;

		    // Compare first name, last name, and middle name for equality, ignoring the separator
		    return this.firstname.equals(other.firstname) &&
		           this.lastname.equals(other.lastname) &&
		           this.middlename.equals(other.middlename);
	}

	public int compareTo(Passport passport) {
		// Compare last names first
	    int lastNameComparison = this.lastname.compareTo(passport.lastname);
	    if (lastNameComparison != 0) {
	        return lastNameComparison;
	    }

	    // If last names are equal, compare first names
	    int firstNameComparison = this.firstname.compareTo(passport.firstname);
	    if (firstNameComparison != 0) {
	        return firstNameComparison;
	    }

	    // If first names are also equal, compare middle names
	    return this.middlename.compareTo(passport.middlename);
	}
	

	public static int getNumberOfPassportObjects() {
		return objectCount;
	}

	public static void resetNumberOfPassportObjects() {
		objectCount=0;
	}
	

	public static Passport normalize(Passport passport, boolean uppercase) {
		// Check if the input passport is null
		if (passport == null) {
			return null;
		}// Create a copy of the provided passport object using the copy constructor
	    Passport newPassport = new Passport(passport);

	    // Apply case transformations to the copied passport's fields
	    if (uppercase) {
	        newPassport.firstname = newPassport.firstname.toUpperCase();
	        newPassport.middlename = newPassport.middlename.toUpperCase();
	        newPassport.lastname = newPassport.lastname.toUpperCase();
	    } else {
	        newPassport.firstname = newPassport.firstname.toLowerCase();
	        newPassport.middlename = newPassport.middlename.toLowerCase();
	        newPassport.lastname = newPassport.lastname.toLowerCase();
	    }

	    // Return the newly transformed passport without triggering revalidation
	    return newPassport;
	
	}

	public boolean changeLastname(String lastname) {
		
	String formattedlastname = validateAndFormat(lastname);
	if (formattedlastname == null) {
		return false;
	}
	
    this.lastname=formattedlastname;
		return true;
	}
	

	/*
	 * This method will generate and return a formatted string in lowercase with the
	 * first character in uppercase. The parameter is valid if it is not null and it
	 * is not blank according to the string method isBlank(). If the parameter is
	 * invalid, the method will return null and perform no further processing. If
	 * the parameter is valid, spaces surrounding the parameter will be removed, the
	 * string will be converted to lowercase, and the first character of the string
	 * (after spaces have been removed) will be in upper case. The following methods
	 * can be helpful during the implementation of this method:
	 * Character.toUpperCase, and the string methods charAt and substring.
	 * 
	 * You can test this method by initially defining it public; once you have
	 * tested it, make it private.
	 * 
	 */
	private static String validateAndFormat(String name) {
		if(name.isBlank()||name==null) {
			return null;
		}else {
			name=name.trim();
			name=name.toLowerCase();
			name= Character.toUpperCase(name.charAt(0)) + name.substring(1) ;
		}
		return name;
		}
		//throw new UnsupportedOperationException("Implement this method");
	}
