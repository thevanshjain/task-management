package com.vansh.spring.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyLogs {

    private final Logger logger = LoggerFactory.getLogger(MyLogs.class);



    @Before("execution(* com.vansh.spring.demo.controller.NoteController.showFormForNotes(..))")
    public void beforeShowingFormForNote(JoinPoint joinPoint){

        String method=joinPoint.getSignature().toShortString();
        logger.info("Showing ....   "+method);

    }



}


