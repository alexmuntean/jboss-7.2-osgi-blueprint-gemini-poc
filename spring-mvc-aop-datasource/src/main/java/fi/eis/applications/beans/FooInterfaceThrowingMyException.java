package fi.eis.applications.beans;

public interface FooInterfaceThrowingMyException {

	public abstract String getMessage();

	public abstract void throwException();
	public abstract void throwException(RuntimeException e);

}