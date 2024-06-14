package View;

import Models.Student;
import Service.IStudentService;
import Service.StudentService;

import java.util.List;
import java.util.Scanner;

public class StudentView {
    public int view (){
        System.out.println("1.Add student");
        System.out.println("2.Remove student");
        System.out.println("3.Search");
        System.out.println("4.Show student list");
        System.out.println("5.Edit student");
        System.out.println("0.Exit");
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        do {
            try {
                System.out.print("Input your choice: ");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Choice must be a number, please try again");
            } catch (Exception e){
                System.out.println("Error,please try again!");
            } finally {
                System.out.println();
            }
        } while (choice < 0 || choice > 4);
        return choice;
    }
    public Student viewAdd(IStudentService studentService){
        Scanner scanner = new Scanner(System.in);
        int code;
        String name;
        String dob;
        String gender;
        String phone;
        String classCode;
        while (true){
            try {
                System.out.println("Input code:");
                code = Integer.parseInt(scanner.nextLine());
                if(studentService.isUniqueCode(code)){
                    break;
                } else {
                    System.out.println("Code already exist");
                }
            } catch (NumberFormatException e){
                System.out.println("Code mut be a number.");
            }
        }
        while (true){
            System.out.println("Input name: ");
            name = scanner.nextLine();
            if(name.length()>=4 && name.length() <=50){
                break;
            } else {
                System.out.println("Name must be between 4 and 50 characters");
            }
        }
        while (true){
            System.out.println("Input dob (dd/MM/YYYY): ");
            dob = scanner.nextLine();
            if(studentService.isValidDate(dob)){
                break;
            } else {
                System.out.println("Invalid date, please try again");
            }
        }
         while (true){
             System.out.println("Input gender (M:Male - F:Female) : " );
             gender = scanner.nextLine();
             if(gender.equalsIgnoreCase("F") || gender.equalsIgnoreCase("F")){
                 break;
             } else {
                 System.out.println("Invalid gender, please try again");
             }
         }
         while (true){
             System.out.println("Input phone: ");
             phone = scanner.nextLine();
             if(StudentService.PHONE_PATTERN.matcher(phone).matches()){
                 break;
             } else {
                 System.out.println("Invalid phone number, please try again");
             }
         }
        System.out.println("Input class code: ");
         classCode = scanner.nextLine();
        Student student = new Student(code,name,dob,gender,phone,classCode);
        return student ;
    }
    public void viewMessage(boolean result){
        if (result){
            System.out.println("Successful");
        }else {
            System.out.println("Failure");
        }
    }
    public void displayStudents(List<Student> students) {
        for (Student student : students) {
            System.out.println(infoStudent(student));
        }
    }
    private String infoStudent(Student student) {
        return
                student.getName()+" "+
                student.getDob()+ " "+
                student.getGender() + " "+
                student.getPhoneNumber()+ " "+
                student.getClassCode();
    }
    public int inputCode() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your code: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public boolean confirmDelete(Student student) {
        System.out.println("You want to delete? "+ student.getName());
        System.out.println("Press Y to confirm, N to cancel");
        Scanner scanner = new Scanner(System.in);
        String confirm = scanner.nextLine().toUpperCase();
        if(confirm.equals("Y")){
            return true;
        } else if (confirm.equals("N")){
            return false;
        } else {
            System.out.println("Invalid input, please try again");
            return  confirmDelete(student);
        }
    }

    public int viewSearch() {
        System.out.println("1.Search by name");
        System.out.println("2.Search by code");
        System.out.println("0.Back to main view");
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        do{
            try{
                System.out.println("Input your choice");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Choice must be a number");
            } catch (Exception e){
                System.out.println("Error, please try again");
            } finally {
                System.out.println();
            }
        } while (choice< 0 || choice > 2);
        return choice;
    }

    public String inputName() {
        System.out.println("Enter name: ");
        return new Scanner(System.in).nextLine();
    }

    public void searchResultByName(List<Student> studentList, String name) {
        System.out.println("RESULT for '"+name+"'");
        for(Student student : studentList){
            System.out.println(infoStudent(student));
        }
    }
    public void searchResultByCode(List<Student> studentList, int code) {
        System.out.println("RESULT for '"+code+"'");
        for(Student student : studentList){
            System.out.println(infoStudent(student));
        }
    }

}
