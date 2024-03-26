package org.example;

import java.util.HashMap;
import java.util.Map;

public class Student1 {
    private String fullName;
    private Map<String, Double> courseInformation;

    public Student1() {
        this.courseInformation = new HashMap<String, Double>();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Map<String, Double> getCourseInformation() {
        return new HashMap<String, Double>(courseInformation);
    }

    public void addCourse(String courseName, double grade) {
        courseInformation.put(courseName, grade);
    }

    // implementati mecanismul de clonare pentru aceasta clasa
    public Student1 clone() throws CloneNotSupportedException{
        Student1 student = new Student1();
        student.fullName = String.valueOf(this.fullName);
        for (Map.Entry<String, Double> entry : this.courseInformation.entrySet()) {
            student.courseInformation.put(String.valueOf(entry.getKey()), entry.getValue());
        }
        return student;
    }
}
