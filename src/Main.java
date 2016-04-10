import java.util.LinkedList;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException {

		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		LinkedList<Person> contacts = new LinkedList<Person>();
		Connector conn = new Connector();
		displayMenu();
		
		int number = scanner.nextInt();

		while (number != 9) {
			switch (number) {
			case 1:
				contacts = conn.readData();
				break;
			case 2:
				Person person = new Person();
				int count = conn.count();
				person = person.createContact(person, scanner, count);
				conn.addPerson(person);
				break;
			case 4:
				conn.displayDB();
				break;
			case 5:
				System.out.println("Please enter the number of the person you wish to delete");
				conn.displayDB();
				number = scanner.nextInt();
				conn.deleteRecord(number);
				break;
			case 6:
				System.out.println("Please enter the number of the person you wish to edit");
				conn.displayDB();
				number = scanner.nextInt();
				conn.editPerson(number, scanner);
				break;
			case 7:
				displayMenu();
				break;
			case 8:
				System.out.println("What would you like to sort by, 'name' or 'address'?");
				String field = scanner.next();
				conn.sortDB(field);
				break;
			default:
				System.out.println("Invalid entry");
				break;
			}if (number != 9 && number != 5)
				System.out.println("Enter next choice, enter 7 to view menu");
			number = scanner.nextInt();
		}
	}

	public static void displayMenu() {
		System.out.println("Please choose from the following options");
		System.out.println("1) Read the data from the file");
		System.out.println("2) Add a new Person");
		System.out.println("4) Display address book");
		System.out.println("5) Delete an entry - enter a number");
		System.out.println("6) Edit a contact");
		System.out.println("7) Display Menu");
		System.out.println("8) Sort");
		System.out.println("9) To Exit");
	}
}
