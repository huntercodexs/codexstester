package codexstester.abstractor.properties;

public abstract class InternalPropertyTests extends UnitaryPropertyTests {

    protected final String internalUrlBaseTest = internalProp.getProperty("internal.tests.base-url");
    protected final String internalUriBaseTest = internalProp.getProperty("internal.tests.base-uri");
    protected final String internalAuthorizationBasic = internalProp.getProperty("internal.tests.header.authorization-basic");
    protected final String internalAuthorizationBasicInvalid = internalProp.getProperty("internal.tests.header.authorization-basic-invalid");
    protected final String internalAuthorizationBearer = internalProp.getProperty("internal.tests.header.authorization-bearer");
    protected final String internalAuthorizationBearerInvalid = internalProp.getProperty("internal.tests.header.authorization-bearer-invalid");
    protected final String internalAppNameAuthorization = internalProp.getProperty("internal.tests.header.api-key.app-name");
    protected final String internalTokenAuthorization = internalProp.getProperty("internal.tests.header.api-key.token");
    protected final String internalSecretAuthorization = internalProp.getProperty("internal.tests.header.api-key.secret");
    protected final String internalValueAuthorization = internalProp.getProperty("internal.tests.header.api-key.value");
    protected final String internalGenericAuthorization = internalProp.getProperty("internal.tests.header.api-key.generic");
    /*Three Additional Headers*/
    protected final String internalAdditionalHeaderName1 = internalProp.getProperty("internal.tests.header.additional-name-1");
    protected final String internalAdditionalHeaderValue1 = internalProp.getProperty("internal.tests.header.additional-value-1");
    protected final String internalAdditionalHeaderName2 = internalProp.getProperty("internal.tests.header.additional-name-2");
    protected final String internalAdditionalHeaderValue2 = internalProp.getProperty("internal.tests.header.additional-value-2");
    protected final String internalAdditionalHeaderName3 = internalProp.getProperty("internal.tests.header.additional-name-3");
    protected final String internalAdditionalHeaderValue3 = internalProp.getProperty("internal.tests.header.additional-value-3");
    protected final String internalAdditionalHeaderName4 = internalProp.getProperty("internal.tests.header.additional-name-4");
    protected final String internalAdditionalHeaderValue4 = internalProp.getProperty("internal.tests.header.additional-value-4");
    protected final String internalAdditionalHeaderName5 = internalProp.getProperty("internal.tests.header.additional-name-5");
    protected final String internalAdditionalHeaderValue5 = internalProp.getProperty("internal.tests.header.additional-value-5");
    /*When Query Parameters is defined*/
    protected final String internalUrlQueryParameters = externalProp.getProperty("internal.tests.url.query-string-parameters");

}
