package sust.paperlessexm.tool;

import java.util.Date;

/**
 *
 * @author Sm19
 */
public class DateCheck {

    public static String dateStatus(Date a, Date b, int len) {
        long now;
        now = a.getTime();
        long L = b.getTime();
        long R = b.getTime() + len * 1000 * 60;

        String status = "Running";
        if (now < L) {
            status = "Scheduled";
        } else if (now > R) {
            status = "Finished";
        }
        return status;
    }
}
