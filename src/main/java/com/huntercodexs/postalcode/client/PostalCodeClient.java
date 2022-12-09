package com.huntercodexs.postalcode.client;

import com.huntercodexs.postalcode.dto.PostalCodeResponseDto;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class PostalCodeClient {

    @Value("${service.postal-code-search.url}")
    String urlAddressSearch;

    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<PostalCodeResponseDto> addressSearch(String postalCode) {
        String urlAddressSearch = this.urlAddressSearch.replaceFirst("@postal_code", postalCode);
        HttpEntity<JSONObject> httpEntity = new HttpEntity<>(null);

        try {
            return restTemplate.exchange(urlAddressSearch, HttpMethod.GET, httpEntity, PostalCodeResponseDto.class);
        } catch (RuntimeException re) {
            System.out.println("[EXCEPTION] " + re.getMessage());
            return null;
        }
    }

}
