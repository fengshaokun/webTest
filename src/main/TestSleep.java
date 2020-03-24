import javax.swing.text.html.parser.Entity;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class TestSleep {


    public static void main(String[] args) throws InterruptedException {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String createdate = sdf.format(date);
        System.out.println(createdate);
        System.out.println(equalsMonth("2020-4-10 08:00:00"));
        if (equalsMonth("2020-4-10 08:00:00")){
            System.out.println(equalsMonth("2020-4-10 08:00:00"));
        }


    }

    public static boolean equalsMonth(String time) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String createdate = sdf.format(date);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = null;
        try {
            parse = sdf2.parse(time);
            return createdate.equals(sdf.format(parse));
        } catch (Exception e) {
            return false;
        }
    }
}
