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

public class Circle extends Shape {

	private boolean fill;
	private int radius;

	public Circle(Gfx g, double xCenter, double yCenter, int radius, boolean fill) {
		super(g, xCenter, yCenter);
		this.radius = radius;
		this.fill = fill;
	}

	@Override
	public void draw() throws NeptuneException {
		if (!this.fill) {
			int pix = super.g.getColor();
			int x = 0;
			int y = this.radius;
			int p = (5 - (this.radius * 4)) / 4;

			this.circlePoints(super.x, super.y, x, y, pix);
			while (x < y) {
				x++;
				if (p < 0) {
					p += (2 * x) + 1;
				} else {
					y--;
					p += (2 * (x - y)) + 1;
				}
				this.circlePoints(super.x, super.y, x, y, pix);
			}
		} else {
			for (int y = -this.radius; y <= this.radius; y++) {
				for (int x = -this.radius; x <= this.radius; x++) {
					if (((x * x) + (y * y)) <= (this.radius * this.radius)) {
						this.g.setPixel(super.x + x, super.y + y);
					}
				}
			}
		}
	}

	private void circlePoints(double cx, double cy, double x, double y, int pix) throws NeptuneException {
		if (x == 0) {
			super.g.setPixel(cx, cy + y);
			super.g.setPixel(cx, cy - y);
			super.g.setPixel(cx + y, cy);
			super.g.setPixel(cx - y, cy);
		} else if (x == y) {
			super.g.setPixel(cx + x, cy + y);
			super.g.setPixel(cx - x, cy + y);
			super.g.setPixel(cx + x, cy - y);
			super.g.setPixel(cx - x, cy - y);
		} else if (x < y) {
			super.g.setPixel(cx + x, cy + y);
			super.g.setPixel(cx - x, cy + y);
			super.g.setPixel(cx + x, cy - y);
			super.g.setPixel(cx - x, cy - y);
			super.g.setPixel(cx + y, cy + x);
			super.g.setPixel(cx - y, cy + x);
			super.g.setPixel(cx + y, cy - x);
			super.g.setPixel(cx - y, cy - x);
		}
	}

}
