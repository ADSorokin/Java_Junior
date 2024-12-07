<<<<<<< HEAD
package ru.sorokinad;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

import java.io.*;

@XmlRootElement
@XmlType(propOrder = {"name", "age", "GPA"})
class StudentGson {
    private String name;
    private int age;
    @XmlTransient
    @Expose
    private transient double GPA;

    public  StudentGson() { }

    public StudentGson(String name, int age, double GPA) {
        this.name = name;
        this.age = age;
        this.GPA = GPA;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGPA() {
        return GPA;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + ", GPA=" + GPA + "}";
    }
}

public class GsonSerialization {
    public static void main(String[] args) throws JAXBException, IOException {
        StudentGson student = new StudentGson("Vasia Dvoeshnikov", 18, 2.5);


        Gson gson = new GsonBuilder().serializeNulls().create();
        String json = gson.toJson(student);
        System.out.println("Сериализованный JSON: " + json.toString());
        FileWriter writer = new FileWriter("student.json");
        writer.write(json);
        writer.close();

        StudentGson deserializedStudent = gson.fromJson(json, StudentGson.class);
        System.out.println("Десериализованный объект: " + deserializedStudent.toString());


        JAXBContext context = JAXBContext.newInstance(StudentGson.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        File xmlFile = new File("student.xml");
        marshaller.marshal(student, xmlFile);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        StudentGson deserializedStudentXml = (StudentGson) unmarshaller.unmarshal(xmlFile);

        System.out.println("Десериализованный XML объект: " + deserializedStudentXml.toString());

    }
=======
package ru.sorokinad;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

import java.io.*;

@XmlRootElement
@XmlType(propOrder = {"name", "age", "GPA"})
class StudentGson {
    private String name;
    private int age;
    @XmlTransient
    @Expose
    private transient double GPA;

    public  StudentGson() { }

    public StudentGson(String name, int age, double GPA) {
        this.name = name;
        this.age = age;
        this.GPA = GPA;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGPA() {
        return GPA;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + ", GPA=" + GPA + "}";
    }
}

public class GsonSerialization {
    public static void main(String[] args) throws JAXBException, IOException {
        StudentGson student = new StudentGson("Vasia Dvoeshnikov", 18, 2.5);


        Gson gson = new GsonBuilder().serializeNulls().create();
        String json = gson.toJson(student);
        System.out.println("Сериализованный JSON: " + json.toString());
        FileWriter writer = new FileWriter("student.json");
        writer.write(json);
        writer.close();

        StudentGson deserializedStudent = gson.fromJson(json, StudentGson.class);
        System.out.println("Десериализованный объект: " + deserializedStudent.toString());


        JAXBContext context = JAXBContext.newInstance(StudentGson.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        File xmlFile = new File("student.xml");
        marshaller.marshal(student, xmlFile);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        StudentGson deserializedStudentXml = (StudentGson) unmarshaller.unmarshal(xmlFile);

        System.out.println("Десериализованный XML объект: " + deserializedStudentXml.toString());

    }
>>>>>>> e44b8c052adbdfe7e11b75f4c96db7313b917200
}