package cdb.utilities.aop.test;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

// @formatter:off
@Log4j2 @Aspect @Component
public class ParamValueAOP {
@Around("execution(* *cdb.controller..*.*(..)) || paramV() || execution(* *cdb.service..*.*(..))")
public Object aop(final ProceedingJoinPoint joinPoint) throws Throwable {
final var sb=new StringBuilder();
Arrays.asList(joinPoint.getArgs()).forEach(t->{
if (t != null) {
sb.append("\n-------------------------------------\n")
.append("데이터타입: {} ,  값: {}     ")
.append("\n-------------------------------------\n");
log.debug(sb.toString(),t.getClass().getSimpleName(),t);
}});
final var result = joinPoint.proceed();
if (result != null) log.debug(
"\n ============================================================ \n "
+ "    {} 형: {} 반환         <<<<<<<<        "
+"\n ============================================================ \n "
, result.getClass().getSimpleName(),result);return result;}
@Pointcut("@annotation(cdb.utilities.aop.test.ParamVal)")private void paramV() {}}
// @formatter:on
