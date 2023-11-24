import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Student extends User {
    private List<Camp> enrolledCamps;
    private List<Camp> withdrawnCamps;
    private List<Enquiry> enquiries;
    private Camp campCommittee;
    private boolean isCampCommittee = false;

    public Student(String userID, String faculty) {
        super(userID, faculty);
        enrolledCamps = new ArrayList<>();
        withdrawnCamps = new ArrayList<>();
        enquiries = new ArrayList<>();
    }

    public List<Camp> getAvailableCamps(List<Camp> allCamps) {
        List<Camp> availableCamps = new ArrayList<>();
        
        // Get the current date in the "dd-MM-yyyy" format
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateFormat.format(currentDate);
        
        // Parse the current date as a Date object for comparison
        try {
            currentDate = dateFormat.parse(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle or log the ParseException as needed
        }
        
        // Iterate through all camps and check if they meet the criteria
        for (Camp camp : allCamps) {
            if ((camp.getUserGroup().equals(this.getFaculty()) || camp.getUserGroup().equals("NTU")) && camp.getVisibility() && !this.enrolledCamps.contains(camp) && !this.withdrawnCamps.contains(camp)) {
                
                String campStartDate = camp.getStartDate();
                String campEndDate = camp.getEndDate();
                String campRegEndDate = camp.getRegistrationEndDate();
                
                // Check if there is a date clash with enrolled camps
                boolean dateClash = false;
                for (Camp enrolledCamp : enrolledCamps) {
                    String enrolledCampStartDate = enrolledCamp.getStartDate();
                    String enrolledCampEndDate = enrolledCamp.getEndDate();
                    
                    try {
                        // Convert date strings to Date objects for comparison
                        Date campStart = dateFormat.parse(campStartDate);
                        Date campEnd = dateFormat.parse(campEndDate);
                        Date enrolledCampStart = dateFormat.parse(enrolledCampStartDate);
                        Date enrolledCampEnd = dateFormat.parse(enrolledCampEndDate);

                        // Check for date clash
                        if ((campStart.before(enrolledCampEnd) || campStart.equals(enrolledCampEnd))
                                && (campEnd.after(enrolledCampStart) || campEnd.equals(enrolledCampStart))) {
                            dateClash = true;
                            break;
                        }
                    } catch (ParseException e) {
                        // Handle or log the ParseException as needed
                        e.printStackTrace();
                    }
                }

                try {
                    // Parse campRegEndDate as a Date object
                    Date regEndDate = dateFormat.parse(campRegEndDate);
                    
                    // Check if the camp's registration deadline is in the future
                    if (!dateClash && !currentDate.after(regEndDate)) {
                        availableCamps.add(camp);
                    }
                } catch (ParseException e) {
                    // Handle or log the ParseException as needed
                    e.printStackTrace();
                }
            }
        }
        
        return availableCamps;
    }
    


    // public List<Camp> getAvailableCamps(List<Camp> allCamps) {
    //     List<Camp> availableCamps = new ArrayList<>();
        
    //     //check each camp if the usergroup matches faculty and if visibility is toggled on
        
    //     //ADD: check if camp is in enrolled or withdrawn camp. if yes dont add it to available camps
    //     for (Camp camp : allCamps) {
    //         if ((camp.getUserGroup().equals(this.getFaculty()) || camp.getUserGroup().equals("NTU")) && camp.getVisibility() && !this.enrolledCamps.contains(camp) && !this.withdrawnCamps.contains(camp)) {
    //             availableCamps.add(camp);
    //         }
    //     }
    //     return availableCamps;
    // }

    // 

    public List<Camp> getEnrolledCamps() {
        return enrolledCamps;
    }

    public List<Camp> getWithdrawnCamps() {
        return withdrawnCamps;
    }

    public List<Enquiry> getEnquiries(){
        return enquiries;
    }

    public boolean isCampCommittee() {
        return this.isCampCommittee;
    }

    public void setIsCampCommittee() {
        this.isCampCommittee = true;
    }

    public Camp getCampCommittee() {
        return this.campCommittee;
    }

    public void setCampCommittee(Camp camp) {
        this.campCommittee = camp;
        return;
    }

    public boolean setEnrolledCamps(Camp camp) {
        if (withdrawnCamps.contains(camp)) {
            return false;
        } else {
            enrolledCamps.add(camp);
            // if ("committee".equals(role)) {
            //     this.isCampCommittee = true;
            //     registerForCampAsCommittee(camp);
            // }
            return true;
        }
    }

    // private void registerForCampAsCommittee(Camp camp) {
    //     // Logic to handle registration as a Camp Committee member
    //     CampCommittee committeeMember = new CampCommittee(this.getUserID(), this.getFaculty(), camp);
    //     camp.addCommitteeMembers(committeeMember); 
    // }

    public boolean setWithdrawnCamps(Camp camp) {
        if (enrolledCamps.contains(camp)) {
            enrolledCamps.remove(camp);
            withdrawnCamps.add(camp);
            return true;
        } else{
            return false;
        }
    }
    
    public void submitEnquiry(Camp camp, String enquiry) {
        //make new enquiry object and set attributes
        Enquiry newEnquiry = new Enquiry();
        newEnquiry.setStudent(this);
        newEnquiry.setCamp(camp); 
        newEnquiry.setEnquiryText(enquiry);
        camp.addEnquiry(newEnquiry);
        this.enquiries.add(newEnquiry);
    }  
    
    public List<Camp> getVisibleCamps(List<Camp> allCamps) {
        List<Camp> visibleCamps = new ArrayList<>();
        
        for (Camp camp : allCamps) {
            if ((camp.getUserGroup().equals(this.getFaculty()) || camp.getUserGroup().equals("NTU")) && camp.getVisibility()) {
                visibleCamps.add(camp);
            }
        }
        return visibleCamps;
    }
    
}
