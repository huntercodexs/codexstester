package com.huntercodexs.codexstester.selenium.constant;

import java.util.List;

public class CodexsBrowserForSeleniumDto {
    private String webDriverName;
    private String webDriverPath;
    private CodexsBrowserForSelenium browser;
    private List<String> options;

    public CodexsBrowserForSeleniumDto() {
    }

    public CodexsBrowserForSeleniumDto(
            String webDriverName,
            String webDriverPath,
            CodexsBrowserForSelenium browser,
            List<String> options
    ) {
        this.webDriverName = webDriverName;
        this.webDriverPath = webDriverPath;
        this.browser = browser;
        this.options = options;
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

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "CodexsBrowserForSeleniumDto(" +
                "webDriverName=" + webDriverName +
                ", webDriverPath=" + webDriverPath +
                ", browser=" + browser +
                ", options=" + options +
                ")";
    }
}
