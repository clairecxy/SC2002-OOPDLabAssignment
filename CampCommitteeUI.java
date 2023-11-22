import java.util.List;
import java.util.Scanner;

public class CampCommitteeUI {

    public void campCommUI(CampCommittee authCampCommittee, Camp commCamp){
        Scanner sc = new Scanner(System.in);        //don't close this!
        sc.useDelimiter("\r\n");

        int campCommSelection;

        
        do{
            System.out.println("=====WELCOME CAMP COMMITTEE MEMBER====="); 
            System.out.println("Please select an action:\n"
                + "(1) View details of camp\n"
                + "(2) Submit suggestions for camp\n"
                + "(3) View and reply enquiries\n"
                + "(4) Edit suggestions\n"
                + "(5) Generate report of student list\n"
                + "(6) Quit");

            campCommSelection = sc.nextInt();

        
            switch (campCommSelection) {
                case 1:     //view details of camp
                    authCampCommittee.viewCampDetails();
                    break;

                case 2:
                    System.out.println("Suggestion: ");
                    String suggestion = sc.next();
                    authCampCommittee.submitSuggestion(suggestion);     //this adds to comm's sugg list and camp's sugg list
                    break;
            
                case 3: //view and reply enquiries
                    // List<Enquiry> enquiries = commCamp.getAllEnquiries();

                    // authCampCommittee.viewEnquiries();
                    // if (!authCampCommittee.hasEnquiries()){
                    //     break;
                    // }

                    // System.out.println("Select enquiry to reply to, 0 to quit: ");
                    // int commReplyChoice = sc.nextInt();

                    // if (commReplyChoice == 0){
                    //     break;
                    // }
                    // else{
                    //     Enquiry replyingEnq = enquiries.get(commReplyChoice-1);
                    //     System.out.println("Enter your reply: ");
                    //     String enqReply = sc.next();
                    //     authCampCommittee.replyEnquiry(replyingEnq, enqReply);
                    // }
                    EnquiryViewingAndReplying enquiryViewingAndReplying = new EnquiryViewingAndReplying(commCamp);
                    enquiryViewingAndReplying.enquiryUI(commCamp);                 

                    break;
                
                case 4: //edit suggestion
                    System.out.println("=====EDIT SUGGESTIONS====="); 
                    List<Suggestion> submittedSuggestions = authCampCommittee.getSuggestions();
                    System.out.println("Select suggestion to be edited, 0 to quit: ");
                    int suggCounter = 1;
                    for (Suggestion suggestions : submittedSuggestions) {
                        System.out.println("(" + suggCounter+ ") Suggestion: " + suggestions.getSuggestionText() + "\n");
                        suggCounter++;
                    }
                    
                    int suggEditChoice = sc.nextInt();

                    if (suggEditChoice == 0){
                        break;
                    }
                    else{
                        Suggestion suggChoice = submittedSuggestions.get(suggEditChoice-1);
                        if (!suggChoice.processStatus()){
                            System.out.println("Previous suggestion: " + suggChoice.getSuggestionText());
                            
                            System.out.println("Enter edited suggestion: ");
                            String editedSugg = sc.next();
                            suggChoice.setSuggestionText(editedSugg);
                        }
                        else{
                            System.out.println("You cannot edit the suggestion after it has been processed!");
                            break;
                        }
                        
                    }
                    break;

                case 5:     //Generate report of student list
                    //authCampCommittee.printGeneralReport(commCamp, filter);
                    break;

                default:
                    break;
            }
        }while (campCommSelection<6);
    }
    
}
