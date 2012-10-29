package nl.logiconline.neptune.system;
/**
 * Neptune
 * ==========
 * 
 * @author J. van Baarsen <jeroen@logiconline.nl>
 * @package nl.logiconline.neptune.system
 * (c) 2012 - LogicOnline
 */
public class NeptuneException extends Exception {
	private static final long serialVersionUID = -5264200907811707657L;

	/**
	 * Create a new exception with a detail message
	 * 
	 * @param message The message describing the cause of this exception
	 */
	public NeptuneException(String message) {
		super(message);
	}

	/**
	 * Create a new exception with a detail message
	 * 
	 * @param message The message describing the cause of this exception
	 * @param e The exception causing this exception to be thrown
	 */
	public NeptuneException(String message, Throwable e) {
		super(message, e);
	}
}
