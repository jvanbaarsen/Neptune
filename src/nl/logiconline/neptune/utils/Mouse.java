package nl.logiconline.neptune.utils;
/**
 * Neptune
 * ==========
 * 
 * @author J. van Baarsen <jeroen@logiconline.nl>
 * @package nl.logiconline.neptune.utils
 * (c) 2012 - LogicOnline
 */
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {

	@Override
	public void mousePressed(MouseEvent e) {
		Log.debug("Mouse pressed; # of clicks: " + e.getClickCount());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Log.debug("Mouse released; # of clicks: " + e.getClickCount());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Log.debug("Mouse clicked (# of clicks: "  + e.getClickCount() + ")");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Log.debug("Mouse entered");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Log.debug("Mouse exited");
	}

	public final static int getX() {
		return MouseInfo.getPointerInfo().getLocation().x;
	}

	public final static int getY() {
		return MouseInfo.getPointerInfo().getLocation().y;
	}

}
