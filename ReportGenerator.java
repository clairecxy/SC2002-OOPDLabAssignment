/**
 * Represents a Report Generator Interface in the system.
 * @author Tan Hui Ling
 * @version 1.0
 * @since 20/11/2023
 */
public interface ReportGenerator {
    
    /**
     * Generates a report for the given Camp.
     * @param reportingCamp The Camp this Report Generator is generating the report for.
     */
    public void generateReport(Camp reportingCamp);

    /**
     * Generates a report containing the Camp's details for the given Camp.
     * @param camp The Camp this Report Generator is generating the report for.
     * @return the String containing the report generated.
     */
    public String getGeneralReportDetails(Camp camp);

    /**
     * Generates a report containing the Camp's attendee details for the given Camp.
     * @param camp The Camp this Report Generator is generating the report for.
     * @return the String containing the report generated.
     */
    public String getGeneralReportAttendees(Camp camp);

    /**
     * Generates a report containing the Camp's Committee member's details for the given Camp.
     * @param camp The Camp this Report Generator is generating the report for.
     * @return the String containing the report generated.
     */
    public String getGeneralReportCampCommittee(Camp camp);

    /**
     * Generates a full report containing all the Camp's information for the given Camp.
     * @param camp The Camp this Report Generator is generating the report for.
     * @return the String containing the report generated.
     */
    public String getGeneralReport(Camp camp);
}