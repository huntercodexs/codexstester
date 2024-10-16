package com.huntercodexs.codexstester.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CodexsWebDriver {

    private WebDriver driver;
    private WebDriverWait wait;

    public CodexsWebDriver() {
    }

    public CodexsWebDriver(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public void setWait(WebDriverWait wait) {
        this.wait = wait;
    }

    @Override
    public String toString() {
        return "CodexsWebDriver(" +
                "driver=" + driver +
                ", wait=" + wait +
                ")";
    }
}
