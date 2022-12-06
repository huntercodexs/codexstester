package codexstester.test.unitary;

import codexstester.setup.bridge.UnitaryPostalCodeBridgeTests;
import codexstester.setup.datasource.DataSourceSampleTests;
import org.junit.Test;

public class UnitarySampleTests extends UnitaryPostalCodeBridgeTests {

    @Test
    public void whenSumAnyNumbersTest() {
        int result = DataSourceSampleTests.dataSourceSampleSum(1000, 10);
        codexsTesterAssertInt(result, 1010);
    }
}
