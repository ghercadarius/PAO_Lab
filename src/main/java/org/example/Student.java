package org.example;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
    private final String fullName;

    private final Map<String, Double> courseInformation;

    public Student(String fullName) {
        this.fullName = fullName;
        this.courseInformation = new HashMap<>();
    }

    public void addCourseInformation(String courseName, double grade) {
        courseInformation.put(courseName, grade);
    }

    public String getFullName() {
        return fullName;
    }

    public Map<String, Double> getCourseInformation() {
        return new HashMap<>(courseInformation);
    }
}

interface StudentSerializer{
    Object serialize(OutputStream outputStream, Student student);
}

class RawDataStudentSerializer implements StudentSerializer{
    @Override
    public Object serialize(OutputStream outputStream, Student student) {
        try{
            outputStream.write(student.getFullName().getBytes());
            outputStream.write("\n".getBytes());
            for(Map.Entry<String, Double> entry : student.getCourseInformation().entrySet()){
                outputStream.write(entry.getKey().getBytes());
                outputStream.write(" ".getBytes());
                outputStream.write(entry.getValue().toString().getBytes());
                outputStream.write("\n".getBytes());
            }
        } catch (Exception e){
            System.out.println("An error occurred while serializing the student");
        }
        return null;
    }
}

interface StudentDeserializer{
    Object deserialize(InputStream inputStream);
}

class RawDataStudentDeserializer implements StudentDeserializer{
    @Override
    public Object deserialize(InputStream inputStream) {
        try{
            int data = inputStream.read();
            StringBuilder studentData = new StringBuilder();
            while(data != -1){
                studentData.append((char) data);
                data = inputStream.read();
            }
            if(studentData.length() == 0){
                return null;
            }
            System.out.println("@");
            String[] studentDataArray = studentData.toString().split("\n");
            String fullName = studentDataArray[0];
            Student student = new Student(fullName);
            System.out.println(student.getFullName());
            for(int i = 1; i < studentDataArray.length; i++){
                String[] courseInformation = studentDataArray[i].split(" ");
                student.addCourseInformation(courseInformation[0], Double.parseDouble(courseInformation[1]));
                System.out.println(courseInformation[0] + " " + courseInformation[1]);
            }
            return student;
        } catch (Exception e){
            System.out.println("An error occurred while deserializing the student");
        }
        return null;
    }
}

class StudentStorage{
    private final StudentSerializer studentSerializer;
    private final StudentDeserializer studentDeserializer;

    public StudentStorage(StudentSerializer studentSerializer, StudentDeserializer studentDeserializer) {
        this.studentSerializer = studentSerializer;
        this.studentDeserializer = studentDeserializer;
    }

    public List<Student> readAllStudents(InputStream inputStream){
        List<Student> students = new ArrayList<>();
        try{
            Student student = (Student) studentDeserializer.deserialize(inputStream);
            while(student != null){
                students.add(student);
                student = (Student) studentDeserializer.deserialize(inputStream);
            }
        } catch (Exception e){
            System.out.println("An error occurred while reading the students");
        }
        return students;
    }

    public void writeAllStudents(OutputStream outputStream, List<Student> students){
        for(Student student : students){
            studentSerializer.serialize(outputStream, student);
        }
    }
}

