package com.huntercodexs.postalcode.service;

import com.huntercodexs.postalcode.dto.PostalCodeRequestDto;
import com.huntercodexs.postalcode.dto.PostalCodeResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PostalCodeService {

    @Autowired
    PostalCodeHandlerService postalCodeHandlerService;

    public ResponseEntity<PostalCodeResponseDto> getAddress(PostalCodeRequestDto postalCodeRequestDto) {
        PostalCodeResponseDto postalCodeResponseDto = postalCodeHandlerService.searchPostalCode(postalCodeRequestDto.getPostalCode());
        return ResponseEntity.status(HttpStatus.OK).body(postalCodeResponseDto);
    }

    public int sum(int a, int b) {
        return a+b;
    }

    private boolean valid(int k) {
        return k != 0;
    }

    private String fullname(String firstName, String lastName) {
        System.out.println("==================> " + firstName);
        System.out.println("==================> " + lastName);
        return firstName.replaceAll(" ", "") +" "+ lastName;
    }

    private String fullname(String firstName, String lastName, Integer age) {
        System.out.println("==================> " + firstName);
        System.out.println("==================> " + lastName);
        System.out.println("==================> " + age);
        return firstName.replaceAll(" ", "") +" "+ lastName + ", Age: " + age;
    }

}
