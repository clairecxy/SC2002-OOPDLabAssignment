//import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.InputMismatchException;

public class CampEditor {
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public void editCamp(int editCampIndex, List<Camp> createdCamps, List<Camp> allCamps, Staff authStaff){
        Scanner sc = new Scanner(System.in);        //don't close this!
        sc.useDelimiter("\n");
        
        Camp editCamp = createdCamps.get(editCampIndex-1);      //get selected camp from list of camps staff created
            int editFieldOption;
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
                +"(10) Delete camp\r\n"
                // +"(11) View suggestions\r\n"
                +"(11) Quit");

                while (!sc.hasNextInt()){
                    sc.next();
                }
                editFieldOption = sc.nextInt();
                
                switch (editFieldOption) {
                    case 1:
                        System.out.println("Please enter new Camp name:");
                        String newCampName = sc.next();
                        editCamp.setCampName(newCampName);
                        break;

                    case 2:
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

                        editCamp.setStartDate(startDate);
                        
                        break;

                    case 3:
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

                        editCamp.setCommitteeSlots(campCommSlots);
                    
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

                    // case 11:
                    //     List<Suggestion> suggList = editCamp.getSuggestion();
                    //     int suggIndex = 1;
                    //     for (Suggestion suggestions : suggList){
                    //         System.out.println("("+suggIndex+")" + suggestions.getSuggestionText());
                    //         suggIndex++;                            
                    //     }
                    //     System.out.println("("+suggIndex+") Quit. ");
                    //     System.out.println("Select suggestion to accept or reject.");
                    //     int suggReplyChoice = sc.nextInt();
                        
                    //     if(suggReplyChoice == suggIndex){
                    //         break;
                    //     }
                    //     else{
                    //         System.out.println("Suggestion: " + suggList.get(suggReplyChoice-1).getSuggestionText());
                    //         System.out.println("(1) Accept Suggestion.\r\n"
                    //             +"(2) Reject suggestion.");
                    //         int suggAccept = sc.nextInt();
                    //         if (suggAccept>2 || suggAccept<1){
                    //             System.out.println("Invalid Choice. Please Re-enter choice.");
                    //             suggAccept = sc.nextInt();
                    //         }
                    //         if(suggAccept == 1){
                    //             suggList.get(suggReplyChoice-1).setSuggestionAccepted(true);
                    //         }
                    //         else if (suggAccept == 2){
                    //             suggList.get(suggReplyChoice-1).setSuggestionAccepted(false);
                    //         }
                    //     }
                        

                    default:
                        break;
                }
            } while (editFieldOption<10);
        //sc.close();
    }


}
