package com.ebaykorea.payback.core.exception;

import java.text.MessageFormat;
import lombok.Getter;

@Getter
public class PaybackException extends RuntimeException{
    private PaybackExceptionCode code;
    private String message;

    public PaybackException(String message) {
        super(message);
    }

    public PaybackException(PaybackExceptionCode paybackMessageType , String message) {
        this.code = paybackMessageType;
        this.message = MessageFormat.format(paybackMessageType.getMessage() , message);
    }

    public PaybackException(PaybackExceptionCode paybackMessageType) {
        this.code = paybackMessageType;
        this.message = paybackMessageType.getMessage();
    }
}
