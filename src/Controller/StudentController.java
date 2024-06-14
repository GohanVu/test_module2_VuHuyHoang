package Controller;

import Models.Student;
import Service.IStudentService;
import Service.StudentService;
import View.StudentView;

import java.util.List;

public class StudentController {
    public static void main(String[] args) {
        StudentView studentView = new StudentView();
        IStudentService studentService = new StudentService();
        int choice;
        int code;
        Student student;
        boolean result;
        List<Student> studentList;
        while(true)
        {
            choice = studentView.view();
            switch (choice) {
                case 1:
                    student = studentView.viewAdd(studentService);
                    result = studentService.add(student);
                    studentView.viewMessage(result);
                    break;
                case 2:
                    code = studentView.inputCode();
                    student = studentService.findByCode(code);
                    if(student == null){
                        studentView.viewMessage(false);
                    } else {
                        boolean isConfirm = studentView.confirmDelete(student);
                        if(isConfirm){
                            studentService.remove(student);
                            studentView.viewMessage(true);
                        }
                    }
                     break;
                case 3:
                    int  searchChoice = studentView.viewSearch();
                    switch (searchChoice){
                        case 1:
                            String name = studentView.inputName();
                            studentList = studentService.searchByName(name);
                            if(studentList == null){
                                System.out.println("Not found your seeach: '" +name+"'");
                            } else {
                                System.out.println("Your search: "+name+"'");
                                studentView.searchResultByName(studentList,name);
                            }
                            break;
                        case 2:
                            code = studentView.inputCode();
                            studentList = studentService.searchByCode(code);
                            if(studentList == null){
                                System.out.println("Invalid code: '"+code+"'");
                            } else {
                                System.out.println("Your search: '"+code+"'");
                                studentView.searchResultByCode(studentList,code);
                            }
                            break;
                    }
                    break;
                case 4:
                    studentList = studentService.getAll();
                    studentView.displayStudents(studentList);
                    studentView.viewMessage(true);
                    break;
                case 5:
                    break;
                case 0:
                    return;
            }
        }
    }
}

