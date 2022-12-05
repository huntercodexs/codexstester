package codexstester.test.internal;

import codexstester.setup.application.InternalSetupTests;
import codexstester.setup.datasource.DataSourceSampleTests;
import org.junit.Test;

public class InternalSampleTests extends InternalSetupTests {

    /**
     * DataSourcePostalCodeTests Helpers
     * THIS TESTS CAN BE REMOVED
     * */

    @Test
    public void test1xx() throws Exception {
        isOk1xxInternalTest();
    }

    @Test
    public void test2xx() throws Exception {
        isOk2xxInternalTest();
    }

    @Test
    public void test3xx() throws Exception {
        isOk3xxInternalTest();
    }

    @Test
    public void test4xx() throws Exception {
        isOk4xxInternalTest();
    }

    @Test
    public void test5xx() throws Exception {
        isOk5xxInternalTest();
    }

    /**
     * Sample ExternalSampleSetupTests
     * THESE TESTS BELOW CAN BE REMOVED OR CHANGED IF NEEDED
     * */

    @Test
    public void whenSimpleTestUsingString_AssertExact() throws Exception {
        String result = DataSourceSampleTests.dataSourceSampleResponse();
        codexsTesterAssertExact("This is a expected sample response", result);
    }

}
