package codexstester.bdd.stepsdef.samples;

import com.huntercodexs.codexstester.resource.parser.CodexsObjectParser;
import com.huntercodexs.codexstester.sample.dto.SampleEmployeeDto;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;

import java.util.List;

public class DataTableSteps {

    CodexsObjectParser codexsObjectParser;

    public DataTableSteps() {
        this.codexsObjectParser = new CodexsObjectParser();
    }

    @Given("user wants to create an employee using the following attributes")
    public void userWantsToCreateAnEmployeeUsingTheFollowingAttributes(DataTable table) {
        System.out.println("========> DataTable");
        List<SampleEmployeeDto> objects = this.codexsObjectParser.listMapToObject(table, SampleEmployeeDto.class);
        System.out.println(objects);
    }

    @And("with the following address information")
    public void withTheFollowingAddressInformation(DataTable table) {
        System.out.println("========> " + table);
    }

    @When("user try to save the new employee {string}")
    public void userTryToSaveTheNewEmployeeUsingAllRequiredFields(String condition) {
        System.out.println("========> " + condition);
    }

    @Then("the result is {string} and response is {string}")
    public void theResultIsSuccessfulAndResponseIs(String status, String response) {
        System.out.println("========> " + status);
        System.out.println("========> " + response);
    }

}