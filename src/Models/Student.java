package Models;

public class Student extends Person{
    private String classCode ;

    public Student(int code, String name, String dob, String gender, String phoneNumber, String classCode) {
        super(code, name, dob, gender, phoneNumber);
        this.classCode = classCode;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }
}
