package nl.logiconline.neptune.system;

import nl.logiconline.neptune.assets.gfx.Font;
import nl.logiconline.neptune.assets.gfx.Sprite;
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

	public void drawSprite(int x, int y, Sprite sprite) {
		int index = 0;
		for (int sy = 0; sy < sprite.getHeight(); sy++) {
			for (int sx = 0; sx < sprite.getWidth(); sx++) {
				this.setPixel(sx + x, sy + y, sprite.getPixels()[index++]);
			}
		}
	}

	public void drawText(int x, int y, String text) {
		Font.getInstance().draw(this, x, y, text);
	}

	public void setPixel(int x, int y, int color) {
		if (this.camera != null) {
			x = x - this.camera.getScrollX();
			y = y - this.camera.getScrollY();
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

	public void drawCirc(int xCenter, int yCenter, int radius) {
		new Circle(this, xCenter, yCenter, radius, false).draw();
	}

	public void fillCirc(int orgX, int orgY, int radius) {
		new Circle(this, orgX, orgY, radius, true).draw();
	}

	public void drawRect(int x, int y, int width, int height) {
		new Rectangle(this, x, y, width, height, false).draw();
	}

	public void fillRect(int x, int y, int width, int height) {
		new Rectangle(this, x, y, width, height, true).draw();
	}

	public void drawLine(int x1, int y1, int x2, int y2) {
		new Line(this, x1, y1, x2, y2).draw();
	}

	/**
	 * OVERLOADS -- OVERLOADS -- OVERLOADS
	 */

	public void drawText(Point2D position, String text) {
		this.drawText(position.getX(), position.getY(), text);
	}

	public void setPixel(Point2D position) {
		this.setPixel(position.getX(), position.getY(), this.color);
	}

	public void setPixel(int x, int y) {
		this.setPixel(x, y, this.color);
	}

	public void setPixel(Point2D position, int color) {
		this.setPixel(position.getX(), position.getY(), color);
	}

	public void drawSprite(Point2D position, Sprite sprite) {
		this.drawSprite(position.getX(), position.getY(), sprite);
	}

	public void drawSprite(Sprite sprite) {
		this.drawSprite(0, 0, sprite);
	}

	public void fillRect(Point2D position, int width, int height) {
		this.fillRect(position.getX(), position.getY(), width, height);
	}

	public void drawRect(Point2D position, int width, int height) {
		this.drawRect(position.getX(), position.getY(), width, height);
	}

}
