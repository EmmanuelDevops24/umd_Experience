package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import programs.Passport;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StudentTests {
	/*
	 * Each method in a JUnit StudentTest class represents a test. You can write
	 * code in a method of this class as you do in the main method of a driver. As
	 * you write your code, define methods in this class that test each of the
	 * methods you need to implement. When you run a method you can have
	 * System.out.println statements to see the results of your code. Using this
	 * approach is simpler than defining driver classes.
	 * 
	 * For this project you don't need to worry about adding assertions (we will
	 * talk about them soon). If you don't add assertions, by default, every test
	 * will pass (so when you run your student tests you will see a green bar). We
	 * have left two examples of tests below so you can see how you can test your
	 * code.
	 * 
	 * You can run a single test (e.g., testingtoString() below) by double-clicking
	 * on the method's name and selecting Run-->Run As-->JUnit Test. You can also
	 * double-click on the method's name and select the white triangle that is
	 * inside of a green circle (under Navigate menu entry).
	 */

	@Test
	public void testingtoString() {
		Passport passport1 = new Passport("Rose", "Sanders");
		System.out.println(passport1);
	}

	@Test
	public void testingSetSeparator() {
		Passport passport1 = new Passport("Tom", "Johnson");
		System.out.println(passport1);
		
		passport1.setSeparator('#');
		System.out.println(passport1);
	}
	
	@Test
	public void testingConstructorWithFirstLastMiddle() {
		Passport passport = new Passport("John", "Michael", "Doe");
		System.out.println("Testing constructor with first, middle, last names");
		assertEquals("Doe,John,Michael", passport.toString());
	}

	@Test
	public void testingConstructorWithFirstLast() {
		Passport passport = new Passport("John", "Doe");
		System.out.println("Testing constructor with first and last names");
		assertEquals("Doe,John", passport.toString());
	}

	@Test
	public void testingDefaultConstructor() {
		Passport passport = new Passport();
		System.out.println("Testing default constructor");
		assertEquals("Samplelastname,Samplefirstname,Samplemiddlename", passport.toString());
	}

	@Test
	public void testingCopyConstructor() {
		Passport original = new Passport("Alice", "Mary", "Smith");
		Passport copy = new Passport(original);
		System.out.println("Testing copy constructor");
		assertEquals(original.toString(), copy.toString());
		assertNotSame(original, copy); // Ensure it's a different object
	}

	@Test
	public void testingAddStamp() {
		Passport passport = new Passport("John", "Michael", "Doe");
		passport.addStamp("USA");
		passport.addStamp("UK");
		System.out.println("Testing addStamp");
		assertEquals("USAUK", passport.getStamps().toString());
	}

	@Test
	public void testingGetStamps() {
		Passport passport = new Passport("John", "Michael", "Doe");
		passport.addStamp("Canada");
		System.out.println("Testing getStamps");
		assertEquals("Canada", passport.getStamps().toString());
	}

	@Test
	public void testingSetSeparatorValid() {
		Passport passport = new Passport("John", "Michael", "Doe");
		System.out.println("Testing setSeparator with valid input");
		assertTrue(passport.setSeparator(';'));
		assertEquals(';', passport.getSeparator());
	}

	@Test
	public void testingSetSeparatorInvalid() {
		Passport passport = new Passport("John", "Michael", "Doe");
		System.out.println("Testing setSeparator with invalid input");
		assertFalse(passport.setSeparator(' ')); // Space character should be invalid
		assertNotEquals(' ', passport.getSeparator());
	}

	@Test
	public void testingChangeLastnameValid() {
		Passport passport = new Passport("John", "Michael", "Doe");
		System.out.println("Testing changeLastname with valid input");
		assertTrue(passport.changeLastname("Smith"));
		assertEquals("Smith,John,Michael", passport.toString());
	}

	@Test
	public void testingChangeLastnameInvalid() {
		Passport passport = new Passport("John", "Michael", "Doe");
		System.out.println("Testing changeLastname with invalid input");
		assertFalse(passport.changeLastname("")); // Invalid (blank) name
		assertEquals("Doe,John,Michael", passport.toString());
	}

	@Test
	public void testingEquals() {
		Passport passport1 = new Passport("John", "Michael", "Doe");
		Passport passport2 = new Passport("John", "Michael", "Doe");
		System.out.println("Testing equals method");
		assertTrue(passport1.equals(passport2));
	}

	@Test
	public void testingCompareTo() {
		Passport passport1 = new Passport("John", "Michael", "Doe");
		Passport passport2 = new Passport("Alice", "Marie", "Smith");
		System.out.println("Testing compareTo method");
		assertTrue(passport1.compareTo(passport2) < 0); // Doe should be "less than" Smith
	}

	@Test
	public void testingNormalizeUppercase() {
		Passport passport = new Passport("John", "Michael", "Doe");
		Passport normalized = Passport.normalize(passport, true);
		System.out.println("Testing normalize (uppercase)");
		assertEquals("DOE,JOHN,MICHAEL", normalized.toString());
	}

	@Test
	public void testingNormalizeLowercase() {
		Passport passport = new Passport("JOHN", "MICHAEL", "DOE");
		Passport normalized = Passport.normalize(passport, false);
		System.out.println("Testing normalize (lowercase)...");
		assertEquals("doe,john,michael", normalized.toString());
	}

	@Test
	public void testingGetNumberOfPassportObjects() {
		Passport.resetNumberOfPassportObjects();
		new Passport("John", "Michael", "Doe");
		new Passport("Jane", "Smith");
		System.out.println("Testing getNumberOfPassportObjects");
		assertEquals(2, Passport.getNumberOfPassportObjects());
	}
}

