import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class staffListCreator {
    
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
