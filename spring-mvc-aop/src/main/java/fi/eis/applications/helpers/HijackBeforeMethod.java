package fi.eis.applications.helpers;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class HijackBeforeMethod {

	@Before("execution(public * fi.eis.applications.beans.FooClass*.*(..))")
    public void printSomething(JoinPoint jp) {
		 System.out.println("HijackBeforeMethod : Before execution of method :: " +jp.getSignature().getName());
	}
}
