/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package controller;

import view.Validation;
import java.util.ArrayList;
import java.util.Collections;
import model.Report;
import model.Student;
import model.StudentList;
import view.Menu;

/**
 *
 * @author Tran HaF
 */
public class Student_Management extends Menu<String> {

    static String[] menu = {"Create", "Find and Sort", "Update/Delete", "Report", "Exit"};
    StudentList studentList = new StudentList();
    ArrayList<Student> students = new ArrayList<>();

    public Student_Management() {
        super("__________ STUDENT MANAGEMENT SYSTEM __________", menu);
    }

    @Override
    public void execute(int choice) throws Exception {
        switch (choice) {
            case 1:
                addStudent();
                break;
            case 2:
                findandSort();
                break;
            case 3:
                while (true) {
                    String ud = Validation.getString("Enter U to Update/ D to Delete: ");
                    switch (ud) {
                        case "U", "u":
                            updateStudent();
                            break;
                        case "D", "d":
                            deleteStudent();
                            break;
                        default:
                            System.out.println("Please enter again!");
                            break;
                    }
                    System.out.println("Do you want to continue?");
                    if (!Validation.checkInputYN()) {
                        return;
                    }
                }
            case 4:
                report();
                break;
            case 5:
                System.out.println("___<3___ Thank you ___<3___");
                System.out.println("Exiting......");
                System.exit(0);
            default:
                this.stop();
        }
    }

    public void addStudent() {
        while (true) {
            String stuID = null;
            while (true) {
                stuID = Validation.getString("Enter Student ID: ");

                if (!checkIDExist(stuID)) {
                    break;
                } else {
                    System.out.println("The Student ID is exist! Please enter again");
                }
            }
            String name = Validation.checkName("Enter Student Name: ");
            String semester = Validation.getString("Enter Semester: ");
            String courseName = Validation.checkCourseName("Enter Course Name: ");

            Student student = new Student(stuID, name, semester, courseName);
            studentList.addStudent(student);

            System.out.println("Add student successfully.");
            System.out.println("Do you want to continue?");
            if (!Validation.checkInputYN()) {
                return;
            }
        }
    }

    public boolean checkIDExist(String stuID) {
        stuID = stuID.trim().toUpperCase();

        for (Student student : studentList.getListStudent()) {
            if (student.getStuID().equalsIgnoreCase(stuID)) {
                return true;
            }
        }
        return false;
    }

    public void findandSort() {
        studentList.displayAll();
        String name = Validation.checkName("Enter Student Name to search: ");
        ArrayList<Student> stuName = studentList.search(student -> student.getStuName().contains(name));
        displaySearch(stuName);
    }

    public void displaySearch(ArrayList<Student> studentL) {
        if (studentL.isEmpty()) {
            System.out.println("No student found.");
        } else {
            System.out.println("List of Student:");
            for (Student student : studentL) {
                System.out.println(student.toString());
            }
        }
        System.out.println();
    }

    public void updateStudent() {
        studentList.displayAll();
        String id = Validation.getString("Enter the Student ID: ");

        Student studentToUpdate = null;
        for (Student student : studentList.getListStudent()) {
            if (student.getStuID().equals(id)) {
                studentToUpdate = student;
                break;
            }
        }

        if (studentToUpdate == null) {
            System.out.println("Not found student with ID: " + id);
            return;
        }

        String name = Validation.getString("Enter new name: ");
        String semester = Validation.getString("Enter new semester: ");
        String courseName = Validation.getString("Enter new courseName: ");

        studentList.updateStudent(studentToUpdate, name, semester, courseName);
        System.out.println("Update successfully");

    }

    public void deleteStudent() {
        studentList.displayAll();
        String id = Validation.getString("Enter the Student ID: ");

        for (Student student : studentList.getListStudent()) {
            if (student.getStuID().equals(id)) {
                System.out.println(student.toString());
            }
        }

        while (true) {
            System.out.println("Are you sure to delete this Student? ");

            if (!Validation.checkInputYN()) {
                System.out.println("Remove not sucessfully");
                break;

            } else {
                if (studentList.deleteStudent(id)) {
                    System.out.println("Remove sucessfully");
                    break;
                }
                System.out.println("Remove not sucessfully because the Code is not found!");
                break;
            }
        }

    }

    public void report() {
        ArrayList<Student> stu = studentList.getListStudent();
        if (stu.isEmpty()) {
            System.out.println("List is Empty.");
            return;
        }
        ArrayList<Report> listReports = new ArrayList<>();

        for (Student student : stu) {
            String id = student.getStuID();
            String courseName = student.getCourseName();
            String studentName = student.getStuName();
           
            int total = 0;

            for (Student students : stu) {
                if (studentName.equalsIgnoreCase(students.getStuName()) && courseName.equalsIgnoreCase(students.getCourseName())) {
                    total++;
                }
            }

            if (!Validation.checkReportExist(listReports, studentName, courseName, total)) {
                listReports.add(new Report(studentName, courseName, total));
            }
        }

        for (Report report : listReports) {
            System.out.printf("%-10s|%-5s|%-5d\n", report.getStuName(), report.getCourseName(), report.getTotalCourse());
        }
    }
}