package com.ebaykorea.payback.api.advice;

import com.ebaykorea.payback.api.dto.common.CommonResponse;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice(basePackages = "com.ebaykorea.payback")
public class ApiResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response
    ) {
        HttpServletResponse servletResponse = ((ServletServerHttpResponse) response).getServletResponse();
        if(body instanceof CommonResponse) {
            ((CommonResponse) body).setCode(servletResponse.getStatus());
            return body;
        } else {
            CommonResponse result = CommonResponse.builder()
                .code(servletResponse.getStatus())
                .data(body)
                .build();
            return result;
        }
    }
}
