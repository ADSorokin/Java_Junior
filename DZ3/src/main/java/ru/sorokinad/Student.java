package ru.sorokinad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.*;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int age;


    @JsonIgnore
    private transient double gpa;

    public Student(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    public Student() {
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGPA() {
        return gpa;
    }

    public void setGPA(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student{" + "name='" + name + '\'' + ", age=" + age + ", gpa=" + gpa + '}';
    }


    public static void main(String[] args) throws IOException {
        Student student = new Student("Vasia Dvoeshnikov", 18, 2.5);

        try {

            FileOutputStream fileOutputStream = new FileOutputStream("student.bin");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(student);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Сериализация в  bin завершена");

            FileInputStream fileInputStream = new FileInputStream("student.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Student deserializedStudent = (Student) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

            System.out.println(deserializedStudent.toString());
            System.out.println("ДеСериализация объекта из bin завершена");
            System.out.println("GPA was not be saved because it is marked as Transient");

            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.writeValue(new File("student.xml"), student);

            System.out.println("Сериализация в XML завершена");

            File file = new File("student.xml");
            XmlMapper xmlMapperOut = new XmlMapper();
            Student value = xmlMapperOut.readValue(file,Student.class);
            System.out.println(value.toString());
            System.out.println("ДеСериализация объекта из XML завершена");
            System.out.println("GPA was not be saved because field is marked  @JsonIgnore");

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(student);

            FileWriter writer = new FileWriter("student.json");
            writer.write(jsonString);
            writer.close();
            System.out.println("Сериализация в JSON завершена");

            ObjectMapper objectMapperOut = new ObjectMapper();
            Student studentOut = objectMapperOut.readValue(jsonString, Student.class);
            System.out.println(studentOut.toString());
            System.out.println("ДеСериализация объекта из JSON завершена");
            System.out.println("GPA was not be saved because field is marked  @JsonIgnore");


            YAMLMapper yamlMapper = new YAMLMapper();
            yamlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String yamlString = yamlMapper.writeValueAsString(student);


            System.out.println("Сериализация в YAML завершена");


            FileWriter yamlwriter = new FileWriter("student.yaml");
            yamlwriter.write(yamlString);
            yamlwriter.close();


            Student deserializedStudentYaml = yamlMapper.readValue(yamlString, Student.class);
            System.out.println(deserializedStudentYaml.toString());
            System.out.println("ДеСериализация объекта из YAML завершена");
            System.out.println("GPA was not be saved because field is marked  @JsonIgnore");


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


}

