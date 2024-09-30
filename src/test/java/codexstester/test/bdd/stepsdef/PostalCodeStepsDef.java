package codexstester.test.bdd.stepsdef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.web.server.LocalServerPort;

public class PostalCodeStepsDef {

    @LocalServerPort
    String port;

    @Given("the user needs to know about one specific postalcode")
    public void theUserNeedsToKnowAboutOneSpecificPostalcode() {
    }

    @When("the user call the endpoint {string} passing the code {string}")
    public void theUserCallTheEndpointEndpointPassingTheCodeCode(String endpoint, String code) {
    }

    @Then("the response should be {int}")
    public void theResponseShouldBeStatusCode(int status) {
    }

    @And("the part of response should be {string}")
    public void thePartOfResponseShouldBeExpected(String expected) {
    }
}
