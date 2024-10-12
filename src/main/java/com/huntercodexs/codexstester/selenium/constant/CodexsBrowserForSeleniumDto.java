package com.huntercodexs.codexstester.selenium.constant;

public class CodexsBrowserForSeleniumDto {
    private String webDriverName;
    private String webDriverPath;
    private CodexsBrowserForSelenium browser;

    public CodexsBrowserForSeleniumDto() {
    }

    public CodexsBrowserForSeleniumDto(String webDriverName, String webDriverPath, CodexsBrowserForSelenium browser) {
        this.webDriverName = webDriverName;
        this.webDriverPath = webDriverPath;
        this.browser = browser;
    }

    public String getWebDriverName() {
        return webDriverName;
    }

    public void setWebDriverName(String webDriverName) {
        this.webDriverName = webDriverName;
    }

    public String getWebDriverPath() {
        return webDriverPath;
    }

    public void setWebDriverPath(String webDriverPath) {
        this.webDriverPath = webDriverPath;
    }

    public CodexsBrowserForSelenium getBrowser() {
        return browser;
    }

    public void setBrowser(CodexsBrowserForSelenium browser) {
        this.browser = browser;
    }

    @Override
    public String toString() {
        return "CodexsBrowserForSeleniumDto(" +
                "webDriverName=" + webDriverName +
                ", webDriverPath=" + webDriverPath +
                ", browser=" + browser +
                ")";
    }
}
