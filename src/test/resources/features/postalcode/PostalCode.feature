#language: en

  Feature: Postal Code

    Scenario Outline: PostalCodeCheck
      Given the user needs to know about one specific postalcode
      When the user call the endpoint <endpoint> passing the code <code>
      Then the response should be <statusCode>
      And the part of response should be <expected>

      Examples:
        | endpoint                                  | code       | statusCode | expected         |
        | "/huntercodexs/codexs-tester/api/address" | "12090002" |        200 | "Sao Caetano"    |
        | "/huntercodexs/codexs-tester/api/address" | "12070180" |        200 | "Emilio Zalluar" |
        | "/huntercodexs/codexs-tester/api/address" | "19999999" |        404 | "Not Found"      |

