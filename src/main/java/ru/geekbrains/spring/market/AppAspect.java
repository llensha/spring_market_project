package ru.geekbrains.spring.market;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Slf4j
@Component
public class AppAspect {
    
    private Map<String, Long> countMethods;
    private Map<String, Long> countControllers;

    @PostConstruct
    public void init() {
        countMethods = new ConcurrentHashMap<>();
        countControllers = new ConcurrentHashMap<>();
    }

    public Map<String, Long> getCountMethods() {
        return Collections.unmodifiableMap(countMethods);
    }

    public Map<String, Long> getCountControllers() {
        return Collections.unmodifiableMap(countControllers);
    }

    @Before("execution(public * ru.geekbrains.spring.market..*(..))")
    public void countMethodUsages(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getDeclaringType().getSimpleName() + '.' + joinPoint.getSignature().getName();
        countMethods.put(methodName, countMethods.getOrDefault(methodName, 0L) + 1L);
    }

    @Around("execution(* ru.geekbrains.spring.market.controllers.*.*(..))")
    public Object countDurationOfMethodsInControllers(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        String controllerName = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName();
        countControllers.put(controllerName, countControllers.getOrDefault(controllerName, 0L) + executionTime);
        return out;
    }

}
