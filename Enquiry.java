/**
 * Represents an enquiry made by a student for a Camp.
 * The enquiry can be viewed by the Staff who created the Camp, and the Camp's Committee members.
 * @author Tan Hui Ling
 * @version 1.0
 * @since 20/11/2023
 */
public class Enquiry {
    /**
     * The Student who submitted this Enquiry.
     */
    private Student student;

    /**
     * The Camp this Enquiry is enquiring about.
     */
    private Camp camp;

    /**
     * The text of this Enquiry.
     */
    private String enquiryText;

    /**
     * Flag indicating whether this Enquiry has been replied to.
     * The default valuse is false indicating that the Enquiry has not been replied to.
     */
    private boolean isReplied = false;

    /**
     * The text of the reply to this Enquiry.
     */
    private String enquiryReply;
    

    
    /** 
     * Sets the Student who submitted the Enquiry.
     * @param student the student that submitted the enquiry.
     */
    public void setStudent(Student student) {
        this.student = student;
    }
    
    /** 
     * Sets the Camp the Enquiry belongs to.
     * @param camp the Enquiry's Camp.
     */
    public void setCamp(Camp camp) {
        this.camp = camp;
    }

    /** 
     * Sets the Enquiry's text.
     * @param enquiryText the Enquiry's text.
     */
    public void setEnquiryText(String enquiryText) {
        this.enquiryText = enquiryText;
    }

    /** 
     * Sets the Enquiry's reply.
     * @param enquiryText the Enquiry's reply.
     */
    public void setEnquiryReply(String enquiryReply) {
        this.enquiryReply = enquiryReply;
    }

    /** 
     * Gets the Student who submitted the Enquiry.
     * @return the Student who submitted the Enquiry.
     */
    public Student getStudent() {
        return student;
    }

    /** 
     * Gets the Camp the Enquiry belongs to.
     * @return the Camp the Enquiry belongs to.
     */
    public Camp getCamp() {
        return camp;
    }

    /** 
     * Gets the Enquiry's text.
     * @return the Enquiry's text.
     */
    public String getEnquiryText() {
        return enquiryText;
    }

    /**
     * Get the Staff or Camp Committee members's reply to the Enquiry.
     * @return the Enquiry's reply.
     */
    public String getEnquiryReply() {
        return enquiryReply;
    }

    /**
     * Indicate that the Enquiry has been replied to by the Staff or Camp Committee members of the Camp.
     * Changes the isReplied flag from false to true.
     */
    public void replied(){
        this.isReplied = true;
    }  

    /** 
     * Returns the reply result of the Enquiry by the Student.
     * true indicates Enquiry has been replied to.
     * false indicates Enquiry has not been replied to.
     * @return the Enquiry's reply status.
     */
    public boolean replyStatus(){
        return this.isReplied;
    }
}
