package nl.logiconline.neptune.system.input;

import java.util.HashMap;


public class Keys {
	public static final class Key {
		public boolean nextState = false;
		public boolean wasDown = false;
		public boolean isDown = false;

		public void update() {
			this.wasDown = this.isDown;
			this.isDown = this.nextState;
		}

		public boolean wasPressed() {
			return !this.wasDown && this.isDown;
		}

		public boolean wasReleased() {
			return this.wasDown && !this.isDown;
		}

		public boolean isPressed() {
			return this.isDown;
		}

		public void release() {
			this.nextState = false;
		}
	}

	private static HashMap<Integer, Key> all = new HashMap<Integer, Key>();
	public static void addKey(int keyCode) {
		if(!Keys.all.containsKey(keyCode)) {
			Keys.all.put(keyCode, new Key());
		}
	}

	public static void toggle(int keyCode, boolean state) {
		if(all.containsKey(keyCode)) {
			all.get(keyCode).nextState = state;
		}
	}

	public static void update() {
		for(Key k : all.values()) {
			k.update();
		}
	}

	public void releaseAll() {
		for(Key k : all.values()) {
			k.release();
		}
	}

	public static boolean isPressed(int keyCode) {
		if(all.containsKey(keyCode)) {
			return all.get(keyCode).isPressed();
		}
		return false;
	}


}
