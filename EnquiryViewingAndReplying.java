import java.util.List;
import java.util.Scanner;

/**
 * Represents a system to view and reply to enquiries in the system.
 * @author Aryan Garg
 * @version 1.0
 * @since 20/11/2023
 */
public class EnquiryViewingAndReplying 
{
    /**
     * The Camp that this Enquiry viewer is viewing Enquiries for.
     */
    private Camp camp;

    /**
     * The list of Enquiries of this Camp.
     */
    private List<Enquiry> enquiryList;

    /**
     * Creates a new EnquiryViewingAndReplying object for use with the given Camp.
     * @param camp the Camp this Enquiry viewer is viewing Enquiries for.
     */
    public EnquiryViewingAndReplying(Camp camp){
        this.camp=camp; 
        this.enquiryList = camp.getAllEnquiries();
    }
    
    /**
     * The method which displays the enquiries for the Camp (if any).
     * Users can select and reply to the displayed enquiries.
     * If the Enquiry was replied to successfully, 1 is returned, else, 0.
     * @param camp the Camp this Enquiry viewer is viewing Enquiries for.
     * @return whether the Enquiry was replied to successfully.
     */
    public int enquiryUI(Camp camp){

        Scanner sc = new Scanner(System.in);        //don't close this!
        sc.useDelimiter("\r\n");

       if (viewEnquiries() == false){         
            System.out.println("There are no enquiries to view.");
            return 0;
        }
        
        System.out.println("Select enquiry to reply to, 0 to quit: ");
        int replyChoice = sc.nextInt();

        if (replyChoice == 0){
            return 0; 
        }
        else{
            Enquiry replyingEnq = enquiryList.get(replyChoice-1);
            System.out.println("Enter your reply: ");
            String enqReply = sc.next();
            replyEnquiry(replyingEnq, enqReply);
            return 1;
        }
        
    }

    /**
     * Allows Users to view a list of Enquiries submitted by Students for the Camp.
     * Upon viewing, Users can reply to an Enquiry they have selected.
     * @return whether an Enquiry was replied to succesfully.
     */
    public boolean viewEnquiries() {
        
            List<Enquiry> enquiries = camp.getAllEnquiries();
        
            if (enquiries != null && !enquiries.isEmpty()) {
                System.out.println("Enquiries for Camp: " + camp.getCampName() + "\n");
                int enquiryCounter = 1;
            
                for (Enquiry enquiry : enquiries) {
                    System.out.println("(" + enquiryCounter+ ") Enquiry Text: " + enquiry.getEnquiryText() + "\n");
                    if (enquiry.replyStatus()){
                        System.out.println("    => Reply: " + enquiry.getEnquiryReply());
                    }
                    enquiryCounter++;

                }
                return true;

            } else {
                System.out.println("No new enquiries found for the camp.");
                return false;
            }
        }

    /**
     * Allows Users to reply to an Enquiry submitted by a Student for the Camp.
     * @param enquiry the Enquiry submitted by the Student.
     * @param reply the reply to the submitted Enquiry.
     */
    public void replyEnquiry(Enquiry enquiry, String reply) {
        if (enquiry != null) {
            enquiry.setEnquiryReply(reply);
            System.out.println("Replied to the enquiry for Camp: " + camp.getCampName());
            enquiry.replied();
        } else {
            System.out.println("Enquiry not found or is null.");
        }
    }

}