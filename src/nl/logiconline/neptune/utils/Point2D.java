package nl.logiconline.neptune.utils;
/**
 * Neptune
 * ==========
 * 
 * @author J. van Baarsen <jeroen@logiconline.nl>
 * @package nl.logiconline.neptune.utils
 * (c) 2012 - LogicOnline
 */
public class Point2D extends java.awt.geom.Point2D.Double{
	private static final long serialVersionUID = 1L;
	public Point2D(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public double getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	@Override
	public double getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
