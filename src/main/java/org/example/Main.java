package org.example;

import org.smartercalculator.CalculationRequest;
import org.smartercalculator.SmarterCalculator;
import org.smartercalculator.calculatorResult.CalculationResult;
import org.smartercalculator.calculatorResult.InvalidOperationException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

class JustMyFirstException extends RuntimeException {
    public JustMyFirstException() {
        super("Oopsie! This is my first exception!");
    }
}


public class Main {
    public static void foo() {
        try {
            System.out.println("1");
            throw new RuntimeException();
        } catch (RuntimeException e) {
            System.out.println("2");
        }

        System.out.println("3");
    }

    public static void bar() {
        try {
            throw new ClassCastException();
        } catch (ClassCastException e) {
        } catch (RuntimeException e) {
            System.out.println("fail");
        }
    }

    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");
        try {
            throw new JustMyFirstException();
        } catch (JustMyFirstException e) {
            System.out.println(e.getMessage());
        }
        args = new String[]{
                "1", "+", "2",
                "2", "*", "5",
                "1", "+", "5.0",
                "1.0", "-", "2",
                "10.0", "/", "1",
        };

        Student1 var1 = new Student1();
        var1.setFullName("Ion");
        var1.addCourse("Math", 10.0);
        var1.addCourse("Physics", 9.0);
        try {
            Student1 var2 = var1.clone();
            var2.addCourse("Chemistry", 8.0);
            var2.setFullName("Vasile");
            System.out.println("VAR1");
            System.out.println(var1.getFullName());
            System.out.println(var1.getCourseInformation());
            System.out.println("VAR2");
            System.out.println(var2.getFullName());
            System.out.println(var2.getCourseInformation());
        } catch (CloneNotSupportedException e) {
            System.out.println("Cloning not supported");
        }

        foo();
        bar();

        RawDataStudentSerializer rawDataStudentSerializer = new RawDataStudentSerializer();
        RawDataStudentDeserializer rawDataStudentDeserializer = new RawDataStudentDeserializer();
        StudentStorage studentStorage = new StudentStorage(rawDataStudentSerializer, rawDataStudentDeserializer);
        Student student = new Student("Ion");
        student.addCourseInformation("Math", 10.0);
        student.addCourseInformation("Physics", 9.0);
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("student.txt");
        } catch (Exception e) {
            System.out.println("An error occurred while creating the output stream");
        }
        studentStorage.writeAllStudents(outputStream, List.of(student));
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("student.txt");
        } catch (Exception e) {
            System.out.println("An error occurred while creating the input stream");
        }
        List<Student> students = studentStorage.readAllStudents(inputStream);
        for (Student student1 : students) {
            System.out.println(student1.getFullName());
            System.out.println(student1.getCourseInformation());
        }
    }

    private static void runSmarterCalculator(String[] args) {
        List<CalculationResult> calculationResults =  SmarterCalculator.calculate(args);

        for (CalculationResult result : calculationResults) {
            CalculationRequest request = result.getRequest();
            try {
                System.out.println("Operation " + request + " has result " + result.computeResult());
            } catch (InvalidOperationException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}