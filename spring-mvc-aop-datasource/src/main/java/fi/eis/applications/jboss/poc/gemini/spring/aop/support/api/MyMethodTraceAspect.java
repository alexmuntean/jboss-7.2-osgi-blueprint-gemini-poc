package fi.eis.applications.jboss.poc.gemini.spring.aop.support.api;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jboss.logging.Logger;


@Aspect
public class MyMethodTraceAspect {

	private Logger logger;
	
	@Before("generalPointcut(methodTrace)")
	public void annotationTraceEnter(JoinPoint joinPoint, MyMethodTrace methodTrace) {
		if (getLogger(joinPoint).isDebugEnabled()) {
			getLogger(joinPoint).debug(joinPoint + " - " + methodTrace + " - " + null);
		}
	}

	@AfterReturning(pointcut="generalPointcut(methodTrace)", returning="result")
	public void annotationTraceExit(JoinPoint joinPoint, MyMethodTrace methodTrace, Object result) {
		if (getLogger(joinPoint).isDebugEnabled()) {
			getLogger(joinPoint).debug(joinPoint + " - " + methodTrace + " - " + result);
		}
	}
	@Pointcut("@within(fi.eis.applications.jboss.poc.gemini.spring.aop.support.api.MyMethodTrace) && @target(methodTrace)")
	public void generalPointcut(MyMethodTrace methodTrace) { }
	
	private Logger getLogger(JoinPoint joinPoint) {
		if (logger == null) {
			logger = Logger.getLogger(joinPoint.getTarget().getClass().getName());
		}
		return logger;
	}
}