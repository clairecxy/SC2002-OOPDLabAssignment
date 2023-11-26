import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a Student in the system.
 * @author Aryan Garg
 * @version 1.0
 * @since 20/11/2023
 */
public class Student extends User {
    
    /**
     * This Student's list of Camps they are enrolled in.
     */
    private List<Camp> enrolledCamps;

    /**
     * This Student's list of Camps they have withdrawn from.
     */
    private List<Camp> withdrawnCamps;

    /**
     * This Student's list of Enquiries they have made for Camps available to them.
     */
    private List<Enquiry> enquiries;

    /**
     * The Camp this Student is a Camp Committee of, if any.
     */
    private Camp campCommittee;

    /**
     * A flag indicating if a Student is a Camp Committee member for any Camp.
     * The default value is false to indicate that a Student is not a Camp Committee member for any Camp.
     */
    private boolean isCampCommittee = false;

    /**
     * Creates a new Student with the given user ID and faculty.
     * The user ID and faculty is inherited from the User class.
     * This Student contains lists for:
     * 1. Enrolled Camps
     * 2. Withdrawn Camps
     * 3. Enquiries submited by this Student.
     * @param userID This Student's user ID.
     * @param faculty This Student's faculty.
     */
    public Student(String userID, String faculty) {
        super(userID, faculty);
        enrolledCamps = new ArrayList<>();
        withdrawnCamps = new ArrayList<>();
        enquiries = new ArrayList<>();
    }

    /**
     * Gets the Camps available to this Student.
     * Filters all Camps within the system via:
     * 1. Date clashes with already registered Camps
     * 2. Whether a Camp is open to their User group.
     * @param allCamps All Camps within the system.
     * @return the list of Camps that are available to the Student.
     */
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

    /**
     * Gets the list of this Student's enrolled Camps.
     * @return the list of Camps that this Student is enrolled in.
     */
    public List<Camp> getEnrolledCamps() {
        return enrolledCamps;
    }

    /**
     * Gets the list of this Student's withdrawn Camps.
     * @return the list of Camps that this Student has withdrawn from.
     */
    public List<Camp> getWithdrawnCamps() {
        return withdrawnCamps;
    }

    /**
     * Gets the list of Enquiries submitted by this Student.
     * @return the list of Enquries submitted by the Student.
     */
    public List<Enquiry> getEnquiries(){
        return enquiries;
    }

    /**
     * Gets the status of a Student being a Camp Committee member for any Camp.
     * true indicates that this Student is a Camp Committee member.
     * false indicates that this Student is not a Camp Committee member.
     * @return whether this Student is a Camp Committee member.
     */
    public boolean isCampCommittee() {
        return this.isCampCommittee;
    }

    /**
     * Changes the flag of a Student being a Camp Committee member to true.
     * Indicates that a Student is a Camp Committee member.
     */
    public void setIsCampCommittee() {
        this.isCampCommittee = true;
    }

    /**
     * Gets the Camp this Student is a Camp Committee of.
     * @return the Camp this Student is a Camp Committee of.
     */
    public Camp getCampCommittee() {
        return this.campCommittee;
    }

    /**
     * Sets the Camp this Student is a Camp Committee of.
     * @param camp the Camp this Student is a Camp Committee of.
     */
    public void setCampCommittee(Camp camp) {
        this.campCommittee = camp;
        return;
    }

    /**
     * Adds a Camp into the list of enrolled Camps of this Student.
     * This student is only allowed to enroll in the Camp if they have not previously enrolled in the same Camp.
     * @param camp the Camp that this Student has enrolled in.
     * @return whether the Camp was successfully enrolled into.
     */
    public boolean setEnrolledCamps(Camp camp) {
        if (withdrawnCamps.contains(camp)) {
            return false;
        } else {
            enrolledCamps.add(camp);
            return true;
        }
    }

    /**
     * Withdraws a Student from the given Camp.
     * The given Camp is added into the list of withdrawn Camps of this Student.
     * A Student can only withdraw from the Camp if they are not a Camp Committee member of the Camp.
     * @param camp the Camp that this Student has withdrawn from.
     * @return whether the Camp was successfully withdrawn from.
     */
    public boolean setWithdrawnCamps(Camp camp) {
        if (enrolledCamps.contains(camp)) {
            enrolledCamps.remove(camp);
            withdrawnCamps.add(camp);
            return true;
        } else{
            return false;
        }
    }
    
    /**
     * Submits a new Enquiry for a Camp that this Student is eligble to join.
     * A new Enuiry object is created using the Enquiry text submitted by this Student for the Camp.
     * @param camp the Camp this Student is enquiring for.
     * @param enquiry the Enquiry text this Student is submitting to the Camp.
     */
    public void submitEnquiry(Camp camp, String enquiry) {
        //make new enquiry object and set attributes
        Enquiry newEnquiry = new Enquiry();
        newEnquiry.setStudent(this);
        newEnquiry.setCamp(camp); 
        newEnquiry.setEnquiryText(enquiry);
        camp.addEnquiry(newEnquiry);
        this.enquiries.add(newEnquiry);
    }  
    
    /**
     * Gets a list of all Camps this Student is eligble to join.
     * @param allCamps the list of all Camps within the system.
     * @return The list of Camps that the Student is eligible to join.
     */
    public List<Camp> getVisibleCamps(List<Camp> allCamps) {
        List<Camp> visibleCamps = new ArrayList<>();
        
        for (Camp camp : allCamps) {
            if ((camp.getUserGroup().equals(this.getFaculty()) || camp.getUserGroup().equals("NTU")) && camp.getVisibility()) {
                visibleCamps.add(camp);
            }
        }
        return visibleCamps;
    }
    
    /**
     * Deletes the given enquiry for the Camp.
     * The enquiry is removed from both the Camp's Enquiry list and this Student's list of submitted Enquiries.
     * @param enquiry the Enquiry that this Student wishes to delete.
     */
    public void deleteEnquiries(Enquiry enquiry) {
        this.enquiries.remove(enquiry);
        enquiry.getCamp().getAllEnquiries().remove(enquiry);
    }
}
