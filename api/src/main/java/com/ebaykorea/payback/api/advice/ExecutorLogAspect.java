package com.ebaykorea.payback.api.advice;

import com.ebaykorea.payback.infrastructure.persistence.redis.support.GsonUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Aspect
@Component
public class ExecutorLogAspect {
    private static final Logger log = LoggerFactory.getLogger(ExecutorLogAspect.class);

    @Pointcut("execution(* com.ebaykorea.payback.api..*.*(..))")
    public void pointCut() {};

    @Pointcut("@annotation(com.ebaykorea.payback.api.advice.LogExecutor)")
    public void timerPointCut() {};

    @Around("timerPointCut()")
    public Object execution(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Object proceed = Optional.empty();
        StopWatch stopWatch = new StopWatch();
        log.info(reqeustPayload(joinPoint , request));
        try {
            stopWatch.start();
            proceed = joinPoint.proceed();
            stopWatch.stop();
            return proceed;
        } finally {
            log.info(responsePayload(joinPoint , request , proceed , stopWatch.getTotalTimeMillis()));
        }
    }

    public String reqeustPayload(ProceedingJoinPoint joinPoint , HttpServletRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        builder.append(joinPoint.getSignature().getDeclaringType().getSimpleName());
        builder.append("#");
        builder.append(joinPoint.getSignature().getName());
        builder.append("]");
        builder.append(" ---> ");
        builder.append(request.getMethod());
        builder.append(" ");
        builder.append(request.getRequestURI());
        builder.append(" :: request payload: ");
        builder.append(GsonUtils.toJson(joinPoint.getArgs()));
        return builder.toString();
    }

    public String responsePayload(ProceedingJoinPoint joinPoint , HttpServletRequest request , Object response , Long timer) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        builder.append(joinPoint.getSignature().getDeclaringType().getSimpleName());
        builder.append("#");
        builder.append(joinPoint.getSignature().getName());
        builder.append("]");
        builder.append(" <--- ");
        builder.append(request.getMethod());
        builder.append(" ");
        builder.append(request.getRequestURI());
        builder.append(" (");
        builder.append(timer);
        builder.append("ms)");
        builder.append(" :: response payload: ");
        builder.append(GsonUtils.toJson(response));
        return builder.toString();
    }
}
