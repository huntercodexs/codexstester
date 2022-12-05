package codexstester.test.unitary;

import codexstester.setup.application.UnitarySetupTests;
import codexstester.setup.datasource.DataSourceSampleTests;
import org.junit.Test;

public class UnitarySampleTests extends UnitarySetupTests {

    @Test
    public void whenSumAnyNumbersTest() {
        int result = DataSourceSampleTests.dataSourceSampleSum(1000, 10);
        codexsTesterAssertInt(result, 1010);
    }
}
