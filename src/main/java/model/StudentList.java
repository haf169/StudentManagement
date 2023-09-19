/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;

/**
 *
 * @author Tran HaF
 */
public class StudentList {
    ArrayList<Student> ListStudent;
    //------------------------------------------------------
    //------------------------------------------------------

    public StudentList() {
        ListStudent = new ArrayList<>();
    }

    public StudentList(ArrayList<Student> ListStudent) {
        this.ListStudent = ListStudent;
    }

    public ArrayList<Student> getListStudent() {
        return ListStudent;
    }

    public void addStudent(Student student) {
        ListStudent.add(student);
    }

    public void updateStudent(Student student, String stuName, String semester, String courseName) {
        if (!stuName.isBlank()) {
            student.setStuName(stuName);
        }
        if (!semester.isBlank()) {
            student.setSemester(semester);
        }
        if (!courseName.isBlank()) {
            student.setCourseName(courseName);
        }
    }

    public boolean deleteStudent(String id) {
        Student student = this.ListStudent.stream().filter(s -> s.getStuID().contains(id)).findFirst().orElse(null);
        if (student == null) {
            return false;
        } else {
            this.ListStudent.remove(student);
            return true;
        }
    }

    public ArrayList<Student> search(Predicate<Student> predicate) {
        ArrayList<Student> students = new ArrayList<>();
        for (Student student : ListStudent) {
            if (predicate.test(student)) {
                students.add(student);
            }
        }
        return students;
    }

    public void displayAll() {
        if (ListStudent.isEmpty()) {
            System.out.println("No Student on the List");
        } else {
            Collections.sort(ListStudent);
            System.out.println("The Students found: ");
            for (Student student : ListStudent) {
                System.out.println(student);
            }
            System.out.println();
        }
    }
}
