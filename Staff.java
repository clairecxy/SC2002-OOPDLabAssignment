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

    // public List<Suggestion> viewSuggestions() {
    //     List<Suggestion> allSuggestions = new ArrayList<>();
    //     for (Camp camp : this.createdCampsList) {
    //         allSuggestions.addAll(camp.getSuggestion());
    //     }
    //     return allSuggestions;
    // }

    // public void approveSuggestion(Suggestion suggestion) {
    //     // Verify that the suggestion is for a camp that this staff member has created
    //     if (this.createdCampsList.contains(suggestion.getCamp())) {
    //         suggestion.setSuggestionAccepted(true);
    //     }
    // }

    public String getGeneralReportDetails(Camp camp) {
        // Check if the camp is in the list of camps this staff member has created
        if (this.createdCampsList.contains(camp)) {
            // Create a StringBuilder to build the report
            StringBuilder report = new StringBuilder();
    
            // Append details of the camp to the report
            report.append("Camp Name: ").append(camp.getCampName()).append("\n");
            report.append("Start Date: ").append(camp.getStartDate()).append("\n");
            report.append("End Date: ").append(camp.getEndDate()).append("\n");
            report.append("Registration End Date: ").append(camp.getRegistrationEndDate()).append("\n");
            report.append("User Group: ").append(camp.getUserGroup()).append("\n");
            report.append("Location: ").append(camp.getLocation()).append("\n");
            report.append("Total Slots: ").append(camp.getTotalSlots()).append("\n");
            report.append("Committee Slots: ").append(camp.getCommitteeSlots()).append("\n");
            report.append("Description: ").append(camp.getDescription()).append("\n");
            report.append("Enquiries and Replies:\n");
    
            // Iterate over the list of enquiries and append their details to the report
            for (Enquiry enquiry : camp.getAllEnquiries()) {
                String enquiryText = enquiry.getEnquiryText();
                String enquiryReply = enquiry.getEnquiryReply();
    
                report.append("Enquiry: ").append(enquiryText).append("\n");
                report.append("Reply: ").append(enquiryReply == null ? "No reply yet" : enquiryReply).append("\n");
                report.append("\n"); // Add a newline for better readability
            }
    
            // Convert the StringBuilder to a String and return
            return report.toString();
        }
    
        // If the camp is not in the createdCampsList, return an appropriate message
        return "Camp not found in the list of camps created by this staff member.";
    }
    

        public String getGeneralReportAttendees(Camp camp) {
            StringBuilder report = new StringBuilder();
        
            // Check if the camp is in the list of camps this staff member has created
            if (this.createdCampsList.contains(camp)) {
                // Retrieve the list of attendees for the camp
                List<Student> attendees = camp.getAttendees();
        
                // Append the header for the attendees section to the report
                report.append("Attendees for camp: ").append(camp.getCampName()).append("\n");
        
                // Check if there are attendees to display
                if (attendees.isEmpty()) {
                    report.append("No attendees to display.\n");
                } else {
                    // Iterate over the list of attendees and append their details to the report
                    for (Student attendee : attendees) {
                        report.append("Attendee UserID: ").append(attendee.getUserID()).append("\n");
                    }
                }
            }
        
            // Convert the StringBuilder to a string and return the report
            return report.toString();
        }
        
        public String getGeneralReportCampCommittee(Camp camp) {
            // Check if the camp is in the list of camps this staff member has created
            if (this.createdCampsList.contains(camp)) {
                // Retrieve the list of committee members for the camp
                List<CampCommittee> committeeMembers = camp.getCommitteeMembers();
        
                // Create a StringBuilder to build the report
                StringBuilder report = new StringBuilder();
                
                // Add the header for the committee members section
                report.append("Committee Members for camp: ").append(camp.getCampName()).append("\n");
                
                // Check if there are committee members to display
                if (committeeMembers.isEmpty()) {
                    report.append("No committee members to display.\n");
                } else {
                    // Iterate over the list of committee members and append their details to the report
                    for (CampCommittee committeeMember : committeeMembers) {
                        report.append("Committee Members UserID: ").append(committeeMember.getUserID()).append("\n");
                    }
                }
        
                // Convert the StringBuilder to a String and return
                return report.toString();
            }
        
            // If the camp is not in the createdCampsList, return an appropriate message
            return "Camp not found in the list of camps created by this staff member.";
        }
        


    // Method to print a performance report of a camp
    public String getPerformanceReport(Camp camp) {
        // Check if the camp is in the list of camps this staff member has created
        if (this.createdCampsList.contains(camp)) {
            // Retrieve the list of committee members for the camp
            List<CampCommittee> committeeMembers = camp.getCommitteeMembers();
    
            // Create a StringBuilder to build the report
            StringBuilder report = new StringBuilder();
    
            // Print the header for the committee members section
            report.append("Committee Members for camp: ").append(camp.getCampName()).append("\n");
    
            // Check if there are committee members to display
            if (committeeMembers.isEmpty()) {
                report.append("No committee members to display.").append("\n");
            } else {
                // Iterate over the list of committee members and append their details to the report
                for (CampCommittee committeeMembersPoints : committeeMembers) {
                    report.append("Committee Members UserID: ").append(committeeMembersPoints.getUserID()).append("\n");
                    report.append("Committee Members Points: ").append(committeeMembersPoints.getTotalPoints()).append("\n");
                }
            }
    
            // Convert the StringBuilder to a String and return
            return report.toString();
        }
    
        // If the camp is not in the createdCampsList, return an appropriate message
        return "Camp not found in the list of camps created by this staff member.";
    }

    public String getGeneralReport(Camp camp) {
        // Check if the camp is in the list of camps this staff member has created
        if (this.createdCampsList.contains(camp)) {
            // Create a StringBuilder to build the report
            StringBuilder report = new StringBuilder();
    
            // Append details of the camp to the report
            report.append("Camp Name: ").append(camp.getCampName()).append("\n");
            report.append("Start Date: ").append(camp.getStartDate()).append("\n");
            report.append("End Date: ").append(camp.getEndDate()).append("\n");
            report.append("Registration End Date: ").append(camp.getRegistrationEndDate()).append("\n");
            report.append("User Group: ").append(camp.getUserGroup()).append("\n");
            report.append("Location: ").append(camp.getLocation()).append("\n");
            report.append("Total Slots: ").append(camp.getTotalSlots()).append("\n");
            report.append("Committee Slots: ").append(camp.getCommitteeSlots()).append("\n");
            report.append("Description: ").append(camp.getDescription()).append("\n");
            report.append("Enquiries and Replies:\n");
    
            // Iterate over the list of enquiries and append their details to the report
            for (Enquiry enquiry : camp.getAllEnquiries()) {
                String enquiryText = enquiry.getEnquiryText();
                String enquiryReply = enquiry.getEnquiryReply();
    
                report.append("Enquiry: ").append(enquiryText).append("\n");
                report.append("Reply: ").append(enquiryReply == null ? "No reply yet" : enquiryReply).append("\n");
                report.append("\n"); // Add a newline for better readability
            }

            report.append("\n");
    
            // Retrieve the list of attendees for the camp
            List<Student> attendees = camp.getAttendees();
    
            // Append the header for the attendees section to the report
            report.append("Attendees for camp: ").append(camp.getCampName()).append("\n");
    
            // Check if there are attendees to display
            if (attendees.isEmpty()) {
                report.append("No attendees to display.\n");
            } else {
                // Iterate over the list of attendees and append their details to the report
                for (Student attendee : attendees) {
                    report.append("Attendee UserID: ").append(attendee.getUserID()).append("\n");
                }
            }

            report.append("\n");
    
            // Retrieve the list of committee members for the camp
            List<CampCommittee> committeeMembers = camp.getCommitteeMembers();
    
            // // Add the header for the committee members section
            // report.append("Committee Members for camp: ").append(camp.getCampName()).append("\n");
    
            // // Check if there are committee members to display
            // if (committeeMembers.isEmpty()) {
            //     report.append("No committee members to display.\n");
            // } else {
            //     // Iterate over the list of committee members and append their details to the report
            //     for (CampCommittee committeeMember : committeeMembers) {
            //         report.append("Committee Members UserID: ").append(committeeMember.getUserID()).append("\n");
            //     }
            // }
    
            // Retrieve the list of committee members for the camp
            committeeMembers = camp.getCommitteeMembers();
    
            // Print the header for the committee members section
            report.append("Committee Members for camp: ").append(camp.getCampName()).append("\n");
    
            // Check if there are committee members to display
            if (committeeMembers.isEmpty()) {
                report.append("No committee members to display.").append("\n");
            } else {
                // Iterate over the list of committee members and append their details to the report
                for (CampCommittee committeeMembersPoints : committeeMembers) {
                    report.append("Committee Members UserID: ").append(committeeMembersPoints.getUserID()).append("\n");
                    report.append("Committee Members Points: ").append(committeeMembersPoints.getTotalPoints()).append("\n");
                }
            }
    
            // Convert the StringBuilder to a String and return
            return report.toString();
        }
    
        // If the camp is not in the createdCampsList, return an appropriate message
        return "Camp not found in the list of camps created by this staff member.";
    }
    
    
}