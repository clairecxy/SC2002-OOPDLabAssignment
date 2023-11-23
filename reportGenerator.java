import java.util.List;
import java.util.Scanner;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class reportGenerator {
    public void reportGenerator(Camp reportingCamp){
        Scanner sc = new Scanner(System.in);        //don't close this!
        sc.useDelimiter("\n");

        int reportChoice;

        do{
            System.out.println("What kind of report would you like to print?\r\n"
            +"(1) All attendees\r\n"
            +"(2) Camp Committee\r\n"
            +"(3) Camp Details\r\n"
            +"(4) Camp Committee Performance Report\r\n"
            +"(5) Camp General Report\r\n"
            +"(6) Exit");
            reportChoice = sc.nextInt();

            switch (reportChoice) {
                case 1:
                    writeReportToFile(getGeneralReportAttendees(reportingCamp), "attendees_report.txt");
                    break;

                case 2:
                    writeReportToFile(getGeneralReportCampCommittee(reportingCamp), "committee_report.txt");
                    break;

                case 3:
                    writeReportToFile(getGeneralReportDetails(reportingCamp), "camp_details_report.txt");
                    break;
            
                case 4:
                    writeReportToFile(getPerformanceReport(reportingCamp), "performance_report.txt");
                    break;

                case 5:
                    writeReportToFile(getGeneralReport(reportingCamp), "performance_report.txt");


                default:
                    break;
            }
            
        }while(reportChoice<6);
        


    }

    private void writeReportToFile(String report, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
            System.out.println("Report written to " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing report to file: " + e.getMessage());
        }
    }

    public String getGeneralReportDetails(Camp camp) {
        // Check if the camp is in the list of camps this staff member has created
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
    

    public String getGeneralReportAttendees(Camp camp) {
        StringBuilder report = new StringBuilder();
    
        // Check if the camp is in the list of camps this staff member has created
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
    
        // Convert the StringBuilder to a string and return the report
        return report.toString();
    }
        
    public String getGeneralReportCampCommittee(Camp camp) {
        // Check if the camp is in the list of camps this staff member has created
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
        


    // Method to print a performance report of a camp
    public String getPerformanceReport(Camp camp) {

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

    public String getGeneralReport(Camp camp) {
        // Check if the camp is in the list of camps this staff member has created
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

    
}
