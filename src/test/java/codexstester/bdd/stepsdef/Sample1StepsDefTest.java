package codexstester.bdd.stepsdef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.web.server.LocalServerPort;

import static com.huntercodexs.codexstester.util.CodexsAssertion.*;

public class Sample1StepsDefTest {

	@LocalServerPort
	String port;

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Given("the sample scenario")
	public void theSampleScenario() {
		System.out.println("Scenario 1 test is running");
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
