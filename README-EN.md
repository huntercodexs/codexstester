# CODEXS TESTER
This project is a complete workspace for writing unit and integration tests

<pre>
||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
WELCOME TO
//||||  //|||\\  ||||\\   ||||||  \\  //  //||||     ||||||  ||||||  //||||  ||||||  ||||||  ||||\\
||      ||   ||  ||   ||  ||||      ||    \\||\\  -    ||    ||||    \\||\\    ||    ||||    ||  //
\\||||  \\|||//  ||||//   ||||||  //  \\  ||||//       ||    ||||||  ||||//    ||    ||||||  ||  \\

Release: 1.0.2
Powered by Huntercodexs (c) 2022
https://github.com/huntercodexs
||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

Codexs Tester is running ...
</pre>

# License

This project is free and can be used as a basis for other projects by anyone under the MIT license.



# Languages

- Language: <a href="README.md">Portuguese Brazil (pt-br)</a> | English



# Pre Requisitos

- MVC Concept
- Maven Project 4.0.0
- Spring Boot 2.0.1.RELEASE
- Java Version 1.8 (jdk1.8.0_212)

> see more details in the pom.xml file of this project



# HTTP status code

This project offers all http codes for REST request handling, for more details of the Status Code at
requests. See more details about HTTP STATUS CODE at



# Installation and Configuration

- Dependencies

First import into the pom.xml file of any project the following dependencies:

<pre>
		&lt;dependency&gt;
			&lt;groupId>org.springframework.boot&lt;/groupId&gt;
			&lt;artifactId>spring-boot-starter-test&lt;/artifactId&gt;
			&lt;scope>test&lt;/scope&gt;
		&lt;/dependency&gt;
		&lt;dependency&gt;
			&lt;groupId>io.projectreactor&lt;/groupId&gt;
			&lt;artifactId>reactor-test&lt;/artifactId&gt;
			&lt;scope>test&lt;/scope&gt;
		&lt;/dependency&gt;
		&lt;dependency&gt;
			&lt;groupId>org.springframework.restdocs&lt;/groupId&gt;
			&lt;artifactId>spring-restdocs-mockmvc&lt;/artifactId&gt;
			&lt;scope>test&lt;/scope&gt;
		&lt;/dependency&gt;
		&lt;dependency&gt;
			&lt;groupId>io.rest-assured&lt;/groupId&gt;
			&lt;artifactId>spring-mock-mvc&lt;/artifactId&gt;
			&lt;scope>test&lt;/scope&gt;
		&lt;/dependency&gt;
		&lt;dependency&gt;
			&lt;groupId>junit&lt;/groupId&gt;
			&lt;artifactId>junit&lt;/artifactId&gt;
			&lt;scope>test&lt;/scope&gt;
		&lt;/dependency&gt;
		&lt;dependency&gt;
			&lt;groupId&gt;net.minidev&lt;/groupId&gt;
			&lt;artifactId&gt;json-smart&lt;/artifactId&gt;
			&lt;version&gt;2.3&lt;/version&gt;
			&lt;scope&gt;compile&lt;/scope&gt;
		&lt;/dependency&gt;
</pre>

- Settings

> The configuration procedure can be summarized in the following steps:

- using git clone command

<pre>
cd @{PROJECT_ROOT_PATH}/src/test/java
git clone https://github.com/huntercodexs/codexstester.git
cd codexstester
git checkout release-@{RELEASE_VERSION} (exemplo: release-1.0.0) 
rm -rf .git .gitignore
</pre>

You can also use the releases available in the github repository of this project by accessing the Releases link as
illustrated in the image below

> NOTE: Always choose the latest release available in the repository, as it contains all project updates
> previously tested and which are of great importance for the development of tests

![img.png](src/data/midias/codexstester-github-image-1.png)

In this case, the procedure is, in parts, the same as explained above in "git clone", that is, the files must be in the
correct folder <pre>@{PROJECT_ROOT_PATH}/src/test/java</pre> of the consuming project.

***NOTE: If the above procedures do not work, or if you still have doubts, follow the instructions below***

After importing the dependencies in your project, check if the folder (package) "test" exists in the project, if not already
exist create this package in the following path:

<pre>
    @{PROJECT_ROOT_PATH}/src/test
</pre>

Inside the "test" path created, make sure that the "java" and "resources" folders exist as shown in the image below:

![img.png](src/data/midias/codexstester-java-resources-sample-project.png)

Then in the project where the tests will be executed, copy the folder (package) "codexstester" into the folder "java"
shown above, and the "codexstester" folder is the same as the one inside the CODEXS TESTER project

NOTE

> It is recommended to do this with the help of an IDE such as IntelliJ, as this tool is capable of copying all
> files and folders correctly renaming the "packages" and references within the project, this prevents failures from occurring
> and unexpected errors besides causing a lot of confusion for a code refactoring procedure. it is also possible
> just drag the entire folder into the consuming project at the specified path

<pre>
    @{PROJECT_ROOT_PATH}/src
</pre>

The image below shows how the scenario should look

![img.png](src/data/midias/codexstester-folder.png)

Now copy the ".properties" files that are inside the path src/test/java/codexstester/setup/properties/files
into the path src/test/resources/@{RESOURCE_NAME} as shown in the image below

![img.png](src%2Fdata%2Fmidias%2Fcodexstester-properties-path.png)

> IMPORTANT: See that two folders were created within the path src/test/resources separating the tests for contained resources
> within the service/application that is consuming the CODEXS TESTER, in this case a resource called postalcode and another
> resource called sample. This setting is not mandatory, but it helps to organize the workspace of
> tests and the project in general, keeping the codes and files separated by their purposes.

These files will be used to carry out part of the test settings, for example the url for requests
and the uri or endpoint of an API, as well as HTTP HEADERS and additional parameters.

At this moment we have the workspace ready to be configured and it should be as shown in the image below, being
You can observe the following setup features:

- path: src/test/java/codexstester/setup/advanced
  - file: [AdvancedSetupTests.java](src/test/java/codexstester/setup/advanced/AdvancedSetupTests.java)
- path: src/test/java/codexstester/setup/bridge
  - [ExternalSampleBridgeTests.java](src/test/java/codexstester/setup/bridge/ExternalSampleBridgeTests.java)
  - [InternalSampleBridgeTests.java](src/test/java/codexstester/setup/bridge/InternalSampleBridgeTests.java)
  - [UnitarySampleBridgeTests.java](src/test/java/codexstester/setup/bridge/UnitarySampleBridgeTests.java)
- path: src/test/java/codexstester/setup/datasource
  - file: [DataSourceSampleTests.java](src/test/java/codexstester/setup/datasource/DataSourceSampleTests.java)
- path: src/test/java/codexstester/setup/properties
  - file: [FilePropertiesSourceTests.java](src/test/java/codexstester/setup/properties/FilePropertiesSourceTests.java)
- path: src/test/java/codexstester/setup/properties/file
  - [external.tests.properties](src/test/java/codexstester/setup/properties/files/external.tests.properties)
  - [internal.tests.properties](src/test/java/codexstester/setup/properties/files/internal.tests.properties)
  - [unitary.tests.properties](src/test/java/codexstester/setup/properties/files/unitary.tests.properties)
- path: src/test/java/codexstester/setup/security
  - [SecuritySourceTests.java](src/test/java/codexstester/setup/security/SecuritySourceTests.java)
- path: src/test/resources/sample
  - [external.tests.properties](src/test/resources/sample/external.tests.properties)
  - [internal.tests.properties](src/test/resources/sample/internal.tests.properties)
  - [unitary.tests.properties](src/test/resources/sample/unitary.tests.properties)

![img.png](src/data/midias/codexstester-setup.png)

- AdvancedSetupTests.java

Starting with the first list file, we have the AdvancedSetupTests java file. This file is intended
serve tests in a narrow and advanced way for comparing data, values and data types. Therefore, in case
If a deeper and more assertive verification is needed in the tests, this file must be used for programs like these
information. It already comes with a series of data ready to exemplify its use, but its use will be explained in more detail.
details in the test conventions and standardization section of that same document.

- ExternalSampleBridgeTests.java
- InternalSampleBridgeTests.java
- UnitarySampleBridgeTests.java

Continuing with the files from the list above, we have the BRIDGE java file, which has the purpose of creating a link
between the test files and the CODEXS TESTER CORE

![img.png](src%2Fdata%2Fmidias%2Fcodexstester-bridge-sample.png)

Put the name of the main class of your spring boot project, which in this case is SampleApplication.java, this is the
class that contains the main method of the project called "main", it is possible that the project has more than one class
main and CODEXS TESTER supports this as will be shown in the testing conventions section.

- DataSourceSampleTests.java

This is the file that should be used as a database for the tests, in which the entire scope of tests will be defined
expected data, data for comparisons, etc. The format and the data to be written in this file are free, and can be
be written in the most convenient way possible for the developer.

- FilePropertiesSourceTests.java

This is the simplest file within the CODEXS TESTER workspace, requiring only one configuration
as following image:

![img.png](src%2Fdata%2Fmidias%2Fcodexstester-file-properties-escope.png)

See that there is only one attribute called targetTests which must be configured according to the needs of the project,
however, this configuration will be explained in more detail in the Testing Conventions and Patterns section.

- external.tests.properties
- internal.tests.properties
- unitary.tests.properties

These are the ".properties" files that should be used during the tests, however they need to be copied inside
from path resources/sample as mentioned earlier. These properties files have a series of fields useful for tests, such 
as HTTP HEADERS, API-KEY, QUERY-PARAMETERS among others, and they will be looked for in the mentioned path during the 
execution of any test.

- SecuritySourceTests.java

It is in this file that access data is available, for example, credentials for authentication via OAuth2, for so be very 
careful when editing this file.

NOTA

> Be careful with the credentials that will be used in the SecuritySourceTests file so that they are not
> exposed in inappropriate places.

- Understanding the features of the CODEXS TESTER workspace

Below we have an image where we can see the main files to run the tests, they are separated by:

- external
- internal
- unitary

For each of the tests we have a sample file called ExternalSampleTests, InternalSampleTests and UnitaySampleTests, but 
they are just examples to speed up the learning process about how the tests work with the use of CODEXS TESTER.

![img.png](src%2Fdata%2Fmidias%2Fcodexstester-tests.png)

> About "external" type tests

Tests of the "external" type are also known as integration tests, they have the ability to run tests
in completely segregated environments and "spread" across any infrastructure, that is, the main objective
of this test and also its main attractiveness lies in the fact that it is possible to run a complete test on an infrastructure
any structure through a call made to a central or request router as in the case of uses with a
API GATEAWAY (for example Zuul from Netflix) which redirects calls to a specific resource
within a safe and controlled environment.

Below is a graphical demonstration of how this works.

![img.png](src/data/midias/codexstester-external.png)

> About "internal" type tests

Regarding the "internal" type tests, as well as the "exrternal" type they can also be used and called tests
of integration, that is, it is possible that the test target service may contain a resource integrated with some external environment,
as in the case of the POSTAL CODE example project (contained in this project) that makes a REST call through an HTTP-CLIENT
to an external resource known as VIA CEP CORREIOS.

However, the tests can be merely internal to the CODEXS TESTER consumer service or project, that is, the calls
REST for the "endpoints" contained in the service, which makes it different from unit tests.

The image below illustrates the processing flow for tests of the internal type:

![img.png](src/data/midias/codexstester-internal.png)

> About "unitary" type tests

In the case of tests of the "unitary" type, we have the premise that the tests will be done on top of any unit
contained in a service or project, that is, we will practically be testing if a method of a class is working
correctly, if an instance is correct or even if the sum of two numbers is being done correctly by a
any calculation.

To exemplify this scenario, we have the following image, which shows in a simple and summarized way how the tests are carried out
units with the CODEXS TESTER workspace.

![img.png](src/data/midias/codexstester-unitary.png)

This was the information about the codexstester/setup configuration path and the codexstester/test tests path, being
that they will be detailed in the conventions and testing standards section of CODEXS TESTER.



# CODEXS TESTER code structure

As already mentioned, the structure of the tests is composed of HEADER, BODY and TESTE, which are detailed below

> HEADER

The HEADER of requests are defined by using the HeadersDto.java class which contains all the fields needed to
perform a REST request, these fields being described below:

- contentType
- httpMethod
- statusCode
- crossOrigin
- origin
- hostname
- ip
- osname
- authorizationBasic
- authorizationBearer
- apiKeyToken
- apiKeyAppName
- apiKeySecret
- apiKeyValue
- apiKeyGeneric
- addtionalName
- addtionalValue
- bodyParameters

The fields above are self-described and do not need further details, however some points need to be clarified.
If it is necessary to use an authentication method such as OAuth2, the field authorizationBasic and authorizationBearer
will be compromised and cannot be used in a call, for example

<pre>
headersDto.setAuthorizationBasic("Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==")
</pre>

If you need to send a custom field in the HEADER, use the **addtionalName** and **addtionalValue** attributes,
what time for purpose create that specific field, for example:

<pre>
headersDto.setAddtionalName("X-Api-Access-Code");
headersDto.setAddtionalValue("XYZ-123");
</pre>

***IMPORTANT: Use the properties file to define additional request headers, up to five are allowed
additional headers***

![img.png](src%2Fdata%2Fmidias%2Fcodexstester-headers-adicionais.png)

> BODY

The BODY of a REST request contains the information that must be transmitted to the final resource to be processed
and used for a specific purpose.

To send this BODY in the request it is necessary to use the RequestDto.java class available in the Codexs workspace
Tester detailed below:

- uri
- id
- dataRequest
- expectedMessage
- expectedCode

See that we can define the URI (uri) where we want to go and that can also be defined in the properties file.

We have the ID (id) that can be used to define a REST request by sending it in the url, that is a RESTFUL API for example
/api/postal-code/{id} => /api/postal-code/1209000.

The dataRequest field must be used to define the BODY REQUEST of the request, that is, the data to send in the test
must be in this field.

It is still possible to define a string to be compared in the test in the expectedMessage field, for example: "Data Not Found",
if a string is not defined in the expectedMessage field, no comparison will be made.

The expectedCode field should not be used, as it will not be considered that way, this field is used in the backend of
CODEXS TESTER according to the function defined for testing, for example:

codexsTesterInternal_StatusCode200_RetrieveOK = expectedCode = 200 OK

> TEST

This is the part of the code that actually executes the test, after the correct parameterization of the HEADER and BODY data of the TEST
can be called without any problem. The tests available in the CODEXS TESTER workspace can be listed
starting typing codexsTester+[Ctrl+Space], in some IDEs like IntelliJ this functionality is automatic.



# Conventions and Standards CODEXS TESTER

<h3>1. Configuring a scope for tests</h3>

To define a test scope it is necessary to edit the FilePropertiesSourceTests.java file, but precisely the attribute
targetTests, where this setting references a separate application or resource contained within the project.

A complete example of this configuration is shown below:

![img.png](src%2Fdata%2Fmidias%2Fcodexstester-scope.png)

As shown in the image below, the above configuration says that the scope of work that CODEXS TESTER must consider will be
located in src/test/resources/sample

![img.png](src%2Fdata%2Fmidias%2Fcodexstester-configuration-properties.png)

With these settings we say for the "external", "internal" or "unitary" tests that the details of "requests" like
HEADERS-HTTP will be available in each test mode specific file.



<h3>2. Setting up a bridge between the tests and the CODEXS TESTER CORE</h3>

![img.png](src%2Fdata%2Fmidias%2Fcodexstester-bridge-reference.png)

This configuration is the most important and must be done carefully and with a correct understanding of how it works, since
that it tells CODEXS TESTER which application will be tested. Imagine that there are two applications in the same project,
which is unlikely to happen with a microservice-oriented project, SampleApplication and PostalCodeApplication, this way.
In this way, it will be necessary to inform CODEXS TESTER which application will be tested, as shown in the image below:

> See that there are two different files, one for the SampleApplication application and another for the
PostalCodeApplication

![img.png](src%2Fdata%2Fmidias%2Fcodexstester-bridge-sample.png)

![img.png](src%2Fdata%2Fmidias%2Fcodexstester-bridge-postalcode.png)

Now see how the test target project is

![img.png](src%2Fdata%2Fmidias%2Fcodexstester-sample-project.png)

You don't need to change anything else in this file, however as an open source project and free to change,
it is possible to add other codes if necessary, but be careful because the CODEXS TESTER may stop working.



<h3>3. Setting up a datasource</h3>

This step is not mandatory, but as already mentioned, it helps to keep the test environment and the code of the test organized.
project. To define a datasource create a file in the path codexstester/setup/datasource with the specific name of your
purpose, for example: DataSourceSampleTests.java or DataSourcePostalCodeTests.java.

This file must contain data and information for the different types of tests that must be performed in the project.
CODEXS TESTER consumer. The DataSourceSampleTests class defined in the DataSourceSampleTests.java file must be
extended in the related tester file, as shown below:

![img.png](src%2Fdata%2Fmidias%2Fcodexstester-extends-datasource-sample.png)

A code example in the datasource can be seen in the code below

![img.png](src%2Fdata%2Fmidias%2Fcodexstester-datasource-sample.png)

Note that there are two methods that return a JSON object that will be used in the tests as data to send in the
request, as will be shown later. It is also possible to observe attributes defined for use in the
tests if it's useful. This file is free in its preparation and programming, just be careful with understanding
the same.



<h3>4. Security settings</h3>

Security settings must be contained in the SecuritySourceTests.java file located in the CODEXS TESTER path
codexstester/setup/security, these settings referring to access data and OAuth2 authorization.

As already mentioned in this document, these configurations must be done with great responsibility, as they are data
access sensitive. It is highly recommended that you do not use access data to production environments or even
homologation, still do not expose the data, even if from development or test environments to people not
authorized. Below is an image to illustrate this issue:

![img.png](src%2Fdata%2Fmidias%2Fcodexstester-config-security.png)



<h3>5. Advanced Settings</h3>

> NOTE: It is not necessary to import any files into the test file, as this is already done by the file
> of bridge mentioned earlier in this document.

The advanced settings for tests must be done in the AdvancedSetupTests.java file, which has the purpose of
offer a more organized and optimized environment to perform large-scale and high-precision tests. The tests
The advanced features of CODEXS TESTER consist of checking whether a piece of data has the following characteristics:

- Name or identification of a key or field

To name or identify a key or field in a test, data in String format must be used, for example:
"name", "age", "address", "data" and etc.

- Data type

To define the type of data, the formats for each field in a given test must be informed, and must be
@{DATA-TYPED}.java format is used, see more details in the examples below.

- Expected value

The expected value field must be used to also test the content of a data, being it in a fixed format and defined in the
CODEXS TESTER as String.

- Strict Mode

The strict mode STRICT-MODE is also available for application in tests, it should be used when there is a
need to make a test with a higher accuracy, because when it is active (true), all data such as name
field or field key, field type and field value will be tested and validated.

CODEXS TESTER advanced tests are limited to eight types of data considered essential in JAVA, namely:

<pre>
- String
- JSON
- DTO
- HashMap
- ArrayList
- LinkedList
- List
- LinkedHashMap
</pre>

However, the format of the fields present in the data structures are free and independent, and can be used
according to the need of the programmer.

Below is an example of how to implement an advanced CODEXS TESTER test using the JSON data type

- Configuration for a JSON response with various types of data in its content

![img.png](src%2Fdata%2Fmidias%2Fcodexstester-json-config.png)

- Test setup responsible for using the above settings

![img.png](src%2Fdata%2Fmidias%2Fcodexstester-json-typed.png)

Notice that there is a definition for testing the responses inside the AdvancedSetupTests.java file, and a simulation of
response within the whenJsonFormatTypedTests() test, and the expected format for this test is JSON, as per the
test name itself suggests codexsTesterCompareJsonFormat();

The advanced tests available in CODEXS TESTER are:

- codexsTesterCompareJsonFormat
- codexsTesterCompareDtoFormat
- codexsTesterCompareHashMapFormat
- codexsTesterCompareArrayListFormat
- codexsTesterCompareLinkedListFormat
- codexsTesterCompareListFormat
- codexsTesterCompareLinkedHashMapFormat

Each of the tests above correspond to a certain type of test according to the name of the test, this facilitates the
understanding and use of each data type structure.

> IMPORTANT: Do not confuse the advanced settings contained in the AdvancedSetupTests.java file with the settings
> contained in the DataSourcesSampleTests.java file, as the latter only serves as a database for the tests and
> the first serves to define the types of data and expected values in a response to any test, be it via
> REST that is through an internal call.



<h3>6. Details about CODEXS TESTER</h3>

- Summary flow for a test programmed with CODEXS TESTER

![img.png](src%2Fdata%2Fmidias%2Fcodexstester-diagram-flow-resumed.png)

Just for the sake of exemplifying in greater detail the engine of the CODEXS TESTER workspace, we see in the figure below
the relationship between the resources of the system and the consumer project. Notice the long way a simple test can go
to execute the instructions correctly and completely, however this whole environment optimizes the work by almost 90%
of the programmer in developing a complex test from scratch.

- Complete flow for a programmed test with CODEXS TESTER

![img.png](src%2Fdata%2Fmidias%2Fcodexstester-diagram-workflow-full.png)



# Programming tests with the CODEXS TESTER workspace

> TIP: All available tests and resources that CODEXS TESTER offers start with the word codexsTester, being
> thus, when codexsTester is typed in the editor/IDE, a huge list of resources available in the
> CODEXS TESTER, for example: codexsTesterAssertionBool().

> REMINDER: Don't forget that the DataSourceSampleTests.java file is essential for testing and can be used in any
> situation and in any of the three types of CODEXS TESTER tests. Nor can we forget the files of
> external.tests.properties, internal.tests.properties and unitary.tests.properties properties, which are very
> important in the CODEXS TESTER workspace.

We will now see situations where the CODEXS TESTER can be used, being in a summation unit test, an external test for
query a home address and an internal test to also query a home address.

The tests will be merely illustrative, but they are tests that have already been carried out and their correct functioning has been verified,
and with these tests it is already possible to understand the work methodology of CODEXS TESTER.

- Test: Sum (Unitary Tests)

If you open the UnitarySampleTests.java file you can see that the first test is a simple sum of two numbers,
as shown below:

<pre>
    @Test
    public void whenSumAnyNumbersTest() {
        int result = DataSourceSampleTests.dataSourceSampleSum(1000, 10);
        codexsTesterAssertInt(result, 1010);
    }
</pre>

The unit tests are very simple and do not need much explanation, if necessary, calmly analyze the
example file SampleTestsUnitaryTests.java and see for yourself that it is very easy to use.

- Test: Address Query (External Tests)

To demonstrate a test example of the "external" type, an address query will be performed using a postal code
valid and that may return the http code 200 (Status Code 200 OK).

In the ExternalSampleTests.java file there are many examples, but only 2 will be presented, both for the same
use case, one with authentication via OAuth2 and another without any authentication.

Below is the sample test mentioned above:

<pre>
    @Test
    public void whenAnyOkRequest_WithNoAuth_RetrieveOk_StatusCode200_ByHttpMethodPOST() throws Exception {
        JSONObject dataRequest = DataSourcePostalCodeTests.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(externalProps.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        codexsTesterExternal_StatusCode200_RetrieveOK(headersDto, requestDto);
    }
</pre>

Looking at the test above, it is visible what the purpose of this test is, considering its name, which, although it is very long
accurately delivers what is being tested. The body of this contains the standard structure used by CODEXS TESTER to
run the tests, and this structure is defined by:

- Request data (BODY REQUEST)
- Header of the request (HEADER REQUEST)
- CODEXS TESTER method to perform the request (CODEXS TESTER)

It is also possible to observe that there is a line of code that makes use of the DataSourcePostalCodeTests.java file which
contains all the data for the tests, whether to test requests, responses or unitary values. This data for
request is used further down the code as BODY REQUEST, and this data will be sent to tests in the form of
text(String).

The HEADER configuration shows that the request will use the http GET method (line 7) and that its content type is
application/json.

Finishing the body of this test we effectively have the call codexsTesterExternal_StatusCode200_RetrieveOK
which in turn also makes clear what its purpose is, that is: An external test that expects a "200 OK" code like
result, and that it has already been defined that it will be done using the http POST method.

- Test: Query Address using OAuth2 as authentication (External Tests)

The test below follows the same principle as the test above, but with additional information, having a defined method for
authentication with OAuth2 and that all your credentials and url configuration contained in the SecuritySourceTests.java data file

Note that the instructions that assemble the HEADER are more detailed, containing the authentication method and additional headers,
as well as the http POST method (HTTP_METHOD_POST).

Finally, the rest of the code needs no comments, as it has the same operation as previously stated in the test above.

<pre>
    @Test
    public void whenAnyOkRequest_WithOAuth2_RetrieveOk_StatusCode200_ByHttpMethodPOST() throws Exception {
        Oauth2RequestTokenDto oauth2RequestTokenDto = codexsTesterSecurityOAuth2Token();
        ResponseEntity&lt;Oauth2ResponseTokenDto&gt; response = codexsTesterExternalOAuth2GetToken(oauth2RequestTokenDto);
        JSONObject dataRequest = DataSourcePostalCodeTests.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBearer(response.getBody().getAccess_token());
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setAdditionalName("Access-Code");
        headersDto.setAdditionalValue("XYZ-123");
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(externalProps.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        codexsTesterExternal_StatusCode200_RetrieveOK(headersDto, requestDto);
    }
</pre>

- Test: Address Query (Intentional Tests)

The difference that an "internal" type test has in relation to the "external" type is almost invisible, because the "magic"
occurs in the "backend" of CODEXS TESTER and not in writing the code. This allows the same concept of code and tests
be used in both situations.

<pre>
    @Test
    public void whenAnyOkRequest_WithNoAuth_RetrieveOk_StatusCode200_ByHttpMethodPOST() throws Exception {
        JSONObject dataRequest = DataSourcePostalCodeTests.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(internalProps.getProperty("internal.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        codexsTesterInternal_StatusCode200_RetrieveOK(headersDto, requestDto);
    }
</pre>

- Test: Query Address using OAuth2 as authentication (Intentional Tests)

<pre>
    @Test
    public void whenAnyOkRequest_WithOAuth2_RetrieveOk_StatusCode200_ByHttpMethodPOST() throws Exception {
        Oauth2RequestTokenDto oauth2RequestTokenDto = codexsTesterSecurityOAuth2Token();
        ResponseEntity&lt;Oauth2ResponseTokenDto&gt; response = codexsTesterInternalOAuth2GetToken(oauth2RequestTokenDto);
        JSONObject dataRequest = DataSourcePostalCodeTests.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBasic(response.getBody().getAccess_token());
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(internalProps.getProperty("internal.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        codexsTesterInternal_StatusCode200_RetrieveOK(headersDto, requestDto);
    }
</pre>

- Test: Advanced Test

As mentioned earlier, advanced tests have a higher degree of assertiveness than conventional ones,
however, it has not yet been shown how to perform a complete test using CODEXS TESTER with the advanced features.

An advanced test in the CODEXS TESTER workspace is nothing more than ensuring that the result of a test is
exactly as expected, as shown below:


Next, the programming of an advanced CODEXS TESTER type test will be shown in detail, which performs the query
from any address through a postal code, validates the HTTP response code, the response format and the type
data contained in the response. It is worth noting that advanced type tests can also be of the "external" type,
"internal" and "unitary", as mentioned earlier, these are the types of tests available in the workspace
TESTER CODEXS.

In short, an "internal" type tester looks like this:

> NOTE: Tests of type "internal" use MockMvc to trigger requests

<pre>
[REQUEST]
HTTP-REQUEST-HEADERS Content-Type: application/json
POST /huntercodexs/postalcode/api/address/12090000

[RESPONSE]
HTTP-STATUS-CODE 200 OK
RESPONSE-BODY JSON {"cep":"12090002","logradouro":"Rua São Caetano","complemento":"","bairro":"Campos Elíseos","localidade":"Taubaté","uf":"SP","ibge":"3554102","gia":"6889","ddd":"12","siafi":"7183"}
</pre>

In the case of tests of the "external" type

> NOTE: Tests of type "external" use RestTempalte to trigger requests

<pre>
[REQUEST]
HTTP-REQUEST-HEADERS Content-Type: application/json
POST http://localhost:38080/huntercodexs/postalcode/api/address/12090000

[RESPONSE]
HTTP-STATUS-CODE 200 OK
RESPONSE-BODY JSON {"cep":"12090002","logradouro":"Rua São Caetano","complemento":"","bairro":"Campos Elíseos","localidade":"Taubaté","uf":"SP","ibge":"3554102","gia":"6889","ddd":"12","siafi":"7183"}
</pre>

See that the only difference between the "internal" and "external" tests is how the REQUEST is done, that is, through MockMvc
or through RestTemplate, where you can see the difference between URL and URI.

As already mentioned earlier in this document, to start a test configuration and programming, you must follow some
CODEXS TESTER rules. First, make sure that the test scope/target is correctly configured.
in the FilePropertiesSourceTests.java file, as instructed below

![codexstester-file-properties-escope-postalcode.png](src%2Fdata%2Fmidias%2Fcodexstester-file-properties-escope-postalcode.png)

See that a scope of work has been defined for the CODEXS TESTER with the name of postalcode, that is, in the path src/test/resources of the
application there should be a folder or "package" with the name postalcode src/test/resources/postalcode.

The next step consists of defining the data types, field names, field values and data types of the
fields, and this configuration must be done in the AdvancedSetupTests.java file contained in the setup/advanced path of
CODEXS TESTER src/test/java/codexstester/setup/advanced/AdvancedSetupTests.java. See below for the expected configuration
for the test in question

<pre>
    /**
     * POSTAL CODE ADVANCED TESTS - JSON TYPED
     */
    public static String[] expectedJsonKeysPostalCode() {
        return new String[]{
                "cep",
                "logradouro",
                "complemento",
                "bairro",
                "localidade",
                "uf",
                "ibge",
                "gia",
                "ddd",
                "siafi"
        };
    }

    public static Object[] expectedJsonValuesPostalCode() {
        return new Object[]{
                "12090002",
                "Rua São Caetano",
                "",
                "Campos Elíseos",
                "Taubaté",
                "SP",
                "3554102",
                "6889",
                "12",
                "7183"
        };
    }

    public static Object[] expectedJsonTypedPostalCode() {
        return new Object[]{
                String.class,
                String.class,
                String.class,
                String.class,
                String.class,
                String.class,
                String.class,
                String.class,
                String.class,
                String.class
        };
    }
</pre>

Notice the following aspects of the configuration, see that we have three methods that have Arrays inside, which
must have the same size, for example Array(10), also see that the method names are self-explanatory about their
goal. Still at this point, it is configured that the fields to be tested will all be of type String.class and that
the expected values are defined in the expectedJsonValuesPostalCode() method.

> STRICT MODE: When a strict test mode is defined (strictMode=true), CODEXS TESTER will also test the values
> contained in the information to be tested, that is, they must have the same name, type and value. If it is not feasible to use
> this functionality, use the non-strict mode (strictMode-false), in which case only name and data type will be validated.

The next step consists of configuring the src/test/resources/postalcode/internal.tests.properties file and also the
src/test/resources/postalcode/external.tests.properties file, informing the data as (in this case) shown below

- internal.tests.properties

<pre>
internal.tests.base-url=/huntercodexs/codexs-tester/api
internal.tests.base-uri=/address
</pre>

- external.tests.properties

<pre>
external.tests.base-url=http://localhost:38080
external.tests.base-uri=/huntercodexs/codexs-tester/api/address
</pre>

Continuing, the next step is to program the data for the REQUEST (datasource), for which a method will be created
inside the src/test/java/codexstester/setup/datasource/DataSourcePostalCodeTests.java file, ***note that the name in
file has a self-explanation about its purpose***.

> NOTE: This procedure is intended to keep the code and structure of the project organized and clean, however
> it is not mandatory that it be done like this, it is possible to enter this data directly in the tests, or else create the
> method inside the test file itself

<pre>
    public static JSONObject dataSourceOkRequest() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.appendField("rulesCode", "XYZ12345");
        jsonObject.appendField("postalCode", "12090002");
        jsonObject.appendField("webhook", "");
        return jsonObject;
    }
</pre>

With that, or rather, with the settings shown above, it is possible to start programming the tests as shown
below.

- Test type "external"

![codexstester-advanced-external-postalcode-test.png](src%2Fdata%2Fmidias%2Fcodexstester-advanced-external-postalcode-test.png)

The image above shows an advanced CODEXS TESTER test, where it is possible to notice the important points of this test and that
deserve attention, see below:

- (1) Extension of the BRIDGE already mentioned in this document and which links @Test with the CORE of CODEXS TESTER
- (2) Use of the file (datasource) to start the request data
- (3) Definition of the HTTP-METHOD method for the request, in this case POST
- (4) The expected HTTP-STATUS-CODE for this request
- (5) In the first line the codexsTesterExternalDispatcher literally triggers the request, in the second line the response
  of the request is converted into the expected JSON format.
- (6) The main method of this test, the codexsTesterCompareJsonFormat, which uses all the configuration done previously
  in the scope of the advanced test, note that it has strictMode=false, that is, the values will not be evaluated, because of
  according to the programmer, they are not relevant to the test.

- "Internal" type test

![codexstester-advanced-internal-postalcode-test.png](src%2Fdata%2Fmidias%2Fcodexstester-advanced-internal-postalcode-test.png)

The image above shows an advanced test of the "internal" type of CODEXS TESTER, with very similar characteristics to the test
to the "external" type shown above, thus dispensing with further details. However, note that in item (6) of
image, in the first line the method used is codexsTesterInternalDispatcher() and not codexsTesterExternalDispatcher(), that is,
the scope of the test is postalcode/internal.tests.properties contained in the path src/test/resources/postalcode/internal.tests.properties,
and the type of test is "internal", as noted in item (4).



# CODEX TESTER helpers

CODEX TESTER has some helpers to assist in the programming of tests, see below what they are:

<pre>
public static String codexsHelperMd5(String data);
public static String codexsHelperGuideGenerator(String tcn);
public static String codexsHelperToday();
public static String codexsHelperOneYearAgo();
public static String codexsHelperFiveYearAgo();
public static void codexsHelperLogTerm(String title, Object data, boolean line);
public static void codexsHelperLogTermTests(String title, Object data, boolean line);
public static JSONObject codexsHelperQueryStringToJson(String queryString);
public static String codexsHelperJsonToString(JSONObject json);
public static JSONObject codexsHelperStringToJson(String string);
</pre>



# Functionalities available in CODEXS TESTER

<pre>
protected static ResponseEntity&lt;Oauth2ResponseTokenDto&gt; codexsTesterExternalOAuth2GetToken(Oauth2RequestTokenDto oauth2RequestTokenDto) {}
protected static ResponseEntity&lt;Object&gt; codexsTesterExternalOAuth2CheckToken(Oauth2RequestCheckTokenDto oauth2RequestCheckTokenDto) {}
protected void codexsTesterExternal_StatusCode400_RetrieveBadRequest(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode401_RetrieveUnauthorized(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode402_RetrievePaymentRequired(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode403_RetrieveForbidden(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode404_RetrieveNotFound(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode405_RetrieveMethodNotAllowed(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode406_RetrieveNotAcceptable(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode407_RetrieveProxyAuthenticationRequired(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode408_RetrieveRequestTimeout(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode409_RetrieveConflict(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode410_RetrieveGone(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode411_RetrieveLengthRequired(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode412_RetrievePreconditionFailed(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode413_RetrievePayloadTooLarge(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode414_RetrieveUriTooLong(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode415_RetrieveUnsupportedMediaType(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode416_RetrieveRequestedRangeNotSatisfiable(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode417_RetrieveExpectationFailed(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode418_RetrieveImATeapotLengthRequired(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode421_RetrieveMisDirectedRequest(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode422_RetrieveUnprocessableEntity(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode423_RetrieveLocked(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode424_RetrieveFailedDependency(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode425_RetrieveTooEarly(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode426_RetrieveUpgradeRequired(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode428_RetrievePreConditionRequired(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode429_RetrieveTooManyRequest(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode431_RetrieveRequestHeaderFieldsTooLarge(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode451_RetrieveUnavailableForLegalReasons(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode100_RetrieveContinue(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode101_RetrieveSwitchingProtocol(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode102_RetrieveProcessing(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode103_RetrieveEarlyHints(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode500_RetrieveInternalServerError(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode501_RetrieveNotImplemented(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode502_RetrieveBadGateway(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode503_RetrieveServiceUnavailable(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode504_RetrieveGatewayTimeout(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode505_RetrieveHttpVersionNotSupported(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode506_RetrieveVariantAlsoNegotiates(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode507_RetrieveInsuficientStorage(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode508_RetrieveLoopDetected(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode510_RetrieveNotExtended(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode511_RetrieveNetworkAuthenticationRequired(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected ResponseEntity&lt;?&lt; codexsTesterExternalDispatcher(RequestDto requestDto, HeadersDto headersDto) {}
protected void codexsTesterExternal_StatusCode300_RetrieveMultipleChoice(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode301_RetrieveMovedPermanently(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode302_RetrieveFound(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode303_RetrieveSeeOther(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode304_RetrieveNotModified(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode305_RetrieveUseProxyDeprecated(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode306_RetrieveUnusedDeprecated(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode307_RetrieveTemporaryRedirect(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode308_RetrievePermanentRedirect(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode200_RetrieveOK(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode201_RetrieveCreated(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode202_RetrieveAccepted(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode203_RetrieveNonAuthoritativeInformation(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode204_RetrieveNoContent(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode205_RetrieveResetContent(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode206_RetrievePartialContent(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode207_RetrieveMultStatusWebdav(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode208_RetrieveMultiStatus(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterExternal_StatusCode226_RetrieveImUsedHttpDeltaEncoding(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterAssertExact(String ref, String text) {}
protected void codexsTesterAssertObject(Object obj1, Object obj2) {}
protected void codexsTesterAssertText(String ref, String text) {}
protected void codexsTesterAssertRegExp(String regExp, String text) {}
protected void codexsTesterAssertInt(int num1, int num2) {}
protected void codexsTesterAssertBool(boolean val, boolean flag) {}
protected void codexsTesterAssertNotNull(Object obj) {}
protected void codexsTesterAssertNull(Object obj) {}
protected void codexsTesterAssertNumber(String number) {}
protected void codexsTesterAssertCpf(String cpf) {}
protected void codexsTesterAssertEmail(String email) {}
protected void codexsTesterAssertPhone(String phoneNumber) {}
protected void codexsTesterAssertSum(int a, int b, int c) {}
protected void codexsTesterInternal_StatusCode100_RetrieveContinue(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode101_RetrieveSwitchingProtocol(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode102_RetrieveProcessing(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode103_RetrieveEarlyHints(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode500_RetrieveInternalServerError(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode501_RetrieveNotImplemented(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode502_RetrieveBadGateway(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode503_RetrieveServiceUnavailable(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode504_RetrieveGatewayTimeout(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode505_RetrieveHttpVersionNotSupported(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode506_RetrieveVariantAlsoNegotiates(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode507_RetrieveInsuficientStorage(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode508_RetrieveLoopDetected(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode510_RetrieveNotExtended(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode511_RetrieveNetworkAuthenticationRequired(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected static ResponseEntity&lt;Oauth2ResponseTokenDto&lt; codexsTesterInternalOAuth2GetToken(Oauth2RequestTokenDto oauth2RequestTokenDto) {}
protected static ResponseEntity&lt;Object&lt; codexsTesterInternalOAuth2CheckToken(Oauth2RequestCheckTokenDto oauth2RequestCheckTokenDto) {}
protected void codexsTesterInternal_StatusCode200_RetrieveOK(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode201_RetrieveCreated(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode202_RetrieveAccepted(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode203_RetrieveNonAuthoritativeInformation(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode204_RetrieveNoContent(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode205_RetrieveResetContent(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode206_RetrievePartialContent(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode207_RetrieveMultStatusWebdav(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode208_RetrieveMultiStatus(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode226_RetrieveImUsedHttpDeltaEncoding(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode300_RetrieveMultipleChoice(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode301_RetrieveMovedPermanently(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode302_RetrieveFound(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode303_RetrieveSeeOther(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode304_RetrieveNotModified(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode305_RetrieveUseProxyDeprecated(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode306_RetrieveUnusedDeprecated(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode307_RetrieveTemporaryRedirect(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode308_RetrievePermanentRedirect(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode400_RetrieveBadRequest(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode401_RetrieveUnauthorized(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode402_RetrievePaymentRequired(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode403_RetrieveForbidden(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode404_RetrieveNotFound(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode405_RetrieveMethodNotAllowed(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode406_RetrieveNotAcceptable(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode407_RetrieveProxyAuthenticationRequired(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode408_RetrieveRequestTimeout(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode409_RetrieveConflict(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode410_RetrieveGone(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode411_RetrieveLengthRequired(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode412_RetrievePreconditionFailed(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode413_RetrievePayloadTooLarge(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode414_RetrieveUriTooLong(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode415_RetrieveUnsupportedMediaType(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode416_RetrieveRequestedRangeNotSatisfiable(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode417_RetrieveExpectationFailed(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode418_RetrieveImATeapotLengthRequired(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode421_RetrieveMisDirectedRequest(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode422_RetrieveUnprocessableEntity(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode423_RetrieveLocked(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode424_RetrieveFailedDependency(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode425_RetrieveTooEarly(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode426_RetrieveUpgradeRequired(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode428_RetrievePreConditionRequired(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode429_RetrieveTooManyRequest(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode431_RetrieveRequestHeaderFieldsTooLarge(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected void codexsTesterInternal_StatusCode451_RetrieveUnavailableForLegalReasons(HeadersDto headersDto, RequestDto requestDto) throws Exception {}
protected String codexsTesterInternalDispatcher(RequestDto requestDto, HeadersDto headersDto) throws Exception {}
</pre>



Make the most of this little job!

----
All rights reserved to Huntercodexs &copy; 2022 - Software Development
Maintained by Jereelton Teixeira (jereelton-devel)
