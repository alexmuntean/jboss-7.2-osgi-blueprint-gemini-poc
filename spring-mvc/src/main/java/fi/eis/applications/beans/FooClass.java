package fi.eis.applications.beans;

public class FooClass implements FooInterface {
	/* (non-Javadoc)
	 * @see fi.eis.applications.beans.FooInterface#getMessage()
	 */
	@Override
	public String getMessage() {
		return "Hello";
	}
}
