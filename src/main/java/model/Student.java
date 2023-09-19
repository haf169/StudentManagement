/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Tran HaF
 */
public class Student implements Comparable<Student> {
    private String stuID;
    private String stuName;
    private String semester;
    private String courseName;

    public Student(String stuID, String stuName, String semester, String courseName) {
        this.stuID = stuID;
        this.stuName = stuName;
        this.semester = semester;
        this.courseName = courseName;
    }

    public String getStuID() {
        return stuID;
    }

    public void setStuID(String stuID) {
        this.stuID = stuID;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "Student{" + "stuID=" + stuID + ", name=" + stuName + ", semester=" + semester + ", courseName=" + courseName + '}';
    }

    @Override
    public int compareTo(Student o) {
        return o.stuName.compareTo(this.stuName);
    }
    
}

