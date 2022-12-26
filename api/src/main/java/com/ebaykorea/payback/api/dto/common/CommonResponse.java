package com.ebaykorea.payback.api.dto.common;

import com.ebaykorea.payback.core.domain.constant.ResponseMessageType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> {

    private int code;
    private T data;
    private String message;

    public CommonResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public CommonResponse(HttpStatus status , T data) {
        this.code = status.value();
        this.data = data;
    }

    public CommonResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public static <T> CommonResponse<T> success(ResponseMessageType responseMessageType, T body) {
        return new CommonResponse<>(responseMessageType.name() , body);
    }

}
