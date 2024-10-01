package codexstester.test.junit4.unitary;

import codexstester.core.util.CodexsHelper;
import codexstester.setup.bridge.PostalCodeBridgeTest;
import codexstester.setup.datasource.PostalCodeDataSource;
import com.huntercodexs.postalcode.client.PostalCodeClient;
import com.huntercodexs.postalcode.database.entity.PostalCodeEntity;
import com.huntercodexs.postalcode.database.repository.PostalCodeRepository;
import com.huntercodexs.postalcode.dto.PostalCodeRequestDto;
import com.huntercodexs.postalcode.dto.PostalCodeResponseDto;
import com.huntercodexs.postalcode.mapper.PostalCodeResponseMapper;
import com.huntercodexs.postalcode.service.PostalCodeHandlerService;
import com.huntercodexs.postalcode.service.PostalCodeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static codexstester.setup.datasource.PostalCodeDataSource.dataSourceSampleResponseJSONString;
import static codexstester.setup.datasource.PostalCodeDataSource.dataSourceSampleResponseString;
import static com.huntercodexs.postalcode.mapper.PostalCodeResponseMapper.mapperFinalResponseDtoByEntity;
import static com.huntercodexs.postalcode.mapper.PostalCodeResponseMapper.mapperInitialResponseDto;

public class PostalCodeUnitaryTests extends PostalCodeBridgeTest {

    @Autowired
    PostalCodeService postalCodeService;
    @Autowired
    PostalCodeHandlerService postalCodeHandlerService;
    @Autowired
    PostalCodeRepository postalCodeRepository;
    @Autowired
    PostalCodeClient postalCodeClient;

    @Test
    public void propsTest() {
        System.out.println(unitaryProps);
    }

    @Test
    public void whenSumAnyNumbersTest() {
        int result = postalCodeService.sum(1000, 10);
        codexsTesterAssertInt(result, 1010);
    }

    @Test
    public void whenSampleResponseMatchFromStringTest_AssertExact() {
        String sampleExpected = dataSourceSampleResponseString();
        System.out.println(sampleExpected);
        /*Use the code below to make a request for any service or any unit test code.*/
        String sampleReceived = dataSourceSampleResponseString();
        System.out.println(sampleReceived);
        codexsTesterAssertExact(sampleExpected, sampleReceived);
    }

    @Test
    public void whenSampleResponseMatchFromJSONStringTest_AssertExact() {
        String sampleExpected = dataSourceSampleResponseJSONString();
        System.out.println(sampleExpected);
        /*Use the code below to make a request for any service or any unit test code.*/
        String sampleReceived = dataSourceSampleResponseJSONString();
        System.out.println(sampleReceived);
        codexsTesterAssertExact(sampleExpected, sampleReceived);
    }

    @Test
    public void whenMapperInitialResponseDtoTest_FromPostalCodeResponseMapper_AssertExact() {
        PostalCodeResponseDto result = mapperInitialResponseDto();
        codexsTesterAssertExact(CodexsHelper.codexsHelperMd5(result.toString()), CodexsHelper.codexsHelperMd5(new PostalCodeResponseDto().toString()));
    }

    @Test
    public void whenMapperFinalResponseDtoTest_FromPostalCodeResponseMapper_AssertExact() {
        PostalCodeResponseDto postalCodeResponseDto = PostalCodeDataSource.dataSourceMapperFinalResponseDto();
        PostalCodeResponseDto result = PostalCodeResponseMapper.mapperFinalResponseDtoByNew(postalCodeResponseDto);
        codexsTesterAssertExact(CodexsHelper.codexsHelperMd5(result.toString()), CodexsHelper.codexsHelperMd5(new PostalCodeResponseDto().toString()));
    }

    @Test
    public void whenMapperFinalResponseDtoByEntityTest_FromPostalCodeResponseMapper_AssertBoolean() {
        PostalCodeEntity postalCodeEntity = PostalCodeDataSource.dataSourcePostalCodeEntityEmpty();
        mapperFinalResponseDtoByEntity(postalCodeEntity);
        codexsTesterAssertBool(true, true);
    }

    @Test
    public void whenRunPostalCodeTest_FromPostalCodeService_AssertExact() {
        PostalCodeRequestDto postalCodeRequestDto = PostalCodeDataSource.dataSourcePostalCodeRequestDto();
        postalCodeRequestDto.setPostalCode("70316109");
        ResponseEntity<PostalCodeResponseDto> result = postalCodeService.getAddress(postalCodeRequestDto);
        codexsTesterAssertExact(result.getBody().getCep().replaceAll("[^0-9]", ""), postalCodeRequestDto.getPostalCode());
    }

    @Test
    public void whenRunPostalCodeUsingInvalidCepTest_FromPostalCodeService_AssertText() {
        PostalCodeRequestDto postalCodeRequestDto = PostalCodeDataSource.dataSourcePostalCodeRequestDto();
        postalCodeRequestDto.setPostalCode("930706800");
        try {
            ResponseEntity<PostalCodeResponseDto> result = postalCodeService.getAddress(postalCodeRequestDto);
        } catch (Exception ex) {
            codexsTesterAssertExact(ex.getMessage(), "Postal Code Not Found");
        }
    }

    @Test
    public void whenRunPostalCodeUsingWrongCepTest_FromPostalCodeService_AssertExact() {
        PostalCodeRequestDto postalCodeRequestDto = PostalCodeDataSource.dataSourcePostalCodeRequestDto();
        postalCodeRequestDto.setPostalCode("62090004");
        try {
            ResponseEntity<PostalCodeResponseDto> result = postalCodeService.getAddress(postalCodeRequestDto);
        } catch (Exception ex) {
            codexsTesterAssertExact(ex.getMessage(), "Postal Code Not Found");
        }
    }

    @Rollback
    @Transactional
    public void whenSavePostalCodeTest_FromPostalCodeHandlerService_AssertTrue_Windows() {
        System.out.println(System.getProperty("os.name"));
        ResponseEntity<PostalCodeResponseDto> dataFake = PostalCodeDataSource.dataSourcePostalCodeEntityResponse();
        postalCodeHandlerService.saveAddress(dataFake);
        PostalCodeEntity result = postalCodeRepository.findByCep(dataFake.getBody().getCep());
        codexsTesterAssertExact(result.getCep(), dataFake.getBody().getCep());
    }

    public void whenSavePostalCodeTest_FromPostalCodeHandlerService_AssertTrue_Linux() {
        System.out.println(System.getProperty("os.name"));
        ResponseEntity<PostalCodeResponseDto> dataFake = PostalCodeDataSource.dataSourcePostalCodeEntityResponse();
        postalCodeHandlerService.saveAddress(dataFake);
        PostalCodeEntity result = postalCodeRepository.findByCep(dataFake.getBody().getCep());
        postalCodeRepository.deleteById(result.getId());
        codexsTesterAssertExact(result.getCep(), dataFake.getBody().getCep());
    }

    @Test
    public void whenSavePostalCodeTest_FromPostalCodeHandlerService_AssertTrue() {
        if (System.getProperty("os.name").equals("Linux")) {
            whenSavePostalCodeTest_FromPostalCodeHandlerService_AssertTrue_Linux();
        } else {
            whenSavePostalCodeTest_FromPostalCodeHandlerService_AssertTrue_Windows();
        }
    }

    @Test
    public void whenRunPostalCodeSearchTest_FromPostalCodeClient_AssertTrue() throws Exception {
        ResponseEntity<PostalCodeResponseDto> response = postalCodeClient.addressSearch("12090002");
        codexsTesterAssertExact("12090002", response.getBody().getCep().replaceAll("[^0-9]", ""));
    }
}
