import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.InputMismatchException;

/**
 * Represents an editor for use by Staff to edit or delete a Camp they have created.
 * @author Claire Chu Xinyi
 * @version 1.0
 * @since 20/11/2023
 */
public class CampEditor {
    
    /**
     * A formatter to format dates.
     */
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * Displays attributes of the Camp that can be edited.
     * Users can choose to delete Camps.
     * Attributes of Camps that can be edited are:
     * 1. Camp Name
     * 2. Start dates
     * 3. End dates
     * 4. Registration deadlines
     * 5. User groups the Camp is open to
     * 6. Location of the Camp
     * 7. Total slots for attendees
     * 8. The number of Camp Committee slots
     * 9. The Camp's description
     * 10. The Camp's visibility
     * @param editCampIndex the index of the selected Camp in the list of Camps created by the Staff.
     * @param createdCamps the list of Camps created by the Staff.
     * @param allCamps the list of all Camps in the system.
     * @param authStaff the Staff using the CampEditor menu.
     * @return whether the Camp has been deleted.
     */
    public boolean editCamp(int editCampIndex, List<Camp> createdCamps, List<Camp> allCamps, Staff authStaff){
        Scanner sc = new Scanner(System.in);        //don't close this!
        sc.useDelimiter("\r\n");

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateFormat.format(currentDate);
        
        Camp editCamp = createdCamps.get(editCampIndex-1);      //get selected camp from list of camps staff created
        int editFieldOption = 0;
        boolean deleted = false;
        do{
            
            System.out.println("Please select field to be edited:\r\n"
            +"(1) Camp Name: " + editCamp.getCampName() + "\r\n"
            +"(2) Start date: " + editCamp.getStartDate() + "\r\n"
            +"(3) End date: " + editCamp.getEndDate() + "\r\n"
            +"(4) Registration closing date: " + editCamp.getRegistrationEndDate() + "\r\n"
            +"(5) User group this camp is open to: own school or whole NTU: " + editCamp.getUserGroup() + "\r\n"
            +"(6) Location: " + editCamp.getLocation() + "\r\n"
            +"(7) Total Slots: " + editCamp.getTotalSlots() + "\r\n"
            +"(8) Camp Committee Slots (max 10): " + editCamp.getCommitteeSlots() + "\r\n"
            +"(9) Description: " + editCamp.getDescription() + "\r\n"
            +"(10) Visbility: " + editCamp.getVisibility() + "\r\n"
            +"(11) Delete camp\r\n"
            +"(12) Quit");

            try{    //exception handling for non-integers and invalid selections
                editFieldOption = Integer.parseInt(sc.next());
                                    
                if(editFieldOption >12 || editFieldOption<1){
                    throw new Exception("A valid selection was not made.");                    
                }


            // while (!sc.hasNextInt()){
            //     sc.next();
            // }
            //editFieldOption = sc.nextInt();
            
            switch (editFieldOption) {
                case 1:
                    String newCampName;
                    boolean isCampNameValid = false;
                    do {
                        System.out.println("Please enter Camp name:");
                        newCampName = sc.next();
            
                        boolean campNameExists = false;
            
                        try {
                            for (Camp camp : allCamps) {
                                if (camp.getCampName().equals(newCampName)) {
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
                    editCamp.setCampName(newCampName);
                    break;

                case 2:
                    String startDate;
                    String currentEndDate = editCamp.getEndDate();
                    boolean startDateValid = false;
                    do {
                        System.out.println("Please enter Camp start date in DD-MM-YYYY format:");
                        startDate = sc.next();
                        try {
                            dateFormat.setLenient(false);
                            Date parsedStartDate = dateFormat.parse(startDate);
                            Date parsedEndDate = dateFormat.parse(currentEndDate);

                            // Check the format after parsing
                            String formattedStartDate = dateFormat.format(parsedStartDate);
                            if (startDate.equals(formattedStartDate)) {
                                if (!parsedStartDate.before(currentDate)){
                                    if (!parsedStartDate.after(parsedEndDate)){
                                        startDateValid = true;
                                    } else{
                                        System.out.println("Start date cannot be after end date.");
                                    }
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

                    editCamp.setStartDate(startDate);
                    break;

                case 3:
                    String endDate;
                    String currentStartDate = editCamp.getStartDate();
                    boolean endDateValid = false;
                    do {
                        System.out.println("Please enter Camp end date in DD-MM-YYYY format:");
                        endDate = sc.next();
                        try {
                            dateFormat.setLenient(false);
                            Date parsedEndDate = dateFormat.parse(endDate);
                            Date parsedStartDate = dateFormat.parse(currentStartDate);

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
                
                    editCamp.setEndDate(endDate);
                    break;

                case 4:
                    String regEndDate;
                    boolean regEndDateValid = false;
                    do {
                        System.out.println("Please enter new registration end date in DD-MM-YYYY format:");
                        regEndDate = sc.next();
                        try {
                            dateFormat.setLenient(false);
                            Date parsedRegEndDate = dateFormat.parse(regEndDate);
                            regEndDateValid = true;
                        } catch (ParseException e) {
                            System.out.println("Invalid date format. Please enter dates in DD-MM-YYYY format.");
                        }
                    } while (!regEndDateValid);
                    
                    editCamp.setRegistrationEndDate(regEndDate);

                    break;

                case 5:
                    String userGrp = "";
                    boolean validUserGrp = false;
                    do {
                        System.out.println("Please enter user group this camp is open to: (ADM, EEE, SCSE, NBS, SSS, or NTU)");
                        userGrp = sc.next();
                        try {
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

                    editCamp.setUserGroup(userGrp);
            
                    break;

                case 6:
                    System.out.println("Please enter new Camp location:");
                    String newLocation = sc.next();
                    editCamp.setLocation(newLocation);
                    break;

                case 7:
                    int totalSlots = 0;
                    boolean validTotalSlots = false;
                    do {
                        try {
                            System.out.println("Please enter total slots of Camp:");
                            totalSlots = sc.nextInt();
                            if (totalSlots <= 0) {
                                System.out.println("There must be at least 1 available slot.");
                                validTotalSlots = false;
                            } else {
                                validTotalSlots = true;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid number for total slots.");
                            sc.next(); // consume the invalid input
                        }
                    } while (!validTotalSlots);
                
                    editCamp.setTotalSlots(totalSlots);
                    break;
                

                case 8:
                    System.out.println("Please enter total slots for Camp Committee: (MAX 10)");
                    int campCommSlots = 0;
                    boolean validCampCommSlots = false;
                    do {
                        try {
                            campCommSlots = sc.nextInt();
                            if (campCommSlots > 10 || campCommSlots < 0) {
                                System.out.println("There cannot be more than 10 or less than 0 slots!");
                            } else {
                                validCampCommSlots = true;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid number for Camp Committee slots.");
                            sc.next(); // consume the invalid input
                        }
                    } while (!validCampCommSlots);

                    editCamp.setCommitteeSlots(campCommSlots);
                
                    break;

                case 9:
                    System.out.println("Please enter new Camp description");
                    String newDescription = sc.next();
                    editCamp.setDescription(newDescription);
                    break;

                case 10:
                    if (editCamp.getAttendees().isEmpty() && editCamp.getCommitteeMembers().isEmpty()){
                        System.out.println("Please select visbility:\n"
                        + "(1) Visible\n"
                        + "(2) Hidden\n"
                        + "(3) Quit");

                        int visibilitySelection = sc.nextInt();

                        switch (visibilitySelection) {
                            case 1:
                                editCamp.setVisibility(true);
                                System.out.println("Camp set as visible.");
                                break;
                            case 2:
                                editCamp.setVisibility(false);
                                System.out.println("Camp set as hidden.");
                                break;
                            default:
                                break;
                        }
                    }
                    else{
                        System.out.println("You are not allowed to change the visbility of your Camp once students have registered.");
                    }
                    break;


                case 11:
                    authStaff.deleteCamp(editCamp);    //delete from staff's list
                    allCamps.remove(editCamp);         // delete from all camp list
                    deleted = true;
                    break;
                    
                default:
                    break;
            }
            }
            catch (NumberFormatException h){
                System.out.println("Invalid input.");
            }
            catch (Exception e) {
                System.out.println(e.getMessage());                
            }       
        } while (editFieldOption<11);
        return deleted;
    }
}
