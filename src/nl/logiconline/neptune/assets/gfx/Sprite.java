package nl.logiconline.neptune.assets.gfx;

import java.awt.image.BufferedImage;

public class Sprite {

	private int width, height;
	private int[] pixels;

	/**
	 * Constructor
	 * @param int[] the pixels of the sprite
	 * @param int The width of the sprite
	 * @param int The height of the sprite
	 */
	public Sprite(int[] pixels, int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}

	/**
	 * Constructor
	 * @param BufferedImage image the image data
	 */
	public Sprite(BufferedImage image) {

		this.width = image.getWidth();
		this.height = image.getHeight();
		this.pixels = new int[this.width * this.height];

		for(int x = 0; x < this.width; x++) {
			for(int y = 0; y < this.height; y++) {
				this.pixels[(y * this.width) + x] = image.getRGB(x, y);
			}
		}
	}

	public int getPixel(int x, int y) {
		if((x >= 0) && (y >= 0) && (x <= this.width) && (y <= this.height)) {
			return this.pixels[(y * this.width) + x];
		}
		return 0;
	}

	/**
	 * Returns the pixel data
	 * @return int[]
	 */
	public int[] getPixels() {
		return this.pixels;
	}

	/**
	 * Returns the width of this sprite
	 * @return int
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Returns the height of this sprite
	 * @return int
	 */
	public int getHeight() {
		return this.height;
	}
}
