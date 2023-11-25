import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
     * 1. Register for Camps
     * 2. Submit enquiries
     * 3. Edit their submitted enquiries
     * 4. View their submitted enquiries and replies.
     * 
     * @param authStudent The Student this menu belongs to.
     * @param authUser The User reference of the Student object.
     * @param allCamps All camps within the Camp Application System.
     */
    public static void studentInterface(Student authStudent, User authUser, List<Camp> allCamps){
        
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\r\n");

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
            +"(5) Logout");    

        System.out.println("Please selection an action:");
            try{    //exception handling for non-integers and invalid selections
                studentChoice = Integer.parseInt(sc.next());
                                    
                if(studentChoice >5 || studentChoice<1){
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

                        System.out.println("\nPlease select an action:\n"
                            + "(1) Register for a camp\n"
                            + "(2) Submit enquiry for a camp\n"
                            + "(3) Edit or delete your enquiry\n"
                            + "(4) View enquiries and replies\n"
                            + "(5) Exit\n");
                        
                        int studentSelection = Integer.parseInt(sc.next());
                                    
                        if(studentSelection >5 || studentChoice<1){
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
                
                                                
                            case 2:     // Submit enquiry for a camp
                                    
                                int visibleCampCounter = 1;                                                
                                System.out.println("\n=====CAMP ENQUIRIES=====");
                                System.out.println("Select camp for enquiries:"); 

                                System.out.println("Available Camps: remaining slots");

                                List<Camp> visibleCamps = authStudent.getVisibleCamps(allCamps);
                                
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

                            case 3:
                                
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
                            default:
                                break;
                    }
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
        } while(studentChoice!=5);
    }
}