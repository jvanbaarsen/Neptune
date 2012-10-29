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


public class Line extends Shape {

	int x2, y2;

	public Line(Gfx g, int x, int y, int x2, int y2) {
		super(g, x, y);
		this.x2 = x2;
		this.y2 = y2;
	}

	@Override
	public void draw() {
		int deltax = Math.abs(this.x2 - super.x);
		int deltay = Math.abs(this.y2 - super.y);
		int x = super.x;
		int y = super.y;

		int xinc1, xinc2, yinc1, yinc2, den, num, numadd, numpixels, curpixel;

		if (this.x2 >= super.x) {
			xinc1 = 1;
			xinc2 = 1;
		} else {
			xinc1 = -1;
			xinc2 = -1;
		}

		if (this.y2 >= super.y) {
			yinc1 = 1;
			yinc2 = 1;
		} else {
			yinc1 = -1;
			yinc2 = -1;
		}

		if (deltax >= deltay) {
			xinc1 = 0;
			yinc2 = 0;
			den = deltax;
			num = deltax / 2;
			numadd = deltay;
			numpixels = deltax;
		} else {
			xinc2 = 0;
			yinc1 = 0;
			den = deltay;
			num = deltay / 2;
			numadd = deltax;
			numpixels = deltay;
		}

		for (curpixel = 0; curpixel <= numpixels; curpixel++) {
			super.g.setPixel(y, x);
			num += numadd;
			if (num >= den) {
				num -= den;
				x += xinc1;
				y += yinc1;
			}
			x += xinc2;
			y += yinc2;
		}
	}
}
