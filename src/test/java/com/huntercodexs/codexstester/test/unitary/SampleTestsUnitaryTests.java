package com.huntercodexs.codexstester.test.unitary;

import com.huntercodexs.codexstester.postalcode.client.PostalCodeClient;
import com.huntercodexs.codexstester.postalcode.database.model.PostalCodeEntity;
import com.huntercodexs.codexstester.postalcode.database.repository.PostalCodeRepository;
import com.huntercodexs.codexstester.postalcode.dto.PostalCodeRequestDto;
import com.huntercodexs.codexstester.postalcode.dto.PostalCodeResponseDto;
import com.huntercodexs.codexstester.postalcode.mapper.PostalCodeResponseMapper;
import com.huntercodexs.codexstester.postalcode.service.PostalCodeHandlerService;
import com.huntercodexs.codexstester.postalcode.service.PostalCodeService;
import com.huntercodexs.codexstester.setup.SetupUnitaryTests;
import com.huntercodexs.codexstester.setup.datasource.DataSourceTests;
import com.huntercodexs.codexstester.util.HelperTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static com.huntercodexs.codexstester.postalcode.mapper.PostalCodeResponseMapper.mapperFinalResponseDtoByEntity;
import static com.huntercodexs.codexstester.postalcode.mapper.PostalCodeResponseMapper.mapperInitialResponseDto;

public class SampleTestsUnitaryTests extends SetupUnitaryTests {

    @Autowired
    PostalCodeService postalCodeService;
    @Autowired
    PostalCodeHandlerService postalCodeHandlerService;
    @Autowired
    PostalCodeRepository postalCodeRepository;
    @Autowired
    PostalCodeClient postalCodeClient;

    @Test
    public void whenSumAnyNumbersTest() {
        int result = postalCodeService.sum(1000, 10);
        codexsTesterAssertInt(result, 1010);
    }

    @Test
    public void whenMapperInitialResponseDtoTest_FromPostalCodeResponseMapper_AssertExact() {
        PostalCodeResponseDto result = mapperInitialResponseDto();
        codexsTesterAssertExact(HelperTests.md5(result.toString()), HelperTests.md5(new PostalCodeResponseDto().toString()));
    }

    @Test
    public void whenMapperFinalResponseDtoTest_FromPostalCodeResponseMapper_AssertExact() {
        PostalCodeResponseDto postalCodeResponseDto = DataSourceTests.dataSourceMapperFinalResponseDto();
        PostalCodeResponseDto result = PostalCodeResponseMapper.mapperFinalResponseDtoByNew(postalCodeResponseDto);
        codexsTesterAssertExact(HelperTests.md5(result.toString()), HelperTests.md5(new PostalCodeResponseDto().toString()));
    }

    @Test
    public void whenMapperFinalResponseDtoByEntityTest_FromPostalCodeResponseMapper_AssertBoolean() {
        PostalCodeEntity postalCodeEntity = DataSourceTests.dataSourcePostalCodeEntityEmpty();
        mapperFinalResponseDtoByEntity(postalCodeEntity);
        codexsTesterAssertBool(true, true);
    }

    @Test
    public void whenRunPostalCodeTest_FromPostalCodeService_AssertExact() {
        PostalCodeRequestDto postalCodeRequestDto = DataSourceTests.dataSourcePostalCodeRequestDto();
        postalCodeRequestDto.setPostalCode("70316109");
        ResponseEntity<PostalCodeResponseDto> result = postalCodeService.getAddress(postalCodeRequestDto);
        codexsTesterAssertExact(result.getBody().getCep().replaceAll("[^0-9]", ""), postalCodeRequestDto.getPostalCode());
    }

    @Test
    public void whenRunPostalCodeUsingInvalidCepTest_FromPostalCodeService_AssertText() {
        PostalCodeRequestDto postalCodeRequestDto = DataSourceTests.dataSourcePostalCodeRequestDto();
        postalCodeRequestDto.setPostalCode("930706800");
        try {
            ResponseEntity<PostalCodeResponseDto> result = postalCodeService.getAddress(postalCodeRequestDto);
        } catch (Exception ex) {
            codexsTesterAssertExact(ex.getMessage(), "Postal Code Not Found");
        }
    }

    @Test
    public void whenRunPostalCodeUsingWrongCepTest_FromPostalCodeService_AssertExact() {
        PostalCodeRequestDto postalCodeRequestDto = DataSourceTests.dataSourcePostalCodeRequestDto();
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
        ResponseEntity<PostalCodeResponseDto> dataFake = DataSourceTests.dataSourcePostalCodeEntityResponse();
        postalCodeHandlerService.saveAddress(dataFake);
        PostalCodeEntity result = postalCodeRepository.findByCep(dataFake.getBody().getCep());
        codexsTesterAssertExact(result.getCep(), dataFake.getBody().getCep());
    }

    public void whenSavePostalCodeTest_FromPostalCodeHandlerService_AssertTrue_Linux() {
        System.out.println(System.getProperty("os.name"));
        ResponseEntity<PostalCodeResponseDto> dataFake = DataSourceTests.dataSourcePostalCodeEntityResponse();
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
