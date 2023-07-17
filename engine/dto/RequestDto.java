package codexstester.engine.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestDto {
    String url;
    String uri;
    String id;
    String dataRequest;
    String expectedMessage;
    int expectedCode;
}
