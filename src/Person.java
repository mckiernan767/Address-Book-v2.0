import java.util.Scanner;

public class Person {
	private int id;
	private String firstName;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String lastName;
	private String zipCode;
	private String phone;

	private String emailAddress;
	private String contractType;
	
	public String getFirstName() {
		return firstName;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}


	public Person() {
	}

	public Person(int id, String firstName, String lastName, String zipCode, String phone, String emailAddress, String contractType) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.zipCode = zipCode;
		this.phone = phone;
		this.emailAddress = emailAddress;
		this.contractType = contractType;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Person createContact(Person person, Scanner scanner, int count) {
		System.out.println("Please enter the person's first name");
		String firstName = scanner.next();
		System.out.println("Please enter the person's last name");
		String lastName = scanner.next();
		System.out.println("Please enter the zipCode");
		String zipCode = scanner.next();
		System.out.println("Please enter the phone number");
		String phoneNumber = scanner.next();
		System.out.println("Please enter the email address");
		String emailAddress = scanner.next();
		
		return person = new Person(count+1, firstName, lastName, zipCode, phoneNumber, emailAddress, "");
	}
	
}
