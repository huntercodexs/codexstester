package codexstester.abstractor.util;

import net.minidev.json.JSONObject;
import org.springframework.util.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CodexsHelperTests {

    public static String codexsHelperMd5(String data) {
        return DigestUtils.md5DigestAsHex(data.getBytes());
    }

    public static String codexsHelperGuideGenerator(String tcn) {
        if (tcn == null || tcn.equals("")) {
            return UUID.randomUUID().toString();
        }
        return tcn;
    }

    public static String codexsHelperToday() {
        Date now = new Date();
        return new SimpleDateFormat("yyy-MM-dd").format(now);
    }

    public static String codexsHelperOneYearAgo() {
        Date now = new Date();
        String today = new SimpleDateFormat("yyy-MM-dd").format(now);
        String[] getDate = today.split("-");
        int yearCurrent = Integer.parseInt(getDate[0]);
        return (yearCurrent-1)+"-"+getDate[1]+"-"+getDate[2];
    }

    public static String codexsHelperFiveYearAgo() {
        Date now = new Date();
        String today = new SimpleDateFormat("yyy-MM-dd").format(now);
        String[] getDate = today.split("-");
        int yearCurrent = Integer.parseInt(getDate[0]);
        return (yearCurrent-6)+"-"+getDate[1]+"-"+getDate[2];
    }

    public static void codexsHelperLogTerm(String title, Object data, boolean line) {
        System.out.println("\n"+title);
        if (data != null && !data.equals("")) {
            System.out.println(data);
        }
        if (line) {
            for (int i = 0; i < 120; i++) System.out.print("-");
        }
        System.out.println("\n");
    }

    public static void codexsHelperLogTermTests(String title, Object data, boolean line) {
        if (line) {
            for (int i = 0; i < 120; i++) System.out.print("-");
            System.out.print("\n");
        }
        if (data != null && !data.equals("")) {
            System.out.println(title + ": " + data);
        } else {
            System.out.println(title);
        }
    }

    public static JSONObject codexsHelperQueryStringToJson(String queryString) {

        String[] splitter = queryString.split("&");
        JSONObject jsonData = new JSONObject();

        for (String split : splitter) {
            String[] splitter2 = split.split("=");
            jsonData.appendField(splitter2[0].trim(), splitter2[1].trim());
        }

        return jsonData;

    }

    public static String codexsHelperJsonToString(JSONObject json) {
        return json.toJSONString();
    }

    public static JSONObject codexsHelperStringToJson(String string) {

        codexsHelperLogTerm("CODEXS TESTER STRING TO JSON", string, true);

        JSONObject jsonData = new JSONObject();
        String strClean = string.replaceAll("[\"{\\[\\]}'/\\\\]+", "");
        String[] splitter = strClean.split(",");

        if (!string.contains(":")) {

            for (String splitOne : splitter) {
                String[] splitter2 = splitOne.split("=");

                try {
                    jsonData.appendField(splitter2[0].trim(), splitter2[1].trim());
                } catch (RuntimeException re) {
                    codexsHelperLogTerm("EXCEPTION ON codexsTesterStringToJson", re.getMessage(), true);
                    jsonData.appendField(splitter2[0].trim(), "");
                }
            }

        } else {

            for (String splitOne : splitter) {
                String[] splitter2 = splitOne.split(":");

                try {
                    jsonData.appendField(splitter2[0].trim(), splitter2[1].trim());
                } catch (RuntimeException re) {
                    codexsHelperLogTerm("EXCEPTION ON codexsTesterStringToJson", re.getMessage(), true);
                    jsonData.appendField(splitter2[0].trim(), "");
                }
            }
        }

        return jsonData;
    }

}
