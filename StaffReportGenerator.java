import java.util.List;
import java.util.Scanner;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
            +"(4) Camp Committee Performance Report\r\n"
            +"(5) Camp General Report\r\n"
            +"(6) Exit");
            reportChoice = sc.nextInt();

            switch (reportChoice) {
                case 1:
                    writeReportToFile(authStaff.getGeneralReportAttendees(reportingCamp), "attendees_report.txt");
                    break;

                case 2:
                    writeReportToFile(authStaff.getGeneralReportCampCommittee(reportingCamp), "committee_report.txt");
                    break;

                case 3:
                    writeReportToFile(authStaff.getGeneralReportDetails(reportingCamp), "camp_details_report.txt");
                    break;
            
                case 4:
                    writeReportToFile(authStaff.getPerformanceReport(reportingCamp), "performance_report.txt");
                    break;

                case 5:
                    writeReportToFile(authStaff.getGeneralReport(reportingCamp), "performance_report.txt");


                default:
                    break;
            }
            
        }while(reportChoice<6);
        


    }

    private void writeReportToFile(String report, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
            System.out.println("Report written to " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing report to file: " + e.getMessage());
        }
    }
}
