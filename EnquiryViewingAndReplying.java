import java.util.List;
import java.util.Scanner;

public class EnquiryViewingAndReplying 
{
    private Camp camp;
    private List<Enquiry> enquiryList;
    public EnquiryViewingAndReplying(Camp camp){
        this.camp=camp; 
        this.enquiryList = camp.getAllEnquiries();
    }
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