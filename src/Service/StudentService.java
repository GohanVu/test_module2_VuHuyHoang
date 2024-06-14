package Service;

import Models.Person;
import Models.Student;
import Repositories.StudentRepo;

import javax.security.auth.Subject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StudentService implements IStudentService {
    private StudentRepo studentRepo = new StudentRepo();
    public static final Pattern PHONE_PATTERN = Pattern.compile("^(090|091)\\d{7}$");

    @Override
    public boolean add(Student student) {
        String name = student.getName();
        String dob = student.getDob();
        int code = student.getCode();
        if(name.length()<4 || name.length()>50){
            return false;
        }
        if(!isValidDate(dob)){
            return false;
        }
        if(!isUniqueCode(code)){
            return false;
        }
        studentRepo.add(student);
        return true;
    }
    @Override
    public boolean isValidDate(String dob){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(dob);
            return true;
        } catch (ParseException e){
            return false;
        }
    }

    @Override
    public List<Student> searchByName(String name) {
        List<Student> studentList = this.getAll();
        List<Student> result = new ArrayList<>();
        for (Student student : studentList){
            if(student.getName().toLowerCase().contains(name.toLowerCase())){
                result.add(student);
            }
        }
        return result.isEmpty()  ? null : result;
    }

    @Override
    public List<Student> searchByCode(int code) {
        List<Student> studentList = this.getAll();
        List<Student> result = new ArrayList<>();
        for (Student student : studentList){
            if(student.getCode() == code){
                result.add(student) ;
            }
        }
        return result.isEmpty() ? null : result;
    }

    @Override
    public boolean codeExists(int code) {
        for (Student student : this.getAll()){
            if(student.getCode() == code){
                return  true ;
            }
        }
        return  false;
    }

    @Override
    public boolean isValidPhoneNumber (String phoneNumber){
        return PHONE_PATTERN.matcher(phoneNumber).matches();
    }
    @Override
    public boolean isUniquePhoneNumber(String phoneNumber){
        return studentRepo.getAll().stream().noneMatch(s-> s.getPhoneNumber().equals(phoneNumber));
    }
    @Override
    public boolean isUniqueCode(int code){
        return studentRepo.getAll().stream().noneMatch(s->s.getCode()== code);
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
