package fi.eis.applications.beans;

import fi.eis.applications.jboss.poc.gemini.spring.aop.support.api.MyExceptionHandler;

public class FooClassThrowingMyException implements FooInterfaceThrowingMyException {

	/* (non-Javadoc)
	 * @see fi.eis.applications.beans.FooInterfaceThrowingMyException#getMessage()
	 */
	@Override
	public String getMessage() {
		return "Hello";
	}

	@MyExceptionHandler
	@Override
	public void throwException() {
		throw new IllegalStateException("Internal failure!");
	}

	@MyExceptionHandler
	@Override
	public void throwException(RuntimeException e) {
		throw e;
	}
}
