import java.util.ArrayList;
import java.util.List;

public class Camp{
    private String campName;
    private String startDate;
    private String endDate;
    private String regEndDate;
    private String userGroup;
    private String location;
    private int totalSlots;
    private int remainingSlots;
    private int committeeSlots;
    private String description;
    private Staff staffInCharge;
    private boolean visible = true;
    private List<Student> attendees;
    private List<CampCommittee> committeeMembers;
    private List<Enquiry> enquiry;
    private List<Suggestion> suggestion;

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
        setDescription(desc);
        this.staffInCharge = staffIC;       

        this.attendees = new ArrayList<>();    
        
        this.committeeMembers = new ArrayList<>(); 

        this.enquiry = new ArrayList<>();      

        this.suggestion = new ArrayList<>();
    }

    public String getCampName(){
        return this.campName;
    }

    public String getStartDate(){
        return this.startDate;
    }

    public String getEndDate(){
        return this.endDate;
    }

    public String getRegistrationEndDate(){
        return this.regEndDate;
    }

    public String getUserGroup(){
        return this.userGroup;
    }

    public String getLocation(){
        return this.location;
    }

    public int getTotalSlots(){
        return this.totalSlots;
    }

    public int getRemainingSlots(){
        return this.remainingSlots;
    }

    public int getCommitteeSlots(){
        return this.committeeSlots;
    }

    public String getDescription(){
        return this.description;
    } 

    public boolean getVisibility(){
        return this.visible;
    }

    public List<Student> getAttendees(){
        return this.attendees;
    }

    public List<CampCommittee> getCommitteeMembers(){
        return this.committeeMembers;
    } 

    public List<Enquiry> getAllEnquiries(){
        return this.enquiry;
    }

    // public Enquiry getNewEnquiry(){
    //     int enquiries = 0;
    //     while (this.enquiry[enquiries].getEnquiryReply() != null){
    //         enquiries++;
    //     }
    //     if(this.enquiry[enquiries].getEnquiryReply() == null)
    //     return this.enquiry[enquiries];
    // }

    public List<Suggestion> getSuggestion(){
        return this.suggestion;
    }

    public void setCampName(String campName){
        this.campName = campName;
    }
                
    public void setStartDate(String startDate){
        this.startDate = startDate;
    }

    public void setEndDate(String endDate){
        this.endDate = endDate;
    }

    public void setRegistrationEndDate(String registrationEndDate){
        this.regEndDate = registrationEndDate;
    }

    public void setUserGroup(String userGroups){
        this.userGroup = userGroups;
    }

    public void setLocation(String locationStr){
        this.location = locationStr;
    }

    public void setTotalSlots(int slots){
        this.totalSlots = slots;

        this.remainingSlots = this.totalSlots - this.attendees.size();
    }

    public void setCommitteeSlots(int commSlots){
        this.committeeSlots = commSlots;
    }

    public void setDescription(String desc){
        this.description = desc;
    }

    public void setVisibility(boolean visibility){
        if (!visibility){
            this.visible = false;
        }
        else{
            this.visible = true;
        }
    }

    
    public void addEnquiry(Enquiry query){
        this.enquiry.add(query);
    }

    public void addSuggestion(Suggestion suggest){
        this.suggestion.add(suggest);
    }
    
    public void addCommitteeMembers(CampCommittee campCommMember){
        this.committeeMembers.add(campCommMember);
    }

    public void addAttendees(Student attendee){
        if (remainingSlots == 0){
            System.out.println("There are no more slots for this camp.");
            break;
        }
        else{
            this.attendees.add(attendee);
        }
    }

    public void removeAttendees(Student attendee){

        // if (committeeMembers.contains(attendee))
        // for (int comms = 0; comms<committeeSlots; comm++){      //reject quit if in committee
        //     if (committeeMembers[comms].getUserID() == attendee.getUserID()){
        //         System.out.println("Committee members cannot quit camp.");
        //         break;
        //     }
        // }

        if (this.attendees.contains(attendee)){
            this.attendees.remove(attendee);
            System.out.println("Student: " + attendee + " successfully quit from " + this.campName);
        }
    }
}