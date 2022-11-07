package com.huntercodexs.codexstester.postalcode.service;

import com.huntercodexs.codexstester.postalcode.client.PostalCodeClient;
import com.huntercodexs.codexstester.postalcode.database.model.PostalCodeEntity;
import com.huntercodexs.codexstester.postalcode.database.repository.PostalCodeRepository;
import com.huntercodexs.codexstester.postalcode.dto.PostalCodeResponseDto;
import com.huntercodexs.codexstester.postalcode.mapper.PostalCodeResponseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class PostalCodeHandlerService {

    @Autowired
    PostalCodeClient postalCodeClient;

    @Autowired
    PostalCodeRepository postalCodeRepository;

    public PostalCodeResponseDto searchPostalCode(String postalCode) {

        PostalCodeEntity address = postalCodeRepository.findByCep(postalCode);

        if (address != null && !address.getCep().equals("")) {
            return PostalCodeResponseMapper.mapperFinalResponseDtoByEntity(address);
        }

        ResponseEntity<PostalCodeResponseDto> result = postalCodeClient.addressSearch(postalCode);

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("RESULT");
        System.out.println(result);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        if (result == null || result.getBody().getCep() == null) {
            throw new RuntimeException("Postal Code Not Found");
        }

        if (!result.getStatusCode().is4xxClientError()) {
            saveAddress(result);
            return result.getBody();
        }

        throw new RuntimeException("Postal Code Error");
    }

    public void saveAddress(ResponseEntity<PostalCodeResponseDto> result) {
        PostalCodeEntity postalCodeEntity = new PostalCodeEntity();
        try {
            postalCodeEntity.setCep(Objects.requireNonNull(result.getBody()).getCep().replace("-", ""));
            postalCodeEntity.setLogradouro(Objects.requireNonNull(result.getBody()).getLogradouro());
            postalCodeEntity.setComplemento(Objects.requireNonNull(result.getBody()).getComplemento());
            postalCodeEntity.setBairro(Objects.requireNonNull(result.getBody()).getBairro());
            postalCodeEntity.setLocalidade(Objects.requireNonNull(result.getBody()).getLocalidade());
            postalCodeEntity.setUf(Objects.requireNonNull(result.getBody()).getUf());
            postalCodeEntity.setIbge(Objects.requireNonNull(result.getBody()).getIbge());
            postalCodeEntity.setGia(Objects.requireNonNull(result.getBody()).getGia());
            postalCodeEntity.setDdd(Objects.requireNonNull(result.getBody()).getDdd());
            postalCodeEntity.setSiafi(Objects.requireNonNull(result.getBody()).getSiafi());
            postalCodeRepository.save(postalCodeEntity);
        } catch (RuntimeException re) {
            System.out.println("saveAddress EXCEPTION: " + re.getMessage());
        }
    }
}
