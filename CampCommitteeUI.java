import java.util.List;
import java.util.Scanner;

public class CampCommitteeUI {

    public void campCommUI(CampCommittee authCampCommittee, Camp commCamp){
        Scanner sc = new Scanner(System.in);        //don't close this!
        sc.useDelimiter("\r\n");

        int campCommSelection = 0;

        
        do{
            System.out.println("\n=====WELCOME CAMP COMMITTEE MEMBER====="); 
            System.out.println("Please select an action:\n"
                + "(1) View details of camp\n"
                + "(2) Submit suggestions for camp\n"
                + "(3) Edit or delete suggestions\n"
                + "(4) View and reply enquiries\n"
                + "(5) Generate report of student list\n"
                + "(6) View your points \n"
                + "(7) Quit");

            try{    //exception handling for non-integers and invalid selections
                campCommSelection = Integer.parseInt(sc.next());
                                    
                if(campCommSelection >7 || campCommSelection<1){
                    throw new Exception("A valid selection was not made.");                    
                }
                   
            switch (campCommSelection) {
                case 1:     //view details of camp
                    authCampCommittee.viewCampDetails();
                    break;

                case 2:
                    System.out.println("Suggestion: ");
                    String suggestion = sc.next();
                    authCampCommittee.submitSuggestion(suggestion);     //this adds to comm's sugg list and camp's sugg list
                    break;
            
                
                
                case 3: //edit suggestion
                    System.out.println("=====EDIT OR DELETE SUGGESTIONS====="); 
                    List<Suggestion> submittedSuggestions = authCampCommittee.getSuggestions();
                    System.out.println("Select suggestion to be edited or deleted, 0 to quit: ");
                    int suggCounter = 1;
                    for (Suggestion suggestions : submittedSuggestions) {
                        System.out.println("(" + suggCounter+ ") Suggestion: " + suggestions.getSuggestionText() + "\n");
                        suggCounter++;
                    }
                    int suggEditChoice;
                    suggEditChoice = Integer.parseInt(sc.next());
                                        
                    if(suggEditChoice > suggCounter-- || suggEditChoice<1){
                        throw new Exception("A valid selection was not made.");                    
                    }
                    
                    if (suggEditChoice == 0){
                        break;
                    }
                    else{
                        
                        int editOrDeleteChoice = 0;
                        boolean delete = false;

                        do {
                            Suggestion suggChoice = submittedSuggestions.get(suggEditChoice-1);
                            System.out.println("Please selection an action:\n"
                            + "(1) Edit suggestion\n"
                            + "(2) Delete suggestion\n"
                            + "(3) Quit");                         
                            
                            editOrDeleteChoice = Integer.parseInt(sc.next());
                                                
                            if(editOrDeleteChoice > 3 || suggEditChoice<1){
                                throw new Exception("A valid selection was not made.");                    
                            }

                            switch (editOrDeleteChoice) {
                                case 1:
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
                                    break;
                                case 2:
                                    if (!suggChoice.processStatus()){
                                       authCampCommittee.deleteSuggestions(suggChoice);
                                        System.out.println("Suggestion successfully deleted.");
                                        delete = true;
                                        //delete logic
                                    }
                                    else{
                                        System.out.println("You cannot delete the suggestion after it has been processed!");
                                        break;
                                    }
                                    break;

                                default:
                                    break;
                                }
                        }while (editOrDeleteChoice<3 && delete == false);

                    }
                    break;

                case 4: //view and reply enquiries
                                   
                    EnquiryViewingAndReplying enquiryViewingAndReplying = new EnquiryViewingAndReplying(commCamp);
                    if (enquiryViewingAndReplying.enquiryUI(commCamp) == 1) {
                        authCampCommittee.addPoints();
                    }
                                    
                    break;

                case 5:     //Generate report of student list
                    System.out.println("\n=====REPORT GENERATOR=====");
                    CampCommitteeReportGenerator CampCommitteeReportGenerator = new CampCommitteeReportGenerator();
                    CampCommitteeReportGenerator.generateReport(commCamp);
                    break;
                
                case 6:
                    int campCommPoints = authCampCommittee.getTotalPoints();
                    System.out.println("Your total points are: " +  campCommPoints);

                default:
                    break;
            }
        }catch (NumberFormatException g){
            System.out.println("Invalid input.");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());                
        }               
    }while (campCommSelection<7);
}
    
}
