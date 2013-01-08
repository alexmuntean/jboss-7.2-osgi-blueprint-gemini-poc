package fi.eis.applications.jboss.poc.gemini.spring.aop.support.api;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ExceptionHandlerAspect {
	@AfterThrowing(throwing = "exception",
	   value = "@annotation(fi.eis.applications.jboss.poc.gemini.spring.aop.support.api.MyExceptionHandler)"
		   )
	public void methodLevel(JoinPoint joinPoint, Exception exception) throws Exception {
		handleException(joinPoint, exception, null);
	}

	private void handleException(JoinPoint joinPoint, Exception exception,
			MyExceptionHandler myExceptionHandler) {
		System.err.println("ExceptionHandlerAspect: Got exception " + exception);
	}
}
