//Recourses used for code
//05/05/2023
// https://www.digitalocean.com/community/tutorials/java-read-file-line-by-line
// https://jenkov.com/tutorials/java/fields.html
// https://www.geeksforgeeks.org/system-out-println-in-java/
// https://www.geeksforgeeks.org/difference-between-print-and-println-in-java/

package solver;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.io.*;
import java.util.*;

public class Reader {
    List<string> _horn;
    // requires file name on creation of object
    public Reader(string file){   // on run extract
       _horn = extract(file);

    }
    //get list of values
    private List <string> extract(string file) {
        try {
            // create a list of string for each line in the file given its path
            List<string> query = Files.readAllLines(Path.get(file));
            return query;
        }
        //on error
        catch (IOExeption issue) {
            issue.printStackTrace();
        }
        return null;
    }

    public void string testField() {
        for(string position : _horn) {
            System.out.println(position);
        }
    }
}