#language: en

  Feature: Sample 1 Feature

    Scenario: Sample 1 Scenario 1
      Given the sample scenario
      When the test is made using username "john" and password "123"
      Then the status is "200"
      And the text is "OK"

    Scenario: Sample 1 Scenario 2
      Given the sample scenario
      When the test is made using username "john" and password "123"
      Then the status is "400"
      And the text is "NOK"
