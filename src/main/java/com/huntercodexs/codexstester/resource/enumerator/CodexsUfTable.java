package com.huntercodexs.codexstester.resource.enumerator;

public enum CodexsUfTable {
    AC(12, "Acre", "Acre", "SSPAC"),
    AL(27, "Alagoas", "Alagoas", "SSPAL"),
    AM(13, "Amazonas", "Amazonas", "SSPAM"),
    AP(16, "Amapá", "Amapa", "SSPAP"),
    BA(29, "Bahia", "Bahia", "SSPBA"),
    CE(23, "Ceará", "Ceara", "SSPCE"),
    DF(53, "Distrito Federal", "Distrito Federal", "SSPDF"),
    ES(32, "Espírito Santo", "Espirito Santo", "SSPES"),
    GO(52, "Goiás", "Goias", "SSPGO"),
    MA(21, "Maranhão", "Maranhao", "SSPMA"),
    MG(31, "Minas Gerais", "Minas Gerais", "SSPMG"),
    MS(50, "Mato Grosso do Sul", "Mato Grosso do Sul", "SSPMS"),
    MT(51, "Mato Grosso", "Mato Grosso", "SSPMT"),
    PA(15, "Pará", "Para", "SSPPA"),
    PB(25, "Paraíba", "Paraiba", "SSPPB"),
    PE(26, "Pernambuco", "Pernambuco", "SSPPE"),
    PI(22, "Piauí", "Piaui", "SSPPI"),
    PR(41, "Paraná", "Parana", "SSPPR"),
    RJ(33, "Rio de Janeiro", "Rio de Janeiro", "SSPRJ"),
    RN(24, "Rio Grande do Norte", "Rio Grande do Norte", "SSPRN"),
    RO(11, "Rondônia", "Rondonia", "SSPRO"),
    RR(14, "Roraima", "Roraima", "SSPRR"),
    RS(43, "Rio Grande do Sul", "Rio Grande do Sul", "SSPRS"),
    SC(42, "Santa Catarina", "Santa Catarina", "SSPSC"),
    SE(28, "Sergipe", "Sergipe", "SSPSE"),
    SP(35, "São Paulo", "Sao Paulo", "SSPSP"),
    TO(17, "Tocantins", "Tocantins", "SSPTO");

    private int ufCode;
    private String stateName;
    private String stateNameClear;
    private String rgsSsp;

    CodexsUfTable(int ufCode, String stateName, String stateNameClear, String rgsSsp) {
        this.ufCode = ufCode;
        this.stateName = stateName;
        this.stateNameClear = stateNameClear;
        this.rgsSsp = rgsSsp;
    }

    public int getUfCode() {
        return ufCode;
    }

    public void setUfCode(int ufCode) {
        this.ufCode = ufCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateNameClear() {
        return stateNameClear;
    }

    public void setStateNameClear(String stateNameClear) {
        this.stateNameClear = stateNameClear;
    }

    public String getRgsSsp() {
        return rgsSsp;
    }

    public void setRgsSsp(String rgsSsp) {
        this.rgsSsp = rgsSsp;
    }

    /**
     * <h1 style="color: #FFFF00; font-size: 11px">ufCode</h1>
     *
     * <p style="color: #CDCDCD">This method return the code from the federate unity according uf parameter</p>
     *
     * @param uf (String: the identification code of state - federate unity)
     * @return int (UF Code)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static int ufCode(String uf) {
        return CodexsUfTable.valueOf(uf).getUfCode();
    }

    /**
     * <h1 style="color: #FFFF00; font-size: 11px">stateName</h1>
     *
     * <p style="color: #CDCDCD">This method return the name of the federate unity according uf parameter</p>
     *
     * @param uf (String: the identification code of state - federate unity)
     * @return String (UF name)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String stateName(String uf) {
        return CodexsUfTable.valueOf(uf).getStateName();
    }

    /**
     * <h1 style="color: #FFFF00; font-size: 11px">stateNameClear</h1>
     *
     * <p style="color: #CDCDCD">This method return the name of the federate unity according uf parameter</p>
     *
     * @param uf (String: the identification code of state - federate unity)
     * @return String (UF name without points or special characters)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String stateNameClear(String uf) {
        return CodexsUfTable.valueOf(uf).getStateNameClear();
    }

    /**
     * <h1 style="color: #FFFF00; font-size: 11px">rgSspCode</h1>
     *
     * <p style="color: #CDCDCD">This method return the rg state of the federate unity according uf parameter</p>
     *
     * @param uf (String: the identification code of state - federate unity)
     * @return String (RG state name)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String rgSspCode(String uf) {
        return CodexsUfTable.valueOf(uf).getRgsSsp();
    }

    /**
     * <h1 style="color: #FFFF00; font-size: 11px">checkUfExists</h1>
     *
     * <p style="color: #CDCDCD">This method check if on federate unity exists</p>
     *
     * @param uf (String: the identification code of state - federate unity)
     * @return boolean
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static boolean checkUfExists(String uf) {
        for (CodexsUfTable value : CodexsUfTable.values()) {
            if (value.name().equals(uf)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <h1 style="color: #FFFF00; font-size: 11px">checkUfCodeExists</h1>
     *
     * <p style="color: #CDCDCD">This method check if exists the uf code</p>
     *
     * @param code (String)
     * @return boolean
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static boolean checkUfCodeExists(String code) {
        for (CodexsUfTable value : CodexsUfTable.values()) {
            if (String.valueOf(value.getUfCode()).equals(code)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <h1 style="color: #FFFF00; font-size: 11px">checkUfNameExists</h1>
     *
     * <p style="color: #CDCDCD">This method check if exists the uf name</p>
     *
     * @param ufName (String)
     * @return boolean
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static boolean checkUfNameExists(String ufName) {
        for (CodexsUfTable value : CodexsUfTable.values()) {
            if (value.getStateName().equals(ufName) || value.getStateNameClear().equals(ufName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <h1 style="color: #FFFF00; font-size: 11px">checkRgSspExists</h1>
     *
     * <p style="color: #CDCDCD">This method check if exists the rg ssp in the list of states</p>
     *
     * @param rgsSsp (String)
     * @return boolean
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static boolean checkRgSspExists(String rgsSsp) {
        for (CodexsUfTable value : CodexsUfTable.values()) {
            if (value.getRgsSsp().equals(rgsSsp)) {
                return true;
            }
        }
        return false;
    }
}
