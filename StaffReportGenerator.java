import java.util.List;
import java.util.Scanner;

public class StaffReportGenerator {
    public void staffReportGenerator(int editCampIndex, List<Camp> createdCamps, List<Camp> allCamps, Staff authStaff){
        Scanner sc = new Scanner(System.in);        //don't close this!
        sc.useDelimiter("\n");

        Camp reportingCamp = createdCamps.get(editCampIndex-1);      //get selected camp from list of camps staff created
        int reportChoice;

        do{
            System.out.println("What kind of report would you like to print?\r\n"
            +"(1) All attendees\r\n"
            +"(2) Camp Committee\r\n"
            +"(3) Camp Details\r\n"
            +"(4) Full General Camp Report\r\n"
            +"(5) Camp Committee Performance Report\r\n"
            +"(6) Exit");
            reportChoice = sc.nextInt();

            switch (reportChoice) {
                case 1:
                    authStaff.printGeneralReportAttendees(reportingCamp);
                    break;

                case 2:
                    authStaff.printGeneralReportCampCommittee(reportingCamp);
                    break;

                case 3:
                    authStaff.printGeneralReportDetails(reportingCamp);
                    break;

                case 4:
                    authStaff.printGeneralReportDetails(reportingCamp);
                    authStaff.printGeneralReportCampCommittee(reportingCamp);
                    authStaff.printGeneralReportAttendees(reportingCamp);
                    break;
            
                case 5:
                    authStaff.printPerformanceReport(reportingCamp);
                    break;

                default:
                    break;
            }
            
        }while(reportChoice<6);
        


    }
}
