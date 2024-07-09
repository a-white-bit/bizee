package com.sparta.bizee.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

/*
 * ResponseDto:
 * HTTP 상태, 데이터를 클라이언트에게 JSON 형태로 반환하는 DTO
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL) // null 인 프로퍼티는 클라이언트에게 반환하지 않음
public class ResponseDto {
    private final int httpCode;
    private final String httpCodeName;
    private final String customMessage;
    private Object data;

    public ResponseDto(ResponseCodeEnum responseCodeEnum) {
        this.httpCode = responseCodeEnum.getStatusCode();
        this.httpCodeName = responseCodeEnum.getCodeName();
        this.customMessage = responseCodeEnum.getMessage();
    }

    public ResponseDto(ResponseCodeEnum responseCodeEnum, Object data) {
        this.httpCode = responseCodeEnum.getStatusCode();
        this.httpCodeName = responseCodeEnum.getCodeName();
        this.customMessage = responseCodeEnum.getMessage();
        this.data = data;
    }

    public ResponseEntity<ResponseDto> createResponseEntity() {
        return ResponseEntity.status(httpCode).body(this);
    }
}