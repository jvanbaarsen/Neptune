package nl.logiconline.neptune.utils;
/**
 * Neptune
 * ==========
 * 
 * @author J. van Baarsen <jeroen@logiconline.nl>
 * @package nl.logiconline.neptune.utils
 * (c) 2012 - LogicOnline
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

@Deprecated
public class KeyInput implements KeyListener {

	private HashMap<Integer, Boolean> pressed = new HashMap<Integer, Boolean>();

	public KeyInput() {
		for (int i = 0; i < 200; i++) {
			this.pressed.put(i, false);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.pressed.put(e.getKeyCode(), true);
	}

	public boolean isPressed(int code) {
		if (this.pressed.containsKey(code)) {
			return this.pressed.get(code);
		}
		return false;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.pressed.put(e.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
