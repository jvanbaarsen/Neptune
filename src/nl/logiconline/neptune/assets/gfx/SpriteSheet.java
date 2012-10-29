package nl.logiconline.neptune.assets.gfx;
/**
 * Neptune
 * ==========
 * 
 * @author J. van Baarsen <jeroen@logiconline.nl>
 * @package nl.logiconline.neptune.assets.gfx
 * (c) 2012 - LogicOnline
 */
import java.awt.image.BufferedImage;

import nl.logiconline.neptune.system.NeptuneException;

public class SpriteSheet {

	private BufferedImage image;
	private int[] pixels;
	private Sprite[][] loadedSprites;
	int totalXSprites, totalYSprites, spriteWidth, spriteHeight;

	/**
	 * Constructor
	 * @param BufferedImage image The image file
	 * @param int spriteWidth The width of a single sprite
	 * @param int spriteHeight The height of a single sprite
	 */
	public SpriteSheet(BufferedImage image, int spriteWidth, int spriteHeight) {
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		this.image = image;

		int w = this.image.getWidth(null);
		int h = this.image.getHeight(null);
		this.pixels = new int[w * h];
		this.totalXSprites = (int) Math.floor(this.image.getWidth() / spriteWidth);
		this.totalYSprites = (int) Math.floor(this.image.getWidth() / spriteWidth);
		this.loadedSprites = new Sprite[this.totalXSprites][this.totalYSprites];
		this.image.getRGB(0, 0, w, h, this.pixels, 0, w);
	}

	/**
	 * Get a single sprite from the spritesheet at X and Y
	 * @param int x the X position of the sprite
	 * @param int y the Y position of the sprite
	 * @return Sprite
	 */
	public Sprite getSprite(int x, int y) throws NeptuneException {
		if((x >= 0) && (y >= y) && (x <= this.loadedSprites.length) && (y <= this.loadedSprites[x].length)) {
			if(this.loadedSprites[x][y] != null) {
				return this.loadedSprites[x][y];
			} else {
				int startX = x * this.spriteWidth;
				int startY = y * this.spriteHeight;
				int[] sprite = new int[this.spriteWidth * this.spriteHeight];
				int index = 0;
				for(int sy = 0; sy < this.spriteHeight; sy++) {
					for(int sx = 0; sx < this.spriteWidth; sx++) {
						sprite[index++] = this.pixels[((sy + startY) * this.image.getWidth()) + (sx + startX)];
					}
				}
				this.loadedSprites[x][y] = new Sprite(sprite, this.spriteWidth, this.spriteHeight);
				return this.loadedSprites[x][y];
			}


		} else {
			throw new NeptuneException("Trying to load a sprite that is out of spritesheet bound (X:"+ x +" Y:"+ y +")");
		}
	}

	public int getSpriteWidth() {
		return this.spriteWidth;
	}

	public int getSpriteHeight() {
		return this.spriteHeight;
	}
}
