import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Camp within the Camp Application System.
 * A Camp is created by a Staff.
 * @author Claire Chu Xinyi
 * @since 20/11/23
 */
public class Camp{
    
    /**
     * The name of this Camp.
     */
    private String campName;

    /**
     * The starting date of this Camp.
     */
    private String startDate;

    /**
     * The ending date of this Camp.
     * The ending date is later than the starting date.
     */
    private String endDate;

    /**
     * The deadline for registration for this Camp.
     * The deadline for registration is eariler than the starting date for this Camp.
     */
    private String regEndDate;

    /**
     * The group of Users eligible to apply for this Camp.
     * Groups include: entire NTU, or individual faculties.
     */
    private String userGroup;

    /**
     * The location this Camp is held at.
     */
    private String location;

    /**
     * The total number of slots this Camp has for attendees.
     */
    private int totalSlots;

    /**
     * The remaining number of available slots this Camp has for attendees.
     */
    private int remainingSlots;

    /**
     * The total number of slots this Camp has for Committee members.
     */
    private int committeeSlots;

    /**
     * The remaining number of available slots this Camp has for Committee members.
     */
    private int remainingCommitteeSlots;

    /**
     * A description of this Camp.
     */
    private String description;

    /**
     * The Staff member who created this Camp.
     */
    private Staff staffInCharge;

    /**
     * The visibility setting of this Camp.
     * true indicates eligible Students are able to see this Camp in their available camps.
     * false indicates all Students are not able to see this Camp in their available camps.
     * Staff that created this Camp is able to view this Camp regardless of its visbility status.
     */
    private boolean visible = true;

    /**
     * A list of Student attendees who signed up for this Camp.
     */
    private List<Student> attendees;

    /**
     * A list of attendees who signed up for this Camp as Committee members.
     */
    private List<CampCommittee> committeeMembers;

    /**
     * A list of Enquiries submitted by Students who are eligible to sign up for this Camp.
     */
    private List<Enquiry> enquiry;

    /**
     * A list of Suggestions submitted by Camp Committee members of this Camp.
     */
    private List<Suggestion> suggestion;

    /**
     * Creates a new Camp with the given Camp information.
     * @param name This Camp's name.
     * @param start This Camp's start date.
     * @param end This Camp's end date.
     * @param endReg This Camp's registration deadline.
     * @param users The User groups this Camp is open to.
     * @param locationStr This Camp's location.
     * @param slots This Camp's number of attendee slots.
     * @param commSlots This Camp's number of Camp Committee slots.
     * @param desc This Camp's description.
     * @param staffIC The Staff member who created this Camp.
     */
    public Camp(String name, String start, String end, String endReg, String users, String locationStr, 
                int slots, int commSlots, String desc, Staff staffIC){
        
        setCampName(name);
        setStartDate(start);
        setEndDate(end);
        setRegistrationEndDate(endReg);
        setUserGroup(users);
        setLocation(locationStr);
        setTotalSlots(slots);
        this.remainingSlots = slots;        // if total slots are changed this is also updated in setTotalSlots fn
        setCommitteeSlots(commSlots);
        this.remainingCommitteeSlots = commSlots;
        setDescription(desc);
        this.staffInCharge = staffIC;       

        this.attendees = new ArrayList<>();    
        
        this.committeeMembers = new ArrayList<>(); 

        this.enquiry = new ArrayList<>();      

        this.suggestion = new ArrayList<>();
        
    }

    /**
     * Gets the name of this Camp.
     * @return this Camp's name.
     */
    public String getCampName(){
        return this.campName;
    }

    /**
     * Gets the starting date of this Camp.
     * @return this Camp's starting date.
     */
    public String getStartDate(){
        return this.startDate;
    }

    /**
     * Gets the ending date of this Camp.
     * @return this Camp's ending date.
     */
    public String getEndDate(){
        return this.endDate;
    }

    /**
     * Gets the deadline for registration of this Camp.
     * @return this Camp's registration deadline.
     */
    public String getRegistrationEndDate(){
        return this.regEndDate;
    }

    /**
     * Gets the User groups this Camp is open to.
     * @return this Camp's User group.
     */
    public String getUserGroup(){
        return this.userGroup;
    }

    /**
     * Gets the location of this Camp.
     * @return this Camp's location.
     */
    public String getLocation(){
        return this.location;
    }

    /**
     * Gets the total number of attendee slots of this Camp.
     * @return this Camp's total attendee slots.
     */
    public int getTotalSlots(){
        return this.totalSlots;
    }

    /**
     * Gets the remaining number of attendee slots of this Camp.
     * @return this Camp's remaining attendee slots.
     */
    public int getRemainingSlots(){
        return this.remainingSlots;
    }

    /**
     * Gets the total number of Camp Committee slots of this Camp.
     * @return this Camp's total Camp Committee slots.
     */
    public int getCommitteeSlots(){
        return this.committeeSlots;
    }

    /**
     * Gets the remaining number of Camp Committee slots of this Camp.
     * @return this Camp's remaining Camp Committee slots.
     */
    public int getRemainingCommitteeSlots(){
        return this.remainingCommitteeSlots;
    }

    /**
     * Gets the description of this Camp.
     * @return this Camp's description.
     */
    public String getDescription(){
        return this.description;
    } 

    /**
     * Gets the Staff member who created this Camp.
     * @return this Camp's Staff member.
     */
    public Staff getStaffIC(){
        return this.staffInCharge;
    }

    /**
     * Gets the visibility status this Camp.
     * @return this Camp's visibility status.
     */

    public boolean getVisibility(){
        return this.visible;
    }

    /**
     * Gets the list of Student attendees of this Camp.
     * @return this Camp's list of Student attendees.
     */
    public List<Student> getAttendees(){
        return this.attendees;
    }

    /**
     * Gets the list of Camp Committee members of this Camp.
     * @return this Camp's list of Camp Committee members.
     */
    public List<CampCommittee> getCommitteeMembers(){
        return this.committeeMembers;
    } 

    /**
     * Gets the list of Enquiries made by Students for this Camp.
     * @return this Camp's list of Student Enquiries.
     */
    public List<Enquiry> getAllEnquiries(){
        return this.enquiry;
    }

    /**
     * Gets the list of unprocessed Suggestions made by Camp Committee members of this Camp.
     * Unprocessed refers to Suggestions not approved or rejected by this Camp's Staff.
     * @return this Camp's list of unprocessed Suggestions.
     */
    public List<Suggestion> getPendingSuggestion() {
        List<Suggestion> pendingSuggestions = new ArrayList<>();
        for (Suggestion suggestion : this.suggestion) {
            if (!suggestion.processStatus()) {
                pendingSuggestions.add(suggestion);
            }
        }
        return pendingSuggestions;
    }

    /**
     * Gets the list of processed Suggestions made by Camp Committee members of this Camp.
     * Processed refers to Suggestions already approved or rejected by this Camp's Staff.
     * @return this Camp's list of processed Suggestions.
     */
    public List<Suggestion> getProccessedSuggestion(){
        List<Suggestion> proccessedSuggestions = new ArrayList<>();
        for (Suggestion suggestion : this.suggestion) {
            if (suggestion.processStatus()) {
                proccessedSuggestions.add(suggestion);
            }
        }
        return proccessedSuggestions;
    }
    
    /**
     * Gets the list of Suggestions made by Camp Committee members of this Camp.
     * @return this Camp's list of Camp Committee submitted Suggestions.
     */
    public List<Suggestion> getSuggestion(){
        return this.suggestion;
    }

    /**
     * Changes the name of this Camp.
     * @param campName this Camp's new name.
     */
    public void setCampName(String campName){
        this.campName = campName;
    }
    
    /**
     * Changes the starting date of this Camp.
     * @param startDate this Camp's new start date.
     */
    public void setStartDate(String startDate){
        this.startDate = startDate;
    }

    /**
     * Changes the ending date of this Camp.
     * @param endDate this Camp's new end date.
     */
    public void setEndDate(String endDate){
        this.endDate = endDate;
    }

    /**
     * Changes the registration deadline of this Camp.
     * @param registrationEndDate this Camp's new registration deadline.
     */
    public void setRegistrationEndDate(String registrationEndDate){
        this.regEndDate = registrationEndDate;
    }

    /**
     * Changes the User group this Camp is open to.
     * User groups are either individual faculties or the whole NTU.
     * @param userGroups the new User group this Camp is open to.
     */
    public void setUserGroup(String userGroups){
        this.userGroup = userGroups;
    }

    /**
     * Changes the location this Camp is held at.
     * @param locationStr the new location of this Camp.
     */
    public void setLocation(String locationStr){
        this.location = locationStr;
    }

    /**
     * Changes the total number of slots for Student attendees of this Camp.
     * Updates this Camp's remaining slots according to the new number of slots.
     * @param slots the new total number of slots for Student attendees for this Camp.
     */
    public void setTotalSlots(int slots){
        this.totalSlots = slots;

        if (this.attendees == null){
            this.remainingSlots = totalSlots;
        }
        else{
            this.remainingSlots = totalSlots - this.attendees.size();
        }
    }

    /**
     * Changes the total number of slots for Camp Committee members of this Camp.
     * Updates this Camp's remaining Committee slots according to the new number of Committee slots.
     * @param commSlots the new total number of Camp Committee slots for this Camp.
     */
    public void setCommitteeSlots(int commSlots){
        this.committeeSlots = commSlots;

        if (this.committeeMembers == null){
            this.remainingCommitteeSlots = committeeSlots;
        }
        else{
            this.remainingCommitteeSlots = this.committeeSlots - this.committeeMembers.size();
        }
    }

    /**
     * Changes this Camp's description.
     * @param desc this Camp's new description.
     */
    public void setDescription(String desc){
        this.description = desc;
    }

    /**
     * Changes the visibility of this Camp.
     * true indicates this Camp will appear in the list of available Camps for members of this Camp's User groups.
     * false indicates this Camp will not appear in the list of available Camps for any Students.
     * This Camp's Staff is able to view the Camp regardless of its visibility.
     * @param visibility whether this Camp is visible to Students.
     */
    public void setVisibility(boolean visibility){
        if (!visibility){
            this.visible = false;
        }
        else{
            this.visible = true;
        }
    }

    /**
     * Adds a Student submitted Enquiry to this Camp's list of Enquiries.
     * @param query the new Enquiry for this Camp.
     */
    public void addEnquiry(Enquiry query){
        this.enquiry.add(query);
    }

    /**
     * Adds a Camp Committee member submitted Suggestion to this Camp's list of Suggestions.
     * @param suggest the new Suggestion for this Camp.
     */
    public void addSuggestion(Suggestion suggest){
        this.suggestion.add(suggest);
    }
    
    /**
     * Adds a new Camp Committee member to this Camp.
     * A return flag indicates whether the addition was successful.
     * 0 indicates failure.
     * 1 indicates success.
     * @param campCommMember the Camp Committee member added to this Camp.
     * @return whether the Camp Committee member addition was successful.
     */
    public int addCommitteeMembers(CampCommittee campCommMember){
        
        if (this.remainingCommitteeSlots == 0){
            System.out.println("There are no more committee slots for this camp.");
            return 0;
        }
        else{
            this.committeeMembers.add(campCommMember);
            this.remainingCommitteeSlots--;
            return 1;
        }
    }

    /**
     * Adds a new Student attendee to this Camp.
     * A return flag indicates whether the addition was successful.
     * 0 indicates failure.
     * 1 indicates success.
     * @param attendee the Student attendee added to this Camp.
     * @return whether the Student attendee addition was successful.
     */
    public int addAttendees(Student attendee){
       
        if (this.remainingSlots == 0){
            System.out.println("There are no more slots for this camp.");
            return 0;
        }
        else{
            this.attendees.add(attendee);
            this.remainingSlots--;
            return 1;
        }
    }

    /**
     * Deletes a Student attendee from this Camp's attendee list.
     * @param attendee the Student attendee to be removed.
     */
    public void removeAttendees(Student attendee){
        if (this.attendees.contains(attendee)){
            this.attendees.remove(attendee);
            this.remainingSlots++;
        }
    }

    /**
     * Deletes a Suggestion from this Camp's Suggestion list.
     * @param suggestions the Suggestion to be deleted.
     */
    public void deleteSuggestion(Suggestion suggestions){
        this.suggestion.remove(suggestions);
    }
}


