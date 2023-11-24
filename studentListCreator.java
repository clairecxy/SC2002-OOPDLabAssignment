import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a creator for a list of Students users using the studentList.csv file.
 * @author Claire Chu Xinyi
 * @version 1.0
 * @since 20/11/2023
 */
public class StudentListCreator {

    /**
     * Creates a new list of Users by extracting data from the studentList.csv file.
     * Extracted student data (userID and faculty) is used to create new CampCommittee objects referenced as Users.
     * CampCommittee extends Student.
     * The students/ camp committee members in the list will be accounts in the Camp Application System.
     * @return the list of Users.
     */
    public List<User> createStudentList(){
        List<User> studentList = new ArrayList<>();

        String studentListPath = "studentList.csv";
        Scanner studentFileScanner = null;

        try {
            studentFileScanner = new Scanner(new File(studentListPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (studentFileScanner != null){
            studentFileScanner.useDelimiter(",|\\r?\\n");       //demil is , and nextline

            while (studentFileScanner.hasNext()){
                
                String name = studentFileScanner.next();
                String email = studentFileScanner.next();                
                String faculty = studentFileScanner.next();               
                String[] splitEmail = email.split("@", 2);
                String userId = splitEmail[0];
                Camp camp = null;
                User user = new CampCommittee(userId, faculty, camp);

                studentList.add(user);
            }
            
        }
        studentFileScanner.close();
        return studentList;
    }
   
}
