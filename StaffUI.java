import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Collections;
import java.util.Comparator;

/**
 * Represents the user menu for Staff in the system.
 * @author Tan Hui Ling
 * @version 1.0
 * @since 20/11/2023
 */
public class StaffUI {

    /**
     * The method which displays the user menu for Staff.
     * The user menus allows for:
     * 1. Changing of Password.
     * 2. Creating new camps.
     * 3. Viewing this Staff's created camps.
     * 4. Viewing all camps in the system.
     * 5. Filtering all camps by the order they are displayed in.
     * 
     * Viewing Camps will allow this Staff to:
     * 1. Edit their Camps.
     * 2, View and reply to enquiries for their Camps.
     * 3. View pending suggestions for their Camps.
     * 4. View proccessed suggestions for their Camps.
     * 5. Print Camp Report(s).
     * 
     * @param authStaff The Staff this menu belongs to. 
     * @param authUser The User reference of the Staff object.
     * @param allCamps All camps within the Camp Application System.
     */
    public static void staffInterface(Staff authStaff, User authUser, List<Camp> allCamps){
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateFormat.format(currentDate);
        
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\r\n");

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


        int staffChoice = 0;

        do{
            System.out.println("\n=====WELCOME STAFF MEMBER=====");
            System.out.println("Please selection an action:\r\n"
            +"(1) Change Password\r\n"
            +"(2) Create new camp\r\n"
            +"(3) View your camps\r\n"      //from here: can edit, delete
            +"(4) View all camps\r\n"
            +"(5) Filter all camps\r\n"
            +"(6) Logout");
            
            try{    //exception handling for non-integers and invalid selections
                staffChoice = Integer.parseInt(sc.next());
                                    
                if(staffChoice >6 || staffChoice<1){
                    throw new Exception("A valid selection was not made.");                    
                }
            // while (!sc.hasNextInt()){
            //     sc.next();
            // }

            switch (staffChoice) {
                case 1:
                    System.out.println("\n=====CHANGE PASSWORD=====");  
                    System.out.println("Please enter new password:");
                    String staffPW = sc.next();
                    authUser.setPassword(staffPW);
                    break;

                case 2:
                    System.out.println("\n=====CREATE NEW CAMP=====");
                    
                    String campName;
                    boolean isCampNameValid = false;

                    do {
                        System.out.println("Please enter Camp name:");
                        campName = sc.next();
            
                        boolean campNameExists = false;
            
                        try {
                            for (Camp camp : allCamps) {
                                if (camp.getCampName().equals(campName)) {
                                    campNameExists = true;
                                    break; // Exit the loop as soon as a matching camp name is found
                                }
                            }
                        } catch (NullPointerException e) {
                            // Handle a possible null reference exception when accessing camp.getCampName()
                            // You can customize the error message or handle the exception as needed.
                            System.out.println("An error occurred while checking camp names. Please try again.");
                            continue;
                        }
            
                        if (campNameExists) {
                            System.out.println("A camp with the same name already exists. Please enter a different name.");
                            isCampNameValid = false; // Set to false to continue the loop
                        } else {
                            isCampNameValid = true; // Set to true to exit the loop
                        }
                    } while (!isCampNameValid);

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
                                if (!parsedStartDate.before(currentDate)){
                                    startDateValid = true;
                                } else{
                                    System.out.println("Start date cannot be in the past.");
                                } 
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
                            Date parsedStartDate = dateFormat.parse(startDate);

                            // Check the format after parsing
                            String formattedEndDate = dateFormat.format(parsedEndDate);
                            if (endDate.equals(formattedEndDate)) {
                                if (!parsedEndDate.before(parsedStartDate)){
                                    endDateValid = true;
                                } else{
                                    System.out.println("End date cannot be before start date.");
                                }
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

                    System.out.println("Please enter Camp description:");
                    String description = sc.next();
                    allCamps.add(authStaff.createCamp(campName, startDate, endDate, regEndDate, userGrp, location, totalSlots, campCommSlots, description, authStaff));
                    // ^ this creates the camp + adds it to the allCamps list & staff's createdCamps list.
                    break;

                case 3:
                    System.out.println("\n=====VIEW YOUR CAMPS=====");
                    List<Camp> createdCamps = authStaff.viewAllCreatedCamps();

                    Collections.sort(createdCamps, campNameComparator);

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

                    boolean deleted = false;

                    do{
                        System.out.println("\n=====CAMP INTERFACE=====");
                        System.out.println("Choose an action:\r\n"
                            +"(1) Edit Camp\r\n"
                            +"(2) View and reply enquiries \r\n"
                            +"(3) View pending suggestions\r\n"
                            +"(4) View proccessed suggestions\r\n"
                            +"(5) Print Camp Report(s)\r\n"
                            +"(6) Quit");
                            staffCampOption = Integer.parseInt(sc.next());
                                                
                            if(staffCampOption >6 || staffCampOption<1){
                                throw new Exception("A valid selection was not made.");                    
                            }
                            
                        switch (staffCampOption) {

                            case 1:
                                System.out.println("\n=====CAMP EDITOR=====");                                                 
                                CampEditor campEditor = new CampEditor();
                                deleted = campEditor.editCamp(editCampIndex, createdCamps, allCamps, authStaff);
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
                                if (pendingSuggestions.isEmpty()){
                                    System.out.println("There are no pending suggestions.");
                                }
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
                                            suggChoice.getCampCommittee().addPoints();
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
                                System.out.println("\n=====PROCESSED SUGGESTIONS====="); 
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
                                break;
                            
                            case 5:
                                System.out.println("\n=====REPORT GENERATOR=====");
                                StaffReportGenerator StaffReportGenerator = new StaffReportGenerator();
                                Camp reportingCamp = createdCamps.get(editCampIndex-1);
                                StaffReportGenerator.generateReport(reportingCamp);
                                break;
                        
                            default:
                                break;
                        }
                        
                    }while (staffCampOption<6 && deleted == false);
                    break;

                case 4:
                    System.out.println("\n=====CAMP VIEWER====="); 

                    Collections.sort(allCamps, campNameComparator);


                    int allCampsIndex = 1;
                    System.out.println("All camps:");
                    for (Camp camps : allCamps){
                        System.out.println("(" + allCampsIndex + ")" + camps.getCampName());
                        allCampsIndex++;
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

                        List<Camp> visibleCamps = authStaff.viewAllCreatedCamps();
                    
                        int visibleCampCounter = 1; 
                        
                            //exception handling for non-integers and invalid selections
                            filterChoice = Integer.parseInt(sc.next());
                                                                           
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

            }
            catch (NumberFormatException f){
                System.out.println("Invalid input.");
            }
            catch (Exception e) {
                System.out.println(e.getMessage());                
            }
            continue;
        }while (staffChoice != 6);
    }
}