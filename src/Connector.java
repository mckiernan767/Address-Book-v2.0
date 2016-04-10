import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Scanner;
import java.sql.PreparedStatement;

public class Connector {

	Connection conn = null;
	PreparedStatement preparedStatement = null;

	public Connector() {
		String name = "addressBook";
		String password = "Password";

		Connection conn = null;
		Statement st = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + name, "root", password);
			if (conn != null) {
				System.out.println("success");
				this.conn = conn;
			}
		} catch (SQLException e) {
			try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("SQL Excption found in Class connector.java.");
			}
			System.out.println("fail");
		} catch (Exception e) {
		}
	}

	public int count() {
		String insertStatement = "select count(*) from person";
		int count = 0;
		try {
			preparedStatement = conn.prepareStatement(insertStatement);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

	public void addPerson(Person person) {
		String insertStatement = "insert into person values(?,?,?,?,?,?,?)";

		int id = count() + 1;
		try {
			preparedStatement = conn.prepareStatement(insertStatement);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, person.getFirstName());
			preparedStatement.setString(3, person.getLastName());
			preparedStatement.setString(4, person.getZipCode());
			preparedStatement.setString(5, person.getPhone());
			preparedStatement.setString(6, person.getEmailAddress());
			preparedStatement.setInt(7, 1);
			preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeToDB(LinkedList<Person> contacts) {
		String insertStatement = "insert into person values(?,?,?,?,?,?,?)";
		for (int i = 0; i < count(); i++) {
			int id = count() + 1;
			Person person = contacts.get(i + 1);
			try {
				preparedStatement = conn.prepareStatement(insertStatement);
				preparedStatement.setInt(1, id);
				preparedStatement.setString(2, person.getFirstName());
				preparedStatement.setString(3, person.getLastName());
				preparedStatement.setString(4, person.getZipCode());
				preparedStatement.setString(5, person.getPhone());
				preparedStatement.setString(6, person.getEmailAddress());
				preparedStatement.setInt(7, 1);
				preparedStatement.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void editPerson(int id, Scanner scanner) {
		System.out.println("what do you want to edit? Enter name, zipcode, phone or email");
		String field = scanner.next();
		System.out.println("field = " + field);
		System.out.println("what is the new value");
		String newValue = scanner.next();
		String editStatement = null;
		if (field.equals("email")) {
			editStatement = "update person set emailAddress = ? where id = ?";
		} else if (field.equals("zipcode")){
			editStatement = "update person set zipCode = ? where id = ?";
		}else if(field.equals("phone")) {
			editStatement = "update person set phoneNumber = ? where id = ?";
		} else
			System.out.println("Wrong field");
		try {
			System.out.println(editStatement);
			preparedStatement = conn.prepareStatement(editStatement);
			preparedStatement.setString(1, newValue);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void displayDB() {
		String displayStatement = "select * from person";
		try {
			preparedStatement = conn.prepareStatement(displayStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String phoneNumber = rs.getString("phoneNumber");
				String emailAddress = rs.getString("emailAddress");
				System.out.println(
						"Person " + id + ' ' + firstName + ' ' + lastName + ' ' + phoneNumber + ' ' + emailAddress);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteRecord(int index) {
		String insertStatement = "delete from person where id =?";
		try {
			preparedStatement = conn.prepareStatement(insertStatement);
			preparedStatement.setInt(1, index);
			preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void sortDB(String field){
		String sortStatement = "select * from person order by ?";
		try {
			preparedStatement = conn.prepareStatement(sortStatement);
			preparedStatement.setString(1, field);
			preparedStatement.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		displayDB();
	}

	public LinkedList<Person> readData() throws SQLException {
		String insertStatement = "select * from person";
		preparedStatement = conn.prepareStatement(insertStatement);
		ResultSet rs = preparedStatement.executeQuery();
		LinkedList<Person> contacts = new LinkedList<Person>();
		Person person;

		while (rs.next()) {
			int id = rs.getInt(1);
			String firstName = rs.getString(2);
			String lastName = rs.getString(3);
			String zipCode = rs.getString(4);
			String phoneNumber = rs.getString(5);
			String emailAddress = rs.getString(6);
			String contractType = rs.getString(7);
			person = new Person(id, firstName, lastName, zipCode, phoneNumber, emailAddress, contractType);
			contacts.add(person);
		}
		return contacts;
	}
}
