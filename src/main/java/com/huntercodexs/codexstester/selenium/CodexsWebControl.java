package com.huntercodexs.codexstester.selenium;

import com.huntercodexs.codexstester.selenium.constant.CodexsBrowserForSelenium;
import com.huntercodexs.codexstester.selenium.constant.CodexsBrowserForSeleniumDto;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CodexsWebControl {

    private final CodexsWebDriver codexsWebDriver;
    private final CodexsBrowserForSeleniumDto codexsBrowserDto;

    public CodexsWebControl(CodexsBrowserForSeleniumDto codexsBrowserForSeleniumDto) {
        this.codexsWebDriver = new CodexsWebDriver();
        this.codexsBrowserDto = codexsBrowserForSeleniumDto;
    }

    private void chromeSetup() {
        ChromeOptions options = new ChromeOptions();

        if (this.codexsBrowserDto.getOptions() != null) {
            options.addArguments(this.codexsBrowserDto.getOptions());
        }

        if (this.codexsBrowserDto.isQuietMode()) {
            options.addArguments("-headless");
        }

        this.codexsWebDriver.setDriver(new ChromeDriver(options));
    }

    private void firefoxSetup() {
        FirefoxOptions options = new FirefoxOptions();

        if (this.codexsBrowserDto.getOptions() != null) {
            options.addArguments(this.codexsBrowserDto.getOptions());
        }

        if (this.codexsBrowserDto.isQuietMode()) {
            options.addArguments("-headless");
        }

        this.codexsWebDriver.setDriver(new FirefoxDriver(options));
    }

    private void operaSetup() {
        OperaOptions options = new OperaOptions();
        options.addArguments(this.codexsBrowserDto.getOptions());
        this.codexsWebDriver.setDriver(new OperaDriver(options));
    }

    private void netscapeSetup() {
        this.firefoxSetup();
    }

    private void safariSetup() {
        SafariOptions options = new SafariOptions();
        this.codexsWebDriver.setDriver(new SafariDriver(options));
    }

    private void ieSetup() {
        InternetExplorerOptions options = new InternetExplorerOptions();
        this.codexsWebDriver.setDriver(new InternetExplorerDriver(options));
    }

    private void edgeSetup() {
        EdgeOptions options = new EdgeOptions();
        this.codexsWebDriver.setDriver(new EdgeDriver(options));
    }

    public void browserSetup() {

        System.setProperty(this.codexsBrowserDto.getWebDriverName(), this.codexsBrowserDto.getWebDriverPath());

        if (this.codexsBrowserDto.getBrowser().name().equals(CodexsBrowserForSelenium.IE.name())) {
            this.ieSetup();
        } else if (this.codexsBrowserDto.getBrowser().name().equals(CodexsBrowserForSelenium.EDGE.name())) {
            this.edgeSetup();
        } else if (this.codexsBrowserDto.getBrowser().name().equals(CodexsBrowserForSelenium.CHROME.name())) {
            this.chromeSetup();
        } else if (this.codexsBrowserDto.getBrowser().name().equals(CodexsBrowserForSelenium.FIREFOX.name())) {
            this.firefoxSetup();
        } else if (this.codexsBrowserDto.getBrowser().name().equals(CodexsBrowserForSelenium.NETSCAPE.name())) {
            this.netscapeSetup();
        } else if (this.codexsBrowserDto.getBrowser().name().equals(CodexsBrowserForSelenium.OPERA.name())) {
            this.operaSetup();
        } else if (this.codexsBrowserDto.getBrowser().name().equals(CodexsBrowserForSelenium.SAFARI.name())) {
            this.safariSetup();
        }

        this.codexsWebDriver.getDriver().manage().window().maximize();
        this.codexsWebDriver.setWait(new WebDriverWait(this.codexsWebDriver.getDriver(), 15L));

    }

    public WebDriver driver() {
        return this.codexsWebDriver.getDriver();
    }

    public WebDriverWait await() {
        return this.codexsWebDriver.getWait();
    }

    public void navigate(String url) {
        if (this.codexsBrowserDto.getBrowser().name().equals(CodexsBrowserForSelenium.FIREFOX.name())) {
            this.codexsWebDriver.getDriver().navigate().to(url);
        } else {
            this.codexsWebDriver.getDriver().get(url);
        }
    }

    public void finish() {
        try {
            // Force login page alive for 5 seconds (just for visualize the login successfully)
            Thread.sleep(2000);
            this.codexsWebDriver.getDriver().quit();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
