package pl.edu.pb.wi.shopservice.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class ServiceLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(ServiceLoggingAspect.class);

    @Before("execution(* pl.edu.pb.wi.shopservice.services.*.*(..))")
    public void logBeforeMethod() {
        logger.info("Executing service method...");
    }

    @After("execution(* pl.edu.pb.wi.shopservice.services.*.*(..))")
    public void logAfterMethod() {
        logger.info("Executed service method.");
    }

    @Around("execution(* pl.edu.pb.wi.shopservice.services.*.*(..))")
    public Object logAroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Starting method: {}", joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        logger.info("Method executed: {}", joinPoint.getSignature().getName());
        return result;
    }
}
