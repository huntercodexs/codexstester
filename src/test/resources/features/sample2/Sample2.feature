#language: en

  Feature: Sample 2 Feature

    Scenario: Sample 2 Scenario 1
      Given the sample two scenario
      When the sample two test is made using username "john" and password "123"
      Then the sample two status is "200"
      And the sample two text is "OK"

    Scenario: Sample 2 Scenario 2
      Given the sample two scenario
      When the sample two test is made using username "john" and password "123"
      Then the sample two status is "400"
      And the sample two text is "NOK"
