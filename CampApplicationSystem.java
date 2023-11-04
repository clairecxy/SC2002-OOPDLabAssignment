import java.util.ArrayList;
import java.util.List;

public class CampApplicationSystem {
    private List<User> allUsers;
    private List<Camp> allCamps;

    public CampApplicationSystem() {
        allUsers = new ArrayList<>();
        allCamps = new ArrayList<>();
    }

    //checks if a User class in allUsers have matching userId and password. If yes retun true, if no return false.
    public boolean loginUser(String userId, String password) {
        for (User user : allUsers) {
            if (user.getUserID().equals(userId) && user.getPassword().equals(password)) {
                System.out.println("Login successful for user: " + userId);
                return true;
            }
        }
        return false;
    }

    public List<User> getAllUser() {
        return allUsers;
    }

    public List<Camp> getAllCamp() {
        return allCamps;
    }

    public void setAllUser(User user) {
        allUsers.add(user);
    }

    public void setAllCamp(Camp camp) {
        allCamps.add(camp);
    }
}
