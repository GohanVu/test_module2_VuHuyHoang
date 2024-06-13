package Service;

import Models.Person;
import Models.Student;
import Repositories.StudentRepo;

import javax.security.auth.Subject;
import java.util.List;

public class StudentService implements IStudentService {
    private StudentRepo studentRepo = new StudentRepo();

    @Override
    public boolean add(Student student) {
        if(student.getName().equals("")){
            return false;
        }
        if (student.getCode()<0|| student.getCode()>1000000){
            return false;
        }
        studentRepo.add(student);
        return true;
    }

    @Override
    public List<Student> getAll() {
        return studentRepo.getAll();
    }
    @Override
    public Student findByCode(int code) {
        return studentRepo.findByCode(code);
    }

    @Override
    public void remove(Student student) {
        studentRepo.remove(student);
    }
}
