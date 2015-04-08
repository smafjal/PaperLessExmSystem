package sust.paperlessexm.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Sm19
 */
public class DateTools {

    public static Date toDate(String day,String hour) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String dateInString = day+" "+hour+":00";
        System.out.println("dateS: "+dateInString);
        Date date = formatter.parse(dateInString);
        return date;
    }

    public static void main(String args[]) throws ParseException {
        String str = "12-12-2015";
        System.out.println("Date: " + toDate(str,"02:29"));
    }
}
