package com.huntercodexs.codexstester.resource.web.constant;

import java.util.List;

public class CodexsBrowserForSeleniumDto {
    private boolean quietMode;
    private String webDriverName;
    private String webDriverPath;
    private String browserBinaryPath;
    private CodexsBrowserForSelenium browser;
    private List<String> options;

    public CodexsBrowserForSeleniumDto() {
    }

    public CodexsBrowserForSeleniumDto(
            boolean quietMode,
            String webDriverName,
            String webDriverPath,
            String browserBinaryPath,
            CodexsBrowserForSelenium browser,
            List<String> options
    ) {
        this.quietMode = quietMode;
        this.webDriverName = webDriverName;
        this.webDriverPath = webDriverPath;
        this.browserBinaryPath = browserBinaryPath;
        this.browser = browser;
        this.options = options;
    }

    public boolean isQuietMode() {
        return quietMode;
    }

    public void setQuietMode(boolean quietMode) {
        this.quietMode = quietMode;
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

    public String getBrowserBinaryPath() {
        return browserBinaryPath;
    }

    public void setBrowserBinaryPath(String browserBinaryPath) {
        this.browserBinaryPath = browserBinaryPath;
    }

    public CodexsBrowserForSelenium getBrowser() {
        return browser;
    }

    public void setBrowser(CodexsBrowserForSelenium browser) {
        this.browser = browser;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "CodexsBrowserForSeleniumDto(" +
                "quietMode=" + quietMode +
                ", webDriverName=" + webDriverName +
                ", webDriverPath=" + webDriverPath +
                ", browserBinaryPath=" + browserBinaryPath +
                ", browser=" + browser +
                ", options=" + options +
                ")";
    }
}
