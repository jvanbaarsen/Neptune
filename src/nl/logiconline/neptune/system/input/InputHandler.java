package nl.logiconline.neptune.system.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import nl.logiconline.neptune.utils.Log;

public class InputHandler implements KeyListener {
	@Override
	public void keyPressed(KeyEvent e) {
		Log.debug("Key pressed");
		this.toggle(e, true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.toggle(e, false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	private void toggle(KeyEvent e, boolean state) {

		Keys.toggle(e.getKeyCode(), state);
	}
}
