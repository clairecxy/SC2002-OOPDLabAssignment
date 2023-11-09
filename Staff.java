import java.util.ArrayList;
import java.util.List;

public class Staff {
    private List<Camp> createdCampsList;
    private CampApplicationSystem campApplicationSystem;

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

    public Enquiry[] viewEnquiries(Camp camp) {
        if (this.createdCampsList.contains(camp)) {
            return camp.getEnquiries().toArray(new Enquiry[0]);
        }
        return new Enquiry[0];
    }

    public boolean replyEnquiry(Enquiry enquiry, String reply) {
        // Here we assume the Enquiry class has a method to set a reply
        if (enquiry != null) {
            enquiry.setReply(reply);
            return true;
        }
        return false;
    }

    public Suggestion[] viewSuggestions(Camp camp) {
        if (this.createdCampsList.contains(camp)) {
            return camp.getSuggestionText().toArray(new Suggestion[0]);
        }
        return new Suggestion[0];
    }

    public void approveSuggestion(Camp camp) {
        // Here we assume there's a method in Camp to approve a suggestion
        // and that suggestions are managed as a list or set within the Camp.
        camp.approveNextSuggestion();
    }

    public void printGeneralReport(Camp camp, String filter) {
        // Assuming there's a method in Camp to generate a report
        // For simplicity, we'll just print the name of the camp and the filter
        if (this.createdCampsList.contains(camp)) {
            String report = camp.generateReport(filter);
            System.out.println(report);
        }
    }

    public void printPerformanceReport(Camp camp) {
        // Assuming there's a method

        svbsrbb