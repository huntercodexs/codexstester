package com.huntercodexs.codexstester.constant;

public enum CodexsConstant {

    CODEXS_GUID("^([0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12})$"),

    CODEXS_JWT_HS256("^([0-9a-zA-Z]{20}|[0-9a-zA-Z]{36})\\.([0-9a-zA-Z]{74}|[0-9a-zA-Z]{90}|[0-9a-zA-Z]{127})\\.([0-9a-zA-Z_-]{43})$"),
    CODEXS_JWT_HS384("^([0-9a-zA-Z]{20}|[0-9a-zA-Z]{36})\\.([0-9a-zA-Z]{90,91}|[0-9a-zA-Z]{127})\\.([0-9a-zA-Z-_]{64})$"),
    CODEXS_JWT_HS512("^([0-9a-zA-Z]{20,36})\\.([0-9a-zA-Z]{90,91}|[0-9a-zA-Z]{127})\\.([0-9a-zA-Z_-]{86})$"),

    CODEXS_JWT_RS256("^([0-9a-zA-Z]{36})\\.([0-9a-zA-Z]{91}|[0-9a-zA-Z]{150})\\.([0-9a-zA-Z_-]{342})$"),
    CODEXS_JWT_RS384("^([0-9a-zA-Z]{36})\\.([0-9a-zA-Z]{91}|[0-9a-zA-Z]{150})\\.([0-9a-zA-Z_-]{342})$"),
    CODEXS_JWT_RS512("^([0-9a-zA-Z]{36})\\.([0-9a-zA-Z]{91}|[0-9a-zA-Z]{150})\\.([0-9a-zA-Z_-]{342})$"),

    CODEXS_JWT_ES256("^([0-9a-zA-Z]{36})\\.([0-9a-zA-Z]{91}|[0-9a-zA-Z]{150})\\.([0-9a-zA-Z_-]{86}|[0-9a-zA-Z_-]{342})$"),
    CODEXS_JWT_ES384("^([0-9a-zA-Z]{36})\\.([0-9a-zA-Z]{91}|[0-9a-zA-Z]{150})\\.([0-9a-zA-Z_-]{128}|[0-9a-zA-Z_-]{342})$"),
    CODEXS_JWT_ES512("^([0-9a-zA-Z]{36})\\.([0-9a-zA-Z]{91}|[0-9a-zA-Z]{150})\\.([0-9a-zA-Z_-]{176}|[0-9a-zA-Z_-]{342})$"),

    CODEXS_JWT_PS256("^([0-9a-zA-Z]{36}\\.[0-9a-zA-Z]{91}\\.[0-9a-zA-Z_-]{342})$"),
    CODEXS_JWT_PS384("^([0-9a-zA-Z]{36}\\.[0-9a-zA-Z]{91}\\.[0-9a-zA-Z_-]{342})$"),
    CODEXS_JWT_PS512("^([0-9a-zA-Z]{36}\\.[0-9a-zA-Z]{91}\\.[0-9a-zA-Z_-]{342})$");

    private final String value;

    CodexsConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String getConstant(CodexsConstant codexsConstant) {
        for (CodexsConstant constant : CodexsConstant.values()) {
            if (constant.name().equals(codexsConstant.name())) {
                return constant.getValue();
            }
        }
        throw new RuntimeException("[Critical Error] Constant not found: " + codexsConstant);
    }
}
