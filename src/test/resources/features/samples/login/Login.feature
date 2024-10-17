# language: en

  # comment: This Spec show how to use a cucumber + selenium to make tests on web applications
  Feature: User login test
    Perform user login on Practice Test Automation website

    @Login
    Scenario Outline: CheckLoginStatus
      Given user is on login page using the browser <browser>
      When user login with <username> and <password>
      Then login status should be <status>

      Examples:
      | browser   | username   | password      | status   |
      | "CHROME"  | "student"  | "Password123" | "true"   |
      | "CHROME"  | "student"  | "Password444" | "false"  |
      | "CHROME"  | "stud3nt"  | "Password123" | "false"  |
      | "FIREFOX" | "student"  | "Password123" | "true"   |
      | "FIREFOX" | "student"  | "Password444" | "false"  |
      | "FIREFOX" | "stud3nt"  | "Password123" | "false"  |
      | "OPERA"   | "student"  | "Password123" | "true"   |
      | "OPERA"   | "student"  | "Password444" | "false"  |
      | "OPERA"   | "stud3nt"  | "Password123" | "false"  |
