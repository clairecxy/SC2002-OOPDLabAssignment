public interface ReportGenerator {
    public void generateReport(Camp reportingCamp);
    // void writeReportToFile(String report, String fileName);
    public String getGeneralReportDetails(Camp camp);
    public String getGeneralReportAttendees(Camp camp);
    public String getGeneralReportCampCommittee(Camp camp);
    public String getGeneralReport(Camp camp);
    // String getPerformanceReport(Camp camp);
}