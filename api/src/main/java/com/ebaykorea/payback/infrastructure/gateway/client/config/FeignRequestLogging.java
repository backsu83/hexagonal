package com.ebaykorea.payback.infrastructure.gateway.client.config;

import feign.Logger;
import feign.Request;
import feign.Response;
import feign.Util;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static feign.Logger.Level.HEADERS;
import static feign.Util.UTF_8;
import static feign.Util.decodeOrDefault;

@Slf4j
public class FeignRequestLogging extends Logger {

    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {

        if (logLevel.ordinal() >= HEADERS.ordinal()) {
            super.logRequest(configKey, logLevel, request);
        } else {
            int bodyLength = 0;
            if (request.body() != null) {
                bodyLength = request.body().length;
            }
            log(configKey, "---> %s %s HTTP/1.1 (%s-byte body) :: feign request payload: %s",
                    request.httpMethod().name(),
                    request.url(),
                    bodyLength,
                    (request.charset() != null) ? new String(request.body()) : "[]");
        }
    }

    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime)
            throws IOException {
        if (logLevel.ordinal() >= HEADERS.ordinal()) {
            return super.logAndRebufferResponse(configKey, logLevel, response, elapsedTime);
        } else {
            int status = response.status();
            Request request = response.request();
            if (response.body() != null && !(status == 204 || status == 205)) {
                byte[] bodyData = Util.toByteArray(response.body().asInputStream());
                log(configKey, "<--- %s %s HTTP/1.1 %s (%sms) :: feign response payload: %s",
                        request.httpMethod().name(),
                        request.url(),
                        status,
                        elapsedTime,
                        decodeOrDefault(bodyData, UTF_8, "Binary data"));
                return response.toBuilder().body(bodyData).build();
            } else {
                log(configKey, "<--- %s %s HTTP/1.1 %s (%sms) :: feign response payload: %s",
                        request.httpMethod().name(),
                        request.url(),
                        status,
                        elapsedTime, "[]");
            }
        }
        return response;
    }

    @Override
    protected void log(String configKey, String format, Object... args) {
        log.info(format(configKey, format, args));
    }

    protected String format(String configKey, String format, Object... args) {
        return String.format(methodTag(configKey) + format, args);
    }
}