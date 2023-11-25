import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Camp Committee member in the system.
 * A Camp Committee member is a student.
 * @author Tan Hui Ling
 * @version 1.0
 * @since 20/11/2023
 */
public class CampCommittee extends Student {
    
    /**
     * The Camp this Camp Committee member is a part of.
     */
    private Camp camp;

    /**
     * The list of Suggestions this Camp Committee member has submitted for this Camp.
     */
    private List<Suggestion> suggestions;

    /**
     * The number of poiints this Camp Committee member has.
     */
    private int points;

    /**
     * Creates a new Camp Committee with the given user ID and faculty.
     * The user ID and faculty is inheritied from the Student class.
     * @param userID this Camp Committee member's User ID.
     * @param faculty this Camp Committee member faculty.
     * @param camp the Camp this Camp Committee member is part of.
     */
    public CampCommittee(String userID, String faculty, Camp camp) {
        super(userID, faculty);
        this.camp = camp;
        suggestions = new ArrayList<>();
        points = 0;
    }

    /**
     * Adds a point to this Camp Committee member.
     */
    public void addPoints() {
        this.points++;
    }

    /**
     * Gets the list of Suggestions this Camp Committee member has submitted for this Camp.
     * @return The list of Suggestions submitted by this Camp Committee member.
     */
    public List<Suggestion> getSuggestions() {
        return suggestions;
    }

    /**
     * Gets the Camp this Camp Committee member is a part of.
     * @return this Camp Committee member's Camp.
     */
    public Camp getCamp() {
        return camp;
    }

    /**
     * Updates the Camp this Camp Committee member is a part of.
     * @param camp the Camp this Camp Committee member is a part of.
     */
    public void setCamp(Camp camp){
        this.camp = camp;
    }

    /**
     * Submits a Suggestion for the Camp this Camp Committee member is a part of.
     * @param suggestion the Suggestion text submitted.
     */
    public void submitSuggestion(String suggestion) {
        Suggestion newSuggestion = new Suggestion();
        newSuggestion.setCampCommittee(this);
        newSuggestion.setCamp(camp); 
        newSuggestion.setSuggestionText(suggestion);
    
        this.suggestions.add(newSuggestion);
        camp.addSuggestion(newSuggestion);
        addPoints();
    }

    /**
     * Prints the details of the Camp this Camp Committee member is a part of.
     */
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
    
    /**
     * Gets the total points the Camp Committee member has.
     * @return this Camp Committee member's total points.
     */
    public int getTotalPoints() {
        return points;
    }

    /**
     * Deletes a Suggestion the Camp Committee member has posted.
     * The Suggestion is removed from both the Camp's Suggestion list and this Camp Committee's list of submitted Enquiries.
     * @param suggestion the Suggestion that this Camp Committee member wishes to delete.
     */
    public void deleteSuggestions(Suggestion suggestion) {
        this.suggestions.remove(suggestion);
        this.camp.deleteSuggestion(suggestion);
    }
}