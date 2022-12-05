package codexstester.abstractor.properties;

public abstract class ExternalPropertyTests extends InternalPropertyTests {

    protected final String externalUrlBaseTest = externalProp.getProperty("external.tests.base-url");
    protected final String externalUriBaseTest = externalProp.getProperty("external.tests.base-uri");
    protected final String externalAuthorizationBasic = externalProp.getProperty("external.tests.header.authorization-basic");
    protected final String externalAuthorizationBasicInvalid = externalProp.getProperty("external.tests.header.authorization-basic-invalid");
    protected final String externalAuthorizationBearer = externalProp.getProperty("external.tests.header.authorization-bearer");
    protected final String externalAuthorizationBearerInvalid = externalProp.getProperty("external.tests.header.authorization-bearer-invalid");
    protected final String externalAppNameAuthorization = externalProp.getProperty("external.tests.header.api-key.app-name");
    protected final String externalTokenAuthorization = externalProp.getProperty("external.tests.header.api-key.token");
    protected final String externalSecretAuthorization = externalProp.getProperty("external.tests.header.api-key.secret");
    protected final String externalValueAuthorization = externalProp.getProperty("external.tests.header.api-key.value");
    protected final String externalGenericAuthorization = externalProp.getProperty("external.tests.header.api-key.generic");
    /*Three Additional Headers*/
    protected final String externalAdditionalHeaderName1 = externalProp.getProperty("external.tests.header.additional-name-1");
    protected final String externalAdditionalHeaderValue1 = externalProp.getProperty("external.tests.header.additional-value-1");
    protected final String externalAdditionalHeaderName2 = externalProp.getProperty("external.tests.header.additional-name-2");
    protected final String externalAdditionalHeaderValue2 = externalProp.getProperty("external.tests.header.additional-value-2");
    protected final String externalAdditionalHeaderName3 = externalProp.getProperty("external.tests.header.additional-name-3");
    protected final String externalAdditionalHeaderValue3 = externalProp.getProperty("external.tests.header.additional-value-3");
    protected final String externalAdditionalHeaderName4 = externalProp.getProperty("external.tests.header.additional-name-4");
    protected final String externalAdditionalHeaderValue4 = externalProp.getProperty("external.tests.header.additional-value-4");
    protected final String externalAdditionalHeaderName5 = externalProp.getProperty("external.tests.header.additional-name-5");
    protected final String externalAdditionalHeaderValue5 = externalProp.getProperty("external.tests.header.additional-value-5");
    /*When Query Parameters is defined*/
    protected final String externalUrlQueryParameters = externalProp.getProperty("external.tests.url.query-string-parameters");

}
