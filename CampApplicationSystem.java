import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.InputMismatchException;

public class CampApplicationSystem {
    public static void main(String[] args) {

        //get date
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateFormat.format(currentDate);
        System.out.println(formattedDate);


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

        // for (User students : staffList){
        //     System.out.println(students.getUserID());
        // }

        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");

        boolean loginSucc = false;
        User authUser = null;
        boolean quit = false;

        while (quit == false){
            System.out.println("\n=====WELCOME TO THE CAMP APPLICATION SYSTEM====="); 
            System.out.println("Please selection an action:\r\n"
            +"(1) Login\r\n"
            +"(2) Quit application");

            int loginChoice = sc.nextInt();

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
                        int staffChoice;

                        do{
                            System.out.println("\n=====WELCOME STAFF MEMBER=====");
                            System.out.println("Please selection an action:\r\n"
                            +"(1) Change Password\r\n"
                            +"(2) Create new camp\r\n"
                            +"(3) View your camps\r\n"      //from here: can edit, delete
                            +"(4) View all camps\r\n"
                            +"(5) Logout");
                            
                            while (!sc.hasNextInt()){
                                sc.next();
                            }
                            staffChoice = sc.nextInt();
                            
                            //sc.nextInt();      //buffer

                            switch (staffChoice) {
                                case 1:
                                    System.out.println("\n=====CHANGE PASSWORD=====");  
                                    System.out.println("Please enter new password:");
                                    String staffPW = sc.next();
                                    authUser.setPassword(staffPW);
                                    break;

                                case 2:
                                    System.out.println("\n=====CREATE NEW CAMP=====");
                                    System.out.println("Please enter Camp name:");
                                    //sc.next();
                                    String campName = sc.next();

                                    // Take the start date
                                    String startDate;
                                    boolean startDateValid = false;
                                    do {
                                        System.out.println("Please enter Camp start date in DD-MM-YYYY format:");
                                        startDate = sc.next();
                                        try {
                                            dateFormat.setLenient(false);
                                            Date parsedStartDate = dateFormat.parse(startDate);

                                            // Check the format after parsing
                                            String formattedStartDate = dateFormat.format(parsedStartDate);
                                            if (startDate.equals(formattedStartDate)) {
                                                startDateValid = true;
                                            } else {
                                                System.out.println("Invalid date format. Please enter dates in DD-MM-YYYY format.");
                                            }
                                        } catch (ParseException e) {
                                            System.out.println("Invalid date format. Please enter dates in DD-MM-YYYY format.");
                                        }
                                    } while (!startDateValid);

                                    // Take the end date
                                    String endDate;
                                    boolean endDateValid = false;
                                    do {
                                        System.out.println("Please enter Camp end date in DD-MM-YYYY format:");
                                        endDate = sc.next();
                                        try {
                                            dateFormat.setLenient(false);
                                            Date parsedEndDate = dateFormat.parse(endDate);

                                            // Check the format after parsing
                                            String formattedEndDate = dateFormat.format(parsedEndDate);
                                            if (endDate.equals(formattedEndDate)) {
                                                endDateValid = true;
                                            } else {
                                                System.out.println("Invalid date format. Please enter dates in DD-MM-YYYY format.");
                                            }
                                        } catch (ParseException e) {
                                            System.out.println("Invalid date format. Please enter dates in DD-MM-YYYY format.");
                                        }
                                    } while (!endDateValid);

                            
                                    // Take the registration end date
                                    String regEndDate;
                                    boolean regEndDateValid = false;
                                    do {
                                        System.out.println("Please enter Camp registration end date in DD-MM-YYYY format:");
                                        regEndDate = sc.next();
                                        try {
                                            dateFormat.setLenient(false);
                                            Date parsedRegEndDate = dateFormat.parse(regEndDate);

                                            // Check the format after parsing
                                            String formattedRegEndDate = dateFormat.format(parsedRegEndDate);
                                            if (regEndDate.equals(formattedRegEndDate)) {
                                                regEndDateValid = true;
                                            } else {
                                                System.out.println("Invalid date format. Please enter dates in DD-MM-YYYY format.");
                                            }
                                        } catch (ParseException e) {
                                            System.out.println("Invalid date format. Please enter dates in DD-MM-YYYY format.");
                                        }
                                    } while (!regEndDateValid);

                                    String userGrp = "";
                                    boolean validUserGrp = false;
                                    do {
                                        try {
                                            System.out.println("Please enter user group this camp is open to: (ADM, EEE, SCSE, NBS, SSS, or NTU)");
                                            userGrp = sc.next();
                                            if (userGrp.equalsIgnoreCase("ADM") ||
                                                userGrp.equalsIgnoreCase("EEE") ||
                                                userGrp.equalsIgnoreCase("SCSE") ||
                                                userGrp.equalsIgnoreCase("NBS") ||
                                                userGrp.equalsIgnoreCase("SSS") ||
                                                userGrp.equalsIgnoreCase("NTU")) {
                                                userGrp = userGrp.toUpperCase();
                                                validUserGrp = true;
                                            } else {
                                                System.out.println("Invalid user group. Please enter a valid user group.");
                                                System.out.println("Valid user groups: ADM, EEE, SCSE, NBS, SSS or NTU");
                                            }
                                        } catch (InputMismatchException e) {
                                            System.out.println("Invalid input. Please enter a valid user group.");
                                            sc.next(); // consume the invalid input
                                        }
                                    } while (!validUserGrp);


                                    System.out.println("Please enter Camp location:");
                                    String location = sc.next();
                                    
                                    int totalSlots = 0;
                                    boolean validTotalSlots = false;
                                    do {
                                        try {
                                            System.out.println("Please enter total slots of Camp:");
                                            totalSlots = sc.nextInt();
                                            if (totalSlots <= 0){
                                                System.out.println("There must be at least 1 avbailable slot.");
                                                validTotalSlots = false;
                                            } else{
                                                validTotalSlots = true;
                                            }
                                        } catch (InputMismatchException e) {
                                            System.out.println("Invalid input. Please enter a valid number for total slots.");
                                            sc.next(); // consume the invalid input
                                        }
                                    } while (!validTotalSlots);

                                    int campCommSlots = 0;
                                    boolean validCampCommSlots = false;
                                    do {
                                        try {
                                            System.out.println("Please enter total slots for Camp Committee: (MAX 10)");
                                            campCommSlots = sc.nextInt();
                                            if (campCommSlots > 10) {
                                                System.out.println("There cannot be more than 10 slots!");
                                                System.out.println("Please re-enter total slots for Camp Committee: (MAX 10)");
                                            } else {
                                                validCampCommSlots = true;
                                            }
                                        } catch (InputMismatchException e) {
                                            System.out.println("Invalid input. Please enter a valid number for Camp Committee slots.");
                                            sc.next(); // consume the invalid input
                                        }
                                    } while (!validCampCommSlots);

                                    
                                    // System.out.println("Please enter total slots of Camp:");
                                    // int totalSlots = sc.nextInt();
                                    // System.out.println("Please enter total slots for Camp Committee: (MAX 10)");
                                    // int campCommSlots = sc.nextInt();
                                    // if (campCommSlots>10){      //check that its max 10
                                    //     System.out.println("There cannot be more than 10 slots!");
                                    //     System.out.println("Please re-enter total slots for Camp Committee: (MAX 10)");
                                    //     campCommSlots = sc.nextInt();
                                    // }
                                    System.out.println("Please enter Camp description:");
                                    String description = sc.next();
                                    allCamps.add(authStaff.createCamp(campName, startDate, endDate, regEndDate, userGrp, location, totalSlots, campCommSlots, description, authStaff));
                                    // ^ this creates the camp + adds it to the allCamps list & staff's createdCamps list.
                                    break;

                                case 3:
                                    System.out.println("\n=====VIEW YOUR CAMPS=====");
                                    List<Camp> createdCamps = authStaff.viewAllCreatedCamps();
                                    System.out.println("Select camp to view:\r\n"
                                    +"List of Camps created:");
                                    int campIndex = 1;
                                    for (Camp campsCreated : createdCamps){
                                        System.out.println("(" + campIndex + ") " + campsCreated.getCampName());
                                        campIndex++;
                                    }
                                    System.out.println("("+ campIndex + ") Exit.");int staffCampOption;
                                    
                                    int editCampIndex = sc.nextInt();
                                    if (editCampIndex == campIndex){
                                        break;
                                    }

                                    do{
                                        System.out.println("\n=====CAMP INTERFACE=====");
                                        System.out.println("Choose an action:\r\n"
                                            +"(1) Edit Camp\r\n"
                                            +"(2) View and reply enquiries \r\n"
                                            +"(3) View pending suggestions\r\n"
                                            +"(4) View proccessed suggestions\r\n"
                                            +"(5) Print Camp Report(s)\r\n"
                                            +"(6) Quit");
                                        staffCampOption = sc.nextInt();
                                        
                                        switch (staffCampOption) {

                                            case 1:
                                                System.out.println("\n=====CAMP EDITOR=====");                                                 
                                                CampEditor campEditor = new CampEditor();
                                                campEditor.editCamp(editCampIndex, createdCamps, allCamps, authStaff);
                                                break;
                                            case 2:
                                                System.out.println("\n=====ENQUIRIES=====");  
                                                Camp staffCampEnquiry = createdCamps.get(editCampIndex-1);
                                                EnquiryViewingAndReplying enquiryViewingAndReplying = new EnquiryViewingAndReplying(staffCampEnquiry);
                                                enquiryViewingAndReplying.enquiryUI(staffCampEnquiry);
                                                break;
                                            case 3:
                                                System.out.println("\n=====PENDING SUGGESTIONS====="); 
                                                List<Suggestion> pendingSuggestions = createdCamps.get(editCampIndex-1).getPendingSuggestion();
                                                int suggCounter = 1;
                                                System.out.println("Select suggestion to be viewed, 0 to quit: ");
                                                for (Suggestion suggestions : pendingSuggestions) {
                                                    System.out.println("(" + suggCounter+ ") Suggestion: " + suggestions.getSuggestionText());
                                                    suggCounter++;
                                                }
                                                int suggEditChoice = sc.nextInt();

                                                if (suggEditChoice == 0){
                                                    break;
                                                }
                                                else{
                                                    Suggestion suggChoice = pendingSuggestions.get(suggEditChoice-1);
                                                    System.out.println("Please select an action:\n"
                                                    + "(1) Approve suggestion\n"
                                                    + "(2) Reject suggestion\n"
                                                    + "(3) Quit");

                                                    int suggestionSelection = sc.nextInt();

                                                    switch (suggestionSelection) {
                                                        case 1:
                                                            suggChoice.setSuggestionAccepted();
                                                            suggChoice.suggProcessed();
                                                            System.out.println("Suggestion has been marked as approved.");
                                                            break;
                                                        case 2:
                                                            suggChoice.suggProcessed();
                                                            System.out.println("Suggestion has been marked as rejected.");
                                                            break;
                                                        default:
                                                            break;
                                                    }
                                                }
                                                break;
                                            case 4:
                                                System.out.println("\n=====PROCCESSED SUGGESTIONS====="); 
                                                List<Suggestion> proccessedSuggesitons = createdCamps.get(editCampIndex-1).getProccessedSuggestion();
                                                int suggCounter1 = 1;
                                                System.out.println("Prccessed suggestions: ");
                                                for (Suggestion suggestions : proccessedSuggesitons) {
                                                    if (suggestions.getSuggestionAccepted()){
                                                        System.out.println("(" + suggCounter1+ ") Suggestion: " + suggestions.getSuggestionText() + " - Accepted");
                                                    } else{
                                                        System.out.println("(" + suggCounter1+ ") Suggestion: " + suggestions.getSuggestionText() + " - Rejected");
                                                    }
                                                    suggCounter1++;
                                                }
                                            
                                            case 5:
                                                System.out.println("\n=====REPORT GENERATOR=====");
                                                reportGenerator reportGenerator = new reportGenerator();
                                                Camp reportingCamp = createdCamps.get(editCampIndex-1);
                                                reportGenerator.reportGenerator(reportingCamp);
                                                break;
                                        
                                            default:
                                                break;
                                        }
                                    }while (staffCampOption<6);
                                    break;

                                case 4:
                                    System.out.println("\n=====CAMP VIEWER====="); 
                                    int allCampsIndex = 1;
                                    System.out.println("All camps:");
                                    for (Camp camps : allCamps){
                                        System.out.println("(" + allCampsIndex + ")" + camps.getCampName());
                                        allCampsIndex++;
                                    }
                                    break;
                                case 5:
                                    loginSucc = false;
                                    authUser = null;
                                    System.out.println("Logout successful."); 
                                    break;

                                default:
                                    break;
                            }

                        }while (staffChoice<5);
                        
                    }
                    //if student login:
                    else if(studentList.contains(authUser)){      //login page for staff
                        Student authStudent = (Student) authUser;   //downcast
                        int studentChoice;

                        //sc.nextInt();
                        
                        do {
                            System.out.println(
                            "\n=====WELCOME STUDENT=====\r\n"
                            +"(1) Change Password\r\n"
                            +"(2) Camp Committee\r\n"
                            +"(3) View your registered camps\r\n"       //can quit camp from here
                            +"(4) View all available camps\r\n"         //can enquire about camps from here
                            +"(5) Logout");       
                        
                        System.out.println("Please selection an action:");
                            studentChoice = sc.nextInt();
                            switch (studentChoice) {    //these loop until Quit is selected
                                case 1:
                                    System.out.println("\n=====CHANGE PASSWORD====="); 
                                    System.out.println("Please enter new password:");
                                    String studentPW = sc.next();
                                    authUser.setPassword(studentPW);
                                    break;

                                case 2:
                                    
                                    if (authStudent.isCampCommittee()){                        
                                        System.out.println("You are entering the camp committee interface..");
                                    }   
                                    else {
                                        System.out.println("You are not a committee member of any camp!");
                                        break;
                                    }
                                    //camp comm interface
                                    
                                    CampCommittee authCampCommittee = (CampCommittee) authStudent;  //downcast
                                    
                                    Camp commCamp = authCampCommittee.getCamp();


                                    CampCommitteeUI campCommIntf = new CampCommitteeUI();
                                    campCommIntf.campCommUI(authCampCommittee, commCamp);

                                    break; 
                                    
                                case 3:
                                    System.out.println("\n=====CAMP MANAGER====="); 
                                    List<Camp> registeredCamps = authStudent.getEnrolledCamps();
                                    System.out.println("Registered Camps:");
                                    int registedCampCounter = 1;
                                    for (Camp regCamps : registeredCamps){
                                        System.out.println("(" + registedCampCounter + ") " + regCamps.getCampName());
                                    }

                                    System.out.println("To quit a camp, enter camp number. To exit, enter 0.");
                                    int quitCampSelection = sc.nextInt();

                                    if (quitCampSelection == 0){        //doesn't want to quit anything
                                        break;
                                    }
                                    // else if (quitCampSelection <= registeredCamps.size()){      //chosen a camp to quit
                                    //     Camp quittingCamp = registeredCamps.get(quitCampSelection-1);
                                    //     if (authStudent instanceof CampCommittee){
                                    //         CampCommittee authCampCommitteeQuit = (CampCommittee) authStudent;
                                    //         if (quittingCamp.getCommitteeMembers().contains(authCampCommitteeQuit)){
                                    //             System.out.println("Camp Committee cannot quit camp!");         //if comm, cannot quit camp
                                    //         }                            
                                    //     }
                                    //     //commslots
                                    //     else{
                                            // authStudent.setWithdrawnCamps(quittingCamp);       //removes from enrolled camp + adds to withdrawn camps
                                        // }
                                    // }

                                    Camp quittingCamp = registeredCamps.get(quitCampSelection-1);
                                    if (authStudent.setWithdrawnCamps(quittingCamp) == true){
                                        System.out.println("You have successfully withdrawn from " + quittingCamp.getCampName());
                                    }

                                case 4:     // View all available camps
                                    System.out.println("\n=====CAMP VIEWER=====");
                                    List<Camp> availCamps = authStudent.getAvailableCamps(allCamps);
                                    System.out.println("Available Camps: remaining slots");
                                    int availCampCounter = 1;
                                    for (Camp availCamp : availCamps) {
                                        System.out.println("(" + availCampCounter + ") " + availCamp.getCampName() + " - Attendee Slots: " + availCamp.getRemainingSlots() + " | Camp Committee Slots: " + availCamp.getRemainingCommitteeSlots());
                                        System.out.println("    => Registration end date: " + availCamp.getRegistrationEndDate() + " | Start date: " + availCamp.getStartDate() + " | End date: " + availCamp.getEndDate());
                                        availCampCounter++;
                                    }
                                    System.out.println("(" + availCampCounter + ") Quit");
                                
                                    boolean continueSelection = true;
                                
                                    while (continueSelection) {
                                        System.out.println("\nPlease select an action:\n"
                                            + "(1) Register for a camp\n"
                                            + "(2) Submit enquiry for a camp\n"
                                            + "(3) Edit your enquiry\n"
                                            + "(4) View enquiries and replies\n"
                                            + "(5) Exit\n");
                                
                                        int studentSelection = sc.nextInt();
                                        sc.nextLine();  // Consume the rest of the line including newline
                                
                                        switch (studentSelection) {
                                            case 1: // Register for a camp
                                                int availCampCounter1 = 1;

                                                System.out.println("\n=====CAMP REGISTRATION=====");

                                                System.out.println("Select camp to register:");

                                                System.out.println("Available Camps: remaining slots");
                                                
                                                for (Camp availCamp : availCamps) {
                                                    System.out.println("(" + availCampCounter1 + ") " + availCamp.getCampName() + " - Attendee Slots: " + availCamp.getRemainingSlots() + " | Camp Committee Slots: " + availCamp.getRemainingCommitteeSlots());
                                                    System.out.println("    => Registration end date: " + availCamp.getRegistrationEndDate() + " | Start date: " + availCamp.getStartDate() + " | End date: " + availCamp.getEndDate());
                                                    availCampCounter1++;
                                                }

                                                System.out.println("(" + availCampCounter1 + ") Quit\n");

                                                int campSelection = sc.nextInt();
                                                sc.nextLine();  // Consume the rest of the line including newline
                                
                                                if (campSelection == availCampCounter) {
                                                    continueSelection = false;
                                                } else if (campSelection > 0 && campSelection < availCampCounter) {
                                                    Camp campToSelect = availCamps.get(campSelection - 1);
                                                    System.out.println("Select role for registration:\n"
                                                        + "(1) Attendee\n"
                                                        + "(2) Camp Committee Member\n");
                                                    int roleSelection = sc.nextInt();
                                                    sc.nextLine(); // Consume newline
                                                    String role = (roleSelection == 1) ? "attendee" : "committee";
                                                    boolean registrationSuccess = authStudent.setEnrolledCamps(campToSelect);

                                                    if (registrationSuccess) {
                                                        if (role == "attendee"){
                                                            campToSelect.addAttendees(authStudent);
                                                            System.out.println("You have successfully registered as a attendee for " + campToSelect.getCampName() + ".");
                                                        }
                                                        else if (role == "committee"){
                                                            if (authStudent.isCampCommittee() == false){
                                                                authStudent.setIsCampCommittee();
                                                                CampCommittee downcastingCampCommittee = (CampCommittee) authStudent;  //downcast
                                                                downcastingCampCommittee.setCamp(campToSelect);
                                                                campToSelect.addCommitteeMembers(downcastingCampCommittee);
                                                                
                                                                System.out.println("You have successfully registered as a camp committee member for " + campToSelect.getCampName() + ".");
                                                            } else{
                                                                System.out.println("You are already a camp committee member for another camp.");
                                                            }
                                                        }
                                                    } else {
                                                        System.out.println("Registration failed. You may have already registered or withdrawn from this camp.");
                                                    }

                                                } else {
                                                    System.out.println("Invalid camp selection. Please try again.");
                                                }
                                                break;
                                
                                                               
                                            case 2:     // Submit enquiry for a camp
                                                  
                                                int availCampCounter2 = 1;                                                
                                                System.out.println("\n=====CAMP ENQUIRIES=====");
                                                System.out.println("Select camp for enquiries:"); 

                                                System.out.println("Available Camps: remaining slots");
                                                
                                                for (Camp availCamp : availCamps) {
                                                    System.out.println("(" + availCampCounter2 + ") " + availCamp.getCampName() + " - Attendee Slots: " + availCamp.getRemainingSlots() + " | Camp Committee Slots: " + availCamp.getRemainingCommitteeSlots());
                                                    System.out.println("    => Registration end date: " + availCamp.getRegistrationEndDate() + " | Start date: " + availCamp.getStartDate() + " | End date: " + availCamp.getEndDate());
                                                    availCampCounter2++;
                                                }

                                                System.out.println("(" + availCampCounter2 + ") Quit\n");

                                            
                                                int enquireCamp = sc.nextInt();
            
                                                if (enquireCamp == availCampCounter+1){
                                                    break;
                                                }
                                                else if (enquireCamp<availCampCounter+1){
                                                    Camp campForEnquire = availCamps.get(enquireCamp-1);
                                                    System.out.println(campForEnquire.getCampName() + " selected.\r\n"
                                                        +"Please enter your enquiry:");
                                                    String enquiry = sc.next();
                                                    authStudent.submitEnquiry(campForEnquire, enquiry); 
                                                        //student submits enquiry
                                                }
                                                break;

                                            case 3:
                                                System.out.println("\n=====EDIT ENQUIRIES====="); 
                                                List<Enquiry> submittedEnquiry = authStudent.getEnquiries();
                                                System.out.println("Select enquiry to be edited, 0 to quit: ");
                                                int enqCounter = 1;
                                                for (Enquiry enquiries : submittedEnquiry) {
                                                    System.out.println("(" + enqCounter+ ") Enquiry: " + enquiries.getEnquiryText() + "\n");
                                                    enqCounter++;
                                                }
                                                
                                                int enqEditChoice = sc.nextInt();
                            
                                                if (enqEditChoice == 0){
                                                    break;
                                                }
                                                else{
                                                    Enquiry enqChoice = submittedEnquiry.get(enqEditChoice-1);
                                                    if (!enqChoice.replyStatus()){
                                                        System.out.println("Previous enquiry: " + enqChoice.getEnquiryText());
                                                        
                                                        System.out.println("Enter edited enquiry: ");
                                                        String editedEnq = sc.next();
                                                        enqChoice.setEnquiryText(editedEnq);
                                                    }
                                                    else{
                                                        System.out.println("You cannot edit the enquiry after it has been processed!");
                                                        break;
                                                    }
                                                    
                                                }
                                                break;

                                            case 4: 
                                                  
                                                int availCampCounter3 = 1;                                                
                                                System.out.println("\n=====VIEW ENQUIRIES=====");
                                                System.out.println("Select camp to view enquiries:"); 

                                                System.out.println("Available Camps: ");
                                                
                                                for (Camp availCamp : availCamps) {
                                                    System.out.println("(" + availCampCounter3 + ") " + availCamp.getCampName());
                                                    availCampCounter3++;
                                                }

                                                System.out.println("(" + availCampCounter3 + ") Quit\n");

                                            
                                                int viewEnquireCamp = sc.nextInt();
                                            
                                                Camp studentCampEnquiry = availCamps.get(viewEnquireCamp-1);
                                                if (studentCampEnquiry.getAllEnquiries().size()==0) {
                                                     System.out.println("No enquiry yet");
                                                }

                                                else {
                                                    for (Enquiry enquiry : studentCampEnquiry.getAllEnquiries()) { 
                                                        String enquiryText = enquiry.getEnquiryText();
                                                        String enquiryReply = enquiry.getEnquiryReply(); // Assumes enquiry has been replied to
                                        
                                                        System.out.println("Enquiry: " + (enquiryText));
                                                        
                                                        System.out.println("Reply: " + (enquiryReply == null ? "No reply yet" : enquiryReply));
                                                        System.out.println(); // Print a blank line for better readability
                                                    }
                                                }    
                                
                                            case 5:     // Exit
                                                continueSelection = false;
                                                break;
                                
                                            default:
                                                System.out.println("Invalid selection. Please try again.");
                                                break;
                                        }
                                    }
                                    break;
                                    
                                
                                
                                // case 4:     //view all avail camps
                                //     System.out.println("=====CAMP VIEWER====="); 
                                //     List<Camp> availCamps = authStudent.getAvailableCamps(allCamps);
                                //     System.out.println("Available Camps: remaining slots");
                                //     int availCampCounter = 1;
                                //     for (Camp availCamp : availCamps){
                                //         System.out.println("(" + availCampCounter + ") " + availCamp.getCampName() + " : " + availCamp.getRemainingSlots());
                                //         availCampCounter++;
                                //     }
            
                                    
                                //     System.out.println("(" + availCampCounter + ") Quit");
            
                                //     System.out.println("Please select an action:"
                                //         + "(1) Register for a camp"
                                //         + "(2) Submit enquiry for a camp" );
                                    
                                //         int studentSelection;
            
                                //         studentSelection = sc.nextInt();
            
                                //     do{
                                //         switch (studentSelection) {
                                //             case 1:     //register for a camp
                                //                 System.out.println("Select camp to register:");
                                //                 int campSelection = sc.nextInt();
                                //                 sc.nextLine();  // Consume the rest of the line including newline
                                                
                                            
                                //                 if (campSelection == availCampCounter + 1) {
                                //                     break;
                                //                 } else if (campSelection <= availCampCounter) { // Assuming campSelection > 0 is validated elsewhere
                                //                     Camp campToSelect = availCamps.get(campSelection - 1);
                                                    
                                //                     System.out.println(campToSelect.getCampName() + " selected.\r\n");
                                //                     authStudent.setEnrolledCamps(campToSelect, "attendee");
                                //                 }
                                //                 break;

              
                                //             case 2:
                                //                 System.out.println("Select camp for enquiries:");       //start of enquiry section
                                //                 int enquireCamp = sc.nextInt();
            
                                //                 if (enquireCamp == availCampCounter+1){
                                //                     break;
                                //                 }
                                //                 else if (enquireCamp<availCampCounter+1){
                                //                     Camp campForEnquire = availCamps.get(enquireCamp-1);
                                //                     System.out.println(campForEnquire.getCampName() + " selected.\r\n"
                                //                         +"Please enter your enquiry:");
                                //                     String enquiry = sc.next();
                                //                     authStudent.submitEnquiry(campForEnquire, enquiry);     //student submits enquiry
                                //                 }
                                //                 break;
                                //             }
                                            
                                //         } while(studentSelection<3);
                                    
                                    // List<Camp> availCamps = authStudent.getAvailableCamps(allCamps);
                                    // System.out.println("Available Camps: remaining slots");
                                    // int availCampCounter = 1;
                                    // for (Camp availCamp : availCamps){
                                    //     System.out.println("(" + availCampCounter + ") " + availCamp.getCampName() + " : " + availCamp.getRemainingSlots());
                                    // }
                                    // System.out.println("(0) Quit");
                                    // System.out.println("Select camp for enquiries:");       //start of enquiry section
                                    // int enquireCamp = sc.nextInt();

                                    // if (enquireCamp == 0 ){
                                    //     break;
                                    // }
                                    // else if (enquireCamp<availCampCounter+1){
                                    //     Camp campForEnquire = availCamps.get(enquireCamp-1);
                                    //     System.out.println(campForEnquire.getCampName() + " selected.\r\n"
                                    //         +"Please enter your enquiry:");
                                    //     String enquiry = sc.next();
                                    //     authStudent.submitEnquiry(campForEnquire, enquiry);     //student submits enquiry
                                    // }
                                    // break;

                                case 5:
                                    loginSucc = false;
                                    authUser = null;
                                    System.out.println("Logout successful."); 
                                    break;

                                default:
                                    break;
                            }
                        } while(studentChoice<5);
                        

                    }

                }
        }
        
        sc.close();

        
    }

    
}
