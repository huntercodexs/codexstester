package codexstester.abstractor.util;

import org.springframework.util.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UtilTests {

    public static String md5(String data){
        return DigestUtils.md5DigestAsHex(data.getBytes());
    }

    public static String guideGenerator(String tcn) {
        if (tcn == null || tcn.equals("")) {
            return UUID.randomUUID().toString();
        }
        return tcn;
    }

    public static String today() {
        Date now = new Date();
        return new SimpleDateFormat("yyy-MM-dd").format(now);
    }

    public static String oneYearAgo() {
        Date now = new Date();
        String today = new SimpleDateFormat("yyy-MM-dd").format(now);
        String[] getDate = today.split("-");
        int yearCurrent = Integer.parseInt(getDate[0]);
        return (yearCurrent-1)+"-"+getDate[1]+"-"+getDate[2];
    }

    public static String fiveYearAgo() {
        Date now = new Date();
        String today = new SimpleDateFormat("yyy-MM-dd").format(now);
        String[] getDate = today.split("-");
        int yearCurrent = Integer.parseInt(getDate[0]);
        return (yearCurrent-6)+"-"+getDate[1]+"-"+getDate[2];
    }

    public static void logTerm(String title, Object data, boolean line) {
        System.out.println("\n");
        System.out.println(title);
        if (data != null && !data.equals("")) {
            System.out.println(data);
        }
        if (line) {
            for (int i = 0; i < 120; i++) System.out.print("-");
        }
    }

}
