package View;

import Models.Student;

import java.util.List;
import java.util.Scanner;

public class StudentView {
    public int view (){
        System.out.println("1.Add student");
        System.out.println("2.Remove student");
        System.out.println("3.Edit student");
        System.out.println("4.Show all");
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
    public Student viewAdd(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input name: ");
        String name = scanner.nextLine();
        System.out.println("Input dob: ");
        String dob = scanner.nextLine();
        System.out.println("Input gender: ");
        String gender = scanner.nextLine();
        System.out.println("Input phone: ");
        String phone = scanner.nextLine();
        System.out.println("Input class code: ");
        String classCode = scanner.nextLine();
        Student student = new Student(name,dob,gender,phone,classCode);
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
            System.out.println(infoProduct(student));
        }
    }
    private String infoProduct(Student student) {
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
}
