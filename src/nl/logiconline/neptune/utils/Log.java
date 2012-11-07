package nl.logiconline.neptune.utils;
/**
 * Neptune
 * ==========
 * 
 * @author J. van Baarsen <jeroen@logiconline.nl>
 * @package nl.logiconline.neptune.utils
 * (c) 2012 - LogicOnline
 */
import java.util.Date;

import nl.logiconline.neptune.system.Neptune;

public class Log {

	public final static void debug(String event) {
		if(Neptune.SHOW_DEBUG) {
			System.out.println((Neptune.SHOW_TIMESTAMP ? ""+(new Date().toString()) +"" : "" ) +" DEBUG: " + event);
		}
	}

	public final static void event(String event) {
		if(Neptune.SHOW_EVENTS) {
			System.out.println((Neptune.SHOW_TIMESTAMP ? ""+(new Date().toString()) +"" : "" ) +" EVENT: " + event);
		}
	}

	public final static void error(String event) {
		if(Neptune.SHOW_ERROR) {
			System.err.println((Neptune.SHOW_TIMESTAMP ? ""+(new Date().toString()) +"" : "" ) +" ERROR: " + event);
		}
	}

	public final static void info(String event) {
		if(Neptune.SHOW_INFO) {
			System.err.println((Neptune.SHOW_TIMESTAMP ? ""+(new Date().toString()) +"" : "" ) +" INFO: " + event);
		}
	}

}
