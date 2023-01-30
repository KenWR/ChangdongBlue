package cdb.utilities.aop.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// @formatter:off
@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE })@Retention(RetentionPolicy.RUNTIME)public @interface Timer {}
// @formatter:on