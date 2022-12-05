package codexstester.abstractor.properties;

import codexstester.abstractor.PropertiesLoaderTests;

public abstract class UnitaryPropertyTests extends PropertiesLoaderTests {

    protected final String unitaryUrlBaseTest = unitaryProp.getProperty("unitary.tests.base-url");
    protected final String unitaryUriBaseTest = unitaryProp.getProperty("unitary.tests.base-uri");
    protected final String unitaryAuthorizationBasic = unitaryProp.getProperty("unitary.tests.header.authorization-basic");
    protected final String unitaryAuthorizationBasicInvalid = unitaryProp.getProperty("unitary.tests.header.authorization-basic-invalid");
    protected final String unitaryAuthorizationBearer = unitaryProp.getProperty("unitary.tests.header.authorization-bearer");
    protected final String unitaryAuthorizationBearerInvalid = unitaryProp.getProperty("unitary.tests.header.authorization-bearer-invalid");
    protected final String unitaryAppNameAuthorization = unitaryProp.getProperty("unitary.tests.header.api-key.app-name");
    protected final String unitaryTokenAuthorization = unitaryProp.getProperty("unitary.tests.header.api-key.token");
    protected final String unitarySecretAuthorization = unitaryProp.getProperty("unitary.tests.header.api-key.secret");
    protected final String unitaryValueAuthorization = unitaryProp.getProperty("unitary.tests.header.api-key.value");
    protected final String unitaryGenericAuthorization = unitaryProp.getProperty("unitary.tests.header.api-key.generic");
    /*Three Additional Headers*/
    protected final String unitaryAdditionalHeaderName1 = unitaryProp.getProperty("unitary.tests.header.additional-name-1");
    protected final String unitaryAdditionalHeaderValue1 = unitaryProp.getProperty("unitary.tests.header.additional-value-1");
    protected final String unitaryAdditionalHeaderName2 = unitaryProp.getProperty("unitary.tests.header.additional-name-2");
    protected final String unitaryAdditionalHeaderValue2 = unitaryProp.getProperty("unitary.tests.header.additional-value-2");
    protected final String unitaryAdditionalHeaderName3 = unitaryProp.getProperty("unitary.tests.header.additional-name-3");
    protected final String unitaryAdditionalHeaderValue3 = unitaryProp.getProperty("unitary.tests.header.additional-value-3");
    protected final String unitaryAdditionalHeaderName4 = unitaryProp.getProperty("unitary.tests.header.additional-name-4");
    protected final String unitaryAdditionalHeaderValue4 = unitaryProp.getProperty("unitary.tests.header.additional-value-4");
    protected final String unitaryAdditionalHeaderName5 = unitaryProp.getProperty("unitary.tests.header.additional-name-5");
    protected final String unitaryAdditionalHeaderValue5 = unitaryProp.getProperty("unitary.tests.header.additional-value-5");
    /*When Query Parameters is defined*/
    protected final String unitaryUrlQueryParameters = externalProp.getProperty("unitary.tests.url.query-string-parameters");

}
