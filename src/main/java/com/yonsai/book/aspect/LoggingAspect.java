package com.yonsai.book.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    // 적용 범위: controller 패키지 안의 모든 메서드
    @Pointcut("execution(* com.yonsai.book.controller..*(..))")
    public void controllerPointcut() {}

    // 1. [Before] 메서드 실행 전 단순 출력
    @Before("controllerPointcut()")
    public void beforeLog(JoinPoint joinPoint) {
        System.out.println("=== [Before] 메서드 실행 전입니다 ===");
    }

    // 2. [Around] 실행 앞뒤, 매개변수, 반환값 출력
    @Around("controllerPointcut()")
    public Object aroundLog(ProceedingJoinPoint joinPoint) throws Throwable {
        
        // 실행 전 로그
        System.out.println("--- [Around] 시작 메서드: " + joinPoint.getSignature().getName());
        System.out.println("--- [Around] 매개변수: " + Arrays.toString(joinPoint.getArgs()));

        // 실제 메서드 실행 (이게 없으면 기능이 안 돌아갑니다!)
        Object result = joinPoint.proceed();

        // 실행 후 로그
        System.out.println("--- [Around] 반환값: " + result);
        System.out.println("--- [Around] 종료 ---");

        return result;
    }
}