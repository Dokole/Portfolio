package com.soapdataservice.app.aop.exception;

import com.soapdataservice.app.exceptions.BadRequestException;
import com.soapdataservice.app.exceptions.DatabaseException;
import com.soapdataservice.app.exceptions.NotFoundException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Component
@Aspect
public class ErrorInterceptor {

    Logger logger = LoggerFactory.getLogger(ErrorInterceptor.class);

    @Around("execution(* com.producingservice.app.service.endpoint..* (..))")
    public Object endpointServiceErrorInterceptor(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (BadRequestException | NotFoundException ex) {
            throw ex;
        } catch (RuntimeException e) {
            logger.error("IN {} - exception during {} transaction. Message: {} ",
                    joinPoint.getSignature().getName(),
                    getObjectClassName(joinPoint),
                    e.getLocalizedMessage());

            throw new DatabaseException("Sorry, an error happened during " +
                    getObjectClassName(joinPoint) + " transaction. Please, try again later.");
        } catch (Throwable throwable) {
            logger.error("Something bad happened. Message: {}", throwable.getMessage());
            throw throwable;
        }
    }

//     BrandEndpointServiceImp - EndpointServiceImp = Brand
    private String getObjectClassName (ProceedingJoinPoint proceedingJoinPoint) {
        String name = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName().toLowerCase();
        return name.replace("endpointserviceimp", "");
    }
}
