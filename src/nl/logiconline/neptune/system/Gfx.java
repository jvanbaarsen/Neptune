package nl.logiconline.neptune.system;
/**
 * Neptune
 * ==========
 * 
 * @author J. van Baarsen <jeroen@logiconline.nl>
 * @package nl.logiconline.neptune.system
 * (c) 2012 - LogicOnline
 */
import nl.logiconline.neptune.assets.gfx.Font;
import nl.logiconline.neptune.assets.gfx.Sprite;
import nl.logiconline.neptune.system.geom.Circle;
import nl.logiconline.neptune.system.geom.Line;
import nl.logiconline.neptune.system.geom.Rectangle;
import nl.logiconline.neptune.utils.Color;
import nl.logiconline.neptune.utils.Point2D;

public class Gfx {

	private int[] pixels;
	private int color = Color.BLACK;
	private int width, height;
	private Camera camera;

	public Gfx(int[] pixels, int width, int height) {
		this.pixels = pixels;
		this.width = width;
		this.height = height;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public Camera getCamra() {
		return this.camera;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getColor() {
		return this.color;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public void drawSprite(Point2D position, Sprite sprite) throws NeptuneException  {
		this.drawSprite(position.getX(), position.getY(), sprite);
	}

	public void drawSprite(Sprite sprite) throws NeptuneException  {
		this.drawSprite(0, 0, sprite);
	}

	public void drawSprite(double x, double y, Sprite sprite) throws NeptuneException {
		int index = 0;
		for (int sy = 0; sy < sprite.getHeight(); sy++) {
			for (int sx = 0; sx < sprite.getWidth(); sx++) {
				this.setPixel(sx + x, sy + y, sprite.getPixels()[index++]);
			}
		}
	}

	public void drawText(Point2D position, String text) throws NeptuneException {
		this.drawText(position.getX(), position.getY(), text);
	}

	public void drawText(double x, double y, String text) throws NeptuneException {
		Font.getInstance().draw(this, x, y, text);
	}

	public void setPixel(Point2D position) throws NeptuneException  {
		this.setPixel(position.getX(), position.getY(), this.color);
	}

	public void setPixel(double x, double y) throws NeptuneException {
		this.setPixel((int)x, (int)y, this.color);
	}

	public void setPixel(int x, int y) throws NeptuneException  {
		this.setPixel(x, y, this.color);
	}

	public void setPixel(Point2D position, int color) throws NeptuneException  {
		this.setPixel(position.getX(), position.getY(), color);
	}

	public void setPixel(double x, double y, int color) throws NeptuneException {
		this.setPixel((int)x, (int)y, color);
	}

	public void setPixel(int x, int y, int color) throws NeptuneException {
		if (this.camera != null) {
			x = x - (int)this.camera.getScrollX();
			y = y - (int)this.camera.getScrollY();
		}

		if ((x >= 0) && (x < this.width) && (y >= 0) && (y < this.height)) {
			int pix1 = this.pixels[(y * this.width) + x];
			int pix2 = color;

			if ((pix2 != 0xffd67fff) && (pix2 != 0xff6b3f7f)) {
				if (((pix2 >> 24) & 0xFF) == 0xFF) {
					this.pixels[(y * this.width) + x] = pix2;
				} else {
					int alpha = ((pix2 >> 24) & 0xFF);
					int r = ((((pix1 >> 16) & 0xFF) * (255 - alpha)) + (((pix2 >> 16) & 0xFF) * (alpha))) / 255;
					int g = ((((pix1 >> 8) & 0xFF) * (255 - alpha)) + (((pix2 >> 8) & 0xFF) * (alpha))) / 255;
					int b = (((pix1 & 0xFF) * (255 - alpha)) + ((pix2 & 0xFF) * (alpha))) / 255;

					this.pixels[(y * this.width) + x] = (alpha << 24) + (r << 16) + (g << 8) + b;
				}
			}
		}
	}

	/**
	 * SHAPES -- SHAPES -- SHAPES
	 */

	public void drawCircle(int xCenter, int yCenter, int radius) throws NeptuneException {
		new Circle(this, xCenter, yCenter, radius, false).draw();
	}

	public void fillCircle(int orgX, int orgY, int radius) throws NeptuneException {
		new Circle(this, orgX, orgY, radius, true).draw();
	}

	public void drawRect(Point2D position, int width, int height) throws NeptuneException  {
		this.drawRect(position.getX(), position.getY(), width, height);
	}

	public void drawRect(double x, double y, int width, int height) throws NeptuneException {
		new Rectangle(this, x, y, width, height, false).draw();
	}

	public void fillRect(Point2D position, int width, int height) throws NeptuneException  {
		this.fillRect(position.getX(), position.getY(), width, height);
	}


	public void fillRect(double x, double y, int width, int height) throws NeptuneException {
		new Rectangle(this, x, y, width, height, true).draw();
	}

	public void drawLine(int x1, int y1, int x2, int y2) throws NeptuneException {
		new Line(this, x1, y1, x2, y2).draw();
	}
}
