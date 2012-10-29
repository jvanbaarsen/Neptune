package nl.logiconline.neptune.system;
/**
 * Neptune
 * ==========
 * 
 * @author J. van Baarsen <jeroen@logiconline.nl>
 * @package nl.logiconline.neptune.system
 * (c) 2012 - LogicOnline
 */
import nl.logiconline.neptune.entities.Entity;

public class Camera {

	private Entity entity;
	private int width, height, x, y;

	public Camera(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getCameraX() {
		if (this.entity != null) {
			return this.entity.getX();
		}
		return this.x;
	}

	public int getCameraY() {
		if (this.entity != null) {
			return this.entity.getY();
		}
		return this.y;
	}

	public int getScrollX() {
		return this.getCameraX() - (Neptune.getWorld().getCanvasWidth() / 2);
	}

	public int getScrollY() {
		return this.getCameraY() - (Neptune.getWorld().getCanvasHeight() / 2);
	}

	public int getHeight() {
		return this.height;
	}

	public int getWidth() {
		return this.width;
	}

	public int screenWidth() {
		return Neptune.getWorld().getWidth();
	}

	public int screenHeight() {
		return Neptune.getWorld().getHeight();
	}

	public void followEntity(Entity entity) {
		this.entity = entity;
	}

}
