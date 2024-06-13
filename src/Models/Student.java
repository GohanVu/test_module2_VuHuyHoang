package Models;

public class Student extends Person{
    private static int nextCode = 1;
    private String classCode ;

    public Student( String name, String dob, String gender, String phoneNumber, String classCode) {
        super(nextCode++, name, dob, gender, phoneNumber);
        this.classCode = classCode;
    }


    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }
}
