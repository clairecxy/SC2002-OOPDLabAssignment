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
    private Student[] attendees;
    private CampCommittee[] committeeMembers;
    private Enquiry[] enquiry;
    private Suggestion[] suggestion;

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

        this.attendees = new Student[this.totalSlots];      //intialised to a null array
        for (int slot=0; slot<this.totalSlots; slot++){
            attendees[slot] = null;
        }
        this.committeeMembers = new CampCommittee[this.committeeSlots];     //intialised to a null array
        for (int slotComm=0; slotComm<this.committeeSlots; slotComm++){
            committeeMembers[slotComm] = null;
        }

        this.enquiry = new Enquiry[100];        //intialised to a null array of size 100
        for (int enq=0; enq<100; enq++){
            enquiry[enq] = null;
        }

        this.suggestion = new Suggestion[100];        //intialised to a null array of size 100
        for (int sugg=0; sugg<100; sugg++){
            suggestion[sugg] = null;
        }
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

    public Student[] getAttendees(){
        return this.attendees;
    }

    public CampCommittee[] getCommitteeMembers(){
        return this.committeeMembers;
    } 

    public Enquiry getNewEnquiry(){
        int enquiries = 0;
        while (this.enquiry[enquiries].enquiryReply != null){
            enquiries++;
        }
        if(this.enquiry[enquiries].enquiryReply == null)
        return this.enquiry[enquiries];
    }

    public Suggestion[] getSuggestion(){
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

        this.remainingSlots = 0;
        for (int checkAvail=0; checkAvail<slots; checkAvail++){
            if (this.attendees[checkAvail] == null){
                this.remainingSlots++;
            }
        }
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
        int queryCount = 0;
        while(this.enquiry[queryCount] != null){
            queryCount++;
        }
        if (this.enquiry[queryCount] == null){
            this.enquiry[queryCount] = query;
        }
    }

    public void addSuggestion(Suggestion suggest){
        int suggestCount = 0;
        while(this.suggestion[suggestCount] != null){
            suggestCount++;
        }
        if (this.suggestion[suggestCount] == null){
            this.suggestion[suggestCount] = suggest;
        }
    }
    
    public void addCommitteeMembers(CampCommittee[] campCommMember){
        for (int commslot=0; commslot<this.committeeSlots; commslot++){
            if(attendees[commslot] != null){
                attendees[commslot] = campCommMember;
                break;
            }
        }
    }

    public void addAttendees(Student attendee){
        if (remainingSlots() == 0){
            System.out.println("There are no more slots for this camp.");
            break;
        }
        for (int slot=0; slot<this.totalSlots; slot++){
            if(attendees[slot] != null){
                attendees[slot] = attendee;
                System.out.println("Successfully joined camp.");
                break;
            }
        }
    }

    public void removeAttendees(Student attendee){

        for (int comms = 0; comms<committeeSlots; comm++){      //reject quit if in committee
            if (committeeMembers[comms].getUserID() == attendee.getUserID()){
                System.out.println("Committee members cannot quit camp.");
                break;
            }
        }

        for (int slot=0; slot<this.totalSlots; slot++){     //successful quit
            if(attendees[slot] == attendee){
                attendees[slot] = null;
                System.out.println("Student: " + attendee + " successfully quit from " + this.campName);
                break;
            }
        }
    }
}