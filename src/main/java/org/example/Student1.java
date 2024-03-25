package org.example;

import java.util.Map;

public class Student1 {
    private String fullName;
    private Map<String, Double> courseInformation;
    // implementati mecanismul de clonare pentru aceasta clasa
    public Student1 clone() {
        Student1 student = new Student1();
        student.fullName = new String(this.fullName);
        for(Map.Entry<String, Double> entry : this.courseInformation.entrySet()) {
            student.courseInformation.put(entry.getKey(), entry.getValue());
        }
        return student;
    }
}
