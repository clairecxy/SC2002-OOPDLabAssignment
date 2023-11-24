import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.InputMismatchException;

public class StaffUI {

    public static void staffInterface(Staff authStaff, User authUser, List<Camp> allCamps){
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateFormat.format(currentDate);
        
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");

        int staffChoice = 0;

        do{
            System.out.println("\n=====WELCOME STAFF MEMBER=====");
            System.out.println("Please selection an action:\r\n"
            +"(1) Change Password\r\n"
            +"(2) Create new camp\r\n"
            +"(3) View your camps\r\n"      //from here: can edit, delete
            +"(4) View all camps\r\n"
            +"(5) Logout");
            
            try{    //exception handling for non-integers and invalid selections
                staffChoice = Integer.parseInt(sc.next());
                                    
                if(staffChoice >5 || staffChoice<1){
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
                        //staffCampOption = sc.nextInt();
                           //exception handling for non-integers and invalid selections
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
                                StaffReportGenerator.StaffReportGenerator(reportingCamp);
                                break;
                        
                            default:
                                break;
                        }
                        
                    }while (staffCampOption<6 && deleted == false);
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
        }while (staffChoice != 5);

    }
}