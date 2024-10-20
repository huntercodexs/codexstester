package codexstester.setup.datasource;

import com.huntercodexs.codexstester.resource.quickjson.QuickJson;

/**
 * SAMPLE DATA SOURCE
 * Use this file to create all tests source
 * */
public abstract class SampleDataSource {

    /*
     * DEFAULT ATTRIBUTES SAMPLES
     * */

    public static final boolean ignoreOAuth2Tests = true;
    public static final String samplePort = "33001";
    public static final String sampleEndpointUri = "/huntercodexs/anny-service/api/any-resource";
    public static final String sampleWebhookUrl = "http://your-domain.com/api/1.1/receptor";
    public static final String sampleOauth2Token = "d4cd86a0-809a-40aa-a590-ef68873dcd7b";

    /*
     * HTTP REST ENDPOINTS SAMPLES
     * */

    public static final String endpointGet = "/get";
    public static final String endpointPost = "/post";
    public static final String endpointPut = "/put";
    public static final String endpointDelete = "/delete";
    public static final String endpointPatch = "/patch";

    public static String dataSourceSampleResponse() {
        return "This is a expected sample response";
    }

    public static int dataSourceSampleSum(int a, int b) {
        return a + b;
    }

    public static String quickJsonDataSource() {
        QuickJson qj = new QuickJson();
        qj.add("name", "Jereelton");
        qj.add("age", "39");
        return qj.json();
    }

}