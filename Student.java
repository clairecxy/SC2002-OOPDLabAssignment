import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private List<Camp> enrolledCamps;
    private List<Camp> withdrawnCamps;

    public Student(String userID, String faculty) {
        super(userID, faculty);
        enrolledCamps = new ArrayList<>();
        withdrawnCamps = new ArrayList<>();
    }

    public List<Camp> getAvailableCamps(CampApplicationSystem campAppSystem) {
        List<Camp> availableCamps = new ArrayList<>();

        //get the list of all camps from the CampApplicationSystem
        List<Camp> allCamps = campAppSystem.getAllCamp();

        //check each camp if the usergroup matches faculty and if visibility is toggled on
        for (Camp camp : allCamps) {
            if (camp.getUserGroup().equals(this.getFaculty()) && camp.getVisibility()) {
                availableCamps.add(camp);
            }
        }
        return availableCamps;
    }

    public List<Camp> getEnrolledCamps() {
        return enrolledCamps;
    }

    public List<Camp> getWithdrawnCamps() {
        return withdrawnCamps;
    }

    public boolean setEnrolledCamps(Camp camp, String role) {
        if (withdrawnCamps.contains(camp)) {
            return false;
        } else {
            enrolledCamps.add(camp);
            return true;
        }
    }

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
    }    
}