import java.util.ArrayList;
import java.util.List;
//import java.util.Scanner;

public class Staff extends User {
    private List<Camp> createdCampsList;
    //private CampApplicationSystem campApplicationSystem;
    //private Scanner scanner;

    public Staff(String userID, String faculty) {
        super(userID, faculty);
        this.createdCampsList = new ArrayList<>();
        //this.campApplicationSystem = campSystem;
    }

    public Camp createCamp(String name, String start, String end, String endReg, String users, String locationStr, 
    int slots, int commSlots, String desc, Staff staffIC) {
        Camp camp = new Camp(name, start, end, endReg, users, locationStr, slots, commSlots, desc, staffIC); // Create a new Camp object (assuming a default constructor exists)
        this.createdCampsList.add(camp); // Add the new Camp to the createdCampsList
        //this.campApplicationSystem.addCamp(camp); // Add to the CampApplicationSystem's list
        return camp;
    }

    public void setCampName(Camp camp, String campName) {
        if (this.createdCampsList.contains(camp)) {
            camp.setCampName(campName);
        }
    }

    public void setStartDate(Camp camp, String startDate) {
        if (this.createdCampsList.contains(camp)) {
            camp.setStartDate(startDate);
        }
    }

    public void setEndDate(Camp camp, String endDate) {
        if (this.createdCampsList.contains(camp)) {
            camp.setEndDate(endDate);
        }
    }

    public void setRegistrationEndDate(Camp camp, String registrationEndDate) {
        if (this.createdCampsList.contains(camp)) {
            camp.setRegistrationEndDate(registrationEndDate);
        }
    }

    public void setUserGroup(Camp camp, String userGroup) {
        if (this.createdCampsList.contains(camp)) {
            camp.setUserGroup(userGroup);
        }
    }

    public void setLocation(Camp camp, String location) {
        if (this.createdCampsList.contains(camp)) {
            camp.setLocation(location);
        }
    }

    public void setTotalSlots(Camp camp, int totalSlots) {
        if (this.createdCampsList.contains(camp)) {
            camp.setTotalSlots(totalSlots);
        }
    }

    public void setCommitteeSlots(Camp camp, int committeeSlots) {
        if (this.createdCampsList.contains(camp)) {
            camp.setCommitteeSlots(committeeSlots);
        }
    }

    public void setDescription(Camp camp, String description) {
        if (this.createdCampsList.contains(camp)) {
            camp.setDescription(description);
        }
    }

    public void deleteCamp(Camp camp) {
        this.createdCampsList.remove(camp);
        //this.campApplicationSystem.remove(camp);
    }

    public void toggleCampVisibility(Camp camp, boolean visibility) {
        if (this.createdCampsList.contains(camp)) {
            camp.setVisibility(visibility);
        }
    }

    public List<Camp> viewAllCreatedCamps(){
        return this.createdCampsList;
    }

    // public List<Camp> viewAllCamps() {
    //     // Return the list of all camps from the CampApplicationSystem
    //     return this.campApplicationSystem.getAllCamp();
    // }

    public List<Enquiry> viewEnquiries() {
        List<Enquiry> allEnquiries = new ArrayList<>();
        for (Camp camp : this.createdCampsList) {
            allEnquiries.addAll(camp.getAllEnquiries());
        }
        return allEnquiries;
    }

    public void replyEnquiry(Enquiry enquiry, String reply) {
            // Check if the staff member is in charge of the camp associated with the enquiry
            if (this.createdCampsList.contains(enquiry.getCamp())) {
                enquiry.setEnquiryReply(reply);
            }
        }

    // public void viewAndReplyToEnquiries() {
    //     this.scanner = new Scanner(System.in);
    //     List<Enquiry> enquiries = this.viewEnquiries();

    //     // Display enquiries with an index
    //     for (int i = 0; i < enquiries.size(); i++) {
    //         Enquiry enquiry = enquiries.get(i);
    //         System.out.println((i + 1) + "Enquiry about " + enquiry.getCampName() +
    //                            "\n" + enquiry.getEnquiryText());
    //     }

    //     // Prompt staff to select enquiry
    //     System.out.println("Enter the number of the enquiry you wish to reply to:");
    //     int enquiryIndex = scanner.nextInt() - 1;
    //     scanner.nextLine(); // Consume newline

    //     // Check validity of enq index
    //     if (enquiryIndex < 0 || enquiryIndex >= enquiries.size()) {
    //         System.out.println("Invalid enquiry number.");
    //         return;
    //     }

    //     // Prompt reply
    //     System.out.println("Enter your reply:");
    //     String reply = scanner.nextLine();

    //     // send reply
    //     boolean success = this.replyEnquiry(enquiries.get(enquiryIndex), reply);

    //     if (success) {
    //         System.out.println("Reply was successfully posted.");
    //     } else {
    //         System.out.println("Failed to post reply. The enquiry does not belong to a camp created by this staff member.");
    //     }
    // }

    public List<Suggestion> viewSuggestions() {
        List<Suggestion> allSuggestions = new ArrayList<>();
        for (Camp camp : this.createdCampsList) {
            allSuggestions.addAll(camp.getSuggestion());
        }
        return allSuggestions;
    }

    public void approveSuggestion(Suggestion suggestion) {
        // Verify that the suggestion is for a camp that this staff member has created
        if (this.createdCampsList.contains(suggestion.getCamp())) {
            suggestion.setSuggestionAccepted(true);
        }
    }

    public void printGeneralReportDetails(Camp camp) {
        // Check if the camp is in the list of camps this staff member has created
        if (this.createdCampsList.contains(camp)) {
            // Print details of the camp
            System.out.println("Camp Name: " + camp.getCampName());
            System.out.println("Start Date: " + camp.getStartDate());
            System.out.println("End Date: " + camp.getEndDate());
            System.out.println("Registration End Date: " + camp.getRegistrationEndDate());
            System.out.println("User Group: " + camp.getUserGroup());
            System.out.println("Location: " + camp.getLocation());
            System.out.println("Total Slots: " + camp.getTotalSlots());
            System.out.println("Committee Slots: " + camp.getCommitteeSlots());
            System.out.println("Description: " + camp.getDescription());
            System.out.println("Enquiries and Replies:");
            for (Enquiry enquiry : camp.getAllEnquiries()) { 
                String enquiryText = enquiry.getEnquiryText();
                String enquiryReply = enquiry.getEnquiryReply(); // Assumes enquiry has been replied to

                System.out.println("Enquiry: " + enquiryText);
                System.out.println("Reply: " + (enquiryReply.isEmpty() ? "No reply yet" : enquiryReply));
                System.out.println(); // Print a blank line for better readability
            }
        }
        }

        public void printGeneralReportAttendees(Camp camp) {
            // Check if the camp is in the list of camps this staff member has created
            if (this.createdCampsList.contains(camp)) {
                // Retrieve the list of attendees for the camp
                List<Student> attendees = camp.getAttendees();
                
                // Print the header for the attendees section
                System.out.println("Attendees for camp: " + camp.getCampName());
                
                // Check if there are attendees to display
                if (attendees.isEmpty()) {
                    System.out.println("No attendees to display.");
                } else {
                    // Iterate over the list of attendees and print their details
                    for (Student attendee : attendees) {
                        System.out.println("Attendee UserID: " + attendee.getUserID());
                    }
                }
            }
        }
        public void printGeneralReportCampCommittee(Camp camp) {
                    // Check if the camp is in the list of camps this staff member has created
                    if (this.createdCampsList.contains(camp)) {
                        // Retrieve the list of commmembers for the camp
                        List<CampCommittee> committeeMembers = camp.getCommitteeMembers();
                        
                        // Print the header for the commembers section
                        System.out.println("Commitee Members for camp: " + camp.getCampName());
                        
                        // Check if there are commitee members to display
                        if (committeeMembers.isEmpty()) {
                            System.out.println("No committee members to display.");
                        } else {
                            // Iterate over the list of attendees and print their details
                            for (CampCommittee committeeMembersAttendees : committeeMembers) {
                                System.out.println("Committee Members UserID: " + committeeMembersAttendees.getUserID());
                            }
                        }
                    }
        } 


    // Method to print a performance report of a camp
    public void printPerformanceReport(Camp camp) {
        if (this.createdCampsList.contains(camp)) {
                        // Retrieve the list of commmembers for the camp
                        List<CampCommittee> committeeMembers = camp.getCommitteeMembers();
                        
                        // Print the header for the commembers section
                        System.out.println("Commitee Members for camp: " + camp.getCampName());
                        
                        // Check if there are commitee members to display
                        if (committeeMembers.isEmpty()) {
                            System.out.println("No committee members to display.");
                        } else {
                            // Iterate over the list of attendees and print their details
                            for (CampCommittee committeeMembersPoints : committeeMembers) {
                                System.out.println("Committee Members UserID: " + committeeMembersPoints.getUserID());
                                System.out.println("Committee Members Points: " + committeeMembersPoints.getTotalPoints());
                            }
                        }
                    }
    }
}