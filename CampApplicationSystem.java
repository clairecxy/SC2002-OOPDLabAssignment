import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CampApplicationSystem {
    public static void main(String[] args) {

        //initialise lists for camps, staff, students and all users
        List<Camp> allCamps = new ArrayList<>();
        List<User> staffList = new ArrayList<>();
        List<User> studentList = new ArrayList<>();
        List<User> allUsers = new ArrayList<>();
        allUsers.addAll(studentList);
        allUsers.addAll(staffList);

        Scanner sc = new Scanner(System.in);
        
        //first screen for login authentication
        System.out.println("LOGIN");
        System.out.println("UserID:");
        String userId = sc.nextLine();
        System.out.println("Password:");
        String password = sc.nextLine();

        boolean loginSucc = false;
        User authUser = null;

        while (!loginSucc){
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
                System.out.println("LOGIN");
                System.out.println("UserID:");
                userId = sc.nextLine();
                System.out.println("Password:");
                password = sc.nextLine();

            }
        }
        
        //if staff login:
        if (staffList.contains(authUser)){      //login page for staff
            Staff authStaff = (Staff) authUser;     //downcast
            System.out.println("Please selection an action:\r\n"
                +"(1) Change Password\r\n"
                +"(2) Create a camp\r\n"
                +"(3) View your camps\r\n"      //from here: can edit, delete
                +"(4) View all camps\r\n");
            int staffChoice = sc.nextInt();

            switch (staffChoice) {
                case 1:  
                    System.out.println("Please enter new password:");
                    String staffPW = sc.nextLine();
                    authUser.setPassword(staffPW);
                    break;

                case 2:
                    System.out.println("Please enter Camp name:");
                    String campName = sc.nextLine();
                    System.out.println("Please enter Camp start date in DD-Month name-YYYY format:");
                    String startDate = sc.nextLine();
                    System.out.println("Please enter Camp end date in DD-Month name-YYYY format:");
                    String endDate = sc.nextLine();
                    System.out.println("Please enter Camp registration end date in DD-Month name-YYYY format:");
                    String regEndDate = sc.nextLine();
                    System.out.println("Please enter user group this camp is open to: (own school or whole NTU)");
                    String userGrp = sc.nextLine();
                    System.out.println("Please enter Camp location:");
                    String location = sc.nextLine();
                    System.out.println("Please enter total slots of Camp:");
                    int totalSlots = sc.nextInt();
                    System.out.println("Please enter total slots for Camp Committee: (MAX 10)");
                    int campCommSlots = sc.nextInt();
                    if (campCommSlots>10){      //check that its max 10
                        System.out.println("There cannot be more than 10 slots!");
                        System.out.println("Please re-enter total slots for Camp Committee: (MAX 10)");
                        campCommSlots = sc.nextInt();
                    }
                    System.out.println("Please enter Camp description");
                    String description = sc.nextLine();
                    allCamps.add(authStaff.createCamp(campName, startDate, endDate, regEndDate, userGrp, location, totalSlots, campCommSlots, description, authStaff));
                    // ^ this creates the camp + adds it to the allCamps list & staff's createdCamps list.

                case 3:
                    List<Camp> createdCamps = authStaff.viewAllCreatedCamps();
                    System.out.println("Select option to edit camp:"
                    +"List of Camps created:");
                    int campIndex = 1;
                    for (Camp campsCreated : createdCamps){
                        System.out.println("(" + campIndex + ")" + campsCreated.getCampName());
                        campIndex++;
                    }
                    System.out.println("("+ campIndex+1 + ") Exit. No edits to be made.");

                    int editCampIndex = sc.nextInt();
                    if (editCampIndex == campIndex+1){
                        break;
                    }
                    else{
                        Camp editCamp = createdCamps.get(editCampIndex-1);      //get selected camp from list of camps staff created
                        int editFieldOption;
                        do{
                            System.out.println("Please select field to be edited:"
                            +"(1) Camp Name"
                            +"(2) Start date"
                            +"(3) End date"
                            +"(4) Registration closing date" 
                            +"(5) User group this camp is open to: own school or whole NTU"
                            +"(6) Location"
                            +"(7) Total Slots"
                            +"(8) Camp Committee Slots (max 10)"
                            +"(9) Description"
                            +"(10) Delete camp"
                            +"(11) Quit");

                            editFieldOption = sc.nextInt();
                            
                            switch (editFieldOption) {
                                case 1:
                                    System.out.println("Please enter new Camp name:");
                                    String newCampName = sc.nextLine();
                                    editCamp.setCampName(newCampName);
                                    break;

                                case 2:
                                    System.out.println("Please enter new start date in DD-month name-YYYY:");
                                    String newStartDate = sc.nextLine();
                                    editCamp.setStartDate(newStartDate);
                                    break;

                                case 3:
                                    System.out.println("Please enter new end date in DD-month name-YYYY:");
                                    String newEndDate = sc.nextLine();
                                    editCamp.setEndDate(newEndDate);
                                    break;

                                case 4:
                                    System.out.println("Please enter new registration end date in DD-month name-YYYY:");
                                    String newRegEndDate = sc.nextLine();
                                    editCamp.setRegistrationEndDate(newRegEndDate);
                                    break;

                                case 5:
                                    System.out.println("Please enter new user group this camp is open to: (own school or whole NTU)");
                                    String newUserGrp = sc.nextLine();
                                    editCamp.setUserGroup(newUserGrp);
                                    break;

                                case 6:
                                    System.out.println("Please enter new Camp location:");
                                    String newLocation = sc.nextLine();
                                    editCamp.setLocation(newLocation);
                                    break;

                                case 7:
                                    System.out.println("Please enter new total slots of Camp:");
                                    int newTotalSlots = sc.nextInt();
                                    editCamp.setTotalSlots(newTotalSlots);
                                    break;

                                case 8:
                                    System.out.println("Please enter total slots for Camp Committee:");
                                    int newCampCommSlots = sc.nextInt();
                                    if (newCampCommSlots>10){      //check that its max 10
                                        System.out.println("There cannot be more than 10 slots!");
                                        System.out.println("Please re-enter total slots for Camp Committee: (MAX 10)");
                                        newCampCommSlots = sc.nextInt();
                                    }
                                    editCamp.setCommitteeSlots(newCampCommSlots);
                                    break;

                                case 9:
                                    System.out.println("Please enter new Camp description");
                                    String newDescription = sc.nextLine();
                                    editCamp.setDescription(newDescription);
                                    break;

                                case 10:
                                    authStaff.deleteCamp(editCamp);    //delete from staff's list
                                    allCamps.remove(editCamp);         // delete from all camp list
                                    break;

                                default:
                                    break;
                            }
                        } while (editFieldOption<11);
                        break;
                    }

                case 4:
                    int allCampsIndex = 1;
                    for (Camp camps : allCamps){
                        System.out.println("(" + allCampsIndex + ")" + camps.getCampName());
                        allCampsIndex++;
                    }
                    break;

                default:
                    break;
            }

        }
        //if student login:
  /*       else if(studentList.contains(authUser)){
            
        }*/
        
        

    //     public List<User> getAllUser() {
    //         return allUsers;
    //     }

    //     public List<Camp> getAllCamp() {
    //         return allCamps;
    //     }

    //     public void addUser(User user) {
    //         allUsers.add(user);
    //     }

    //     public void addCamp(Camp camp) {
    //         allCamps.add(camp);
    //     }
    }

    
}
