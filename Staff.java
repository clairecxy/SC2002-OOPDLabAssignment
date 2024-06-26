import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Staff within the system.
 * @author Aryan Garg
 * @version 1.0
 * @since 20/11/2023
 */
public class Staff extends User {
    
    /**
     * A list of Camps created by this Staff.
     */
    private List<Camp> createdCampsList;

    /** 
     * Creates a new Staff with the respective user ID and faculty.
     * The user ID and faculty is inherited from the User class.
     * @param userID The user's ID.
     * @param faculty The user's faculty.
    */
    public Staff(String userID, String faculty) {
        super(userID, faculty);
        this.createdCampsList = new ArrayList<>();
    }

    /**
     * A Camp created by the Staff with the given attributes.
     * @param name The Camp's name.
     * @param start The Camp's starting date.
     * @param end The Camp's ending date.
     * @param endReg The Camp's registration deadline.
     * @param users The User groups the Camp is open to.
     * @param locationStr The Camp's location.
     * @param slots The number of attendee slots the Camp has.
     * @param commSlots The number of Camp Committee slots the Camp has.
     * @param desc The Camp's description.
     * @param staffIC This Staff.
     * @return The created Camp.
     */
    public Camp createCamp(String name, String start, String end, String endReg, String users, String locationStr, 
    int slots, int commSlots, String desc, Staff staffIC) {
        Camp camp = new Camp(name, start, end, endReg, users, locationStr, slots, commSlots, desc, staffIC); // Create a new Camp object (assuming a default constructor exists)
        this.createdCampsList.add(camp); // Add the new Camp to the createdCampsList
        return camp;
    }
    
    /**
     * Deletes the Camp created by this Staff from the system.
     * Removes the Camp from this Staff's list of created Camps.
     * @param camp The Camp to be deleted.
     */
    public void deleteCamp(Camp camp) {
        this.createdCampsList.remove(camp);
    }

    /**
     * Gets the list of Camps created by this Staff.
     * @return the list of Camps created by this Staff.
     */
    public List<Camp> viewAllCreatedCamps(){
        return this.createdCampsList;
    }   
    
}