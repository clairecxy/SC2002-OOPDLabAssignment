public interface ReportGenerator {
    void generateReport(Camp reportingCamp);
    // void writeReportToFile(String report, String fileName);
    String getGeneralReportDetails(Camp camp);
    String getGeneralReportAttendees(Camp camp);
    String getGeneralReportCampCommittee(Camp camp);
    String getGeneralReport(Camp camp);
    // String getPerformanceReport(Camp camp);
}