import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a creator for a list of Staff users using the staffList.csv file.
 * @author Claire Chu Xinyi
 * @version 1.0
 * @since 20/11/2023
 */
public class StaffListCreator {
    
    /**
     * Creates a new list of Users by extracting data from the staffList.csv file.
     * Extracted staff data (userID and faculty) is used to create new Staff objects referenced as Users.
     * The staff members in the list will be accounts in the Camp Application System.
     * @return the list of Users.
     */
    public List<User> createStaffList(){
        List<User> staffList = new ArrayList<>();

        String staffListPath = "staffList.csv";
        Scanner staffFileScanner = null;

        try {
            staffFileScanner = new Scanner(new File(staffListPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (staffFileScanner != null){
            staffFileScanner.useDelimiter(",|\\r?\\n");       //demil is , and nextline

            while (staffFileScanner.hasNext()){
                
                String name = staffFileScanner.next();
                String email = staffFileScanner.next();                
                String faculty = staffFileScanner.next();               
                String[] splitEmail = email.split("@", 2);
                String userId = splitEmail[0];

                User user = new Staff(userId, faculty);
                staffList.add(user);
            }
            
        }
        staffFileScanner.close();
        return staffList;
    }

}
