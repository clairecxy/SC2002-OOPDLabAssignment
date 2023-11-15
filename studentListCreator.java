import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class studentListCreator {

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

                User user = new Student(userId, faculty);
                studentList.add(user);
            }
            
        }
        studentFileScanner.close();
        return studentList;
    }
   
}
