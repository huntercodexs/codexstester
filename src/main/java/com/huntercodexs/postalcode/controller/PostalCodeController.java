package com.huntercodexs.postalcode.controller;

import com.huntercodexs.postalcode.dto.PostalCodeRequestDto;
import com.huntercodexs.postalcode.dto.PostalCodeResponseDto;
import com.huntercodexs.postalcode.service.PostalCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("${api.prefix}")
public class PostalCodeController {

    @Autowired
    PostalCodeService postalCodeService;

    @PostMapping(path = "/address", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PostalCodeResponseDto> getAddress(
            @Valid @RequestBody(required = true) PostalCodeRequestDto addressRequestDto
    ) {
        return postalCodeService.getAddress(addressRequestDto);
    }

}
