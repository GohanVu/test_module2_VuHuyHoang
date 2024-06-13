package Repositories;

import Models.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class StudentRepo {
    private static final String SRC_STUDENT = "D:\\Codegym\\module2\\Module 2\\Exam_VuHuyHoang\\src\\data\\students.csv";
    private static List<Student> students = new ArrayList<>();
    static {
        File file = new File(SRC_STUDENT);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (Exception e){
                System.out.println("Error creating new file");
            }

        }
    }
    private static void writeFile(List<Student> students, boolean append) {
    File file = new File(SRC_STUDENT);
    FileWriter fileWriter = null;
    BufferedWriter bufferedWriter = null;
    try{
        fileWriter = new FileWriter(file,append);
        bufferedWriter = new BufferedWriter(fileWriter);
        for (Student student : students){
            bufferedWriter.write(toString(student));
            bufferedWriter.newLine();
        }
    } catch (IOException e){
        System.out.println("File writing error");
    } finally {
        if (bufferedWriter != null){
            try {
                bufferedWriter.close();
            } catch (IOException e){
                System.out.println("File closing error");
            }
        }
    }
}
    private static String toString (Student student){
        return student.getCode()+","+student.getName()+","+student.getDob()+","+student.getGender()+","+student.getPhoneNumber()+","+student.getClassCode();
    }
    public void add (Student student){
        List<Student> pants1 = getAll();
        pants1.add(student);
        writeFile(Collections.singletonList(student),true);
    }
    public List<Student> getAll() {
        List<Student> students1 = new LinkedList<>();
        File file = new File(SRC_STUDENT);
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine())!=null){
                String[] temp = line.split(",");
                students1.add(new Student(temp[1],temp[2],temp[3],temp[4],temp[5]));
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        } catch (IOException e){
            System.out.println("File reading error");
        } finally {
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e){
                    System.out.println("File closing error");
                }
            }
        }
        return students1;
    }

    public Student findByCode(int code) {
        List<Student> students1 =getAll();
        for (Student student : students1){
            if(student == null){
                return null;
            }
            if (student.getCode() == code){
                return student;
            }
        }
        return null;
    }

    public void remove(Student student) {
    List<Student> students1 = getAll();
    Student toRemove = null;
    for (Student s : students1) {
        if (s.getCode() == student.getCode()) {
            toRemove = s;
            break;
        }
    }
    if (toRemove != null) {
        students1.remove(toRemove);

        writeFile(students1, false);
    }
}

}

