//import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CampEditor {
    
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
                +"(11) View suggestions\r\n"
                +"(12) Quit");

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

                    case 11:
                        List<Suggestion> suggList = editCamp.getSuggestion();
                        int suggIndex = 1;
                        for (Suggestion suggestions : suggList){
                            System.out.println("("+suggIndex+")" + suggestions.getSuggestionText());
                            suggIndex++;                            
                        }
                        System.out.println("("+suggIndex+") Quit. ");
                        System.out.println("Select suggestion to accept or reject.");
                        int suggReplyChoice = sc.nextInt();
                        
                        if(suggReplyChoice == suggIndex){
                            break;
                        }
                        else{
                            System.out.println("Suggestion: " + suggList.get(suggReplyChoice-1).getSuggestionText());
                            System.out.println("(1) Accept Suggestion.\r\n"
                                +"(2) Reject suggestion.");
                            int suggAccept = sc.nextInt();
                            if (suggAccept>2 || suggAccept<1){
                                System.out.println("Invalid Choice. Please Re-enter choice.");
                                suggAccept = sc.nextInt();
                            }
                            if(suggAccept == 1){
                                suggList.get(suggReplyChoice-1).setSuggestionAccepted(true);
                            }
                            else if (suggAccept == 2){
                                suggList.get(suggReplyChoice-1).setSuggestionAccepted(false);
                            }
                        }
                        

                    default:
                        break;
                }
            } while (editFieldOption<12);
        //sc.close();
    }


}
