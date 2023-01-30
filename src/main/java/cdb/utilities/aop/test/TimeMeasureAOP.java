package cdb.utilities.aop.test;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.log4j.Log4j2;

// @formatter:off
@Log4j2 @Aspect @Component
public class TimeMeasureAOP {
  @Around("execution(* *cdb.controller..*.*(..)) || timer() || execution(* *cdb.service..*.*(..))")
  public Object aop(final ProceedingJoinPoint joinPoint) throws Throwable {
    final var stopWatch = new StopWatch();stopWatch.start();final var result = joinPoint.proceed();stopWatch.stop();
    final var msTot = stopWatch.getTotalTimeMillis()/1000.0;
    final var methodSignature = (MethodSignature) joinPoint.getSignature();
    final var extMethod = methodSignature.getMethod().getName();
    final var extClass = joinPoint.getTarget().getClass().getSimpleName();
    final var sb=new StringBuilder();
    sb.append("\n시간계산>>>");
    sb.append("\n============================================================\n")
      .append("  {}클래스의 메서드: {} ... 실행시간: {}초          ")
      .append("\n============================================================\n");
    log.debug(sb.toString(),extClass, extMethod, msTot);
    return result;}
@Pointcut("@annotation(cdb.utilities.aop.test.Timer)")private void timer() {}
}
// @formatter:on
