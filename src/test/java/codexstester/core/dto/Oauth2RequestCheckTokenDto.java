package codexstester.core.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Oauth2RequestCheckTokenDto {
    String url;
    String authorization;
    String token;
    String additionalName;
    String additionalValue;
}
