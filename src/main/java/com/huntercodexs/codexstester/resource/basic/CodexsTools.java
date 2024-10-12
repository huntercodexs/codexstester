package com.huntercodexs.codexstester.resource.basic;

import com.huntercodexs.codexstester.resource.enumerator.CodexsDataMask;
import com.huntercodexs.codexstester.resource.enumerator.CodexsTraceType;
import org.springframework.util.DigestUtils;

import java.util.Base64;
import java.util.UUID;

import static com.huntercodexs.codexstester.resource.enumerator.CodexsDataMask.dataMasked;

public class CodexsTools {

    public static String md5(String data){
        return DigestUtils.md5DigestAsHex(data.getBytes());
    }

    public static String guide(String tcn) {
        if (tcn == null || tcn.equals("")) {
            return UUID.randomUUID().toString();
        }
        return tcn;
    }

    public static String base64(String input) {
        byte[] inputBytes = input.getBytes();
        byte[] base64InputBytes = Base64.getEncoder().encode(inputBytes);
        return new String(base64InputBytes);
    }

    /**
     *
     * <h6 style="color: #FFFF00; font-size: 11px">trace</h6>
     *
     * <p style="color: #CDCDCD">This method is used to make a total trace and tracking in the idp
     * transaction flow where the result can be applied to information or error log level</p>
     *
     * @param track (String: Tracking log)
     * @param id (String: User or resource identifier)
     * @param message (String: Message to trace in log)
     * @param label (TraceType: label to stick the log detail and give an emphasis in the target message)
     * @param type (String: Type of log [info, error])
     * @return trace (String - trace detail)
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String trace(String track, String id, String message, CodexsTraceType label, String type) {

        if (track == null || track.isEmpty()) track = "........-....-....-....-............";
        if (label == null) label = CodexsTraceType.UNKNOWN;
        if (type == null) type = "";

        String idMasked = dataMasked(id, "*", CodexsDataMask.GENERIC_MASK);
        String trace = "TCN ["+track+"] ["+idMasked+"] ["+label.name().toUpperCase()+"]";

        if (type.equals("error")) {
            System.out.println(trace + " " + message);
        } else {
            System.out.println(trace + " " + message);
        }
        return trace;
    }

}
