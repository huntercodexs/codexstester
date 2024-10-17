package codexstester.bdd.stepsdef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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
