package fi.eis.applications.beans;

import fi.eis.applications.jboss.poc.gemini.spring.aop.support.api.MyExceptionHandler;

public class FooClass implements FooInterface {

	/* (non-Javadoc)
	 * @see fi.eis.applications.beans.FooInterface#getMessage()
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
}
