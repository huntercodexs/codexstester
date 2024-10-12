package com.huntercodexs.codexstester.internal;

import com.huntercodexs.codexstester.dto.HeadersDto;
import com.huntercodexs.codexstester.dto.RequestDto;
import com.huntercodexs.codexstester.properties.ExternalProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public abstract class InternalHttpHeadersFactory extends ExternalProperty {

    protected InternalHttpHeadersFactory(String target) {
        super(target);
    }

    protected void createBeforeInternalTests(String user_data) throws Exception {
        genericMockMvc.perform(
                MockMvcRequestBuilders
                        .post(internalUriBaseTest)
                        .content(user_data)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", internalAuthorizationBasic)
        ).andReturn();
    }

    protected void rollbackInternalTests(String id) throws Exception {
        genericMockMvc.perform(
                MockMvcRequestBuilders
                        .delete(internalUrlBaseTest + internalUriBaseTest +"/"+id)
                        .header("Authorization", internalAuthorizationBasic)
        ).andReturn();
    }

    protected void assertInternalTests(String expected, String received) {

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        if (received.contains(expected)) {
            codexsTesterAssertObject(expected, received);
            resulted(true, element);
        } else {
            resulted(false, element);
            codexsTesterAssertObject(expected, received);
        }
    }

    protected HttpHeaders internalBuilderHeaders(RequestDto requestDto, HeadersDto headersDto) {

        HttpHeaders headers = new HttpHeaders();

        /*
         * From properties file
         */
        if (internalAuthorizationBasic != null && !internalAuthorizationBasic.equals("")) {
            headers.set("Authorization", "Basic " + internalAuthorizationBasic.replaceFirst("Basic ", ""));
        }
        if (internalAuthorizationBearer != null && !internalAuthorizationBearer.equals("")) {
            headers.set("Authorization", "Bearer " + internalAuthorizationBearer.replaceFirst("Bearer ", ""));
        }
        if (internalTokenAuthorization != null && !internalTokenAuthorization.equals("")) {
            headers.set("Api-Key-Token", internalTokenAuthorization);
        }
        if (internalAppNameAuthorization != null && !internalAppNameAuthorization.equals("")) {
            headers.set("Api-Key-App-Name", internalAppNameAuthorization);
        }
        if (internalSecretAuthorization != null && !internalSecretAuthorization.equals("")) {
            headers.set("Api-Key-Secret", internalSecretAuthorization);
        }
        if (internalValueAuthorization != null && !internalValueAuthorization.equals("")) {
            headers.set("Api-Key-Value", internalValueAuthorization);
        }
        if (internalGenericAuthorization != null && !internalGenericAuthorization.equals("")) {
            headers.set("Api-Key-Generic", internalGenericAuthorization);
        }
        if (internalAdditionalHeaderName1 != null && !internalAdditionalHeaderName1.equals("")) {
            if (internalAdditionalHeaderValue1 != null && !internalAdditionalHeaderValue1.equals("")) {
                headers.set(internalAdditionalHeaderName1, internalAdditionalHeaderValue1);
            }
        }
        if (internalAdditionalHeaderName2 != null && !internalAdditionalHeaderName2.equals("")) {
            if (internalAdditionalHeaderValue2 != null && !internalAdditionalHeaderValue2.equals("")) {
                headers.set(internalAdditionalHeaderName2, internalAdditionalHeaderValue2);
            }
        }
        if (internalAdditionalHeaderName3 != null && !internalAdditionalHeaderName3.equals("")) {
            if (internalAdditionalHeaderValue3 != null && !internalAdditionalHeaderValue3.equals("")) {
                headers.set(internalAdditionalHeaderName3, internalAdditionalHeaderValue3);
            }
        }
        if (internalAdditionalHeaderName4 != null && !internalAdditionalHeaderName4.equals("")) {
            if (internalAdditionalHeaderValue4 != null && !internalAdditionalHeaderValue4.equals("")) {
                headers.set(internalAdditionalHeaderName4, internalAdditionalHeaderValue4);
            }
        }
        if (internalAdditionalHeaderName5 != null && !internalAdditionalHeaderName5.equals("")) {
            if (internalAdditionalHeaderValue5 != null && !internalAdditionalHeaderValue5.equals("")) {
                headers.set(internalAdditionalHeaderName5, internalAdditionalHeaderValue5);
            }
        }

        /*
         * From HeadersDto Class (Overwrite above headers)
         */
        if (headersDto.getAuthorizationBasic() != null && !headersDto.getAuthorizationBasic().equals("")) {
            headers.set("Authorization", "Basic " + headersDto.getAuthorizationBasic().replaceFirst("Basic ", ""));
        }
        if (headersDto.getAuthorizationBearer() != null && !headersDto.getAuthorizationBearer().equals("")) {
            headers.set("Authorization", "Bearer " + headersDto.getAuthorizationBearer().replaceFirst("Bearer ", ""));
        }
        if (headersDto.getApiKeyToken() != null && !headersDto.getApiKeyToken().equals("")) {
            headers.set("Api-Key-Token", headersDto.getApiKeyToken());
        }
        if (headersDto.getApiKeyAppName() != null && !headersDto.getApiKeyAppName().equals("")) {
            headers.set("Api-Key-App-Name", headersDto.getApiKeyAppName());
        }
        if (headersDto.getApiKeySecret() != null && !headersDto.getApiKeySecret().equals("")) {
            headers.set("Api-Key-Secret", headersDto.getApiKeySecret());
        }
        if (headersDto.getApiKeyValue() != null && !headersDto.getApiKeyValue().equals("")) {
            headers.set("Api-Key-Value", headersDto.getApiKeyValue());
        }
        if (headersDto.getApiKeyGeneric() != null && !headersDto.getApiKeyGeneric().equals("")) {
            headers.set("Api-Key-Generic", headersDto.getApiKeyGeneric());
        }
        if (headersDto.getAdditionalName() != null && !headersDto.getAdditionalName().equals("")) {
            if (headersDto.getAdditionalValue() != null && !headersDto.getAdditionalValue().equals("")) {
                headers.set(headersDto.getAdditionalName(), headersDto.getAdditionalValue());
            }
        }

        /*Default Headers*/
        if (headersDto.getContentType() != null && !headersDto.getContentType().equals("")) {
            headers.set("Content-Type", headersDto.getContentType());
        } else {
            headers.set("Content-Type", "application/json;charset=UTF-8");
        }

        return headers;
    }

}
