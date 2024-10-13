package codexstester.bdd.sample2.stepsdef;

import codexstester.setup.bridge.SampleBridgeTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.web.server.LocalServerPort;

public class Sample2StepsDefTest extends SampleBridgeTest {

    @LocalServerPort
    String port;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Given("the sample two scenario")
    public void theSampleTwoScenario() {
        System.out.println("Scenario 2 test is running");
    }

    @When("the sample two test is made using username {string} and password {string}")
    public void theSampleTwoTestIsMadeUsingUsernameAndPassword(String username, String password) {
        codexsTesterAssertExact("john", username);
        codexsTesterAssertExact("123", password);
    }

    @Then("the sample two status is {string}")
    public void theSampleTwoStatusIs(String status) {
        if (status.equals("200")) {
            codexsTesterAssertInt(200, Integer.parseInt(status));
        } else {
            codexsTesterAssertInt(400, Integer.parseInt(status));
        }
    }

    @And("the sample two text is {string}")
    public void theSampleTwoTextIs(String text) {
        if (text.equals("OK")) {
            codexsTesterAssertText("OK", text);
        } else {
            codexsTesterAssertText("NOK", text);
        }
    }

}
