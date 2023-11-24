import java.util.ArrayList;
import java.util.List;

public class CampCommittee extends Student {
    private Camp camp;
    private List<Suggestion> suggestions;
    private int points;


    public CampCommittee(String userID, String faculty, Camp camp) {
        super(userID, faculty);
        this.camp = camp;
        suggestions = new ArrayList<>();
        points = 0;
    }

    public void addPoints() {
        this.points++;
    }

    // public void setSuggestions(Suggestion suggestion) {
    //     suggestions.add(suggestion);
    // }

    // public int getPoints() {         //use getTotalPoints instead
    //     return points;
    // }

    public List<Suggestion> getSuggestions() {
        return suggestions;
    }

    public Camp getCamp() {
        return camp;
    }

    public void setCamp(Camp camp){
        this.camp = camp;

    }

    public void submitSuggestion(String suggestion) {
        Suggestion newSuggestion = new Suggestion();
        newSuggestion.setCampCommittee(this);
        newSuggestion.setCamp(camp); 
        newSuggestion.setSuggestionText(suggestion);
    
        this.suggestions.add(newSuggestion);
        camp.addSuggestion(newSuggestion);
        addPoints();
    }

    public void viewCampDetails() {
        String campName = camp.getCampName();
        String startDate = camp.getStartDate();
        String endDate = camp.getEndDate();
        String registrationEndDate = camp.getRegistrationEndDate();
        String userGroup = camp.getUserGroup();
        String location = camp.getLocation();
        int totalSlots = camp.getTotalSlots();
        int remainingSlots = camp.getRemainingSlots();
        int committeeSlots = camp.getCommitteeSlots();
        String description = camp.getDescription();

        System.out.println("Camp Name: " + campName);
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        System.out.println("Registration End Date: " + registrationEndDate);
        System.out.println("User Group: " + userGroup);
        System.out.println("Location: " + location);
        System.out.println("Total Slots: " + totalSlots);
        System.out.println("Remaining Slots: " + remainingSlots);
        System.out.println("Committee Slots: " + committeeSlots);
        System.out.println("Description: " + description);
    }
    
    public boolean hasEnquiries(){
        List<Enquiry> enquiriesCheck = camp.getAllEnquiries();
        if (enquiriesCheck != null && !enquiriesCheck.isEmpty()) {
            return true;
        }
        else{
            return false;
        }
    }

    // public void viewEnquiries() {
    //     List<Enquiry> enquiries = camp.getAllEnquiries();
    
    //     if (enquiries != null && !enquiries.isEmpty()) {
    //         System.out.println("Enquiries for Camp: " + camp.getCampName() + "\n");
    //         int enquiryCounter = 1;
    //         for (Enquiry enquiry : enquiries) {
    //             System.out.println("(" + enquiryCounter+ ") Enquiry Text: " + enquiry.getEnquiryText() + "\n");
    //             enquiryCounter++;
    //         }
    //     } else {
    //         System.out.println("No new enquiries found for the camp.");
    //     }
    // }

    public void replyEnquiry(Enquiry enquiry, String reply) {
        if (enquiry != null) {
            enquiry.setEnquiryReply(reply);
            System.out.println("Replied to the enquiry for Camp: " + camp.getCampName());
            addPoints();
        } else {
            System.out.println("Enquiry not found or is null.");
        }
    }

    public void printGeneralReport(Camp camp, String filter) { 


    }

    public int getTotalPoints() {
        for (Suggestion suggestion : suggestions) {
            // addPoints();
            if (suggestion.getSuggestionAccepted() == true) {
                addPoints();
            }
        }

        return points;

    }

    public void deleteSuggestions(Suggestion suggestion) {
        this.suggestions.remove(suggestion);
        this.camp.deleteSuggestion(suggestion);
    }
}