import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private List<Camp> enrolledCamps;
    private List<Camp> withdrawnCamps;
    private List<Enquiry> enquiries;
    private boolean isCampCommittee = false;

    public Student(String userID, String faculty) {
        super(userID, faculty);
        enrolledCamps = new ArrayList<>();
        withdrawnCamps = new ArrayList<>();
        enquiries = new ArrayList<>();
    }

    public List<Camp> getAvailableCamps(List<Camp> allCamps) {
        List<Camp> availableCamps = new ArrayList<>();
        
        //check each camp if the usergroup matches faculty and if visibility is toggled on
        
        //ADD: check if camp is in enrolled or withdrawn camp. if yes dont add it to available camps
        for (Camp camp : allCamps) {
            if ((camp.getUserGroup().equals(this.getFaculty()) || camp.getUserGroup().equals("NTU")) && camp.getVisibility() && !this.enrolledCamps.contains(camp) && !this.withdrawnCamps.contains(camp)) {
                availableCamps.add(camp);
            }
        }
        return availableCamps;
    }

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
}
