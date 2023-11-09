public class User {
    private String userID;
    private String faculty;
    //default password is passwordss
    private String password = "password";

    public User(String userID, String faculty) {
        this.userID = userID;
        this.faculty = faculty;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
}
