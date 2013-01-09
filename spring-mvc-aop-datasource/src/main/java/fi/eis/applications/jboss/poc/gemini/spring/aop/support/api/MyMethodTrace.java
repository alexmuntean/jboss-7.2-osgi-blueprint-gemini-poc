package fi.eis.applications.jboss.poc.gemini.spring.aop.support.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Annotation for defining method tracing.
 * 
 * <p>Copyright (c) 2010 OP-Keskus osk</p>
 * 
 * @author Roope Ikonen
 * @version 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyMethodTrace {
	/**
	 * Should method input and output values be logged on method enter/exit.
	 */
	public boolean logValues() default false;
}
