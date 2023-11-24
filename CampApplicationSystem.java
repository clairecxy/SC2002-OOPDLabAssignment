import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
//import java.text.ParseException;
//import java.util.InputMismatchException;

public class CampApplicationSystem {
    public static void main(String[] args) {

        //get date
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateFormat.format(currentDate);

        //initialise lists for camps, staff, students and all users
        
        //student lists
        StudentListCreator studentListCreator = new StudentListCreator();
        List<User> studentList = studentListCreator.createStudentList();

        //staff lists
        StaffListCreator staffListCreator = new StaffListCreator();
        List<User> staffList = staffListCreator.createStaffList();
        
        List<Camp> allCamps = new ArrayList<>();
        List<User> allUsers = new ArrayList<>();
        allUsers.addAll(studentList);
        allUsers.addAll(staffList);

        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\r\n");
        
        
            boolean loginSucc = false;
            User authUser = null;
            boolean quit = false;

            while (quit == false){
                System.out.println("\n=====WELCOME TO THE CAMP APPLICATION SYSTEM====="); 
                System.out.println("Todays date is: " + formattedDate);
                System.out.println("Please selection an action:\n"
                +"(1) Login\n"
                +"(2) Quit application");
                
                try{    //exception handling for non-integers and invalid selections
                    int loginChoice = Integer.parseInt(sc.next());
                                        
                    if(loginChoice >2 || loginChoice<1){
                        throw new Exception("A valid selection was not made.");                    
                    }

                    switch (loginChoice) {
                        case 2:
                            System.out.println("Quitting application...");
                            quit = true;
                            break;
                        case 1:
                            System.out.println("\n=====LOGIN=====");
                            while (!loginSucc){
                                //first screen for login authentication
                                System.out.println("UserID:");
                                String userId = sc.next();
                                System.out.println("Password:");
                                String password = sc.next();
                                
                                for (User user : allUsers) {
                                    if (user.getUserID().equals(userId) && user.getPassword().equals(password)) {
                                        System.out.println("Login successful for user: " + userId);
                                        loginSucc = true;
                                        authUser = user;
                                        break;
                                    }
                                }
                                if (!loginSucc){
                                    System.out.println("Authentication failed. Please try again.");
                                }
                            }
                            
                            //if staff login:
                            if (staffList.contains(authUser)){      //login page for staff
                                Staff authStaff = (Staff) authUser;     //downcast
                                StaffUI staffUI = new StaffUI();
                                staffUI.staffInterface(authStaff, authUser, allCamps);

                                loginSucc = false;
                                authUser = null;
                                System.out.println("Logout successful."); 
                            }
                            //if student login:
                            else if(studentList.contains(authUser)){      //login page for staff
                                Student authStudent = (Student) authUser;
                                StudentUI studentUI = new StudentUI();
                                studentUI.studentInterface(authStudent, authUser, allCamps);
                                loginSucc = false;
                                authUser = null;
                                System.out.println("Logout successful."); 
                            }
                            
                    }
                
                }
                catch (NumberFormatException f){
                    System.out.println("Invalid input.");
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());                
                }               
            }
          
    } 
}
