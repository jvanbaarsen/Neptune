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


public class Rectangle extends Shape {

	private boolean fill;
	private int width, height;

	public Rectangle(Gfx g, int x, int y, int width, int height, boolean fill) {
		super(g, x, y);
		this.width = width;
		this.height = height;
		this.fill = fill;
	}

	@Override
	public void draw() throws NeptuneException {
		if (this.fill) {
			for (int x = super.x; x < (super.x + this.width); x++) {
				for (int y = super.y; y < (super.y + this.height); y++) {
					super.g.setPixel(x, y);
				}
			}
		} else {
			//Draw top line
			for (int tx = this.x; tx <= (this.x + this.width); tx++) {
				this.g.setPixel(tx, this.y);
			}

			//Draw bottom line
			for (int tx = this.x; tx <= (this.x + this.width); tx++) {
				this.g.setPixel(tx, (this.y + this.height));
			}

			//Draw Left line
			for (int ty = this.y; ty <= (this.y + this.height); ty++) {
				this.g.setPixel(this.x, ty);
			}

			//Draw right line
			for (int ty = this.y; ty <= ((this.y + this.height)); ty++) {
				this.g.setPixel(this.x + this.width, ty);
			}
		}
	}

}
