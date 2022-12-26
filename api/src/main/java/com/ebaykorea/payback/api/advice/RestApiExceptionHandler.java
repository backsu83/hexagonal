package com.ebaykorea.payback.api.advice;

import com.ebaykorea.payback.api.dto.common.CommonExceptionResponseDto;
import com.ebaykorea.payback.core.exception.PaybackException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Slf4j
@RestControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            @Nullable Object body,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        CommonExceptionResponseDto response = CommonExceptionResponseDto.builder()
            .exceptionMessage(ex.getLocalizedMessage())
            .exceptionCode(status.name())
            .build();
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<CommonExceptionResponseDto> handleException(Exception ex) {
        log.error(ex.getLocalizedMessage(), ex);
        CommonExceptionResponseDto response = CommonExceptionResponseDto.builder()
            .exceptionMessage(ex.getLocalizedMessage())
            .exceptionCode(HttpStatus.INTERNAL_SERVER_ERROR.name())
            .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {PaybackException.class})
    public ResponseEntity<CommonExceptionResponseDto> handlePaybackException(PaybackException ex) {
        log.error(ex.getLocalizedMessage(), ex);
        CommonExceptionResponseDto response = CommonExceptionResponseDto.builder()
            .exceptionMessage(ex.getMessage())
            .exceptionCode(ex.getCode().name())
            .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
