import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Staff {
    private List<Camp> createdCampsList;
    private CampApplicationSystem campApplicationSystem;
    private Scanner scanner;

    public Staff(CampApplicationSystem campSystem) {
        this.createdCampsList = new ArrayList<>();
        this.campApplicationSystem = campSystem;
    }

    public boolean createCamp() {
        Camp camp = new Camp(); // Create a new Camp object (assuming a default constructor exists)
        this.createdCampsList.add(camp); // Add the new Camp to the createdCampsList
        this.campApplicationSystem.addCamp(camp); // Add to the CampApplicationSystem's list
        return true; // Assuming the creation is always successful for this example
    }

    public void setCampName(Camp camp, String campName) {
        if (this.createdCampsList.contains(camp)) {
            camp.setName(campName);
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
        this.campApplicationSystem.remove(camp);
    }

    public void toggleCampVisibility(Camp camp, boolean visibility) {
        if (this.createdCampsList.contains(camp)) {
            camp.setVisibility(visibility);
        }
    }

    public List<Camp> viewAllCamps() {
        // Return the list of all camps from the CampApplicationSystem
        return this.campApplicationSystem.getAllCamp();
    }

    public List<Enquiry> viewEnquiries() {
        List<Enquiry> allEnquiries = new ArrayList<>();
        for (Camp camp : this.createdCampsList) {
            allEnquiries.addAll(camp.getEnquiryText());
        }
        return allEnquiries;
    }

    public boolean replyEnquiry(Enquiry enquiry, String reply) {
            // Check if the staff member is in charge of the camp associated with the enquiry
            if (this.createdCampsList.contains(enquiry.getCamp())) {
                enquiry.setEnquiryReply(reply);
                return true; // reply was successfully added to the enquiry
            }
            return false; // The staff member is not in charge of the camp, or the camp was not found
        }

    public void viewAndReplyToEnquiries() {
        this.scanner = new Scanner(System.in);
        List<Enquiry> enquiries = this.viewEnquiries();

        // Display enquiries with an index
        for (int i = 0; i < enquiries.size(); i++) {
            Enquiry enquiry = enquiries.get(i);
            System.out.println((i + 1) + "Enquiry about " + enquiry.getCampName() +
                               "\n" + enquiry.getEnquiryText());
        }

        // Prompt staff to select enquiry
        System.out.println("Enter the number of the enquiry you wish to reply to:");
        int enquiryIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        // Check validity of enq index
        if (enquiryIndex < 0 || enquiryIndex >= enquiries.size()) {
            System.out.println("Invalid enquiry number.");
            return;
        }

        // Prompt reply
        System.out.println("Enter your reply:");
        String reply = scanner.nextLine();

        // send reply
        boolean success = this.replyEnquiry(enquiries.get(enquiryIndex), reply);

        if (success) {
            System.out.println("Reply was successfully posted.");
        } else {
            System.out.println("Failed to post reply. The enquiry does not belong to a camp created by this staff member.");
        }
    }

    public List<Suggestion> viewSuggestions() {
        List<Suggestion> allSuggestions = new ArrayList<>();
        for (Camp camp : this.createdCampsList) {
            allSuggestions.addAll(camp.getSuggestionText());
        }
        return allEnquiries;
    }

    public boolean approveSuggestion(Suggestion suggestion) {
        // Verify that the suggestion is for a camp that this staff member has created
        if (this.createdCampsList.contains(suggestion.getCamp())) {
            suggestion.setSuggestionAccepted(true);
            return true; // Suggestion was successfully approved
        }
        return false; // Suggestion not found in the camps created by this staff, or staff not in charge
    }

    public void printGeneralReport(Camp camp, String filter) {
        if (this.createdCampsList.contains(camp)) {
            String report = camp.generateReport(filter);
            System.out.println(report);
        }
    }  
    
    public void printGeneralReport(Camp camp, String filter) {
        // Check if the camp is in the list of camps this staff member has created
        if (this.createdCampsList.contains(camp)) {
            System.out.println("General report for camp: " + camp.getCampName());
            if ("attendees".equals(filter)) {
                System.out.println("Number of attendees: " + camp.getAttendees().length);
            }
        
        } else {
            System.out.println("Cannot print report: Camp not found in your created camps list.");
        }
    }

    // Method to print a performance report of a camp
    public void printPerformanceReport(Camp camp) {
        
           