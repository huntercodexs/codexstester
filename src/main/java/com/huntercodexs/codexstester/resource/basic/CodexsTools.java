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
