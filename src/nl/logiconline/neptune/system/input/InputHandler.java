package nl.logiconline.neptune.system.input;
/**
 * Neptune
 * ==========
 * 
 * @author J. van Baarsen <jeroen@logiconline.nl>
 * @package nl.logiconline.neptune.system.input
 * (c) 2012 - LogicOnline
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
	@Override
	public void keyPressed(KeyEvent e) {
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
