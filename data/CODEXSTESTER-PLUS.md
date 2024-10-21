# CODEXSTESTER - PLUS
The simple library to write tests in java

# Overview

## About

<pre>
 _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
|                                                                                                       |
|  //||||  //|||\\  ||||\\   ||||||  \\  //  //||||     ||||||  ||||||  //||||  ||||||  ||||||  ||||\\  |
|  ||      ||   ||  ||   ||  ||||      ||    \\||\\  -    ||    ||||    \\||\\    ||    ||||    ||  //  |
|  \\||||  \\|||//  ||||//   ||||||  //  \\  ||||//       ||    ||||||  ||||//    ||    ||||||  ||  \\  |
|                                                                                                       |
|  Release: X.X.XXX                                                                                     |
|  https://github.com/huntercodexs                                                                      |
|  Powered by HunterCodexs (c) 2022 (owned by jereelton-devel)                                          |
|                                                                                                       |
|  Date now is: 2021/10/18 09:31:16                                                                     |
|_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _|
</pre>

The Codexstester is a simple library tool to make some kind of tests in java, with it, you can test your code, 
infrastructure, containers, databases, brokers and more. Just give a careful read in this documentation to get 
how easy is implement and use this great library.

## Infra Structure Code

> Deprecated ![check-red.png](media/check-red.png)

![codexstester-diagram-flow-Full.png](media/codexstester-diagram-flow-Full.png)

## License

This project is free and can be used as a basis for other projects by anyone under the MIT license.

## Pre Requisites

These pre-requisites were tested and approved to put in use, so it is very recommended that you follow this rules 
to get a successful in the codexstester implementation.

- JDK/JRE 
  - Version 8
  - Version 11
  - Version 17
  - Version 21
- Spring Boot
  - 2.0.1.RELEASE
  - 2.6.4
  - 2.1.5
  - 3.3.2
- Concepts
  - MVC
  - Mock
  - Cucumber
    - Version 4.2.0
    - Version 7.10.1
    - Version 7.12.0
  - Container
  - Junit
  - Broker
  - Integration

## HTTP status code

This project offers all http codes for REST request handling, for more details of the Status Code at
requests. See more details about HTTP STATUS CODE at https://developer.mozilla.org/en-US/docs/Web/HTTP/Status

## Project Structure

The complete structure for Codexstester is shown below

<pre>
src
├── main
│   └── java
│       └── com
│           └── huntercodexs
│               └── codexstester
│                   ├── bridge
│                   │   └── CodexsTesterCoreBridge.java
│                   ├── constant
│                   │   └── CodexsConstant.java
│                   ├── dto
│                   │   ├── HeadersDto.java
│                   │   ├── JwtResponseDto.java
│                   │   └── RequestDto.java
│                   ├── external
│                   │   ├── AbstractExternalRequest.java
│                   │   ├── AbstractExternalRestTemplate.java
│                   │   ├── ExternalHttpHeadersFactory.java
│                   │   ├── ExternalRequest1xx.java
│                   │   ├── ExternalRequest2xx.java
│                   │   ├── ExternalRequest3xx.java
│                   │   ├── ExternalRequest4xx.java
│                   │   └── ExternalRequest5xx.java
│                   ├── http
│                   │   ├── AvailableHttpMethod.java
│                   │   └── AvailableHttpStatus.java
│                   ├── ignition
│                   │   ├── CodexsTesterIgnition.java
│                   │   └── CodexTesterPropertiesLoader.java
│                   ├── internal
│                   │   ├── AbstractInternalMockMvc.java
│                   │   ├── AbstractInternalRequest.java
│                   │   ├── InternalHttpHeadersFactory.java
│                   │   ├── InternalRequest1Xx.java
│                   │   ├── InternalRequest2Xx.java
│                   │   ├── InternalRequest3Xx.java
│                   │   ├── InternalRequest4Xx.java
│                   │   └── InternalRequest5Xx.java
│                   ├── properties
│                   │   ├── ExternalProperty.java
│                   │   ├── InternalProperty.java
│                   │   └── UnitaryProperty.java
│                   ├── resource
│                   │   ├── basic
│                   │   │   ├── CodexsBase.java
│                   │   │   ├── CodexsDate.java
│                   │   │   ├── CodexsPath.java
│                   │   │   ├── CodexsStringHandler.java
│                   │   │   ├── CodexsTools.java
│                   │   │   └── CodexsValidator.java
│                   │   ├── crypto
│                   │   │   └── CodexsCrypto.java
│                   │   ├── currency
│                   │   │   └── CodexsCurrency.java
│                   │   ├── enumerator
│                   │   │   ├── CodexsDataMask.java
│                   │   │   ├── CodexsTokenType.java
│                   │   │   ├── CodexsTraceType.java
│                   │   │   └── CodexsUfTable.java
│                   │   ├── file
│                   │   │   ├── CodexsFileHandler.java
│                   │   │   ├── CodexsFileReader.java
│                   │   │   └── CodexsFileWriter.java
│                   │   ├── format
│                   │   │   ├── CodexsFormatter.java
│                   │   │   └── CodexsMask.java
│                   │   ├── http
│                   │   │   ├── CodexsHttpClient.java
│                   │   │   ├── CodexsHttpSimulator.java
│                   │   │   └── CodexsResponseEntitySimulator.java
│                   │   ├── image
│                   │   │   └── CodexsImage.java
│                   │   ├── parser
│                   │   │   ├── CodexsJsonParser.java
│                   │   │   └── CodexsObjectParser.java
│                   │   ├── quickjson
│                   │   │   ├── core
│                   │   │   │   ├── QuickJsonAbstract.java
│                   │   │   │   ├── QuickJsonBuilder.java
│                   │   │   │   ├── QuickJsonData.java
│                   │   │   │   └── QuickJsonExtractor.java
│                   │   │   └── QuickJson.java
│                   │   ├── randomize
│                   │   │   └── CodexsRandom.java
│                   │   ├── stdout
│                   │   │   └── CodexsStdout.java
│                   │   └── web
│                   │       ├── CodexsWebControl.java
│                   │       ├── CodexsWebDriver.java
│                   │       ├── CodexsWebElements.java
│                   │       └── constant
│                   │           ├── CodexsBrowserForSeleniumDto.java
│                   │           └── CodexsBrowserForSelenium.java
│                   ├── sample
│                   │   ├── controller
│                   │   │   └── SampleController.java
│                   │   └── dto
│                   │       ├── SampleEmployeeAddressDto.java
│                   │       └── SampleEmployeeDto.java
│                   ├── SampleApplication.java
│                   ├── security
│                   │   ├── AuthType.java
│                   │   ├── BasicAuthSecurity.java
│                   │   ├── CodexsSecurity.java
│                   │   ├── CodexsSecuritySetup.java
│                   │   ├── dto
│                   │   │   ├── BasicAuthRequestDto.java
│                   │   │   ├── JwtAuthRequestDto.java
│                   │   │   ├── Oauth2RequestCheckTokenDto.java
│                   │   │   ├── Oauth2RequestTokenDto.java
│                   │   │   ├── Oauth2ResponseTokenCheckDto.java
│                   │   │   └── Oauth2ResponseTokenDto.java
│                   │   ├── JwtAuthSecurity.java
│                   │   └── Oauth2Security.java
│                   ├── unitary
│                   │   └── AbstractUnitary.java
│                   └── util
│                       ├── CodexsAdvanced.java
│                       ├── CodexsArrayListComparator.java
│                       ├── CodexsAssertion.java
│                       ├── CodexsDtoComparator.java
│                       ├── CodexsHashMapComparator.java
│                       ├── CodexsHelper.java
│                       ├── CodexsJsonComparator.java
│                       ├── CodexsJsonParser.java
│                       ├── CodexsLinkedHashMapComparator.java
│                       ├── CodexsLinkedListComparator.java
│                       └── CodexsListComparator.java
└── test
    ├── java
    │   └── codexstester
    │       ├── bdd
    │       │   ├── runner
    │       │   │   ├── AppReportRunnerTest.java
    │       │   │   ├── AppRunnerTest.java
    │       │   │   └── samples
    │       │   │       ├── BackgroundReportRunnerTest.java
    │       │   │       ├── BackgroundRunnerTest.java
    │       │   │       ├── CounterReportRunnerTest.java
    │       │   │       ├── CounterRunnerTest.java
    │       │   │       ├── DataTableReportRunnerTest.java
    │       │   │       ├── DataTableRunnerTest.java
    │       │   │       ├── LambdaReportRunnerTest.java
    │       │   │       ├── LambdaRunnerTest.java
    │       │   │       ├── ListReportRunnerTest.java
    │       │   │       ├── ListRunnerTest.java
    │       │   │       ├── LoginReportRunnerTest.java
    │       │   │       └── LoginRunnerTest.java
    │       │   └── stepsdef
    │       │       ├── AppSteps.java
    │       │       └── samples
    │       │           ├── BackgroundAPISteps.java
    │       │           ├── BackgroundDBSteps.java
    │       │           ├── BackgroundUserSteps.java
    │       │           ├── CounterSteps.java
    │       │           ├── DataTableSteps.java
    │       │           ├── integration
    │       │           │   └── CucumberSpringIntegration.java
    │       │           ├── LambdaSteps.java
    │       │           ├── ListSteps.java
    │       │           └── LoginSteps.java
    │       ├── junit4
    │       │   ├── external
    │       │   │   └── SampleExternalTests.java
    │       │   ├── internal
    │       │   │   └── SampleInternalTests.java
    │       │   └── unitary
    │       │       └── SampleUnitaryTests.java
    │       ├── LICENSE
    │       ├── README.md
    │       └── setup
    │           ├── advanced
    │           │   └── AdvancedSetup.java
    │           ├── bridge
    │           │   └── SampleBridgeTest.java
    │           ├── datasource
    │           │   └── SampleDataSource.java
    │           ├── properties
    │           │   ├── external.tests.properties
    │           │   ├── internal.tests.properties
    │           │   └── unitary.tests.properties
    │           └── security
    │               └── SecuritySetup.java
    └── resources
        ├── extent.properties
        ├── features
        │   ├── app
        │   │   └── App.feature
        │   └── samples
        │       ├── background
        │       │   └── Background.feature
        │       ├── counter
        │       │   ├── Counter.feature
        │       │   └── CounterPlus.feature
        │       ├── datatable
        │       │   └── DataTable.feature
        │       ├── lambda
        │       │   └── Lambda.feature
        │       ├── list
        │       │   └── List.feature
        │       └── login
        │           └── Login.feature
        ├── junit4
        │   ├── file.txt
        │   └── sample
        │       ├── external.tests.properties
        │       ├── internal.tests.properties
        │       └── unitary.tests.properties
        ├── junit-platform.properties
        └── reporting
            ├── extent-config.xml
            └── html-config.xml
</pre>


## Use cases

Below are some situations where it is possible to use Codexstester

- Integrated tests
    - External requests using a client
- Unitary tests
    - Sum of two numbers
    - Data processing
    - Data persistence
    - Private methods
- Complete flow of a REST request
    - Consistency test
- Authentication
    - MFA/2FA
    - OAuth2
- Container
- Message Broker
- Database

# Configuration

### From GitHUB repository

- First, get the codexstester from huntercodexs GitHUB account

<pre>
jereelton@jereelton-acer-nitro:~$ git clone https://github.com/huntercodexs/codexstester.git
jereelton@jereelton-acer-nitro:~$ cd codexstester
jereelton@jereelton-acer-nitro:~$ git checkout {RELEASE-TARGET}
</pre>


- Build the project using your preferred IDE, for example:
  - int the IntelliJ IDEA, use: Ctrl+F9


- Compile the project to generate the jar file
  - This step will be generated the jar file package in the .m2 repository package locally in your machine, for example:
    - /home/{USER}/.m2/repository/com/huntercodexs/codexstester/codexstester/2.0.0-SNAPSHOT


- Now, import the codexstester library into the pom.xml file of any project the following dependency, for example:

<code>

		<dependency>
			<groupId>com.huntercodexs.codexstester</groupId>
			<artifactId>codexstester</artifactId>
			<version>2.0.0-SNAPSHOT</version>
			<scope>provided</scope>
			<exclusions>
				<exclusion>
					<groupId>com.vaadin.external.google</groupId>
					<artifactId>android-json</artifactId>
				</exclusion>
			</exclusions>
		</dependency>

</code>

### From GitHUB Releases

- First, get the codexstester jar file from huntercodexs GitHUB account in the releases section 
  - https://github.com/huntercodexs/codexstester/releases
  - or goto the section # Releases in this documentation and choose the specific release according the project requirements


- After downloaded the jar file for example codexstester-2.0.0-SNAPSHOT.jar, save it in the .m2 path, for example:
  - /home/{USER}/.m2/repository/com/huntercodexs/codexstester/codexstester/2.0.0-SNAPSHOT


- Now, import the codexstester library into the pom.xml file of any project the following dependency, for example:

<code>

		<dependency>
			<groupId>com.huntercodexs.codexstester</groupId>
			<artifactId>codexstester</artifactId>
			<version>2.0.0-SNAPSHOT</version>
			<scope>provided</scope>
			<exclusions>
				<exclusion>
					<groupId>com.vaadin.external.google</groupId>
					<artifactId>android-json</artifactId>
				</exclusion>
			</exclusions>
		</dependency>

</code>

### From GitHUB Packages

It is possible to get the codexstester project from GitHub packages as a maven dependency for java projects, so for 
that give a look at https://github.com/huntercodexs/codexstester/releases or section # Releases in this documentation 
to get the correctly version.

After getting the correct Codexstester version, put it in the pom.xml file as showed below:

<code>

		<dependency>
			<groupId>com.huntercodexs</groupId>
			<artifactId>codexstester</artifactId>
			<version>1.0.9-SNAPSHOT</version>
			<scope>provided</scope>
			<exclusions>
				<exclusion>
					<groupId>com.vaadin.external.google</groupId>
					<artifactId>android-json</artifactId>
				</exclusion>
			</exclusions>
		</dependency>

</code>

You can check all packages available following the link https://github.com/huntercodexs?tab=packages&repo_name=codexstester.

### From Maven Central

![check-red.png](media/check-red.png) Unavailable yet

### Codexstester Generator

Finally, use the codexstester-generator to create the specifics tests. 
Click on the link 
<a href="https://github.com/huntercodexs/codexstester/blob/main/data/CODEXSTESTER-GENERATOR.md">Codexstester Generator</a> 
to get more details about it.

# Usage

Getting into the direct point, we are show some examples for Codexstester use case tests, you can see all these tests in 
the codexstester project source files src/test/java/codexstester. Each release of this repository has the own samples 
and files configurations to support you in your development.

The samples contained in this documentation cover all possible tests that codexstester can be made, below are the list 
of type of tests that you can make using the codexstester library.

- Unitary
- Integration
- Database
- Cucumber
- Broker
- Container
- Web

We are going to elaborate a bit more each one of these items to be more clearly and specific.

### Unitary

![codexstester-unitary.png](media/codexstester-unitary.png)

Unitary tests are simple tests that has the priority to check and guarantee the perfect functionality for one specific 
method, function, command, class, script or any other kind of code unit. So in order to use Codexstester to automate 
this process we will give a look into some examples affordable below.

<code>

    @Test
    public void whenSumAnyNumbersTest() {
        int result = SampleDataSource.dataSourceSampleSum(1000, 10);
        codexsTesterAssertInt(result, 1010, null);
    }

    @Test
    public void quickJsonTest() {
        String result = quickJsonDataSource();
        codexsTesterAssertExact("{\"name\":\"Jereelton\",\"age\":39}", result, null);
    }

    @Test
    public void whenOrgJsonFormatTypedWithDataTreeTests() throws Exception {

        /*SIMULATE RESPONSE SAMPLE*/
        String string = "value1";
        QuickJson quickJson = new QuickJson();
        HeadersDto headersDto = new HeadersDto();
        HashMap<Object, Object> hashMap = new HashMap<>();
        ArrayList<Object> arrayList = new ArrayList<>();
        LinkedList<Object> linkedList = new LinkedList<>();
        List<String> list = new ArrayList<>();
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();

        org.json.JSONObject orgJsonResponseSimulate = new org.json.JSONObject();
        orgJsonResponseSimulate.put("field1", string);
        orgJsonResponseSimulate.put("field2", quickJson);
        orgJsonResponseSimulate.put("field3", headersDto);
        orgJsonResponseSimulate.put("field4", hashMap);
        orgJsonResponseSimulate.put("field5", arrayList);
        orgJsonResponseSimulate.put("field6", linkedList);
        orgJsonResponseSimulate.put("field7", list);
        orgJsonResponseSimulate.put("field8", linkedHashMap);

        codexsTesterCompareJsonFormat(
                expectedJsonDataTree(),
                orgJsonResponseSimulate,
                true,
                "none",
                true);

    }

</code>

The scope directory for the unitary tests is the following:

<pre>
src
└── test
    ├── java
    │   └── codexstester
    │       ├── junit4
    │       │   └── unitary
    │       │       └── SampleUnitaryTests.java
    │       └── setup
    │           ├── advanced
    │           │   └── AdvancedSetup.java
    │           ├── bridge
    │           │   └── SampleBridgeTest.java
    │           ├── datasource
    │           │   └── SampleDataSource.java
    │           ├── properties
    │           │   └── unitary.tests.properties
    │           └── security
    │               └── SecuritySetup.java # This file is used for Credentials/Authorization
    └── resources
        ├── junit4
        │   ├── file.txt # This file is used for specific tests, for example: MFA Tests
        │   └── sample
        │       └── unitary.tests.properties
</pre>

### Integration

The integration tests are used to verify the communication between the systems, resources, apis, services among others 
that belong a specific flow in the code. These tests are defined and separated in two ways: internal and external, 
where internal refers to the tests that are been made internally in the application, and the external tests are used 
make tests externally of the application, or yet make tests in the application but using the online runtime service.

In both cases we have the same purpose, to check and guarantee the perfect integration in the targeted flow, for example, 
if you want to validate one integration between your application and any address service application, in this case 
you have an integration with that address service application witch you use to get the information about one address.

Below we can see the flow for each one of this kind of integration:

![codexstester-external.png](media/codexstester-external.png)

![codexstester-internal.png](media/codexstester-internal.png)

The following structure to integration testes is:

<pre>
src
└── test
    ├── java
    │   └── codexstester
    │       ├── junit4
    │       │   ├── external
    │       │   │   └── SampleExternalTests.java
    │       │   ├── internal
    │       │   │   └── SampleInternalTests.java
    │       └── setup
    │           ├── advanced
    │           │   └── AdvancedSetup.java
    │           ├── bridge
    │           │   └── SampleBridgeTest.java
    │           ├── datasource
    │           │   └── SampleDataSource.java
    │           ├── properties
    │           │   ├── external.tests.properties
    │           │   ├── internal.tests.properties
    │           └── security
    │               └── SecuritySetup.java
    └── resources
        ├── junit4
        │   ├── file.txt
        │   └── sample
        │       ├── external.tests.properties
        │       ├── internal.tests.properties
</pre>

Following in the explanation, below are some examples to use the integration tests with Codexstester.

- For Internal tests

<code>

    @Test
    public void whenSimpleTestUsingString_AssertExact() throws Exception {
        String result = SampleDataSource.dataSourceSampleResponse();
        codexsTesterAssertExact("This is a expected sample response", result, null);
    }

    /*SAMPLE*/
    @Test
    public void whenAnyOkRequestSample_WithNoAuth_RetrieveOk_StatusCode200_ByHttpMethodGET() throws Exception {
        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_GET);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(internalProps.getProperty("internal.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest("");
        requestDto.setExpectedMessage("Welcome to codexstester sample API");

        codexsTesterInternal_StatusCode200_RetrieveOK(headersDto, requestDto);
    }

    /*SAMPLE*/
    @Test
    @Ignore
    public void whenAnyOkRequestSample_WithNoAuth_RetrieveCreated_StatusCode201_ByHttpMethodPOST() throws Exception {
        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(internalProps.getProperty("internal.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest("");
        requestDto.setExpectedMessage(null);

        codexsTesterInternal_StatusCode201_RetrieveCreated(headersDto, requestDto);
    }

    /*SAMPLE - GET*/
    @Test
    public void whenRequestTest_RetrieveOK_StatusCode200_ByHttpMethodGET() throws Exception {
        JSONObject dataRequest = new JSONObject();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_GET);
        headersDto.setAuthorizationBasic("123-TEST");

        RequestDto requestDto = new RequestDto();
        requestDto.setExpectedCode(OK_200);
        requestDto.setUri(endpointGet);
        requestDto.setId("123456");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        MockHttpServletResponse dispatcherResult = codexsTesterInternalDispatcher(requestDto, headersDto);
        codexsTesterAssertExact("GET OK - 123456", dispatcherResult.getContentAsString(), null);
    }

    /*SAMPLE - POST*/
    @Test
    public void whenRequestTest_RetrieveCreated_StatusCode201_ByHttpMethodPOST() throws Exception {
        JSONObject dataRequest = new JSONObject();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_POST);
        headersDto.setAuthorizationBasic("123-TEST");

        RequestDto requestDto = new RequestDto();
        requestDto.setExpectedCode(CREATED_201);
        requestDto.setUri(endpointPost);
        requestDto.setId("123456");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        MockHttpServletResponse dispatcherResult = codexsTesterInternalDispatcher(requestDto, headersDto);
        codexsTesterAssertExact("POST OK - 123456", dispatcherResult.getContentAsString(), null);
    }

    /*SAMPLE - PUT*/
    @Test
    public void whenRequestTest_RetrieveAccepted_StatusCode202_ByHttpMethodPUT() throws Exception {
        JSONObject dataRequest = new JSONObject();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_PUT);
        headersDto.setAuthorizationBasic("123-TEST");

        RequestDto requestDto = new RequestDto();
        requestDto.setExpectedCode(ACCEPTED_202);
        requestDto.setUri(endpointPut);
        requestDto.setId("123456");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        MockHttpServletResponse dispatcherResult = codexsTesterInternalDispatcher(requestDto, headersDto);
        codexsTesterAssertExact("PUT OK - 123456", dispatcherResult.getContentAsString(), null);
    }

    /*SAMPLE - DELETE*/
    @Test
    public void whenRequestTest_RetrieveAccepted_StatusCode202_ByHttpMethodDELETE() throws Exception {
        JSONObject dataRequest = new JSONObject();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_DELETE);
        headersDto.setAuthorizationBasic("123-TEST");

        RequestDto requestDto = new RequestDto();
        requestDto.setExpectedCode(ACCEPTED_202);
        requestDto.setUri(endpointDelete);
        requestDto.setId("123456");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        MockHttpServletResponse dispatcherResult = codexsTesterInternalDispatcher(requestDto, headersDto);
        codexsTesterAssertExact("DELETE OK - 123456", dispatcherResult.getContentAsString(), null);
    }

    /*SAMPLE - PATCH*/
    @Test
    public void whenRequestTest_RetrieveAccepted_StatusCode202_ByHttpMethodPATCH() throws Exception {
        JSONObject dataRequest = new JSONObject();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_PATCH);
        headersDto.setAuthorizationBasic("123-TEST");

        RequestDto requestDto = new RequestDto();
        requestDto.setExpectedCode(ACCEPTED_202);
        requestDto.setUri(endpointPatch);
        requestDto.setId("123456");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        MockHttpServletResponse dispatcherResult = codexsTesterInternalDispatcher(requestDto, headersDto);
        codexsTesterAssertExact("PATCH OK - 123456", dispatcherResult.getContentAsString(), null);
    }

</code>

You can see in the above test code that the integration tests are flexible and can be used for a lot of types of 
situations, since a just and simple internal tests to get the sum until a rest request to validate a complete flow 
of processing.

- For External test

<code>

    public class SampleExternalTests extends SampleBridgeTest {

        SecuritySetup securitySetup;
    
        @Before
        public void setup() {
            this.securitySetup = new SecuritySetup();
        }
    
        @Test
        public void whenSimpleTestUsingString_AssertExact() throws Exception {
            String result = SampleDataSource.dataSourceSampleResponse();
            codexsTesterAssertExact("This is a expected sample response", result, null);
        }
    
        @Test
        public void oauth2Test() {
            SecuritySetup securitySetup = new SecuritySetup();
            Oauth2RequestTokenDto requestTokenDto = securitySetup.oauth2Token("local");
            CodexsSecurity codexsSecurity = new CodexsSecurity(requestTokenDto);
            String token = codexsSecurity.token();
    
            codexsTesterAssertGuid(token, null);
    
            Oauth2RequestCheckTokenDto requestCheckTokenDto = securitySetup.oauth2CheckToken("local", token);
            boolean status = codexsSecurity.checkToken(requestCheckTokenDto);
    
            codexsTesterAssertBool(true, status, null);
        }
    
        @Test
        public void basicAuthTest() {
            SecuritySetup securitySetup = new SecuritySetup();
            BasicAuthRequestDto authRequestDto = securitySetup.basicAuth("local");
            CodexsSecurity codexsSecurity = new CodexsSecurity(authRequestDto);
            Object response = codexsSecurity.basicAuth();
    
            codexsTesterAssertGuid(String.valueOf(response), null);
        }
    
        @Test
        public void jwtAuthTest() {
            SecuritySetup securitySetup = new SecuritySetup();
            JwtAuthRequestDto jwtAuthRequestDto = securitySetup.jwtAuth("local");
            CodexsSecurity codexsSecurity = new CodexsSecurity(jwtAuthRequestDto);
    
            JwtResponseDto response = codexsSecurity.jwtAuth(JwtResponseDto.class);
    
            //JWT
            //codexsTesterAssertJwtHS256(response.getJwt(), null);
            codexsTesterAssertJwtHS384(response.getJwt(), null);
            //codexsTesterAssertJwtHS512(response.getJwt());
    
            //JWT-ASSIGN
            //codexsTesterAssertJwtRS256(response.getJwt());
            //codexsTesterAssertJwtRS384(response.getJwt());
            //codexsTesterAssertJwtRS512(response.getJwt());
        }
    
        /*SAMPLE*/
        @Test
        @Ignore
        public void whenAnyOkRequest_WithNoAuth_RetrieveOk_StatusCode200_ByHttpMethodGET() throws Exception {
            HeadersDto headersDto = new HeadersDto();
            headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
            headersDto.setHttpMethod(HTTP_METHOD_GET);
    
            RequestDto requestDto = new RequestDto();
            requestDto.setUri(externalProps.getProperty("external.tests.base-uri"));
            requestDto.setId("");
            requestDto.setDataRequest("");
            requestDto.setExpectedMessage("Welcome to sample from Codexs Tester");
    
            codexsTesterExternal_StatusCode200_RetrieveOK(headersDto, requestDto);
        }
    
        /**
         * OAuth2 Example
         * */
        @Test
        public void whenAnyRequest_Oauth2_Correctly_RetrieveCreated_StatusCode200_GET() throws Exception {
            QuickJson dataRequest = new QuickJson("{\"test\":\"123 testing...\"}");
    
            HeadersDto headersDto = new HeadersDto();
            headersDto.setAuthorizationBearer(securitySetup.oauth2Authorization("local"));//OAUTH2
            headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
            headersDto.setHttpMethod(HTTP_METHOD_GET);
            headersDto.setObjectResponse(String.class);
    
            RequestDto requestDto = new RequestDto();
            requestDto.setExpectedCode(OK_200);
            requestDto.setUrl("http://localhost:33009");
            requestDto.setUri("/huntercodexs/client/api/admin");
            requestDto.setId(""); // /huntercodexs/client/api/admin/{id}
            requestDto.setDataRequest(dataRequest.toString());
            requestDto.setExpectedMessage("Admin is running on OAUTH2-CLIENT-DEMO");
    
            Object response = codexsTesterExternalDispatcher(requestDto, headersDto).getBody();
    
            codexsTesterAssertExact("Admin is running on OAUTH2-CLIENT-DEMO", String.valueOf(response), null);
        }
    
        /**
         * Basic Auth Example
         * */
        @Test
        public void whenAnyRequest_BasicAuth_Correctly_RetrieveCreated_StatusCode200_GET() throws Exception {
            HeadersDto headersDto = new HeadersDto();
            headersDto.setAuthorizationBasic(securitySetup.basicAuthorization("local")); //BASIC AUTH
            headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
            headersDto.setHttpMethod(HTTP_METHOD_POST);
            headersDto.setObjectResponse(String.class);
    
            RequestDto requestDto = new RequestDto();
            requestDto.setExpectedCode(OK_200);
            requestDto.setUrl("http://localhost:35000");
            requestDto.setUri("/api/auth/basic");
            requestDto.setId(""); // /api/auth/basic/{id}
            requestDto.setDataRequest(null);
            requestDto.setExpectedMessage(null);
    
            Object response = codexsTesterExternalDispatcher(requestDto, headersDto).getBody();
    
            codexsTesterAssertGuid(String.valueOf(response), null);
        }
    
        /**
         * JWT Auth Example
         * */
        @Test
        public void whenAnyRequest_JwtAuth_Correctly_RetrieveCreated_StatusCode200_GET() throws Exception {
            HeadersDto headersDto = new HeadersDto();
            headersDto.setAuthorizationBearer(securitySetup.jwtAuthorization("local", false)); //JWT AUTH
            headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
            headersDto.setHttpMethod(HTTP_METHOD_GET);
            headersDto.setObjectResponse(String.class);
    
            RequestDto requestDto = new RequestDto();
            requestDto.setExpectedCode(OK_200);
            requestDto.setUrl("http://localhost:35000");
            requestDto.setUri("/api/auth/jwt/check");
            requestDto.setId(""); // /api/auth/basic/{id}
            requestDto.setDataRequest(null);
            requestDto.setExpectedMessage(null);
    
            Object response = codexsTesterExternalDispatcher(requestDto, headersDto).getBody();
    
            codexsTesterAssertExact("OK", String.valueOf(response), null);
        }
    
        /**
         * LOGIN Web Example - using Selenium
         * */
        private static CodexsBrowserForSeleniumDto getCodexsBrowserForSeleniumDto(CodexsBrowserForSelenium browser) {
            CodexsBrowserForSeleniumDto codexsBrowserDto = new CodexsBrowserForSeleniumDto();
    
            /* > CHROME */
            if (browser.equals(CodexsBrowserForSelenium.CHROME)) {
                codexsBrowserDto.setBrowser(CodexsBrowserForSelenium.CHROME);
                codexsBrowserDto.setQuietMode(false);
                codexsBrowserDto.setOptions(Collections.singletonList("--remote-allow-origins=*"));
                codexsBrowserDto.setWebDriverName("webdriver.chrome.driver");
                codexsBrowserDto.setWebDriverPath("/usr/bin/chromedriver");
                return codexsBrowserDto;
            }
    
            /* > FIREFOX
             * NOTE: It is required to install the gecko driver for firefox
             * https://github.com/mozilla/geckodriver/releases
             */
            if (browser.equals(CodexsBrowserForSelenium.FIREFOX)) {
                codexsBrowserDto.setBrowser(CodexsBrowserForSelenium.FIREFOX);
                codexsBrowserDto.setQuietMode(false);
                codexsBrowserDto.setWebDriverName("webdriver.gecko.driver");
                codexsBrowserDto.setWebDriverPath("/home/jereelton/.mozilla/webdriver/geckodriver");
                return codexsBrowserDto;
            }
    
            /* > OPERA
             * NOTE: It is required to install the operadriver
             * https://github.com/operasoftware/operachromiumdriver/releases
             */
            if (browser.equals(CodexsBrowserForSelenium.OPERA)) {
                codexsBrowserDto.setBrowser(CodexsBrowserForSelenium.OPERA);
                codexsBrowserDto.setQuietMode(false);
                codexsBrowserDto.setOptions(Collections.singletonList("--remote-allow-origins=*"));
                codexsBrowserDto.setWebDriverName("webdriver.opera.driver");
                codexsBrowserDto.setWebDriverPath("/home/jereelton/.local/bin/operalinux/operadriver");
                codexsBrowserDto.setBrowserBinaryPath("/usr/bin/opera");
                return codexsBrowserDto;
            }
    
            throw new RuntimeException("[Critical Error] Invalid Browser: " + browser.name());
        }
    
        private void loginRequest(String username, String password, boolean status, CodexsBrowserForSelenium browser) {
    
            //DATA-----------------
            CodexsBrowserForSeleniumDto codexsBrowserDto = getCodexsBrowserForSeleniumDto(browser);
    
            //SETUP-----------------
            CodexsWebControl codexsWebControl = new CodexsWebControl(codexsBrowserDto);
            codexsWebControl.browserSetup();
    
            //NAVIGATE--------------------
            codexsWebControl.navigate("https://practicetestautomation.com/practice-test-login/");
    
            //OPERATION-----------------
            WebElement usernameField = codexsWebControl.await().until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(inputElement("username"))));
            usernameField.sendKeys(username);
    
            WebElement passwordField = codexsWebControl.await().until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(inputElement("password"))));
            passwordField.sendKeys(password);
    
            WebElement submitButton = codexsWebControl.await().until(
                    ExpectedConditions.elementToBeClickable(By.xpath(buttonElement("submit"))));
            submitButton.click();
    
            //ASSERT--------------------
            if (status) {
    
                // When login is successfully the button logout is visible
                WebElement logoutButton = codexsWebControl.await().until(
                        ExpectedConditions.elementToBeClickable(By.xpath(aElement("Log out"))));
    
                codexsTesterAssertBool(status, logoutButton.isDisplayed(), null);
    
            } else {
    
                // When login is wrong the p=text with id=error is visible
                WebElement loginError = codexsWebControl.await().until(
                        ExpectedConditions.elementToBeClickable(By.xpath(divElement("error"))));
    
                codexsTesterAssertBool(true, loginError.isDisplayed(), null);
    
                if (loginError.getText().contains("username")) {
                    codexsTesterAssertExact("Your username is invalid!", loginError.getText(), null);
                } else {
                    codexsTesterAssertExact("Your password is invalid!", loginError.getText(), null);
                }
    
            }
    
            //FINISH--------------------
            codexsWebControl.finish();
    
        }
    
        @Test
        public void whenLoginIsRequest_WithCorrectUsernamePassword_CHROMEBROWSER_SELENIUM_WEB_Test() {
    
            String username = "student";
            String password = "Password123";
            loginRequest(username, password, true, CodexsBrowserForSelenium.CHROME);
    
        }
    
    }

</code>

The external tests are very useful to verify the online service as mentioned early in this documentation and seems 
a little bit with the internal tests. However, it is necessary to say one more thing about that: The external tests 
afford one security resource named SecuritySetup that has as single purpose to offer one simple and understandable 
way to write security code for authentication, for example, Basic Auth, JWT Token and OAuth2.

All this is accessible in this piece of code:

<code>

        SecuritySetup securitySetup;
    
        @Before
        public void setup() {
            this.securitySetup = new SecuritySetup();
        }

</code>

> See more details in the Security session below

### Cucumber

Codexstester afford the Behavior Driven Development using Cucumber, and you can write yous specifications, stespdef, 
runner and generate reports in pdf, image, html and json output formats.

In order to use Cucumber in the Codexstester ecosystem see the CODEXSTESTER-GENERATOR documentation to understand a lot 
more how to write and configure the Codexstester + Cucumber. However, you can keep in your mind that the Codexstester 
afford the following Cucumber resources:

- Features
- Runner Tests
- Step Definitions
- Properties Report

When you start a codexstester generator for the first time (using the --install parameter), the following directory 
structures for cucumber scope are created. Realize that the cucumber structure contains the sample files that has the 
only purpose to offer assistance for developers in sample cases, and also one initial application scope tests called App. 
These file can be removed or changed, it is just for compare and take notes, nut be careful when you will do this to 
avoid mistakes and broke the tests workspace. Below is the Cucumber structure inside the Codexstester:

<pre>
src
└── test
    ├── java
    │   └── codexstester
    │       ├── bdd
    │       │   ├── runner
    │       │   │   ├── AppReportRunnerTest.java
    │       │   │   ├── AppRunnerTest.java
    │       │   │   └── samples
    │       │   │       ├── BackgroundReportRunnerTest.java
    │       │   │       ├── BackgroundRunnerTest.java
    │       │   │       ├── CounterReportRunnerTest.java
    │       │   │       ├── CounterRunnerTest.java
    │       │   │       ├── DataTableReportRunnerTest.java
    │       │   │       ├── DataTableRunnerTest.java
    │       │   │       ├── LambdaReportRunnerTest.java
    │       │   │       ├── LambdaRunnerTest.java
    │       │   │       ├── ListReportRunnerTest.java
    │       │   │       ├── ListRunnerTest.java
    │       │   │       ├── LoginReportRunnerTest.java
    │       │   │       └── LoginRunnerTest.java
    │       │   └── stepsdef
    │       │       ├── AppSteps.java
    │       │       └── samples
    │       │           ├── BackgroundAPISteps.java
    │       │           ├── BackgroundDBSteps.java
    │       │           ├── BackgroundUserSteps.java
    │       │           ├── CounterSteps.java
    │       │           ├── DataTableSteps.java
    │       │           ├── integration
    │       │           │   └── CucumberSpringIntegration.java # The main file to get correct integration for Cucumber + Spring Boot
    │       │           ├── LambdaSteps.java
    │       │           ├── ListSteps.java
    │       │           └── LoginSteps.java
    │       └── setup # This folder has shared resources (be careful)
    │           ├── advanced
    │           │   └── AdvancedSetup.java
    │           ├── datasource
    │           │   └── SampleDataSource.java
    │           └── security
    │               └── SecuritySetup.java
    └── resources
        ├── features
        │   ├── app
        │   │   └── App.feature
        │   └── samples
        │       ├── background
        │       │   └── Background.feature
        │       ├── counter
        │       │   ├── Counter.feature
        │       │   └── CounterPlus.feature
        │       ├── datatable
        │       │   └── DataTable.feature
        │       ├── lambda
        │       │   └── Lambda.feature
        │       ├── list
        │       │   └── List.feature
        │       └── login
        │           └── Login.feature
        ├── extent.properties
        ├── junit-platform.properties
        └── reporting
            ├── extent-config.xml
            └── html-config.xml
</pre>

- Cucumber Example

> Feature File

<pre>
src
└── test
    └── resources
        ├── features
        │   ├── app
        │   │   └── App.feature
</pre>

<pre>

# language: en

  @App
  Feature: App

    @AppScenario
    Scenario: App Testing
      Given the app is starting
      When everything is done
      Then say "Hello, welcome to App"

</pre>

> Step File

<pre>
src
└── test
    ├── java
    │   └── codexstester
    │       ├── bdd
    │       │   └── stepsdef
    │       │       ├── AppSteps.java
</pre>

<code>

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

</code>

> Runner Test File

<pre>
src
└── test
    ├── java
    │   └── codexstester
    │       ├── bdd
    │       │   ├── runner
    │       │   │   ├── AppRunnerTest.java
</pre>

<code>

    package codexstester.bdd.runner;

    import io.cucumber.junit.Cucumber;
    import io.cucumber.junit.CucumberOptions;
    import org.junit.runner.RunWith;
    
    @RunWith(Cucumber.class)
    @CucumberOptions(
    glue = "codexstester.bdd.stepsdef",
    features = "src/test/resources/features/app/App.feature",
    plugin = {"pretty", "html:target/cucumber-reports/app-report.html"},
    tags = "@AppScenario"
    )
    public class AppRunnerTest {
    }

</code>
 
> Runner Report Test File

<pre>
src
└── test
    ├── java
    │   └── codexstester
    │       ├── bdd
    │       │   ├── runner
    │       │   │   ├── AppReportRunnerTest.java
</pre>

<code>

    package codexstester.bdd.runner;

    import org.junit.platform.suite.api.ConfigurationParameter;
    import org.junit.platform.suite.api.SelectClasspathResource;
    import org.junit.platform.suite.api.Suite;
    
    import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
    
    @Suite
    @SelectClasspathResource("features/app")
    @ConfigurationParameter(
    key = GLUE_PROPERTY_NAME,
    value = "codexstester.bdd.stepsdef"
    )
    public class AppReportRunnerTest {
    }

</code>

> Report Tests Configuration

<pre>
src
└── test
    └── resources
        ├── extent.properties
        ├── junit-platform.properties
        └── reporting
            ├── extent-config.xml
            └── html-config.xml
</pre>

The extent.properties file is the most important in this scope, you can define in it some of the configurations that 
offer a lit bit more control in the report generation, give a look at the sample below to get more details:

<pre>
extent.reporter.klov.start=true
extent.reporter.spark.start=true
extent.reporter.json.start=true
extent.reporter.pdf.start=true
extent.reporter.html.start=true

extent.reporter.klov.config=src/test/resources/klov.properties
extent.reporter.html.config=src/test/resources/reporting/html-config.xml

extent.reporter.html.out=test-output/Html/ExtentHtml.html
extent.reporter.json.out=test-output/Json/ExtentJson.json
extent.reporter.pdf.out=test-output/PdfReport/ExtentPdf.pdf

#test,exception,category,device,author,log,dashboard - default order
extent.reporter.spark.vieworder=dashboard,test,category,exception,author,device,log

#embed image as base64 string src. Default is false
#extent.reporter.spark.base64imagesrc=true

screenshot.dir=test-output/
screenshot.rel.path=../

systeminfo.os=Linux Ubuntu
systeminfo.version=22.04 LTS

#this is optional and creates the report in base folder name with date time format
basefolder.name=ExtentReports/CodexsTesterReport
basefolder.datetimepattern=yyyyMMddHHmmss
</pre>

### Database

![check-red.png](media/check-red.png) Unavailable yet


### Broker

![check-red.png](media/check-red.png) Unavailable yet

### Container

![check-red.png](media/check-red.png) Unavailable yet

### Web

![check-red.png](media/check-red.png) Unavailable yet


# Security

When it is required to make tests throughout the internet using for example REST over the HTTP Protocol, worries about 
safety connection and data protection is very common and important. So in this way the Codexstester was designed to 
offer some kind of security for connections as Basic Authentication and JWT Web Token. In this section we will to see 
how to implement that resource to protect our request even using in the test scope or environment.

The Codexstester offer the following security data protection:

- Basic Auth
- JWT Token
- OAuth2
- AWS Secret (IAM) - This is not available yet, it will be affordable in the Codexstester thirds releases, but can be already considered.

Below is a sample data protection using the Codexstester Security, the path for this file is

<pre>
└── test
    ├── java
    │   └── codexstester
    │       └── setup
    │           └── security
    │               └── SecuritySetup.java
</pre>

and the sample content is something like below

<code>

    package codexstester.setup.security;

    import com.huntercodexs.codexstester.dto.JwtResponseDto;
    import com.huntercodexs.codexstester.security.AuthType;
    import com.huntercodexs.codexstester.security.CodexsSecurity;
    import com.huntercodexs.codexstester.security.CodexsSecuritySetup;
    import com.huntercodexs.codexstester.security.dto.BasicAuthRequestDto;
    import com.huntercodexs.codexstester.security.dto.JwtAuthRequestDto;
    import com.huntercodexs.codexstester.security.dto.Oauth2RequestCheckTokenDto;
    import com.huntercodexs.codexstester.security.dto.Oauth2RequestTokenDto;
    
    import java.util.Base64;
    import java.util.HashMap;
    
    public class SecuritySetup implements CodexsSecuritySetup {
    
        private boolean rsa;
    
        /**
         * SECURITY SETTINGS (Change it as needed)
         * WARNING: DO NOT REMOVE THESE METHODS JUST CHANGE IT
         **/
    
        @Override
        public Oauth2RequestTokenDto oauth2Token(String env) {
    
            HashMap<String, String> header = new HashMap<>();
            Oauth2RequestTokenDto oauth2RequestTokenDto = new Oauth2RequestTokenDto();
    
            switch (env) {
                case "local":
                    /*Sample 1*/
                    /*oauth2RequestTokenDto.setUrl("http://localhost:33100/huntercodexs/arch-demo/service-authorizator/api/rest/oauth/v1/oauth/token");
                    oauth2RequestTokenDto.setGrant("password");
                    oauth2RequestTokenDto.setUser("OAUTH2DEMO_USER");
                    oauth2RequestTokenDto.setPass("1234567890");
                    oauth2RequestTokenDto.setAuth("Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==");
                    oauth2RequestTokenDto.setClientId("arch_demo_client_1");
                    oauth2RequestTokenDto.setSecret("11111111-2222-3333-4444-555555555555");
    
                    header = new HashMap<>();
                    header.put("Access-Code", "XYZ-123");
                    oauth2RequestTokenDto.setAddHeader(header);*/
    
                    /*Sample 2 - Without additional header*/
                    oauth2RequestTokenDto.setUrl("http://localhost:33100/huntercodexs/server/api/rest/oauth/v1/oauth/token");
                    oauth2RequestTokenDto.setAuth("Basic Y2xpZW50X2lkOmNiZmNjNzRiLTA3Y2QtNGFiYi05MDZiLWFiZGRkOGZhMWJlYw==");
                    oauth2RequestTokenDto.setGrant("password");
                    oauth2RequestTokenDto.setUser("OAUTH2DEMO_USER");
                    oauth2RequestTokenDto.setPass("1234567890");
                    oauth2RequestTokenDto.setClientId("client_id");
                    oauth2RequestTokenDto.setSecret("cbfcc74b-07cd-4abb-906b-abddd8fa1bec");
    
                    return oauth2RequestTokenDto;
                case "dev":
                    oauth2RequestTokenDto.setUrl("http://192.168.0.174:33001/huntercodexs/arch-demo/service-authorizator/api/rest/oauth/v1/oauth/token");
                    oauth2RequestTokenDto.setAuth("Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==");
                    oauth2RequestTokenDto.setGrant("password");
                    oauth2RequestTokenDto.setUser("OAUTH2DEMO_USER");
                    oauth2RequestTokenDto.setPass("1234567890");
                    oauth2RequestTokenDto.setClientId("arch_demo_client_1");
                    oauth2RequestTokenDto.setSecret("11111111-2222-3333-4444-555555555555");
    
                    header = new HashMap<>();
                    header.put("Access-Code", "XYZ-123");
                    oauth2RequestTokenDto.setAddHeader(header);
    
                    return oauth2RequestTokenDto;
                case "test":
                case "homolog":
                    oauth2RequestTokenDto.setUrl("http://homolog.huntercodexs.com/huntercodexs/arch-demo/service-authorizator/api/rest/oauth/v1/oauth/token");
                    oauth2RequestTokenDto.setAuth("Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==");
                    oauth2RequestTokenDto.setGrant("password");
                    oauth2RequestTokenDto.setUser("OAUTH2DEMO_USER");
                    oauth2RequestTokenDto.setPass("1234567890");
                    oauth2RequestTokenDto.setClientId("arch_demo_client_1");
                    oauth2RequestTokenDto.setSecret("11111111-2222-3333-4444-555555555555");
    
                    header = new HashMap<>();
                    header.put("Access-Code", "XYZ-123");
                    oauth2RequestTokenDto.setAddHeader(header);
    
                    return oauth2RequestTokenDto;
                case "prod":
                    oauth2RequestTokenDto.setUrl("http://api.huntercodexs.com/huntercodexs/arch-demo/service-authorizator/api/rest/oauth/v1/oauth/token");
                    oauth2RequestTokenDto.setAuth("Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==");
                    oauth2RequestTokenDto.setGrant("password");
                    oauth2RequestTokenDto.setUser("OAUTH2DEMO_USER");
                    oauth2RequestTokenDto.setPass("1234567890");
                    oauth2RequestTokenDto.setClientId("arch_demo_client_1");
                    oauth2RequestTokenDto.setSecret("11111111-2222-3333-4444-555555555555");
    
                    header = new HashMap<>();
                    header.put("Access-Code", "XYZ-123");
                    oauth2RequestTokenDto.setAddHeader(header);
    
                    return oauth2RequestTokenDto;
            }
            return null;
        }
    
        @Override
        public Oauth2RequestCheckTokenDto oauth2CheckToken(String env, String token) {
    
            HashMap<String, String> header = new HashMap<>();
            Oauth2RequestCheckTokenDto oauth2RequestCheckTokenDto = new Oauth2RequestCheckTokenDto();
    
            switch (env) {
                case "local":
                    /*Sample 1*/
                    /*oauth2RequestCheckTokenDto.setUrl("http://localhost:33001/huntercodexs/arch-demo/service-authorizator/api/rest/oauth/v1/oauth/check_token");
                    oauth2RequestCheckTokenDto.setAuthorization("Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==");
                    oauth2RequestCheckTokenDto.setClientId("arch_demo_client_1");
                    oauth2RequestCheckTokenDto.setSecret("11111111-2222-3333-4444-555555555555");
                    oauth2RequestCheckTokenDto.setToken(token);
    
                    header = new HashMap<>();
                    header.put("Access-Code", "XYZ-123");
                    oauth2RequestCheckTokenDto.setAddHeader(header);*/
    
                    /*Sample 2 - Without additional header*/
                    oauth2RequestCheckTokenDto.setUrl("http://localhost:33100/huntercodexs/server/api/rest/oauth/v1/oauth/check_token");
                    oauth2RequestCheckTokenDto.setAuthorization("Basic Y2xpZW50X2lkOmNiZmNjNzRiLTA3Y2QtNGFiYi05MDZiLWFiZGRkOGZhMWJlYw==");
                    oauth2RequestCheckTokenDto.setClientId("client_id");
                    oauth2RequestCheckTokenDto.setSecret("cbfcc74b-07cd-4abb-906b-abddd8fa1bec");
                    oauth2RequestCheckTokenDto.setToken(token);
    
                    return oauth2RequestCheckTokenDto;
                case "dev":
                    oauth2RequestCheckTokenDto.setUrl("http://192.168.0.174:33001/huntercodexs/arch-demo/service-authorizator/api/rest/oauth/v1/oauth/check_token");
                    oauth2RequestCheckTokenDto.setAuthorization("Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==");
                    oauth2RequestCheckTokenDto.setToken(token);
                    oauth2RequestCheckTokenDto.setClientId("arch_demo_client_1");
                    oauth2RequestCheckTokenDto.setSecret("11111111-2222-3333-4444-555555555555");
    
                    header = new HashMap<>();
                    header.put("Access-Code", "XYZ-123");
                    oauth2RequestCheckTokenDto.setAddHeader(header);
    
                    return oauth2RequestCheckTokenDto;
                case "test":
                case "homolog":
                    oauth2RequestCheckTokenDto.setUrl("http://homolog.huntercodexs.com/huntercodexs/arch-demo/service-authorizator/api/rest/oauth/v1/oauth/check_token");
                    oauth2RequestCheckTokenDto.setAuthorization("Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==");
                    oauth2RequestCheckTokenDto.setToken(token);
                    oauth2RequestCheckTokenDto.setClientId("arch_demo_client_1");
                    oauth2RequestCheckTokenDto.setSecret("11111111-2222-3333-4444-555555555555");
    
                    header = new HashMap<>();
                    header.put("Access-Code", "XYZ-123");
                    oauth2RequestCheckTokenDto.setAddHeader(header);
    
                    return oauth2RequestCheckTokenDto;
                case "prod":
                    oauth2RequestCheckTokenDto.setUrl("http://api.huntercodexs.com/huntercodexs/arch-demo/service-authorizator/api/rest/oauth/v1/oauth/check_token");
                    oauth2RequestCheckTokenDto.setAuthorization("Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==");
                    oauth2RequestCheckTokenDto.setToken(token);
                    oauth2RequestCheckTokenDto.setClientId("arch_demo_client_1");
                    oauth2RequestCheckTokenDto.setSecret("11111111-2222-3333-4444-555555555555");
    
                    header = new HashMap<>();
                    header.put("Access-Code", "XYZ-123");
                    oauth2RequestCheckTokenDto.setAddHeader(header);
    
                    return oauth2RequestCheckTokenDto;
            }
    
            return null;
        }
    
        @Override
        public BasicAuthRequestDto basicAuth(String env) {
    
            HashMap<String, String> header;
            BasicAuthRequestDto basicAuthRequestDto = new BasicAuthRequestDto();
    
            switch (env) {
                case "local":
    
                    header = new HashMap<>();
                    header.put("Access-Code", "XYZ-123");
                    basicAuthRequestDto.setAddHeader(header);
    
                    basicAuthRequestDto.setUrl("http://localhost:35000/api/auth/basic");
                    basicAuthRequestDto.setUsername("username");
                    basicAuthRequestDto.setPassword("password");
                    basicAuthRequestDto.setAddHeader(header);
                    return basicAuthRequestDto;
    
                case "dev":
    
                    header = new HashMap<>();
                    header.put("Access-Code", "XYZ-123");
                    basicAuthRequestDto.setAddHeader(header);
    
                    basicAuthRequestDto.setUrl("http://192.168.0.174:35000/api/auth/basic");
                    basicAuthRequestDto.setUsername("username");
                    basicAuthRequestDto.setPassword("password");
                    basicAuthRequestDto.setAddHeader(header);
                    return basicAuthRequestDto;
    
                case "test":
                case "homolog":
    
                    header = new HashMap<>();
                    header.put("Access-Code", "XYZ-123");
                    basicAuthRequestDto.setAddHeader(header);
    
                    basicAuthRequestDto.setUrl("http://homolog.huntercodexs.com/api/auth/basic");
                    basicAuthRequestDto.setUsername("username");
                    basicAuthRequestDto.setPassword("password");
                    basicAuthRequestDto.setAddHeader(header);
                    return basicAuthRequestDto;
    
                case "prod":
    
                    header = new HashMap<>();
                    header.put("Access-Code", "XYZ-123");
                    basicAuthRequestDto.setAddHeader(header);
    
                    basicAuthRequestDto.setUrl("http://api.huntercodexs.com/api/auth/basic");
                    basicAuthRequestDto.setUsername("username");
                    basicAuthRequestDto.setPassword("password");
                    basicAuthRequestDto.setAddHeader(header);
                    return basicAuthRequestDto;
    
            }
    
            return basicAuthRequestDto;
        }
    
        @Override
        public JwtAuthRequestDto jwtAuth(String env) {
    
            HashMap<String, String> header;
            JwtAuthRequestDto jwtAuthRequestDto = new JwtAuthRequestDto();
    
            switch (env) {
                case "local":
    
                    header = new HashMap<>();
                    header.put("Access-Code", "XYZ-123");
                    jwtAuthRequestDto.setAddHeader(header);
    
                    // RS512, RSA
                    if (this.rsa) {
                        jwtAuthRequestDto.setUrl("http://localhost:35000/api/auth/jwt-assign");
                    } else {
                        // HS512 (NOT RSA)
                        jwtAuthRequestDto.setUrl("http://localhost:35000/api/auth/jwt");
                    }
    
                    jwtAuthRequestDto.setUsername("username");
                    jwtAuthRequestDto.setPassword("password");
                    jwtAuthRequestDto.setAuthType(AuthType.NONE);
                    jwtAuthRequestDto.setAuthType(AuthType.BASIC);
                    jwtAuthRequestDto.setAuthType(AuthType.BEARER);
                    jwtAuthRequestDto.setAuthType(AuthType.USERNAME_PASSWORD);
                    jwtAuthRequestDto.setBearerToken("");
                    jwtAuthRequestDto.setBasicAuth("");
                    jwtAuthRequestDto.setAddHeader(header);
                    return jwtAuthRequestDto;
    
                case "dev":
    
                    header = new HashMap<>();
                    header.put("Access-Code", "XYZ-123");
                    jwtAuthRequestDto.setAddHeader(header);
    
                    jwtAuthRequestDto.setUrl("http://192.168.0.174:35000/api/auth/jwt");
                    jwtAuthRequestDto.setUsername("username");
                    jwtAuthRequestDto.setPassword("password");
                    jwtAuthRequestDto.setAuthType(AuthType.NONE);
                    jwtAuthRequestDto.setAuthType(AuthType.BASIC);
                    jwtAuthRequestDto.setAuthType(AuthType.BEARER);
                    jwtAuthRequestDto.setAuthType(AuthType.USERNAME_PASSWORD);
                    jwtAuthRequestDto.setBearerToken("");
                    jwtAuthRequestDto.setBasicAuth("");
                    jwtAuthRequestDto.setAddHeader(header);
                    return jwtAuthRequestDto;
    
                case "test":
                case "homolog":
    
                    header = new HashMap<>();
                    header.put("Access-Code", "XYZ-123");
                    jwtAuthRequestDto.setAddHeader(header);
    
                    jwtAuthRequestDto.setUrl("http://homolog.huntercodexs.com/api/auth/jwt");
                    jwtAuthRequestDto.setUsername("username");
                    jwtAuthRequestDto.setPassword("password");
                    jwtAuthRequestDto.setAuthType(AuthType.NONE);
                    jwtAuthRequestDto.setAuthType(AuthType.BASIC);
                    jwtAuthRequestDto.setAuthType(AuthType.BEARER);
                    jwtAuthRequestDto.setAuthType(AuthType.USERNAME_PASSWORD);
                    jwtAuthRequestDto.setBearerToken("");
                    jwtAuthRequestDto.setBasicAuth("");
                    jwtAuthRequestDto.setAddHeader(header);
                    return jwtAuthRequestDto;
    
                case "prod":
    
                    header = new HashMap<>();
                    header.put("Access-Code", "XYZ-123");
                    jwtAuthRequestDto.setAddHeader(header);
    
                    jwtAuthRequestDto.setUrl("http://api.huntercodexs.com/api/auth/jwt");
                    jwtAuthRequestDto.setUsername("username");
                    jwtAuthRequestDto.setPassword("password");
                    jwtAuthRequestDto.setAuthType(AuthType.NONE);
                    jwtAuthRequestDto.setAuthType(AuthType.BASIC);
                    jwtAuthRequestDto.setAuthType(AuthType.BEARER);
                    jwtAuthRequestDto.setAuthType(AuthType.USERNAME_PASSWORD);
                    jwtAuthRequestDto.setBearerToken("");
                    jwtAuthRequestDto.setBasicAuth("");
                    jwtAuthRequestDto.setAddHeader(header);
                    return jwtAuthRequestDto;
    
            }
    
            return jwtAuthRequestDto;
        }
    
        public String oauth2Authorization(String env) {
            CodexsSecurity codexsSecurity = new CodexsSecurity(oauth2Token(env));
            return codexsSecurity.token();
        }
    
        public String basicAuthorization(String env) {
            BasicAuthRequestDto basicAuthRequestDto = basicAuth(env);
    
            String login = basicAuthRequestDto.getUsername()+":"+basicAuthRequestDto.getPassword();
            byte[] inputBytes = login.getBytes();
            byte[] base64InputBytes = Base64.getEncoder().encode(inputBytes);
    
            return new String(base64InputBytes);
        }
    
        public String jwtAuthorization(String env, boolean rsa) {
            this.rsa = rsa;
            JwtAuthRequestDto jwtAuthRequestDto = jwtAuth(env);
            CodexsSecurity codexsSecurity = new CodexsSecurity(jwtAuthRequestDto);
    
            JwtResponseDto response = codexsSecurity.jwtAuth(JwtResponseDto.class);
            return response.getJwt();
        }
    
    }

</code>

As we can see there is one interface named CodexsSecuritySetup witch implement the methods

<code>

    Oauth2RequestTokenDto oauth2Token(String env);
    Oauth2RequestCheckTokenDto oauth2CheckToken(String env, String token);
    BasicAuthRequestDto basicAuth(String env);
    JwtAuthRequestDto jwtAuth(String env);

</code>

The parameter is free to set, you can use according you need but some values are very common to use in that:

- local
- dev
- stage
- prod

fell free to use anyone that you want to but for now, add this request in your files tests, for example

<code>

    SecuritySetup securitySetup;

    @Before
    public void setup() {
        this.securitySetup = new SecuritySetup();
    }

    /**
     * OAuth2 Example
     * */
    @Test
    public void whenAnyRequest_Oauth2_Correctly_RetrieveCreated_StatusCode200_GET() throws Exception {
        QuickJson dataRequest = new QuickJson("{\"test\":\"123 testing...\"}");

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBearer(securitySetup.oauth2Authorization("local"));//OAUTH2
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_GET);
        headersDto.setObjectResponse(String.class);

        RequestDto requestDto = new RequestDto();
        requestDto.setExpectedCode(OK_200);
        requestDto.setUrl("http://localhost:33009");
        requestDto.setUri("/huntercodexs/client/api/admin");
        requestDto.setId(""); // /huntercodexs/client/api/admin/{id}
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage("Admin is running on OAUTH2-CLIENT-DEMO");

        Object response = codexsTesterExternalDispatcher(requestDto, headersDto).getBody();

        codexsTesterAssertExact("Admin is running on OAUTH2-CLIENT-DEMO", String.valueOf(response), null);
    }

    /**
     * Basic Auth Example
     * */
    @Test
    public void whenAnyRequest_BasicAuth_Correctly_RetrieveCreated_StatusCode200_GET() throws Exception {
        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBasic(securitySetup.basicAuthorization("local")); //BASIC AUTH
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_POST);
        headersDto.setObjectResponse(String.class);

        RequestDto requestDto = new RequestDto();
        requestDto.setExpectedCode(OK_200);
        requestDto.setUrl("http://localhost:35000");
        requestDto.setUri("/api/auth/basic");
        requestDto.setId(""); // /api/auth/basic/{id}
        requestDto.setDataRequest(null);
        requestDto.setExpectedMessage(null);

        Object response = codexsTesterExternalDispatcher(requestDto, headersDto).getBody();

        codexsTesterAssertGuid(String.valueOf(response), null);
    }

    /**
     * JWT Auth Example
     * */
    @Test
    public void whenAnyRequest_JwtAuth_Correctly_RetrieveCreated_StatusCode200_GET() throws Exception {
        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBearer(securitySetup.jwtAuthorization("local", false)); //JWT AUTH
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_GET);
        headersDto.setObjectResponse(String.class);

        RequestDto requestDto = new RequestDto();
        requestDto.setExpectedCode(OK_200);
        requestDto.setUrl("http://localhost:35000");
        requestDto.setUri("/api/auth/jwt/check");
        requestDto.setId(""); // /api/auth/basic/{id}
        requestDto.setDataRequest(null);
        requestDto.setExpectedMessage(null);

        Object response = codexsTesterExternalDispatcher(requestDto, headersDto).getBody();

        codexsTesterAssertExact("OK", String.valueOf(response), null);
    }

</code>

Give a close in the code block above and see slightly careful the line when say

<pre>
headersDto.setAuthorizationBearer(securitySetup.oauth2Authorization("local")); //OAUTH2
</pre>

or

<pre>
headersDto.setAuthorizationBearer(securitySetup.jwtAuthorization("local", false)); //JWT AUTH
</pre>

or 

<pre>
headersDto.setAuthorizationBasic(securitySetup.basicAuthorization("local")); //BASIC AUTH
</pre>

all these lines means thar you want to use the certain kind of authentication, and you are passing the value received 
in the process of authentication previously made by Codexstester according the security configurations present in the
SecuritySetup java class file.

With this scenario it is very possible to use many types of authentication in the same test or in the same flow, just 
keep in your mind that this process is required in case the service integration expects some kind of authentication.

************************************************************************************************************************

# Releases

Here you can find the latest release of codexstester according your application requirements.


- Release 2.0.11

<a href="https://github.com/huntercodexs/codexstester/releases/tag/r.2.0.11">
  Java 21 - Spring Boot 3.3.2 - Cucumber 7.12.0 - 2.0.11-SNAPSHOT
</a>

- Release 2.0.10

<a href="https://github.com/huntercodexs/codexstester/releases/tag/r.2.0.10">
  Java 21 - Spring Boot 3.3.2 - Cucumber 7.10.1 - 2.0.10-SNAPSHOT
</a>

- Release 2.0.9

<a href="https://github.com/huntercodexs/codexstester/releases/tag/r.2.0.9">
  Java 17 - Spring Boot 2.6.4 - Cucumber 7.12.0 - 2.0.9-SNAPSHOT
</a>

- Release 2.0.8

<a href="https://github.com/huntercodexs/codexstester/releases/tag/r.2.0.8">
  Java 17 - Spring Boot 2.6.4 - Cucumber 7.10.1 - 2.0.8-SNAPSHOT
</a>

- Release 2.0.7

<a href="https://github.com/huntercodexs/codexstester/releases/tag/r.2.0.7">
  Java 17 - Spring Boot 2.6.4 - Cucumber 4.2.0 - 2.0.7-SNAPSHOT
</a>

- Release 2.0.6

<a href="https://github.com/huntercodexs/codexstester/releases/tag/r.2.0.6">
  Java 11 - Spring Boot 2.6.4 - Cucumber 7.12.0 - 2.0.6-SNAPSHOT
</a>

- Release 2.0.5

<a href="https://github.com/huntercodexs/codexstester/releases/tag/r.2.0.5">
  Java 11 - Spring Boot 2.6.4 - Cucumber 7.10.1 - 2.0.5-SNAPSHOT
</a>

- Release 2.0.4

<a href="https://github.com/huntercodexs/codexstester/releases/tag/r.2.0.4">
  Java 11 - Spring Boot 2.6.4 - Cucumber 4.2.0 - 2.0.4-SNAPSHOT
</a>
  
- Release 2.0.3

<a href="https://github.com/huntercodexs/codexstester/releases/tag/r.2.0.3">
  Java 8 - Spring Boot 2.6.4 - Cucumber 7.12.0 - 2.0.3-SNAPSHOT
</a>

- Release 2.0.2

<a href="https://github.com/huntercodexs/codexstester/releases/tag/r.2.0.2">
  Java 8 - Spring Boot 2.6.4 - Cucumber 7.10.1 - 2.0.2-SNAPSHOT
</a>

- Release 2.0.1

<a href="https://github.com/huntercodexs/codexstester/releases/tag/r.2.0.1">
  Java 8 - Spring Boot 2.6.4 - Cucumber 4.2.0 - 2.0.1-SNAPSHOT
</a>

- Release 2.0.0

<a href="https://github.com/huntercodexs/codexstester/releases/tag/r.2.0.0">
  Java 8 - Spring Boot 2.0.1.RELEASE - Cucumber 4.2.0 - 2.0.0-SNAPSHOT
</a>
  
- Release 1.0.12

<a href="https://github.com/huntercodexs/codexstester/releases/tag/r.1.0.12">
  Java 21 - 1.0.12-SNAPSHOT
</a>

- Release 1.0.11

<a href="https://github.com/huntercodexs/codexstester/releases/tag/r.1.0.11">
  Java 17 - 1.0.11-SNAPSHOT
</a>

- Release 1.0.10

<a href="https://github.com/huntercodexs/codexstester/releases/tag/r.1.0.10">
  Java 11 - 1.0.10-SNAPSHOT
</a>

- Release 1.0.9

<a href="https://github.com/huntercodexs/codexstester/releases/tag/r.1.0.9">
  Java 8 - 1.0.9-SNAPSHOT
</a>


************************************************************************************************************************
Enjoy this job as you prefer.

------------------------------------------------------------------------------------------------------------------------
* All rights reserved to Huntercodexs &copy; 2021 - Software Development
* Maintained by Jereelton Teixeira (jereelton-devel)
************************************************************************************************************************
