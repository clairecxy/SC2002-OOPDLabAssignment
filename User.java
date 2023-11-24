/**
 * Represents a user in the system
 * A user can be a staff or student
 * @author Tan Hui Ling
 * @version 1.3
 */
public class User {
    /**
     * The user ID of a User
     */
    private String userID;

    /**
     * The faculty this User belongs to
     */
    private String faculty;
    
    /**
     * The password of this user. Default value is "password".
     */
    private String password = "password";

    /** 
     * Creates a new User with the respective user ID and faculty
     * @param userID The user's ID
     * @param faculty The user's faculty
    */
    public User(String userID, String faculty) {
        this.userID = userID;
        this.faculty = faculty;
    }

    /**
     * Gets the user ID of this user
     * @return this user's ID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Edits the user's ID
     * @param userID this user's new ID
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * Gets the user's faculty
     * @return this user's faculty
     */
    public String getFaculty() {
        return faculty;
    }

    /**
     * Changes the faculty of this user
     * @param faculty this user's new faculty
     */
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    /**
     * Gets the current password of this user
     * @return this user's current password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Changes the password of this user
     * @param newPassword the new password of this user
     */
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
}
