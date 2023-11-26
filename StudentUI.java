import java.util.List;
import java.util.Scanner;
import java.util.Comparator;
import java.util.Collections;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * Represents the user menu for Students in the system.
 * @author Claire Chu Xinyi
 * @version 1.0
 * @since 20/11/2023
 */
public class StudentUI {
    
    /**
     * The method which displays the user menu for Students.
     * The user menus allows for:
     * 1. Changing the password.
     * 2. Entering the Camp Committee Interface.
     * 3. Viewing all the Student's registered Camps.
     * 4. Viewing all the Camps available for Student's registration.
     * 5. Logging out of the system.
     * 
     * Through the Camp Viewer, students can:
     * 1. Register for Camps.
     * 2. Submit enquiries.
     * 3. Edit their submitted enquiries.
     * 4. View their submitted enquiries and replies.
     * 
     * @param authStudent The Student this menu belongs to.
     * @param authUser The User reference of the Student object.
     * @param allCamps All Camps within the Camp Application System.
     */
    public static void studentInterface(Student authStudent, User authUser, List<Camp> allCamps){
        
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\r\n");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Comparator<Camp> campNameComparator = new Comparator<Camp>() {
            public int compare(Camp camp1, Camp camp2) {
                return camp1.getCampName().compareTo(camp2.getCampName());
            }
        };

        Comparator<Camp> campLocationComparator = new Comparator<Camp>() {
            public int compare(Camp camp1, Camp camp2) {
                return camp1.getLocation().compareTo(camp2.getLocation());
            }
        };

        Comparator<Camp> campDateComparator = new Comparator<Camp>() {
            public int compare(Camp camp1, Camp camp2) {
                try {
                    Date camp1StartDate = dateFormat.parse(camp1.getStartDate());
                    Date camp2StartDate = dateFormat.parse(camp2.getStartDate());
                    return camp1StartDate.compareTo(camp2StartDate);
                } catch (ParseException e) {
                    // Handle the exception here or log it.
                    e.printStackTrace(); // For demonstration purposes, you can replace this with proper error handling.
                    return 0; // Return a default value or handle it according to your application's logic.
                }
            }
        };

        Comparator<Camp> campAttendeeComparator = new Comparator<Camp>() {
            public int compare(Camp camp1, Camp camp2) {
                return Integer.compare(camp1.getTotalSlots(), camp2.getTotalSlots());
            }
        };

        Comparator<Camp> campCampCommitteeComparator = new Comparator<Camp>() {
            public int compare(Camp camp1, Camp camp2) {
                return Integer.compare(camp1.getCommitteeSlots(), camp2.getCommitteeSlots());
            }
        };


        int studentChoice = 0;

        do {
            System.out.println("\n=====WELCOME STUDENT=====");

            if (authStudent.isCampCommittee()){
                System.out.println("You are camp committee member of: " + authStudent.getCampCommittee().getCampName());
            }
            
            System.out.println(
            "(1) Change Password\r\n"
            +"(2) Enter Camp Committee Interface\r\n"
            +"(3) View your registered camps\r\n"       //can quit camp from here
            +"(4) View all available camps\r\n"         //can enquire about camps from here
            +"(5) Filter all camps\r\n"
            +"(6) Logout");    

        System.out.println("Please selection an action:");
            try{    //exception handling for non-integers and invalid selections
                studentChoice = Integer.parseInt(sc.next());
                                    
                if(studentChoice >6 || studentChoice<1){
                    throw new Exception("A valid selection was not made.");                    
                }

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

                    Collections.sort(registeredCamps, campNameComparator);


                    System.out.println("Registered Camps:");
                    int registeredCampCounter = 1;
                    if (registeredCamps.isEmpty()){
                        System.out.println("You have no registered camps.");
                    }
                    if (authStudent.isCampCommittee()){
                        Camp campCommitteeCamp = authStudent.getCampCommittee();
                        for (Camp regCamps : registeredCamps){
                            if (regCamps == campCommitteeCamp){
                                System.out.println("(" + registeredCampCounter + ") " + regCamps.getCampName() + " - Camp Committee");
                            }
                            else {
                                System.out.println("(" + registeredCampCounter + ") " + regCamps.getCampName() + " - Attendee");
                            }
                            registeredCampCounter++;
                        }
                    } else {
                        for (Camp regCamps : registeredCamps){
                            System.out.println("(" + registeredCampCounter + ") " + regCamps.getCampName() + " - Attendee");
                            registeredCampCounter++;
                        }
                    }

                    System.out.println("To quit a camp, enter camp number. To exit, enter 0.");
                    int quitCampSelection = sc.nextInt();

                    if (quitCampSelection == 0){        //doesn't want to quit anything
                        break;
                    }
                
                    Camp quittingCamp = registeredCamps.get(quitCampSelection-1);
                    
                    if (authStudent.getCampCommittee() == quittingCamp){
                        System.out.println("You cannot withdrawn from " + quittingCamp.getCampName() + " as you are a camp committee member.");
                    } 
                    else if (authStudent.setWithdrawnCamps(quittingCamp) == true){
                        quittingCamp.removeAttendees(authStudent);
                        System.out.println("You have successfully withdrawn from " + quittingCamp.getCampName());
                    }
                    break;


                case 4:     // View all available camps
                    System.out.println("\n=====CAMP VIEWER=====");
                    List<Camp> availCamps = authStudent.getAvailableCamps(allCamps);

                    Collections.sort(availCamps, campNameComparator);

                    System.out.println("Available Camps: remaining slots");
                    int availCampCounter = 1;
                    for (Camp availCamp : availCamps) {
                        System.out.println("(" + availCampCounter + ") " + availCamp.getCampName() + " - Attendee Slots: " + availCamp.getRemainingSlots() + " | Camp Committee Slots: " + availCamp.getRemainingCommitteeSlots());
                        System.out.println("    => Registration end date: " + availCamp.getRegistrationEndDate() + " | Start date: " + availCamp.getStartDate() + " | End date: " + availCamp.getEndDate());
                        availCampCounter++;
                    }
                    System.out.println("(" + availCampCounter + ") Quit");

                        System.out.println("\nPlease select an action:\n"
                            + "(1) Register for a camp\n"
                            + "(2) View details of a camp\n"
                            + "(3) Submit enquiry for a camp\n"
                            + "(4) Edit or delete your enquiry\n"
                            + "(5) View enquiries and replies\n"
                            + "(6) Exit\n");
                        
                        int studentSelection = Integer.parseInt(sc.next());
                                    
                        if(studentSelection >6 || studentChoice<1){
                            throw new Exception("A valid selection was not made.");                    
                        }

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
                                    break;
                                } else if (campSelection > 0 && campSelection < availCampCounter) {
                                    Camp campToSelect = availCamps.get(campSelection - 1);
                                    System.out.println("Select role for registration:\n"
                                        + "(1) Attendee\n"
                                        + "(2) Camp Committee Member\n");
                                    int roleSelection = sc.nextInt();
                                    sc.nextLine(); // Consume newline
                                    String role = (roleSelection == 1) ? "attendee" : "committee";

                                    if (!authStudent.getWithdrawnCamps().contains(campToSelect)) {
                                        if (role == "attendee"){
                                            
                                            if (campToSelect.addAttendees(authStudent)==1){
                                                authStudent.setEnrolledCamps(campToSelect);
                                                System.out.println("You have successfully registered as a attendee for " + campToSelect.getCampName() + ".");
                                            }
                                            }
                                        
                                        else if (role == "committee"){
                                            if (authStudent.isCampCommittee() == false){
                                                
                                                CampCommittee downcastingCampCommittee = (CampCommittee) authStudent;  //downcast
                                                
                                                if (campToSelect.addCommitteeMembers(downcastingCampCommittee) == 1) {  
                                                    downcastingCampCommittee.setCamp(campToSelect);
                                                    authStudent.setEnrolledCamps(campToSelect);
                                                    authStudent.setIsCampCommittee();
                                                    authStudent.setCampCommittee(campToSelect);              
                                                    System.out.println("You have successfully registered as a camp committee member for " + campToSelect.getCampName() + ".");
                                                }
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
                
                            case 2:
                                
                                System.out.println("\n=====CAMP DETAILS=====");
                                System.out.println("Select camp to view details:");

                                System.out.println("Available Camps: remaining slots");
                                int availCampCounter2 = 1;
                                for (Camp availCamp : availCamps) {
                                    System.out.println("(" + availCampCounter2 + ") " + availCamp.getCampName() + " - Attendee Slots: " + availCamp.getRemainingSlots() + " | Camp Committee Slots: " + availCamp.getRemainingCommitteeSlots());
                                    System.out.println("    => Registration end date: " + availCamp.getRegistrationEndDate() + " | Start date: " + availCamp.getStartDate() + " | End date: " + availCamp.getEndDate());
                                    availCampCounter2++;
                                }

                                System.out.println("(" + availCampCounter2 + ") Quit\n");

                                int campSelection1 = sc.nextInt();
                                sc.nextLine();

                                Camp camp = availCamps.get(campSelection1 - 1);

                                System.out.println("Camp Name: " + camp.getCampName());
                                System.out.println("Start Date: " + camp.getStartDate());
                                System.out.println("End Date: " + camp.getEndDate());
                                System.out.println("Registration End Date: " + camp.getRegistrationEndDate());
                                System.out.println("User Group: " + camp.getUserGroup());
                                System.out.println("Location: " + camp.getLocation());
                                System.out.println("Total Slots: " + camp.getTotalSlots());
                                System.out.println("Remaining Slots: " + camp.getRemainingSlots());
                                System.out.println("Committee Slots: " + camp.getCommitteeSlots());
                                System.out.println("Description: " + camp.getDescription());

                                break;

                                
                            case 3:     // Submit enquiry for a camp
                                    
                                int visibleCampCounter = 1;                                                
                                System.out.println("\n=====CAMP ENQUIRIES=====");
                                System.out.println("Select camp for enquiries:"); 

                                System.out.println("Available Camps: remaining slots");

                                List<Camp> visibleCamps = authStudent.getVisibleCamps(allCamps);

                                Collections.sort(visibleCamps, campNameComparator);

                                if (authStudent.isCampCommittee()){
                                    Camp campCommittee = authStudent.getCampCommittee();
                                    visibleCamps.remove(campCommittee);
                                }
                                
                                for (Camp availCamp : visibleCamps) {
                                    System.out.println("(" + visibleCampCounter + ") " + availCamp.getCampName() + " - Attendee Slots: " + availCamp.getRemainingSlots() + " | Camp Committee Slots: " + availCamp.getRemainingCommitteeSlots());
                                    System.out.println("    => Registration end date: " + availCamp.getRegistrationEndDate() + " | Start date: " + availCamp.getStartDate() + " | End date: " + availCamp.getEndDate());
                                    visibleCampCounter++;
                                }

                                System.out.println("(" + visibleCampCounter + ") Quit\n");

                            
                                int enquireCamp = sc.nextInt();

                                if (enquireCamp == visibleCampCounter+1){
                                    break;
                                }
                                else if (enquireCamp<visibleCampCounter+1){
                                    Camp campForEnquire = visibleCamps.get(enquireCamp-1);
                                    System.out.println(campForEnquire.getCampName() + " selected.\r\n"
                                        +"Please enter your enquiry:");
                                    String enquiry = sc.next();
                                    authStudent.submitEnquiry(campForEnquire, enquiry); 
                                        //student submits enquiry
                                }
                                else{
                                    System.out.println("A valid selection was not made.");
                                }
                                break;

                            case 4:
                                
                                System.out.println("\n=====EDIT OR DELETE ENQUIRIES====="); 
                                List<Enquiry> submittedEnquiry = authStudent.getEnquiries();
                                System.out.println("Select enquiry to be edited, 0 to quit: ");
                                int enqCounter = 1;
                                for (Enquiry enquiries : submittedEnquiry) {
                                    System.out.println("(" + enqCounter+ ") Enquiry: " + enquiries.getEnquiryText() + "\n");
                                    enqCounter++;
                                }
                                
                                int enqEditChoice = sc.nextInt();
                                Enquiry enqChoice = submittedEnquiry.get(enqEditChoice-1);
                                if (enqChoice.replyStatus()){
                                    System.out.println("You cannot edit the enquiry after it has been processed!");
                                    break;
                                }     
                                if (enqEditChoice == 0){
                                    break;
                                }
                                else{

                                    int editOrDeleteChoice = 0;
                                    boolean delete = false;

                                    do {
                                    
                                    System.out.println("Please selection an action:\n"
                                    + "(1) Edit enquiry\n"
                                    + "(2) Delete enquiry\n"
                                    + "(3) Quit");

                                    editOrDeleteChoice = Integer.parseInt(sc.next());
                                        
                                    if(editOrDeleteChoice >2 || editOrDeleteChoice<1){
                                        throw new Exception("A valid selection was not made.");                    
                                    }

                                    switch (editOrDeleteChoice) {
                                        case 1:
                                            System.out.println("Previous enquiry: " + enqChoice.getEnquiryText());
                                            
                                            System.out.println("Enter edited enquiry: ");
                                            String editedEnq = sc.next();
                                            enqChoice.setEnquiryText(editedEnq);
                                            break;
                                        
                                        case 2:
                                            authStudent.deleteEnquiries(enqChoice);
                                            System.out.println("Enquiry successfully deleted.");
                                            delete = true;
                                            //delete logic
                                            break;
                                            
                                        default:
                                            break;
                                    }
                                        
                                }while (editOrDeleteChoice<3 && delete == false);
                                }
                                break;

                            case 5: 
                                    
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
                                if (viewEnquireCamp == availCampCounter3){
                                    break;
                                }
                            
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
                                break;  
                            default:
                                break;
                    }   
                    break;
                    
                case 5:
                int filterChoice = 0;
                do{
                    System.out.println("Please select filter option:\n"
                    + "(1) Start date (Earliest to Latest)\n"
                    + "(2) Location (Alphabetical order)\n"
                    + "(3) Total slots\n"
                    + "(4) Camp Committee slots\n"
                    + "(5) Quit");

                    List<Camp> visibleCamps = authStudent.getVisibleCamps(allCamps);
                  
                    int visibleCampCounter = 1; 
                    
                        //exception handling for non-integers and invalid selections
                        filterChoice = Integer.parseInt(sc.next());
                        //int filterChoice = sc.nextInt();
                                            
                        if(filterChoice >5 || filterChoice<1){
                            throw new Exception("A valid selection was not made.");                    
                        }
                    
                        switch (filterChoice) {    //these loop until Quit is selected
                            case 1:
                                Collections.sort(visibleCamps, campDateComparator);
                                for (Camp availCamp : visibleCamps) {
                                    System.out.println("(" + visibleCampCounter + ") " + availCamp.getCampName());
                                    System.out.println("    => Start date: " + availCamp.getStartDate());
                                    visibleCampCounter++;
                                }
                                break;
                            case 2:
                                Collections.sort(visibleCamps, campLocationComparator);
                                for (Camp availCamp : visibleCamps) {
                                    System.out.println("(" + visibleCampCounter + ") " + availCamp.getCampName());
                                    System.out.println("    => Location: " + availCamp.getLocation());
                                    visibleCampCounter++;
                                }
                                break;
                            case 3:
                                Collections.sort(visibleCamps, campAttendeeComparator);
                                for (Camp availCamp : visibleCamps) {
                                    System.out.println("(" + visibleCampCounter + ") " + availCamp.getCampName());
                                    System.out.println("    => Total attendee slots: " + availCamp.getTotalSlots());
                                    visibleCampCounter++;
                                }
                                break;
                            case 4:
                                Collections.sort(visibleCamps, campCampCommitteeComparator);
                                for (Camp availCamp : visibleCamps) {
                                    System.out.println("(" + visibleCampCounter + ") " + availCamp.getCampName());
                                    System.out.println("    => Total camp cammittee slots: " + availCamp.getCommitteeSlots());
                                    visibleCampCounter++;
                                }
                                break;

                        }

                    }while (filterChoice<5); 
                    break;
                
                default:
                    break;
                }
            
            
            }catch (NumberFormatException f){
                System.out.println("Invalid input.");
            }
            catch (Exception e) {
                System.out.println(e.getMessage());                
            }
           
        } 
        while(studentChoice!=6);
    }
}
