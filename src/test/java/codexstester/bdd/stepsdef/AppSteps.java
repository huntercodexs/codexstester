package codexstester.bdd.stepsdef;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AppSteps {

    @Given("the app is starting")
    public void theFollowingAnimals() {
        System.out.println("We are starting...");
    }

    @When("everything is done")
    public void everyThingIsDone() {
        System.out.println("Everything is done");
    }

    @Then("say {string}")
    public void say(String message) {
        System.out.println(message);
    }
}
