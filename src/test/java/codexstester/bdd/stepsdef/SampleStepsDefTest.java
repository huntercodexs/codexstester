package codexstester.bdd.stepsdef;

import codexstester.setup.bridge.SampleBridgeTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.web.server.LocalServerPort;

public class SampleStepsDefTest extends SampleBridgeTest {

	@LocalServerPort
	String port;

	@Given("the sample scenario")
	public void theSampleScenario() {
		System.out.println("Scenario test is running");
	}

	@When("the test is made using username {string} and password {string}")
	public void theTestIsMadeUsingUsernameAndPassword(String username, String password) {
		codexsTesterAssertExact("john", username);
		codexsTesterAssertExact("123", password);
	}

	@Then("the status is {string}")
	public void theStatusIs(String status) {
		if (status.equals("200")) {
			codexsTesterAssertInt(200, Integer.parseInt(status));
		} else {
			codexsTesterAssertInt(400, Integer.parseInt(status));
		}
	}

	@And("the text is {string}")
	public void theTextIs(String text) {
		if (text.equals("OK")) {
			codexsTesterAssertText("OK", text);
		} else {
			codexsTesterAssertText("NOK", text);
		}
	}
}
