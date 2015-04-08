package sust.paperlessexm.tool;

/**
 *
 * @author Sm19
 */
public class Check {

    public static boolean isInt(String str) {
        for(int i=0;i<str.length();i++) {
            if(!(str.charAt(i)>='0' && str.charAt(i)<='9')) return false;
        }
        return true;
    }
}
