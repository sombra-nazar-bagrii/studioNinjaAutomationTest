package ComfigurationClasses;

import java.time.Month;
import java.util.Random;

/**
 * Created by sombramac-19 on 3/26/18.
 */
public final class Utility {
    private Utility() {}

    public static String getRandDate(){
        Random rand = new Random();
        Integer i = rand.nextInt(28) +1;
        return i.toString();
    }

    public static String getRandNumber(){
        Random rand = new Random();
        Integer i = rand.nextInt(98) +1;
        return i.toString();
    }

    public static int getMonthNumber (String monthName){
        return Month.valueOf(monthName.toUpperCase()).getValue() -1;
    }
}
