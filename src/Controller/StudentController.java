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
                    student = studentView.viewAdd();
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
                case 3:
                    break;
                case 4:
                    studentList = studentService.getAll();
                    studentView.displayStudents(studentList);
                    studentView.viewMessage(true);
                    break;
                case 0:
                    return;
            }
        }
    }
}

