package com.huntercodexs.codexstester.resource.format;

import static com.huntercodexs.codexstester.resource.basic.CodexsStringHandler.repeat;

public class CodexsMask {

    /**
     * <p style="color: #FFFF00; font-size: 11px; weight: bold">cardNumberMasked</p>
     *
     * <p style="color: #CDCDCD">Mask a card number with a specific mask</p>
     *
     * @param cardNumber (String)
     * @param mask (String)
     * @return String (Masked Card Number)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String cardNumberMasked(String cardNumber, String mask) {
        if (mask.isEmpty()) mask = "*";
        mask = repeat(mask, 4);
        String regexCard = "([0-9]{4})([- ]?)([0-9]{4})([- ]?)([0-9]{4})([- ]?)([0-9]{4})";
        return cardNumber.replaceAll(regexCard, "$1$2"+mask+"$4"+mask+"$6$7");
    }

}
