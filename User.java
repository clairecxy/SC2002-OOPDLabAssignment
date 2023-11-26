/**
 * Represents a user in the system.
 * A user can be a staff or student.
 * @author Tan Hui Ling
 * @version 1.0
 * @since 20/11/2023
 */
public class User {
    /**
     * The user ID of a User.
     */
    private String userID;

    /**
     * The faculty this User belongs to.
     */
    private String faculty;
    
    /**
     * The password of this User. Default value is "password".
     */
    private String password = "password";

    /** 
     * Creates a new User with the given user ID and faculty.
     * @param userID The User's ID.
     * @param faculty The User's faculty.
    */
    public User(String userID, String faculty) {
        this.userID = userID;
        this.faculty = faculty;
    }

    /**
     * Gets the user ID of this User.
     * @return this User's ID.
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Changes the User's ID.
     * @param userID this User's new ID.
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * Gets the User's faculty.
     * @return this User's faculty.
     */
    public String getFaculty() {
        return faculty;
    }

    /**
     * Changes the faculty of this User.
     * @param faculty this User's new faculty.
     */
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    /**
     * Gets the current password of this User.
     * @return this User's current password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Changes the password of this User.
     * @param newPassword the new password of this User.
     */
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
}
