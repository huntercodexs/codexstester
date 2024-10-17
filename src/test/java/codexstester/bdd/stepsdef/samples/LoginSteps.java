package codexstester.bdd.stepsdef.samples;

import com.huntercodexs.codexstester.resource.web.CodexsWebControl;
import com.huntercodexs.codexstester.resource.web.constant.CodexsBrowserForSelenium;
import com.huntercodexs.codexstester.resource.web.constant.CodexsBrowserForSeleniumDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Collections;

import static com.huntercodexs.codexstester.resource.web.CodexsWebElements.*;
import static com.huntercodexs.codexstester.util.CodexsAssertion.codexsTesterAssertBool;
import static com.huntercodexs.codexstester.util.CodexsAssertion.codexsTesterAssertExact;

public class LoginSteps {

    CodexsWebControl codexsWebControl;
    CodexsBrowserForSeleniumDto codexsBrowserDto;

    public void browserSetup(String browser) {
        this.codexsBrowserDto = new CodexsBrowserForSeleniumDto();

        /* > CHROME */
        if (browser.equals("CHROME")) {
            this.codexsBrowserDto.setBrowser(CodexsBrowserForSelenium.CHROME);
            this.codexsBrowserDto.setQuietMode(false);
            this.codexsBrowserDto.setOptions(Collections.singletonList("--remote-allow-origins=*"));
            this.codexsBrowserDto.setWebDriverName("webdriver.chrome.driver");
            this.codexsBrowserDto.setWebDriverPath("/usr/bin/chromedriver");
        }

        /* > FIREFOX
         * NOTE: It is required to install the gecko driver for firefox
         * https://github.com/mozilla/geckodriver/releases
         */
        else if (browser.equals("FIREFOX")) {
            this.codexsBrowserDto.setBrowser(CodexsBrowserForSelenium.FIREFOX);
            this.codexsBrowserDto.setQuietMode(false);
            this.codexsBrowserDto.setWebDriverName("webdriver.gecko.driver");
            this.codexsBrowserDto.setWebDriverPath("/home/jereelton/.mozilla/webdriver/geckodriver");
        }

        /* > OPERA
         * NOTE: It is required to install the operadriver
         * https://github.com/operasoftware/operachromiumdriver/releases
         */
        else if (browser.equals("OPERA")) {
            this.codexsBrowserDto.setBrowser(CodexsBrowserForSelenium.OPERA);
            this.codexsBrowserDto.setQuietMode(false);
            this.codexsBrowserDto.setOptions(Collections.singletonList("--remote-allow-origins=*"));
            this.codexsBrowserDto.setWebDriverName("webdriver.opera.driver");
            this.codexsBrowserDto.setWebDriverPath("/home/jereelton/.local/bin/operalinux/operadriver");
            this.codexsBrowserDto.setBrowserBinaryPath("/usr/bin/opera");
        }

        this.codexsWebControl = new CodexsWebControl(this.codexsBrowserDto);
        this.codexsWebControl.browserSetup();
    }

    @Given("user is on login page using the browser {string}")
    public void userIsOnLoginPage(String browser) {
        this.browserSetup(browser);
        this.codexsWebControl.navigate("https://practicetestautomation.com/practice-test-login/");
    }

    @When("user login with {string} and {string}")
    public void userLoginWithUsernameAndPassword(String username, String password) {

        WebElement usernameField = this.codexsWebControl.await().until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(inputElement("username"))));
        usernameField.sendKeys(username);

        WebElement passwordField = this.codexsWebControl.await().until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(inputElement("password"))));
        passwordField.sendKeys(password);

        WebElement submitButton = this.codexsWebControl.await().until(
                ExpectedConditions.elementToBeClickable(By.xpath(buttonElement("submit"))));
        submitButton.click();

    }

    @Then("login status should be {}")
    public void loginSuccessfully(boolean status) {

        if (status) {

            // When login is successfully the button logout is visible
            WebElement logoutButton = this.codexsWebControl.await().until(
                    ExpectedConditions.elementToBeClickable(By.xpath(aElement("Log out"))));

            codexsTesterAssertBool(status, logoutButton.isDisplayed(), null);

        } else {

            // When login is wrong the p=text with id=error is visible
            WebElement loginError = this.codexsWebControl.await().until(
                    ExpectedConditions.elementToBeClickable(By.xpath(divElement("error"))));

            codexsTesterAssertBool(true, loginError.isDisplayed(), null);

            if (loginError.getText().contains("username")) {
                codexsTesterAssertExact("Your username is invalid!", loginError.getText(), null);
            } else {
                codexsTesterAssertExact("Your password is invalid!", loginError.getText(), null);
            }

        }

        this.codexsWebControl.finish();

    }

}
