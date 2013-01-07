package fi.eis.applications.helpers;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.MethodBeforeAdvice;

@Aspect
public class HijackBeforeMethod implements MethodBeforeAdvice {
	@Override
	public void before(Method arg0, Object[] arg1, Object arg2)
			throws Throwable {
		System.out.println("HijackBeforeMethod : Before method hijacked! " + arg0.getName());
	}
	@Before("execution(public * fi.eis.applications.beans.FooClass*.*(..))")
    public void printSomething(JoinPoint jp) {
		 System.out.println("HijackBeforeMethod : Before execution of method :: " +jp.getSignature().getName());
	}

}
