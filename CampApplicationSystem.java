import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CampApplicationSystem {
    public static void main(String[] args) {

        //initialise lists for camps, staff, students and all users
        
        //student lists
        studentListCreator studentListCreator = new studentListCreator();
        List<User> studentList = studentListCreator.createStudentList();

        //staff lists
        staffListCreator staffListCreator = new staffListCreator();
        List<User> staffList = staffListCreator.createStaffList();
        
        List<Camp> allCamps = new ArrayList<>();
        List<User> allUsers = new ArrayList<>();
        allUsers.addAll(studentList);
        allUsers.addAll(staffList);

        for (User students : staffList){
            System.out.println(students.getUserID());
        }

        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\r\n");
        
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
            int staffChoice;

            do{
                System.out.println("Please selection an action:\r\n"
                +"(1) Change Password\r\n"
                +"(2) Create a camp\r\n"
                +"(3) View your camps\r\n"      //from here: can edit, delete
                +"(4) View all camps\r\n"
                +"(5) Quit");
                
                staffChoice = sc.nextInt();
                
                //sc.nextInt();      //buffer

                switch (staffChoice) {
                    case 1:  
                        System.out.println("Please enter new password:");
                        String staffPW = sc.next();
                        authUser.setPassword(staffPW);
                        break;

                    case 2:
                        System.out.println("=====CREATE NEW CAMP=====");
                        System.out.println("Please enter Camp name:");
                        //sc.next();
                        String campName = sc.next();
                        System.out.println("Please enter Camp start date in DD-Month name-YYYY format:");
                        String startDate = sc.next();
                        System.out.println("Please enter Camp end date in DD-Month name-YYYY format:");
                        String endDate = sc.next();
                        System.out.println("Please enter Camp registration end date in DD-Month name-YYYY format:");
                        String regEndDate = sc.next();
                        System.out.println("Please enter user group this camp is open to: (own school or whole NTU)");
                        String userGrp = sc.next();
                        System.out.println("Please enter Camp location:");
                        String location = sc.next();
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
                        String description = sc.next();
                        allCamps.add(authStaff.createCamp(campName, startDate, endDate, regEndDate, userGrp, location, totalSlots, campCommSlots, description, authStaff));
                        // ^ this creates the camp + adds it to the allCamps list & staff's createdCamps list.
                        break;

                    case 3:
                        List<Camp> createdCamps = authStaff.viewAllCreatedCamps();
                        System.out.println("Select option to edit camp:\r\n"
                        +"List of Camps created:");
                        int campIndex = 1;
                        for (Camp campsCreated : createdCamps){
                            System.out.println("(" + campIndex + ") " + campsCreated.getCampName());
                            campIndex++;
                        }
                        System.out.println("("+ campIndex + ") Exit. No edits to be made.");

                        int editCampIndex = sc.nextInt();
                        if (editCampIndex == campIndex){
                            break;
                        }
                        else{
                            Camp editCamp = createdCamps.get(editCampIndex-1);      //get selected camp from list of camps staff created
                            int editFieldOption;
                            do{
                                System.out.println("Please select field to be edited:\r\n"
                                +"(1) Camp Name\r\n"
                                +"(2) Start date\r\n"
                                +"(3) End date\r\n"
                                +"(4) Registration closing date\r\n" 
                                +"(5) User group this camp is open to: own school or whole NTU\r\n"
                                +"(6) Location\r\n"
                                +"(7) Total Slots\r\n"
                                +"(8) Camp Committee Slots (max 10)\r\n"
                                +"(9) Description\r\n"
                                +"(10) Delete camp\r\n"
                                +"(11) Quit");

                                editFieldOption = sc.nextInt();
                                
                                switch (editFieldOption) {
                                    case 1:
                                        System.out.println("Please enter new Camp name:");
                                        String newCampName = sc.next();
                                        editCamp.setCampName(newCampName);
                                        break;

                                    case 2:
                                        System.out.println("Please enter new start date in DD-month name-YYYY:");
                                        String newStartDate = sc.next();
                                        editCamp.setStartDate(newStartDate);
                                        break;

                                    case 3:
                                        System.out.println("Please enter new end date in DD-month name-YYYY:");
                                        String newEndDate = sc.next();
                                        editCamp.setEndDate(newEndDate);
                                        break;

                                    case 4:
                                        System.out.println("Please enter new registration end date in DD-month name-YYYY:");
                                        String newRegEndDate = sc.next();
                                        editCamp.setRegistrationEndDate(newRegEndDate);
                                        break;

                                    case 5:
                                        System.out.println("Please enter new user group this camp is open to: (own school or whole NTU)");
                                        String newUserGrp = sc.next();
                                        editCamp.setUserGroup(newUserGrp);
                                        break;

                                    case 6:
                                        System.out.println("Please enter new Camp location:");
                                        String newLocation = sc.next();
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
                                        String newDescription = sc.next();
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
                        System.out.println("All camps:");
                        for (Camp camps : allCamps){
                            System.out.println("(" + allCampsIndex + ")" + camps.getCampName());
                            allCampsIndex++;
                        }
                        break;

                    default:
                        break;
                }

            }while (staffChoice<5);
            
        }
        //if student login:
        else if(studentList.contains(authUser)){      //login page for staff
            Student authStudent = (Student) authUser;   //downcast
            System.out.println(
                "(1) Change Password\r\n"
                +"(2) Camp Committee\r\n"
                +"(3) View your registered camps\r\n"       //can quit camp from here
                +"(4) View all available camps\r\n"         //can enquire about camps from here
                +"(5) Quit");       
            int studentChoice;
            //sc.nextInt();
            
            do {
                System.out.println("Please selection an action:");
                studentChoice = sc.nextInt();
                switch (studentChoice) {    //these loop until Quit is selected
                    case 1:
                        System.out.println("Please enter new password:");
                        String studentPW = sc.next();
                        authUser.setPassword(studentPW);
                        break;

                    case 2:
                        if (authStudent instanceof CampCommittee){                        
                            System.out.println("You are entering the camp committe interface..");
                        }
                        else{
                            System.out.println("You are not a committee member of any camp!");
                            break;
                        }
                        //camp comm interface
                        CampCommittee authCampCommittee = (CampCommittee) authStudent;
                        Camp commCamp = authCampCommittee.getCamp();
                        
                        System.out.println("Please select an action:"
                            + "(1) View details of camp"
                            + "(2) Submit suggestions for camp"
                            + "(3) View and reply enquiries"
                            + "(4) Edit suggestions"
                            + "(5) Generate report of student list"
                            + "(6) Quit");

                        int campCommSelection = sc.nextInt();

                        do{
                            switch (campCommSelection) {
                                case 1:     //view details of camp
                                    authCampCommittee.viewCampDetails();
                                    break;

                                case 2:
                                    System.out.println("Suggestion: ");
                                    String suggestion = sc.next();
                                    authCampCommittee.submitSuggestion(suggestion);     //this adds to comm's sugg list and camp's sugg list
                            
                                case 3: //view and reply enquiries
                                    List<Enquiry> enquiries = commCamp.getAllEnquiries();
    
                                    authCampCommittee.viewEnquiries();
                                    if (!authCampCommittee.hasEnquiries()){
                                        break;
                                    }

                                    System.out.println("Select enquiry to reply to, 0 to quit: ");
                                    int commReplyChoice = sc.nextInt();

                                    if (commReplyChoice == 0){
                                        break;
                                    }
                                    else{
                                        Enquiry replyingEnq = enquiries.get(commReplyChoice-1);
                                        System.out.println("Enter your reply: ");
                                        String enqReply = sc.next();
                                        authCampCommittee.replyEnquiry(replyingEnq, enqReply);
                                    }
                                    break;
                                
                                case 4: //edit suggestion
                                    List<Suggestion> submittedSuggestions = authCampCommittee.getSuggestions();
                                    System.out.println("Select suggestion to be edited, 0 to quit: ");
                                    int suggCounter = 1;
                                    for (Suggestion suggestions : submittedSuggestions) {
                                        System.out.println("(" + suggCounter+ ") Suggestion: " + suggestions.getSuggestionText() + "\n");
                                        suggCounter++;
                                    }
                                    
                                    int suggEditChoice = sc.nextInt();

                                    if (suggEditChoice == 0){
                                        break;
                                    }
                                    else{
                                        Suggestion suggChoice = submittedSuggestions.get(suggEditChoice-1);
                                        if (!suggChoice.processStatus()){
                                            System.out.println("Previous suggestion: " + suggChoice.getSuggestionText());
                                            
                                            System.out.println("Enter edited suggestion: ");
                                            String editedSugg = sc.next();
                                            suggChoice.setSuggestionText(editedSugg);
                                        }
                                        else{
                                            System.out.println("You cannot edit the suggestion after it has been processed!");
                                            break;
                                        }
                                        
                                    }
                                    break;

                                case 5:     //Generate report of student list
                                    //authCampCommittee.printGeneralReport(commCamp, filter);
                                    break;

                                default:
                                    break;
                            }
                        }while (campCommSelection<6);

                    case 3:
                        List<Camp> registeredCamps = authStudent.getEnrolledCamps();
                        System.out.println("Registered Camps");
                        int registedCampCounter = 1;
                        for (Camp regCamps : registeredCamps){
                            System.out.println("(" + registedCampCounter + ") " + regCamps.getCampName());
                        }

                        System.out.println("To quit a camp, enter camp number. To exit, enter 0.");
                        int quitCampSelection = sc.nextInt();

                        if (quitCampSelection == 0){        //doesn't want to quit anything
                            break;
                        }
                        else if (quitCampSelection <= registeredCamps.size()){      //chosen a camp to quit
                            Camp quittingCamp = registeredCamps.get(quitCampSelection-1);
                            if (authStudent instanceof CampCommittee){
                                CampCommittee authCampCommitteeQuit = (CampCommittee) authStudent;
                                if (quittingCamp.getCommitteeMembers().contains(authCampCommitteeQuit)){
                                    System.out.println("Camp Committee cannot quit camp!");         //if comm, cannot quit camp
                                }                            
                            }
                            else{
                                authStudent.setWithdrawnCamps(quittingCamp);       //removes from enrolled camp + adds to withdrawn camps
                            }
                        }

                    case 4:     //view all avail camps
                        List<Camp> availCamps = authStudent.getAvailableCamps(allCamps);
                        System.out.println("Available Camps: remaining slots");
                        int availCampCounter = 1;
                        for (Camp availCamp : availCamps){
                            System.out.println("(" + availCampCounter + ") " + availCamp.getCampName() + " : " + availCamp.getRemainingSlots());
                        }
                        System.out.println("(" + availCampCounter+1 + ") Quit");
                        System.out.println("Select camp for enquiries:");       //start of enquiry section
                        int enquireCamp = sc.nextInt();

                        if (enquireCamp == availCampCounter+1){
                            break;
                        }
                        else if (enquireCamp<availCampCounter+1){
                            Camp campForEnquire = availCamps.get(enquireCamp-1);
                            System.out.println(campForEnquire.getCampName() + " selected.\r\n"
                                +"Please enter your enquiry:");
                            String enquiry = sc.next();
                            authStudent.submitEnquiry(campForEnquire, enquiry);     //student submits enquiry
                        }
                        break;

                    default:
                        break;
                }
            } while(studentChoice<5);
            

        }
        
        sc.close();

        

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
