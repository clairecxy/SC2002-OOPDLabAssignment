public class Enquiry {
    private Student student;
    private Camp camp;
    private String enquiryText;
    private boolean isReplied = false;
    private String enquiryReply;
    

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCamp(Camp camp) {
        this.camp = camp;
    }

    public void setEnquiryText(String enquiryText) {
        this.enquiryText = enquiryText;
    }

    public void setEnquiryReply(String enquiryReply) {
        this.enquiryReply = enquiryReply;
    }

    public Student getStudent() {
        return student;
    }

    public Camp getCamp() {
        return camp;
    }

    public String getEnquiryText() {
        return enquiryText;
    }

    public String getEnquiryReply() {
        return enquiryReply;
    }

    public void replied(){
        this.isReplied = true;
    }
}
