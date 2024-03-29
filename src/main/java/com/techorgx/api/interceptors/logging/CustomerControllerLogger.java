package com.techorgx.api.interceptors.logging;

import com.techorgx.api.controllers.CustomerController;
import com.techorgx.api.models.CustomerRequestModel;
import com.techorgx.api.util.HeaderUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CustomerControllerLogger {
    public static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private HeaderUtils headerUtils;

    @Around("execution(* com.techorgx.api.controllers.CustomerController.addCustomer(..))")
    public Object addCustomerLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info(
                "Request received on addCustomer endpoint: "
                        + headerUtils.formatRequest(headerUtils.getHttpRequest())
        );
        @SuppressWarnings("unchecked")
        ResponseEntity<String> result = (ResponseEntity<String>) joinPoint.proceed();
        logger.info(
                    "Response sent from addCustomer endpoint: "
                            + result
        );
        return result;
    }

    @Around("execution(* com.techorgx.api.controllers.CustomerController.getCustomer(..))")
    public Object getCustomerLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info(
                "Request received on getCustomer endpoint: "
                        + headerUtils.formatRequest(headerUtils.getHttpRequest())
        );
        @SuppressWarnings("unchecked")
        ResponseEntity<CustomerRequestModel> responseEntity = (ResponseEntity<CustomerRequestModel>) joinPoint.proceed();
        logger.info(
                    "Response sent from getCustomer endpoint: "
                            + responseEntity.toString()
        );

        return responseEntity;
    }
}
