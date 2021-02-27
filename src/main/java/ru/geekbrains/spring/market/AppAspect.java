package ru.geekbrains.spring.market;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Slf4j
@Component
public class AppAspect {
    
    public static Map<String, Integer> countMethods = new HashMap<>();
    public static Map<String, Long> countControllers = new HashMap<>();


    @Before("execution(* ru.geekbrains.spring.market.controllers.*.*(..))")
    public void beforeAnyMethodWithDetails(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        if (countMethods.containsKey(methodName)) {
            countMethods.replace(methodName, countMethods.get(methodName) + 1);
        } else {
            countMethods.put(methodName, 1);
        }
        log.info(countMethods.toString());
        int maxValue = 0;
        StringBuilder maxMethodName = new StringBuilder();
        for (Map.Entry<String, Integer> item : countMethods.entrySet()) {
            if (item.getValue() > maxValue) {
                maxValue = item.getValue();
                maxMethodName.replace(0, maxMethodName.length(), item.getKey());
            }
        }
        log.info("Наиболее частый метод: " + maxMethodName);
    }

    @Around("execution(* ru.geekbrains.spring.market.controllers.*.*(..))")
    public Object methodProfiling(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        String controllerName = proceedingJoinPoint.getSignature().getDeclaringTypeName();
        if (countControllers.containsKey(controllerName)) {
            countControllers.replace(controllerName, countControllers.get(controllerName) + duration);
        } else {
            countControllers.put(controllerName, duration);
        }
        log.info(countControllers.toString());
        Long maxValue = 0L;
        StringBuilder maxControllerName = new StringBuilder();
        for (Map.Entry<String, Long> item : countControllers.entrySet()) {
            if (item.getValue() > maxValue) {
                maxValue = item.getValue();
                maxControllerName.replace(0, maxControllerName.length(), item.getKey());
            }
        }
        log.info("Дольше всего выполняются методы: " + maxControllerName);
        return out;
    }

}
