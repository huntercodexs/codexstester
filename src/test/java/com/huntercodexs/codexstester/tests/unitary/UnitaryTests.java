//package com.huntercodexs.codexstester.tests.unitary;
//
//import com.huntercodexs.codexstester.client.AddressClient;
//import com.huntercodexs.codexstester.config.codexsresponser.errors.CodexsResponserEditableErrors;
//import com.huntercodexs.codexstester.config.codexsresponser.exception.CodexsResponserException;
//import com.huntercodexs.codexstester.database.model.AddressEntity;
//import com.huntercodexs.codexstester.database.repository.AddressRepository;
//import com.huntercodexs.codexstester.dto.AddressResponseDto;
//import com.huntercodexs.codexstester.rules.RulesService;
//import com.huntercodexs.codexstester.service.AddressService;
//import com.huntercodexs.codexstester.service.SyncService;
//import com.huntercodexs.codexstester.abstractor.AbstractUnitaryTests;
//import com.huntercodexs.codexstester.tests.datasource.DataSourceTests;
//import com.huntercodexs.codexstester.utils.HelperTests;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import static com.huntercodexs.codexstester.mapper.AddressResponseMapper.mapperFinalResponseDtoByEntity;
//import static com.huntercodexs.codexstester.mapper.AddressResponseMapper.mapperInitialResponseDto;
//
//public class UnitaryTests extends AbstractUnitaryTests {
//
//    @Autowired
//    AddressService addressService;
//    @Autowired
//    SyncService syncService;
//    @Autowired
//    RulesService rulesService;
//    @Autowired
//    AddressClient addressClient;
//    @Autowired
//    AddressRepository addressRepository;
//
//    @Test
//    public void whenMapperInitialResponseDtoTest_FromAddressResponseMapper_AssertExact() {
//        AddressResponseDto result = mapperInitialResponseDto();
//        assertionExact(HelperTests.md5(result.toString()), HelperTests.md5(new AddressResponseDto().toString()));
//    }
//
//    @Test
//    public void whenMapperFinalResponseDtoTest_FromAddressResponseMapper_AssertExact() {
//        AddressResponseDto addressResponseDto = DataSourceTests.dataSourceMapperFinalResponseDto();
//        AddressResponseDto result = mapperFinalResponseDtoByEntity(addressResponseDto);
//        assertionExact(HelperTests.md5(result.toString()), HelperTests.md5(new AddressResponseDto().toString()));
//    }
//
//    @Test
//    public void whenMapperFinalResponseDtoByEntityTest_FromAddressResponseMapper_AssertBoolean() {
//        AddressEntity addressEntity = DataSourceTests.dataSourceAddressEntityEmpty();
//        mapperFinalResponseDtoByEntity(addressEntity);
//        assertionBool(true, true);
//    }
//
//    @Test
//    public void whenRunAddressSyncTest_FromSyncService_AssertExact() {
//        AddressEntity addressEntity = DataSourceTests.dataSourceAddressEntityFill();
//        AddressResponseDto result = syncService.runAddressSync(addressEntity.getCep());
//        assertionExact(result.getCep().replaceAll("[^0-9]", ""), addressEntity.getCep());
//    }
//
//    @Test
//    public void whenRunAddressSyncUsingNewCepTest_FromSyncService_AssertExact() {
//        String cepTest = "12070020";
//        AddressResponseDto result = syncService.runAddressSync(cepTest);
//        System.out.println(result);
//        assertionExact(result.getCep().replaceAll("[^0-9]", ""), cepTest);
//    }
//
//    @Test
//    public void whenRunAddressSyncUsingInvalidCepTest_FromSyncService_AssertText() {
//        String cepTest = "930706800";
//        try {
//            AddressResponseDto result = syncService.runAddressSync(cepTest);
//        } catch (Exception ex) {
//            assertionExact(ex.getMessage(), "Address not found");
//        }
//    }
//
//    @Test
//    public void whenRunAddressSyncUsingWrongCepTest_FromSyncService_AssertExact() {
//        String cepTest = "62090004";
//        try {
//            AddressResponseDto result = syncService.runAddressSync(cepTest);
//        } catch (Exception ex) {
//            assertionExact(ex.getMessage(), "Address not found");
//        }
//    }
//
//    @Rollback
//    @Transactional
//    public void whenSaveAddressTest_FromSyncService_AssertTrue_Windows() {
//        System.out.println(System.getProperty("os.name"));
//        ResponseEntity<AddressResponseDto> dataFake = DataSourceTests.dataSourceAddressEntityResponse();
//        syncService.saveAddress(dataFake);
//        AddressEntity result = addressRepository.findByCep(dataFake.getBody().getCep());
//        assertionExact(result.getCep(), dataFake.getBody().getCep());
//    }
//
//    public void whenSaveAddressTest_FromSyncService_AssertTrue_Linux() {
//        System.out.println(System.getProperty("os.name"));
//        ResponseEntity<AddressResponseDto> dataFake = DataSourceTests.dataSourceAddressEntityResponse();
//        syncService.saveAddress(dataFake);
//        AddressEntity result = addressRepository.findByCep(dataFake.getBody().getCep());
//        addressRepository.deleteById(result.getId());
//        assertionExact(result.getCep(), dataFake.getBody().getCep());
//    }
//
//    @Test
//    public void whenSaveAddressTest_FromSyncService_AssertTrue() {
//        if (System.getProperty("os.name").equals("Linux")) {
//            whenSaveAddressTest_FromSyncService_AssertTrue_Linux();
//        } else {
//            whenSaveAddressTest_FromSyncService_AssertTrue_Windows();
//        }
//    }
//
//    @Test
//    public void whenExceptionHandlerTest_FromResponseExceptionHandler_AssertExact() {
//        try {
//            throw new CodexsResponserException(CodexsResponserEditableErrors.SERVICE_ERROR_TEST);
//        } catch (Exception ex) {
//            assertionExact(ex.getMessage(), CodexsResponserEditableErrors.SERVICE_ERROR_TEST.getMessage());
//        }
//    }
//
//    /**
//     * @apiNote Before run this test check if Server Rules is down
//     * */
//    @Test
//    public void whenRunRulesServerButItIsDownTest_AssertText() throws Exception {
//        try {
//            rulesService.isRulesValid("XYZ-123", "SERVICE-NAME-TEST");
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//            assertionText("Access Denied", ex.getMessage());
//        }
//    }
//
//    @Test
//    public void whenRunAddressSearchTest_FromAddressClient_AssertTrue() throws Exception {
//        ResponseEntity<AddressResponseDto> response = addressClient.addressSearch("12090002");
//        assertionExact("12090002", response.getBody().getCep().replaceAll("[^0-9]", ""));
//    }
//
//}
