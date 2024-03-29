package nl.logiconline.neptune.system.geom;
/**
 * Neptune
 * ==========
 * 
 * @author J. van Baarsen <jeroen@logiconline.nl>
 * @package nl.logiconline.neptune.system.geom
 * (c) 2012 - LogicOnline
 */
import nl.logiconline.neptune.system.Gfx;
import nl.logiconline.neptune.system.NeptuneException;

abstract class Shape {

	protected Gfx g;
	protected double x, y;

	public Shape(Gfx g, double x, double y) {
		this.g = g;
		this.x = x;
		this.y = y;
	}

	public abstract void draw() throws NeptuneException;

}