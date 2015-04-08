package sust.paperlessexm.Main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Sm19
 */
public class WriteNow {

    static void write(String str) {
        String text = str;
        
        try {
            File file = new File("C:\\Users\\Sm19\\Documents\\NetBeansProjects\\1.txt");
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            output.write(text);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
