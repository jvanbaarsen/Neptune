package nl.logiconline.neptune.utils;

import java.util.Date;

import nl.logiconline.neptune.World;

public class Log {

	public final static void debug(String event) {
		if(World.SHOW_DEBUG) {
			System.out.println((new Date().toString()) + " DEBUG: - " + event);
		}
	}

	public final static void event(String event) {
		if(World.SHOW_EVENTS) {
			System.out.println((new Date().toString()) + " EVENT: - " + event);
		}
	}

	public final static void error(String event) {
		if(World.SHOW_ERROR) {
			System.err.println((new Date().toString()) + " ERROR: - " + event);
		}
	}

}
