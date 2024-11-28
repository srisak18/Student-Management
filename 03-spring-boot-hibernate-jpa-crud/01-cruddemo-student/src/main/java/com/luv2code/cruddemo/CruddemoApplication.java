package com.luv2code.cruddemo;

import java.util.*;
import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
//import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class CruddemoApplication {
	protected Scanner scanner = new Scanner(System.in);
	protected Scanner scan = new Scanner(System.in);
	private Student tempStudent;


	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {

		return runner->{
			String cont = "yes";
			while(cont.equals("yes")){
				System.out.println("Select an option:\n 1. Create record for single student.\n 2. Create record for multiple students. \n 3. Read a record by Student ID.\n 4. Read List of Student's Recod.\n " +
						"5. Get the Student record by Lastname.\n 6. Get the Student record by Firstname.\n " +
						"7. Get the Student record by Email.\n 8. Exit.");
				int option = scanner.nextInt();
				switch (option) {
					case 1:
						createStudent(studentDAO);
						break;
					case 2:
						createMultipleStudents(studentDAO);
						break;
					case 3:
						readStudent(studentDAO);
						break;
						case 4:
							readAllStudents(studentDAO);
							break;
							case 5:
								readByLastname(studentDAO);
								break;
								case 6:
									readByFirstname(studentDAO);
									break;
									case 7:
										readByEmail(studentDAO);
										break;
					case 8:
						System.out.println("Execution completed..");
						System.exit(0);
					default:
						System.out.println("Invalid option");
						break;
				}
				System.out.println("Do you want to continue? (yes/no)");
				cont = scanner.next();
			}
		};
	}

	private Student getInputStudent() {

		System.out.println("Enter student first name: ");
		String firstName = scan.next();
		System.out.println("Enter student last name: ");
		String lastName = scan.next();
		System.out.println("Enter student email: ");
		String email = scan.next();
		return new Student(firstName, lastName, email);
	}

	private void readStudent(StudentDAO studentDAO) {

		// retrieve student based on the id: primary key
		System.out.println("Enter student ID: ");
		int id = scanner.nextInt();
		tempStudent = studentDAO.findById(id);

		// display students
		System.out.println("The Student details are: ");
		System.out.println(tempStudent);
	}


	private void createMultipleStudents(StudentDAO studentDAO) {
		try{
			//create multiple student
			System.out.println("Enter number of students: ");
			int n = scan.nextInt();
			for(int i = 0; i < n; i++) {
				tempStudent = getInputStudent();
				System.out.println("Saving the student...");
				studentDAO.save(tempStudent);

				// display id of the saved student
				System.out.println("Successfully saved the student..." + "\n" + tempStudent.getId());
			}
		}
		catch(InputMismatchException e){
			System.out.println("The input you entered is invalid");
		}
		catch(Exception e){
			System.out.println("Something went wrong");
		}

	}

	private void createStudent(StudentDAO studentDAO) {

		// create the student object
		tempStudent = getInputStudent();

		//save the student object
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		// display id of the saved student
		System.out.println("Successfully saved the student..." + "\n" + tempStudent.getId());
	}


	private void readAllStudents(StudentDAO studentDAO) {

		// Get list of Students
		List<Student> students = studentDAO.findAll();

		// display list of Student's records
		System.out.println("The Student's Record: ");
		for(Student student : students) {
			System.out.println(student);
		}
	}


	private void readByLastname(StudentDAO studentDAO) {

		// Get the Lastname from user
		System.out.println("Enter student last name: ");
		String lastname = scan.next();

		// Get list of students
		List<Student> students = studentDAO.findByLastName(lastname);

		// Display the result
		System.out.println("The Student's Record: ");
		for(Student student : students) {
			System.out.println(student);
		}

	}

	private void readByFirstname(StudentDAO studentDAO) {

		// Get the Firstname from user
		System.out.println("Enter student first name: ");
		String firstname = scan.next();

		// Get list of students
		List<Student> students = studentDAO.findByFirstName(firstname);

		// Display the result
		System.out.println("The Student's Record: ");
		for(Student student : students) {
			System.out.println(student);
		}
	}

	private void readByEmail(StudentDAO studentDAO) {

		// Get the email domain from user
		System.out.println("Enter student email domain: ");
		String email_domain = '%' + scan.next();

		// Get the list of students
		List<Student> students = studentDAO.findByEmail(email_domain);

		// Display the result
		System.out.println("The Student's Record: ");
		for(Student student : students) {
			System.out.println(student);
		}
	}
}


