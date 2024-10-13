package codexstester.bdd.stepsdef;

import codexstester.setup.bridge.SampleBridgeTest;
import com.huntercodexs.codexstester.selenium.CodexsWebControl;
import com.huntercodexs.codexstester.selenium.constant.CodexsBrowserForSelenium;
import com.huntercodexs.codexstester.selenium.constant.CodexsBrowserForSeleniumDto;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static com.huntercodexs.codexstester.selenium.CodexsWebElements.*;

public class Sample3StepsDefTest extends SampleBridgeTest {

    CodexsWebControl codexsWebControl;
    CodexsBrowserForSeleniumDto codexsBrowserDto;

    @Before
    public void setUp() {
        this.codexsBrowserDto = new CodexsBrowserForSeleniumDto();
        this.codexsBrowserDto.setBrowser(CodexsBrowserForSelenium.CHROME);
        this.codexsBrowserDto.setOptions(List.of("--remote-allow-origins=*"));
        this.codexsBrowserDto.setWebDriverName("webdriver.chrome.driver");
        this.codexsBrowserDto.setWebDriverPath("/usr/bin/chromedriver");

        this.codexsWebControl = new CodexsWebControl(this.codexsBrowserDto);
        this.codexsWebControl.browserSetup();
    }

    @After
    public void tearDown() {
        this.codexsWebControl.finish();
    }

    @Given("user is on login page")
    public void userIsOnLoginPage() {
        this.codexsWebControl.driver().get("https://practicetestautomation.com/practice-test-login/");
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
            Assert.assertEquals(status, logoutButton.isDisplayed());

        } else {

            // When login is wrong the p=text with id=error is visible
            WebElement loginError = this.codexsWebControl.await().until(
                    ExpectedConditions.elementToBeClickable(By.xpath(divElement("error"))));
            Assert.assertTrue(loginError.isDisplayed());

            if (loginError.getText().contains("username")) {
                Assert.assertEquals("Your username is invalid!", loginError.getText());
            } else {
                Assert.assertEquals("Your password is invalid!", loginError.getText());
            }

        }

    }

}
