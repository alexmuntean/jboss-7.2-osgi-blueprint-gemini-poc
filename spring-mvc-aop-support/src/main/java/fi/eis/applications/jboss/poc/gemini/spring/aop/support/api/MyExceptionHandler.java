package fi.eis.applications.jboss.poc.gemini.spring.aop.support.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import fi.eis.applications.jboss.poc.gemini.spring.aop.support.MyException;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface MyExceptionHandler {
	Class<?>[] ignoredExceptions() default { MyException.class };
}
